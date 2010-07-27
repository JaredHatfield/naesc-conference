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

import com.naesc2011.conference.shared.CorporateCompany;
import com.naesc2011.conference.shared.CorporateCorrespondence;
import com.naesc2011.conference.shared.PMF;

public class ProcessCorrespondenceAdd extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PermissionManager p = new PermissionManager();
		
		// TODO: (Security) Check the permission level
		if (p.IsUserLoggedIn()) {
			String outcome = req.getParameter("outcome");
			String nextSteps = req.getParameter("nextSteps");
			String notes = req.getParameter("notes");
			String id = req.getParameter("companyId");
			
			if (outcome == null || nextSteps == null || notes == null) {
				Log.info("The required field was not found");
			}
			else{
				PersistenceManager pm = PMF.get().getPersistenceManager();
				CorporateCompany c = CorporateCompany.GetCompany(pm, id);
				CorporateCorrespondence cc = new CorporateCorrespondence(outcome, nextSteps, notes);
				c.getCorrespondence().add(cc);
		        pm.close();
			}
			
			resp.sendRedirect("/CompanyDisplay.jsp?id=" + id);
		} 
		else {
			Log.info("User is not logged in!");
			resp.sendRedirect("/CompanyList.jsp");
		}
	}
}
