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
<%@ page import="com.naesc2011.conference.shared.CorporateCompany" %>
<%@ page import="com.naesc2011.conference.shared.PMF" %>
<html>
  <body>
<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
%>
<p>Hello, <%= user.getNickname() %>! (You can
<a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a>.)</p>
<a href="/CompanyList.jsp">Company List</a>
<h2>Edit a Company</h2>

<%
	if(request.getParameter("id") == null){
    	String redirectURL = "/CompanyList.jsp";
    	response.sendRedirect(redirectURL);
    }
%>

<%		PersistenceManager pm = PMF.get().getPersistenceManager();
		CorporateCompany c = CorporateCompany.GetCompany(pm, request.getParameter("id"));
%>
		
<%		pm.close(); %>

	<form action="/Process/CompanyEdit" method="post">
		<div>Name: <input type="text" name="name" value="<%= c.getName() %>" /></div>
		<div>Contact: <input type="text" name="contact" value="<%= c.getContact() %>" /></div>
		<input type="hidden" name="id" value="<%= c.getKey().getId() %>">
		<div><input type="submit" value="Update" /></div>
	</form>

<%
    } else {
%>
<p>Hello!
<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
to include your name with greetings you post.</p>
<%
    }
%>
  </body>
</html>