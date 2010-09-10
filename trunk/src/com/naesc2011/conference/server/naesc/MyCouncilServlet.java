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

public class MyCouncilServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PermissionManager p = new PermissionManager();
        boolean authenticated = PermissionManager.SetUpPermissions(p, request);

        if (authenticated) {
            String pid = request.getParameter("id");
            if (pid != null) {
                long id = Long.parseLong(pid);
                PersistenceManager pm = PMF.get().getPersistenceManager();

                List<CouncilPermission> councils = CouncilPermission
                        .GetPermission(pm, p.getUser().getUserId());
                Boolean haspermission = false;
                for (int i = 0; i < councils.size(); i++) {
                    if (councils.get(i).getCouncil().getId() == id) {
                        haspermission = true;
                        break;
                    }
                }

                if (haspermission) {
                    Council council = Council.GetCouncil(pm, pid);
                    request.setAttribute("council", council);
                    String url = "/naesc/mycouncil.jsp";
                    ServletContext context = getServletContext();
                    RequestDispatcher dispatcher = context
                            .getRequestDispatcher(url);
                    dispatcher.forward(request, response);
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
