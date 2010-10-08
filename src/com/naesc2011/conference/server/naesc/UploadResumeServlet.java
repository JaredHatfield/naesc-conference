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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naesc2011.conference.server.InvalidFormException;
import com.naesc2011.conference.server.PermissionDeniedException;
import com.naesc2011.conference.server.PermissionManager;
import com.naesc2011.conference.shared.ConferenceAttendee;
import com.naesc2011.conference.shared.ConferenceSettings;
import com.naesc2011.conference.shared.Council;
import com.naesc2011.conference.shared.CouncilPermission;
import com.naesc2011.conference.shared.PMF;

public class UploadResumeServlet extends HttpServlet {

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
            ConferenceSettings cs = ConferenceSettings
                    .GetConferenceSettings(pm);
            if (!((haspermission && cs.isRegistrationOpen()) || p.IsUserAdmin())) {
                throw new PermissionDeniedException();
            }

            Council council = Council.GetCouncil(pm, pid);
            String mid = request.getParameter("m");
            boolean found = false;
            for (int i = 0; i < council.getAttendees().size(); i++) {
                long cid = council.getAttendees().get(i).getKey().getId();
                if ((cid + "").equals(mid)) {
                    ConferenceAttendee ca = council.getAttendees().get(i);
                    request.setAttribute("attendee", ca);

                    // Only allow the page to be displayed if they do
                    // not have a resume uploaded
                    if (ca.getResume() == null) {
                        found = true;
                    }

                    break;
                }
            }

            if (found) {
                String url = "/naesc/uploadresume.jsp";
                ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context
                        .getRequestDispatcher(url);
                dispatcher.forward(request, response);
            } else {
                throw new PermissionDeniedException();
            }

        } catch (ServletException e) {
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
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
