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
import java.util.List;

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

public class ProcessSaveAttendeeServlet extends HttpServlet {

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
            String pid = request.getParameter("id");

            // Test if the user is logged in
            if (!PermissionManager.SetUpPermissions(p, request)) {
                throw new PermissionDeniedException();
            }

            // Test to make sure all of the mandatory parameters were set
            request.setAttribute("id", pid);
            if (pid == null) {
                throw new InvalidFormException();
            }

            boolean haspermission = CouncilPermission.HasPermission(pm, pid, p);
            String mid = request.getParameter("m");
            ConferenceSettings cs = ConferenceSettings
                    .GetConferenceSettings(pm);
            if (!(((haspermission || AttendeePermission.HasPermission(pm, p
                    .getUser().getEmail(), pid, mid)) && cs
                    .isRegistrationOpen()) || p.IsUserAdmin())) {
                throw new PermissionDeniedException();
            }

            Council council = Council.GetCouncil(pm, pid);
            boolean found = false;
            ConferenceAttendee ca = null;
            for (int i = 0; i < council.getAttendees().size(); i++) {
                long cid = council.getAttendees().get(i).getKey().getId();
                if ((cid + "").equals(mid)) {
                    ca = council.getAttendees().get(i);
                    found = true;
                    break;
                }
            }

            if (found) {
                // Mark the time that the attendee was updated
                ca.update();

                // Set all of the parameters that were passed in
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

                // Update the AttendeePermission & email
                String email = request.getParameter("email");
                if (!email.equals(ca.getEmail())) {
                    // Update the current email address
                    ca.setEmail(email);

                    // Update the permission object
                    List<AttendeePermission> ap = AttendeePermission
                            .GetPermission(pm, council.getKey(), ca.getKey());
                    if (ap.size() == 1) {
                        ap.get(0).setEmail(email);
                    }
                }

                // Update the tour selection
                String tourid = request.getParameter("tour");
                if (tourid != null) {
                    if (tourid.equals("-1")) {
                        // No tour was selected
                        if (ca.getTour() != null) {
                            // Remove the previously selected tour
                            Tour t = Tour.GetTour(pm, ca.getTour());
                            t.removeAttendee(council.getKey(), ca);
                        }
                    } else {
                        if (ca.getTour() == null
                                || !(ca.getTour().getId() + "").equals(tourid)) {
                            // We only process changes if it was
                            // actually changed

                            if (ca.getTour() != null) {
                                // Remove the previously selected
                                // tour
                                Tour t = Tour.GetTour(pm, ca.getTour());
                                t.removeAttendee(council.getKey(), ca);
                            }

                            // Set the new tour as selected
                            Tour t = Tour.GetTour(pm, tourid);
                            if (t.hasRoom()) {
                                t.addAttendee(council.getKey(), ca);
                            }
                        }
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
