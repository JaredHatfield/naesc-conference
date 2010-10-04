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

import com.naesc2011.conference.server.PermissionManager;
import com.naesc2011.conference.shared.ConferenceAttendee;
import com.naesc2011.conference.shared.ConferenceSettings;
import com.naesc2011.conference.shared.Council;
import com.naesc2011.conference.shared.CouncilPermission;
import com.naesc2011.conference.shared.PMF;
import com.naesc2011.conference.shared.Tour;

public class ProcessAddAttendeeServlet extends HttpServlet {
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
            String pid = request.getParameter("councilid");
            if (pid != null) {
                PersistenceManager pm = PMF.get().getPersistenceManager();
                boolean haspermission = CouncilPermission.HasPermission(pm,
                        pid, p);

                ConferenceSettings cs = ConferenceSettings
                        .GetConferenceSettings(pm);
                Council council = Council.GetCouncil(pm, pid);

                if (haspermission
                        && cs.getMaxAttendees() > council.getAttendees().size()
                        && cs.isRegistrationOpen()) {
                    ConferenceAttendee ca = new ConferenceAttendee(
                            cs.getRegistrationFee());

                    // Set all of the parameters that were passed in
                    ca.setFirstName(request.getParameter("firstName"));
                    ca.setMiddleName(request.getParameter("middleName"));
                    ca.setLastName(request.getParameter("lastName"));
                    ca.setMajor(request.getParameter("major"));
                    ca.setEmail(request.getParameter("email"));
                    ca.setGender(ConferenceAttendee.Gender.valueOf(request
                            .getParameter("gender")));
                    ca.setShirtSize(ConferenceAttendee.ShirtSize
                            .valueOf(request.getParameter("shirtSize")));
                    ca.setEmergencyContactName(request.getParameter("ecName"));
                    ca.setEmergencyContactPhone(request.getParameter("ecPhone"));
                    ca.setArrivalInformation(request
                            .getParameter("arrivalInformation"));
                    ca.setVegetarian(request.getParameter("vegetarian") != null);
                    ca.setAllergies(request.getParameter("allergies"));

                    // Make the object persistent
                    try {
                        council.getAttendees().add(ca);
                        pm.makePersistent(ca);
                    } finally {
                    }

                    // Save the tour selection
                    String tourid = request.getParameter("tour");
                    if (tourid != null && !tourid.equals("-1")) {
                        Tour t = Tour.GetTour(pm, tourid);
                        if (t.hasRoom()) {
                            t.addAttendee(council.getKey(), ca);
                        }
                    }

                    pm.close();

                    response.sendRedirect("/mycouncil?id=" + pid);
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
