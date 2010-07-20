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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naesc2011.conference.server.PermissionManager;
import com.naesc2011.conference.server.PermissionUserInstance;

public class ProcessPermissionChange extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String userId = req.getParameter("userid");
		PermissionUserInstance.Permission permission = PermissionUserInstance.Permission.valueOf(req.getParameter("permission"));
		PermissionManager.SetPermission(userId, permission);
		resp.sendRedirect("/admin/ManageUserPermission.jsp");
	}
}
