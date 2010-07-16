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
		
		<% if(c.getMajorMechanical()){ %>
			<div>Mechanical:  <input type="checkbox" name="majorMechanical" checked="yes" /></div>
		<% } 
		   else {
		%>
			<div>Mechanical:  <input type="checkbox" name="majorMechanical" /></div>
		<% } %>
		
		<% if(c.getMajorCivil()){ %>
			<div>Civil:  <input type="checkbox" name="majorCivil" checked="yes" /></div>
		<% } 
		   else {
		%>
			<div>Civil:  <input type="checkbox" name="majorCivil" /></div>
		<% } %>
		
		<% if(c.getMajorComputer()){ %>
			<div>Computer:  <input type="checkbox" name="majorComputer" checked="yes" /></div>
		<% } 
		   else {
		%>
			<div>Computer:  <input type="checkbox" name="majorComputer" /></div>
		<% } %>
		
		<% if(c.getMajorElectrical()){ %>
			<div>Electrical:  <input type="checkbox" name="majorElectrical" checked="yes" /></div>
		<% } 
		   else {
		%>
			<div>Electrical:  <input type="checkbox" name="majorElectrical" /></div>
		<% } %>
		
		<% if(c.getMajorChemical()){ %>
			<div>Chemical:  <input type="checkbox" name="majorChemical" checked="yes" /></div>
		<% } 
		   else {
		%>
			<div>Chemical:  <input type="checkbox" name="majorChemical" /></div>
		<% } %>
		
		<% if(c.getMajorBiological()){ %>
			<div>Biological:  <input type="checkbox" name="majorBiological" checked="yes" /></div>
		<% } 
		   else {
		%>
			<div>Biological:  <input type="checkbox" name="majorBiological" /></div>
		<% } %>
		
		<% if(c.getMajorIndustrial()){ %>
			<div>Industrial:  <input type="checkbox" name="majorIndustrial" checked="yes" /></div>
		<% } 
		   else {
		%>
			<div>Industrial:  <input type="checkbox" name="majorIndustrial" /></div>
		<% } %>
		
		<% if(c.getMajorAeronautical()){ %>
			<div>Aeronautical:  <input type="checkbox" name="majorAeronautical" checked="yes" /></div>
		<% } 
		   else {
		%>
			<div>Aeronautical:  <input type="checkbox" name="majorAeronautical" /></div>
		<% } %>
		
		<% if(c.getMajorManagement()){ %>
			<div>Management:  <input type="checkbox" name="majorManagement" checked="yes" /></div>
		<% } 
		   else {
		%>
			<div>Management:  <input type="checkbox" name="majorManagement" /></div>
		<% } %>
		
		<% if(c.getMajorMaterials()){ %>
			<div>Materials:  <input type="checkbox" name="majorMaterials" checked="yes" /></div>
		<% } 
		   else {
		%>
			<div>Materials:  <input type="checkbox" name="majorMaterials" /></div>
		<% } %>
		
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