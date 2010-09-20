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

public class EditDelegateServlet extends HttpServlet {

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
                    String url = "/naesc/editdelegate.jsp";
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
