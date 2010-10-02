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

import com.naesc2011.conference.server.PermissionManager;
import com.naesc2011.conference.shared.PMF;
import com.naesc2011.conference.shared.Tour;

public class AdminProcessAddTourServlet extends HttpServlet {

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
            PersistenceManager pm = PMF.get().getPersistenceManager();

            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int maximum = Integer.parseInt(request.getParameter("maximum"));

            if (name != null && description != null) {
                Tour t = new Tour(name, description, maximum);
                try {
                    Tour.InsertTour(pm, t);
                } finally {
                    pm.close();
                }
            }

            response.sendRedirect("/admin/managetour");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
