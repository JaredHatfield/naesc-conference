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
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.naesc2011.conference.server.PermissionManager;
import com.naesc2011.conference.shared.ConferenceAttendee;
import com.naesc2011.conference.shared.Council;
import com.naesc2011.conference.shared.CouncilPermission;
import com.naesc2011.conference.shared.PMF;

public class ProcessUploadResumeServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PermissionManager p = new PermissionManager();
        boolean authenticated = PermissionManager.SetUpPermissions(p, request);

        if (authenticated) {
            if (authenticated) {
                String pid = request.getParameter("id");
                request.setAttribute("id", pid);
                if (pid != null) {
                    PersistenceManager pm = PMF.get().getPersistenceManager();
                    boolean haspermission = CouncilPermission.HasPermission(pm,
                            pid, p);

                    if (haspermission) {
                        Council council = Council.GetCouncil(pm, pid);
                        String mid = request.getParameter("m");
                        boolean found = false;
                        ConferenceAttendee ca = null;
                        for (int i = 0; i < council.getAttendees().size(); i++) {
                            long cid = council.getAttendees().get(i).getKey()
                                    .getId();
                            if ((cid + "").equals(mid)) {
                                ca = council.getAttendees().get(i);
                                found = true;
                                break;
                            }
                        }

                        if (found) {

                            // Save the reference to the resume that was
                            // uploaded
                            BlobstoreService blobstoreService = BlobstoreServiceFactory
                                    .getBlobstoreService();
                            Map<String, BlobKey> blobs = blobstoreService
                                    .getUploadedBlobs(request);
                            BlobKey blobKey = blobs.get(ca.getResumeKey());

                            if (blobKey == null) {
                                // It was not uploaded
                            } else {
                                // It was uploaded, so save the key.
                                ca.setResume(blobKey);
                            }

                            pm.close();
                            response.sendRedirect("/editattendee?id=" + pid
                                    + "&m=" + mid);
                        } else {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND);
                        }
                    } else {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }
}
