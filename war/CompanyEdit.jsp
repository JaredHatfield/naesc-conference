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

	<form method="post" action="/Process/CompanyEdit">
		<div>Name: <input type="text" name="name" value="<%= c.getName() %>" /></div>
		<div>Address: <input type="text" name="address" value="<%= c.getAddress() %>" /></div>
		<div>Pledged: <input type="text" name="pledged" value="<%= c.getPledged() %>" /></div>
		<div>Pledge Date: <input type="text" name="pledgeDate" value="<%= c.getPledgeDate() %>" /></div>
		<div>Sector: <input type="text" name="sector" value="<%= c.getSector() %>" /></div>
		<div>Products: <input type="text" name="products" value="<%= c.getProducts() %>" /></div>
		<br />
		<div>Mechanical: <input type="text" name="majorMechanical" value="<%= c.getMajorMechanical() %>" /></div>
		<div>Civil: <input type="text" name="majorCivil" value="<%= c.getMajorCivil() %>" /></div>
		<div>Computer: <input type="text" name="majorComputer" value="<%= c.getMajorComputer() %>" /></div>
		<div>Electrical: <input type="text" name="majorElectrical" value="<%= c.getMajorElectrical() %>" /></div>
		<div>Chemical: <input type="text" name="majorChemical" value="<%= c.getMajorChemical() %>" /></div>
		<div>Biological: <input type="text" name="majorChemical" value="<%= c.getMajorBiological() %>" /></div>
		<div>Industrial: <input type="text" name="majorIndustrial" value="<%= c.getMajorIndustrial() %>" /></div>
		<div>Aeronautical: <input type="text" name="majorAeronautical" value="<%= c.getMajorAeronautical() %>" /></div>
		<div>Management: <input type="text" name="majorManagement" value="<%= c.getMajorManagement() %>" /></div>
		<div>Materials: <input type="text" name="majorMaterials" value="<%= c.getMajorMaterials() %>" /></div>
		<br />
		<div>Description: <input type="text" name="description" value="<%= c.getDescription() %>" /></div>
		<div>Primary POC Name: <input type="text" name="primaryPOCName" value="<%= c.getPrimaryPOCName() %>" /></div>
		<div>Primary POC Title: <input type="text" name="primaryPOCTitle" value="<%= c.getPrimaryPOCTitle() %>" /></div>
		<div>Primary POC CellPhone: <input type="text" name="primaryPOCCellPhone" value="<%= c.getPrimaryPOCCellPhone() %>" /></div>
		<div>Primary POC WorkPhone: <input type="text" name="primaryPOCWorkPhone" value="<%= c.getPrimaryPOCWorkPhone() %>" /></div>
		<div>Primary POC Email: <input type="text" name="primaryPOCEmail" value="<%= c.getPrimaryPOCEmail() %>" /></div>
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