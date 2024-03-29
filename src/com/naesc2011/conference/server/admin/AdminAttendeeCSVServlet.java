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
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naesc2011.conference.server.PermissionDeniedException;
import com.naesc2011.conference.server.PermissionManager;
import com.naesc2011.conference.shared.ConferenceAttendee;
import com.naesc2011.conference.shared.Council;
import com.naesc2011.conference.shared.PMF;
import com.naesc2011.conference.shared.Tour;

public class AdminAttendeeCSVServlet extends HttpServlet {

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

            List<Council> councils = Council.GetAllCouncils(pm);
            List<Tour> tours = Tour.GetAllTours(pm);
            response.setContentType("application/CSV");
            PrintWriter writer = response.getWriter();
            writer.write("\"Council\",\"Last Name\",\"First Name\",\"Middle Name\","
                    + "\"Major\",\"Email\",\"Gender\",\"Shirt Size\","
                    + "\"Emergency Contact Name\",\"Emergency Contact Phone\",\"Arrival Informtion\","
                    + "\"Vegetarian\",\"Allergies\",\"Voting Status\",\"Registration Fee\",\"Tour\"\n");

            for (int i = 0; i < councils.size(); i++) {
                Council council = councils.get(i);
                List<ConferenceAttendee> attendees = council.getAttendees();
                for (int j = 0; j < attendees.size(); j++) {
                    writer.write('"' + council.getName() + '"' + ',');
                    ConferenceAttendee attendee = attendees.get(j);
                    writer.write('"' + attendee.getLastName() + '"' + ',' + '"'
                            + attendee.getFirstName() + '"' + ',' + '"'
                            + attendee.getMiddleName() + '"' + ',' + '"'
                            + attendee.getMajor() + '"' + ',' + '"'
                            + attendee.getEmail() + '"' + ',' + '"'
                            + attendee.getGender() + '"' + ',' + '"'
                            + attendee.getShirtSize() + '"' + ',' + '"'
                            + attendee.getEmergencyContactName() + '"' + ','
                            + '"' + attendee.getEmergencyContactPhone() + '"'
                            + ',' + '"' + attendee.getArrivalInformation()
                            + '"' + ',');
                    if (attendee.getVegetarian()) {
                        writer.write('"' + "Yes" + '"' + ',');
                    } else {
                        writer.write('"' + "No" + '"' + ',');
                    }

                    writer.write('"' + attendee.getAllergies() + '"' + ','
                            + '"' + attendee.getVoteStatus() + '"' + ',' + '"'
                            + '$' + attendee.getRegistartionFee() + '"' + ',');
                    if (attendee.getTour() == null) {
                        // The attendee does not have a tour selected
                    } else {
                        for (int k = 0; k < tours.size(); k++) {
                            Tour tour = tours.get(k);
                            if (tour.getKey().equals(attendee.getTour())) {
                                writer.write('"' + tour.getName() + '"');
                                break;
                            }
                        }
                    }

                    writer.write("\n");
                }

                writer.write("\n");
            }
        } catch (IOException e) {
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        } catch (PermissionDeniedException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } finally {
            pm.close();
        }
    }
}
