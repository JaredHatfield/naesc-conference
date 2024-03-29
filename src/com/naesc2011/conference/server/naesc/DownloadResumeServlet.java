/*
 * NAESC Conference is a Google App Engine web application that provides
 * a conference registration system.
 * Copyright (C) 2010  Speed School Student Council
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *  
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.naesc2011.conference.server.naesc;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.naesc2011.conference.server.InvalidFormException;
import com.naesc2011.conference.server.PermissionDeniedException;
import com.naesc2011.conference.server.PermissionManager;
import com.naesc2011.conference.shared.AttendeePermission;
import com.naesc2011.conference.shared.ConferenceAttendee;
import com.naesc2011.conference.shared.Council;
import com.naesc2011.conference.shared.CouncilPermission;
import com.naesc2011.conference.shared.PMF;

public class DownloadResumeServlet extends HttpServlet {

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Processes the request from the client.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PermissionManager p = new PermissionManager();
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            // Test if the user is logged in
            if (!PermissionManager.SetUpPermissions(p, request)) {
                throw new PermissionDeniedException();
            }

            // Test to make sure all of the mandatory parameters were set
            String pid = request.getParameter("id");
            request.setAttribute("id", pid);
            if (pid == null) {
                throw new InvalidFormException();
            }

            // Test if the user has permission for this council
            boolean haspermission = CouncilPermission.HasPermission(pm, pid, p);
            String mid = request.getParameter("m");
            if (!(haspermission
                    || AttendeePermission.HasPermission(pm, p.getUser()
                            .getEmail(), pid, mid) || p.IsUserAdmin())) {
                throw new PermissionDeniedException();
            }

            Council council = Council.GetCouncil(pm, pid);
            boolean found = false;
            BlobKey blobKey = null;
            String name = "";
            for (int i = 0; i < council.getAttendees().size(); i++) {
                long cid = council.getAttendees().get(i).getKey().getId();
                if ((cid + "").equals(mid)) {
                    ConferenceAttendee ca = council.getAttendees().get(i);
                    name = ca.getLastName() + ", " + ca.getFirstName();

                    // Only allow the page to be displayed if they do
                    // not have a resume uploaded
                    if (ca.getResume() != null) {
                        blobKey = ca.getResume();
                        found = true;
                    }

                    break;
                }
            }

            if (found) {
                // Display the file from the blobstore
                BlobstoreService blobstoreService = BlobstoreServiceFactory
                        .getBlobstoreService();

                // Retreive the file name of the uploaded blob
                // BlobInfoFactory blobInfoFactory = new BlobInfoFactory();
                // BlobInfo blobInfo = blobInfoFactory.loadBlobInfo(blobKey);
                // String filename = blobInfo.getFilename();

                response.setContentType("application/pdf");
                response.setHeader("Content-disposition",
                        "attachment; filename=\"" + council.getName() + " - "
                                + name + "\"");

                blobstoreService.serve(blobKey, response);
            } else {
                throw new PermissionDeniedException();
            }

        } catch (IOException e) {
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        } catch (PermissionDeniedException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } catch (InvalidFormException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } finally {
            pm.close();
        }
    }
}
