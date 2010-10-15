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
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naesc2011.conference.server.PermissionDeniedException;
import com.naesc2011.conference.server.PermissionManager;
import com.naesc2011.conference.shared.AttendeePermission;
import com.naesc2011.conference.shared.Award;
import com.naesc2011.conference.shared.ConferenceSettings;
import com.naesc2011.conference.shared.Council;
import com.naesc2011.conference.shared.CouncilPermission;
import com.naesc2011.conference.shared.PMF;
import com.naesc2011.conference.shared.Tour;

public class HomeServlet extends HttpServlet {

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Processes the request from the client.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PermissionManager p = new PermissionManager();
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            if (!request.getRequestURI().equals("/")) {
                // We will display 404 pages for everything except for the home
                // page.
                throw new PermissionDeniedException();
            }
            boolean authenticated = PermissionManager.SetUpPermissions(p,
                    request);

            // The conference settings
            ConferenceSettings cs = ConferenceSettings
                    .GetConferenceSettings(pm);
            request.setAttribute("conferencesettings", cs);

            // The list of tours
            List<Tour> tours = Tour.GetAllTours(pm);
            request.setAttribute("tours", tours);

            // The list of awards
            List<Award> awards = Award.GetAllAwards(pm);
            request.setAttribute("awards", awards);

            if (authenticated) {
                // We are authenticated, is user council admin
                List<CouncilPermission> councils = CouncilPermission
                        .GetPermission(pm, p.getUser().getUserId());

                if (councils.size() == 0) {
                    // The user has no permissions to existing councils
                    request.setAttribute("nocouncil", true);
                } else {
                    // The user has access to an existing council
                    request.setAttribute("nocouncil", false);

                    List<Council> c = new ArrayList<Council>();
                    for (int i = 0; i < councils.size(); i++) {
                        c.add(Council.GetCouncil(pm, councils.get(i)
                                .getCouncil()));
                    }

                    request.setAttribute("councils", c);
                }

                // Is the user an attendee admin
                List<AttendeePermission> ap = AttendeePermission.GetPermission(
                        pm, p.getUser().getEmail());
                request.setAttribute("ap", ap);
            }

            String url = "/naesc/home.jsp";
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (PermissionDeniedException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } finally {
            pm.close();
        }
    }
}
