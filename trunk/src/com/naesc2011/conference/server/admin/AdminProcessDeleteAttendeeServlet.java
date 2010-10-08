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
package com.naesc2011.conference.server.admin;

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
import com.naesc2011.conference.shared.PMF;
import com.naesc2011.conference.shared.Tour;

public class AdminProcessDeleteAttendeeServlet extends HttpServlet {

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Processes the request from the client.
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
                        // Remove the attendee from any tour they may be
                        // attending
                        if (ca.getTour() != null) {
                            // Remove the previously selected tour
                            Tour t = Tour.GetTour(pm, ca.getTour());
                            t.removeAttendee(council.getKey(), ca);
                        }

                        // Delete the resume if it was uploaded
                        BlobKey blobKey = ca.getResume();
                        if (blobKey != null) {
                            BlobstoreService blobstoreService = BlobstoreServiceFactory
                                    .getBlobstoreService();
                            blobstoreService.delete(blobKey);
                            ca.setResume(null);
                        }

                        // Now delete the attendee from the council
                        council.getAttendees().remove(ca);

                        // Close the persistence connection
                        pm.close();

                        response.sendRedirect("/mycouncil?id=" + pid);
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
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
