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

import com.google.appengine.api.datastore.Key;
import com.naesc2011.conference.server.PermissionManager;
import com.naesc2011.conference.shared.Award;
import com.naesc2011.conference.shared.AwardApplication;
import com.naesc2011.conference.shared.AwardSubmission;
import com.naesc2011.conference.shared.Council;
import com.naesc2011.conference.shared.CouncilPermission;
import com.naesc2011.conference.shared.PMF;

public class EditAwardServlet extends HttpServlet {

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
            String aid = request.getParameter("a");
            if (pid != null && aid != null) {
                PersistenceManager pm = PMF.get().getPersistenceManager();
                boolean haspermission = CouncilPermission.HasPermission(pm,
                        pid, p);

                if (haspermission) {
                    // Set the award
                    Award award = Award.GetAward(pm, aid);
                    request.setAttribute("award", award);

                    Council council = Council.GetCouncil(pm, pid);
                    request.setAttribute("councilid", council.getKey().getId());
                    List<AwardSubmission> las = council.getAwardSubmissions();

                    for (int i = 0; las != null && i < las.size(); i++) {
                        Key k1 = las.get(i).getAward();
                        Key k2 = award.getKey();
                        if (k1.equals(k2)) {
                            AwardSubmission sub = las.get(i);
                            request.setAttribute("submission", sub);
                            AwardApplication aa = AwardApplication.GetAward(pm,
                                    sub.getApplication());
                            request.setAttribute("application", aa);
                            break;
                        }
                    }

                    String url = "/naesc/editaward.jsp";
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
