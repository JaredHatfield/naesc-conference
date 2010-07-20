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
import javax.servlet.http.*;

import org.mortbay.log.Log;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.naesc2011.conference.shared.CorporateCompany;
import com.naesc2011.conference.shared.PMF;

public class ProcessCompanyAdd extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		if (user != null) {
			String name = req.getParameter("name");
			String address = req.getParameter("address");
			Double pledged = Double.valueOf(req.getParameter("pledged"));
			String pledgeDate = req.getParameter("pledgeDate");
			String sector = req.getParameter("sector");
			String products = req.getParameter("products");

			Boolean majorMechanical = false;
			if(req.getParameter("majorMechanical") != null && req.getParameter("majorMechanical").equals("on")){
				majorMechanical = true;
			}
			
			Boolean majorCivil = false;
			if(req.getParameter("majorCivil") != null && req.getParameter("majorCivil").equals("on")){
				majorCivil = true;
			}

			Boolean majorComputer = false;
			if(req.getParameter("majorComputer") != null && req.getParameter("majorComputer").equals("on")){
				majorComputer = true;
			}
			
			Boolean majorElectrical = false;
			if(req.getParameter("majorElectrical") != null && req.getParameter("majorElectrical").equals("on")){
				majorElectrical = true;
			}
			
			Boolean majorChemical = false;
			if(req.getParameter("majorChemical") != null && req.getParameter("majorChemical").equals("on")){
				majorChemical = true;
			}
			
			Boolean majorBiological = false;
			if(req.getParameter("majorBiological") != null && req.getParameter("majorBiological").equals("on")){
				majorBiological = true;
			}
			
			Boolean majorIndustrial = false;
			if(req.getParameter("majorIndustrial") != null && req.getParameter("majorIndustrial").equals("on")){
				majorIndustrial = true;
			}
			
			Boolean majorAeronautical = false;
			if(req.getParameter("majorAeronautical") != null && req.getParameter("majorAeronautical").equals("on")){
				majorAeronautical = true;
			}
			
			Boolean majorManagement = false;
			if(req.getParameter("majorManagement") != null && req.getParameter("majorManagement").equals("on")){
				majorManagement = true;
			}
			
			Boolean majorMaterials = false;
			if(req.getParameter("majorMaterials") != null && req.getParameter("majorMaterials").equals("on")){
				majorMaterials = true;
			}
			
			String description = req.getParameter("description");
			String primaryPOCName = req.getParameter("primaryPOCName");
			String primaryPOCTitle = req.getParameter("primaryPOCTitle");
			String primaryPOCCellPhone = req.getParameter("primaryPOCCellPhone");
			String primaryPOCWorkPhone = req.getParameter("primaryPOCWorkPhone");
			String primaryPOCEmail = req.getParameter("primaryPOCEmail");
			if (name == null) {
				Log.info("The required field name was not found");
			}
			else{
				PersistenceManager pm = PMF.get().getPersistenceManager();
				CorporateCompany cc = new CorporateCompany(name);
				cc.setAddress(address);
				cc.setPledged(pledged);
				cc.setPledgeDate(pledgeDate);
				cc.setSector(sector);
				cc.setProducts(products);
				cc.setMajorMechanical(majorMechanical);
				cc.setMajorCivil(majorCivil);
				cc.setMajorComputer(majorComputer);
				cc.setMajorElectrical(majorElectrical);
				cc.setMajorChemical(majorChemical);
				cc.setMajorBiological(majorBiological);
				cc.setMajorIndustrial(majorIndustrial);
				cc.setMajorAeronautical(majorAeronautical);
				cc.setMajorManagement(majorManagement);
				cc.setMajorMaterials(majorMaterials);
				cc.setDescription(description);
				cc.setPrimaryPOCName(primaryPOCName);
				cc.setPrimaryPOCTitle(primaryPOCTitle);
				cc.setPrimaryPOCCellPhone(primaryPOCCellPhone);
				cc.setPrimaryPOCWorkPhone(primaryPOCWorkPhone);
				cc.setPrimaryPOCEmail(primaryPOCEmail);
				try {
					pm.makePersistent(cc);
		        } finally {
		            pm.close();
		        }
			}
		} 
		else {
			Log.info("User is not logged in!");
		}
		
		resp.sendRedirect("/CompanyList.jsp");
	}
}