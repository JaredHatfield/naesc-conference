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
import java.text.ParseException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naesc2011.conference.server.PermissionDeniedException;
import com.naesc2011.conference.server.PermissionManager;
import com.naesc2011.conference.shared.ConferenceSettings;
import com.naesc2011.conference.shared.PMF;

public class AdminProcessSaveConferenceSettingsServlet extends HttpServlet {

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

            ConferenceSettings cs = ConferenceSettings
                    .GetConferenceSettings(pm);

            String conferenceName = request.getParameter("conferenceName");
            cs.setConferenceName(conferenceName);

            String address = request.getParameter("address");
            cs.setAddress(address);

            String earlyFeeStr = request.getParameter("earlyFee");
            double earlyFee = Double.parseDouble(earlyFeeStr);
            cs.setEarlyRegistrationFee(earlyFee);

            try {
                String earlyDateStr = request.getParameter("earlyDate");
                cs.setEarlyRegistrationDate(earlyDateStr);
            } catch (ParseException e) {
            }

            String lateFeeStr = request.getParameter("lateFee");
            double lateFee = Double.parseDouble(lateFeeStr);
            cs.setLateRegistrationFee(lateFee);

            try {
                String lateDateStr = request.getParameter("lateDate");
                cs.setLateRegistrationDate(lateDateStr);
            } catch (ParseException e) {
            }

            String maxAttendeesStr = request.getParameter("maxattendees");
            int max = Integer.parseInt(maxAttendeesStr);
            cs.setMaxAttendees(max);

            response.sendRedirect("/admin/conferencesettings");
        } catch (IOException e) {
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        } catch (PermissionDeniedException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } finally {
            pm.close();
        }
    }
}
