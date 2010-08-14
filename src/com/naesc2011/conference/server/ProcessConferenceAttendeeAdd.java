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
package com.naesc2011.conference.server;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.log.Log;

import com.naesc2011.conference.shared.ConferenceAttendee;
import com.naesc2011.conference.shared.PMF;

public class ProcessConferenceAttendeeAdd extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        PermissionManager p = new PermissionManager();

        // TODO: (Security) Check the permission level
        if (p.IsUserLoggedIn()) {

            PersistenceManager pm = PMF.get().getPersistenceManager();
            ConferenceAttendee ca = new ConferenceAttendee();

            // Set all of the parameters that were passed in
            ca.setFirstName(req.getParameter("firstName"));
            ca.setMiddleName(req.getParameter("middleName"));
            ca.setLastName(req.getParameter("lastName"));
            ca.setMajor(req.getParameter("major"));
            ca.setEmail(req.getParameter("email"));
            ca.setGender(ConferenceAttendee.Gender.valueOf(req
                    .getParameter("gender")));
            ca.setShirtSize(ConferenceAttendee.ShirtSize.valueOf(req
                    .getParameter("shirtSize")));
            ca.setEmergencyContactName(req.getParameter("ecName"));
            ca.setEmergencyContactPhone(req.getParameter("ecPhone"));

            // Make the object persistent
            try {
                pm.makePersistent(ca);
            } finally {
                pm.close();
            }

        } else {
            Log.info("User is not logged in!");
        }

        resp.sendRedirect("/ConferenceAttendeeList.jsp");
    }
}
