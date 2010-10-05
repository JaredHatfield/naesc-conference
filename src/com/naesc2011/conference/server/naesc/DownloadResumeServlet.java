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

public class DownloadResumeServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PermissionManager p = new PermissionManager();
        boolean authenticated = PermissionManager.SetUpPermissions(p, request);

        if (authenticated) {
            String pid = request.getParameter("id");
            request.setAttribute("id", pid);
            if (pid != null) {
                PersistenceManager pm = PMF.get().getPersistenceManager();
                boolean haspermission = CouncilPermission.HasPermission(pm,
                        pid, p);

                if (haspermission || p.IsUserAdmin()) {
                    Council council = Council.GetCouncil(pm, pid);
                    String mid = request.getParameter("m");
                    boolean found = false;
                    BlobKey blobKey = null;
                    for (int i = 0; i < council.getAttendees().size(); i++) {
                        long cid = council.getAttendees().get(i).getKey()
                                .getId();
                        if ((cid + "").equals(mid)) {
                            ConferenceAttendee ca = council.getAttendees().get(
                                    i);
                            request.setAttribute("attendee", ca);

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
                        blobstoreService.serve(blobKey, response);
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
