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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naesc2011.conference.server.PermissionManager;
import com.naesc2011.conference.shared.Council;
import com.naesc2011.conference.shared.CouncilPermission;
import com.naesc2011.conference.shared.PMF;

public class ProcessRegisterServlet extends HttpServlet {
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
            // Process this form!
            PersistenceManager pm = PMF.get().getPersistenceManager();

            List<CouncilPermission> councils = CouncilPermission.GetPermission(
                    pm, p.getUser().getUserId());
            if (councils.size() == 0) {
                String name = request.getParameter("name");
                String university = request.getParameter("university");
                String location = request.getParameter("location");
                if (name != null) {
                    Council c = new Council(name);
                    c.setUniversity(university);
                    c.setLocation(location);
                    try {
                        Council.InsertCouncil(pm, c);
                        CouncilPermission cp = new CouncilPermission(p
                                .getUser().getUserId(), c.getKey());
                        CouncilPermission.InserCouncilPermission(pm, cp);
                    } finally {
                        pm.close();
                    }
                }
            }
        }

        request.setAttribute("redirecturl", "/home");
        String url = "/naesc/redirect.jsp";
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
