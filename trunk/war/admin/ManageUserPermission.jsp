<%--
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
 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="java.util.List" %>
<%@ page import="com.naesc2011.conference.shared.PMF" %>
<%@ page import="com.naesc2011.conference.server.*" %>
<%  PermissionManager p = new PermissionManager(); %>
<%@ include file="../header.jsp" %>
<%
    if (p.IsUserLoggedIn()) {
%>


<%		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<PermissionUserInstance> allUsers = PermissionManager.GetAllUsers(pm);
%>
	<h1>Manage Users</h1>
		<table border=1>
			<tr>
				<td>Email</td>
				<td>Permissions</td>
			</tr>
<%		for(int i = 0; i < allUsers.size(); i++){
			PermissionUserInstance pui =  allUsers.get(i);
%>
			<tr>
				<td><%= pui.getUser().getEmail() %></td>
				<td>
					<form method="post" action="/admin/PermissionChange">
						<select name='permission' onchange='this.form.submit()'>
						<% for (PermissionUserInstance.Permission perm : PermissionUserInstance.Permission.values()) { %>
							<% if(perm == pui.getUserPermission()) { %>
								<option selected="selected"><%= perm %></option>
							<% }
							   else { %>
							   	<option><%= perm %></option>
							<% } %>
						<% } %>
						</select>
						<input type="hidden" name="userid" value="<%= pui.getUserId() %>" />
					</form>
				</td>
			</tr>
		<br />
<%		} %>
		</table>
<%		pm.close(); %>


<% } %>
<%@ include file="../footer.jsp" %>