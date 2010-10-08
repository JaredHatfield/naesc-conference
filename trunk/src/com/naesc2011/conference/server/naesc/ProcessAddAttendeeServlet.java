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

import com.naesc2011.conference.server.InvalidFormException;
import com.naesc2011.conference.server.PermissionDeniedException;
import com.naesc2011.conference.server.PermissionManager;
import com.naesc2011.conference.shared.AttendeePermission;
import com.naesc2011.conference.shared.ConferenceAttendee;
import com.naesc2011.conference.shared.ConferenceSettings;
import com.naesc2011.conference.shared.Council;
import com.naesc2011.conference.shared.CouncilPermission;
import com.naesc2011.conference.shared.PMF;
import com.naesc2011.conference.shared.Tour;

public class ProcessAddAttendeeServlet extends HttpServlet {

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Processes the request from the client.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PermissionManager p = new PermissionManager();
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            // Test if the user is logged in
            if (!PermissionManager.SetUpPermissions(p, request)) {
                throw new PermissionDeniedException();
            }

            // Test to make sure all of the mandatory parameters were set
            String pid = request.getParameter("councilid");
            if (pid == null) {
                throw new InvalidFormException();
            }

            // Test if the user has permission for this council
            boolean haspermission = CouncilPermission.HasPermission(pm, pid, p);
            ConferenceSettings cs = ConferenceSettings
                    .GetConferenceSettings(pm);
            Council council = Council.GetCouncil(pm, pid);
            if (!((haspermission
                    && cs.getMaxAttendees() > council.getAttendees().size() && cs
                    .isRegistrationOpen()) || p.IsUserAdmin())) {
                throw new PermissionDeniedException();
            }

            // We only add the member if they check the authorization
            // box and agreed to the payment terms.
            if (request.getParameter("authorization") != null) {
                // Set all of the parameters that were passed in
                ConferenceAttendee ca = new ConferenceAttendee(
                        cs.getRegistrationFee(), request.getParameter("email"));
                ca.setFirstName(request.getParameter("firstName"));
                ca.setMiddleName(request.getParameter("middleName"));
                ca.setLastName(request.getParameter("lastName"));
                ca.setMajor(request.getParameter("major"));
                ca.setGender(ConferenceAttendee.Gender.valueOf(request
                        .getParameter("gender")));
                ca.setShirtSize(ConferenceAttendee.ShirtSize.valueOf(request
                        .getParameter("shirtSize")));
                ca.setEmergencyContactName(request.getParameter("ecName"));
                ca.setEmergencyContactPhone(request.getParameter("ecPhone"));
                ca.setArrivalInformation(request
                        .getParameter("arrivalInformation"));
                ca.setVegetarian(request.getParameter("vegetarian") != null);
                ca.setAllergies(request.getParameter("allergies"));

                // Make the object persistent
                council.getAttendees().add(ca);
                pm.makePersistent(ca);

                // Add the attendee permission
                AttendeePermission ap = new AttendeePermission(ca.getEmail(),
                        council.getKey(), ca.getKey());
                AttendeePermission.InserAttendeePermission(pm, ap);

                // Save the tour selection
                String tourid = request.getParameter("tour");
                if (tourid != null && !tourid.equals("-1")) {
                    Tour t = Tour.GetTour(pm, tourid);
                    if (t.hasRoom()) {
                        t.addAttendee(council.getKey(), ca);
                    }
                }

                response.sendRedirect("/mycouncil?id=" + pid);
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
