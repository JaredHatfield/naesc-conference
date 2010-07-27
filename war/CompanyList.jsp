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
<%@ page import="java.util.List;" %>
<%@ page import="com.naesc2011.conference.shared.CorporateCompany" %>
<%@ page import="com.naesc2011.conference.shared.PMF" %>
<%@ page import="com.naesc2011.conference.server.*" %>
<%  PermissionManager p = new PermissionManager(); %>
<%@ include file="header.jsp" %>
<%
    if (p.IsUserLoggedIn()) {
%>

<h2>List of Companies</h2>
<%	PersistenceManager pm = PMF.get().getPersistenceManager();
	List<CorporateCompany> c = CorporateCompany.GetAllCompanies(pm);
%>
	<a href="/CompanyAdd.jsp">Add a Company</a><br /><br />
	<table border=1 cellpadding=4 cellspacing=0>
<%		for(int i = 0; i < c.size(); i++)
		{
			CorporateCompany cc =  c.get(i);
%>
			<tr>
				<td><%= (i+1) %></td>
				<td><a href="/CompanyDisplay.jsp?id=<%= cc.getKey().getId() %>"><%= cc.getName() %></a></td>
				<td><a href="/CompanyEdit.jsp?id=<%= cc.getKey().getId() %>">edit</a></td>
			</tr>
<%		}
		pm.close();
%>
	</table>

<% } %>
<%@ include file="footer.jsp" %>