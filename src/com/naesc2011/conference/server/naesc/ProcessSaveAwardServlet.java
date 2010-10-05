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
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naesc2011.conference.server.PermissionManager;
import com.naesc2011.conference.shared.Award;
import com.naesc2011.conference.shared.AwardApplication;
import com.naesc2011.conference.shared.AwardSubmission;
import com.naesc2011.conference.shared.ConferenceSettings;
import com.naesc2011.conference.shared.Council;
import com.naesc2011.conference.shared.CouncilPermission;
import com.naesc2011.conference.shared.PMF;

public class ProcessSaveAwardServlet extends HttpServlet {
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
            String pid = request.getParameter("id");
            String aid = request.getParameter("a");
            if (pid != null && aid != null) {
                PersistenceManager pm = PMF.get().getPersistenceManager();
                boolean haspermission = CouncilPermission.HasPermission(pm,
                        pid, p);

                ConferenceSettings cs = ConferenceSettings
                        .GetConferenceSettings(pm);

                if ((haspermission && cs.isRegistrationOpen())
                        || p.IsUserAdmin()) {
                    Award award = Award.GetAward(pm, aid);
                    Council council = Council.GetCouncil(pm, pid);
                    List<AwardSubmission> sub = council.getAwardSubmissions();
                    AwardSubmission csub = null;
                    String q1 = request.getParameter("q1");
                    String q2 = request.getParameter("q2");
                    String q3 = request.getParameter("q3");
                    String q4 = request.getParameter("q4");
                    String submit = request.getParameter("submitbutton");

                    if (q1 != null && q2 != null && q3 != null && q4 != null
                            && submit != null) {
                        for (int i = 0; i < sub.size(); i++) {
                            if (sub.get(i).getAward().equals(award.getKey())) {
                                // Located the submision
                                csub = sub.get(i);
                                break;
                            }
                        }

                        if (csub != null) {
                            // Retrieve the application and update it
                            if (csub.getSubmitted()) {
                                // The application was already submitted, so we
                                // don't do
                                // anything.
                            } else {
                                // The application wasn't submitted yet so we
                                // update.
                                AwardApplication app = AwardApplication
                                        .GetAward(pm, csub.getApplication());
                                app.setQuestion1(q1);
                                app.setQuestion2(q2);
                                app.setQuestion3(q3);
                                app.setQuestion4(q4);
                                if (submit.equals("Submit")) {
                                    // The application was submitted so mark
                                    // that flag.
                                    csub.setSubmitted(true);
                                    csub.setSubmittedOn(new Date());

                                }
                            }

                        } else {
                            // There was no submission so we need to add one
                            AwardApplication app = new AwardApplication(q1, q2,
                                    q3, q4);
                            AwardApplication.InsertAward(pm, app);
                            AwardSubmission submission = new AwardSubmission(
                                    award.getKey(), app.getKey());
                            council.getAwardSubmissions().add(submission);
                            if (submit.equals("Submit")) {
                                // The application was submitted so mark
                                // that flag.
                                submission.setSubmitted(true);
                                submission.setSubmittedOn(new Date());

                            }
                        }

                        pm.close();
                    }
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
