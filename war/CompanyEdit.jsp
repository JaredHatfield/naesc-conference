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
<%@ page import="com.naesc2011.conference.server.*" %>

<%  PermissionManager p = new PermissionManager(); %>
<%@ include file="header.jsp" %>
<%
    if (p.IsUserLoggedIn()) {
%>

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
		<fieldset> 
		<legend>Edit Company</legend>
		<p><label>Name:</label><input type="text" name="name" value="<%= c.getName() %>" /></p>
		<p><label>Address:</label><input type="text" name="address" value="<%= c.getAddress() %>" /></p>
		<p><label>Pledged:</label><input type="text" name="pledged" value="<%= c.getPledged() %>" /></p>
		<p><label>Pledge Date:</label><input type="text" name="pledgeDate" value="<%= c.getPledgeDate() %>" /></p>
		<p><label>Sector:</label><input type="text" name="sector" value="<%= c.getSector() %>" /></p>
		<p><label>Products:</label><input type="text" name="products" value="<%= c.getProducts() %>" /></p>
		
		<% if(c.getMajorMechanical()){ %>
			<p><label>Mechanical:</label><input type="checkbox" name="majorMechanical" checked="yes" /></p>
		<% } 
		   else {
		%>
			<p><label>Mechanical:</label><input type="checkbox" name="majorMechanical" /></p>
		<% } %>
		
		<% if(c.getMajorCivil()){ %>
			<p><label>Civil:</label><input type="checkbox" name="majorCivil" checked="yes" /></p>
		<% } 
		   else {
		%>
			<p><label>Civil:</label><input type="checkbox" name="majorCivil" /></p>
		<% } %>
		
		<% if(c.getMajorComputer()){ %>
			<p><label>Computer:</label><input type="checkbox" name="majorComputer" checked="yes" /></p>
		<% } 
		   else {
		%>
			<p><label>Computer:</label><input type="checkbox" name="majorComputer" /></p>
		<% } %>
		
		<% if(c.getMajorElectrical()){ %>
			<p><label>Electrical:</label><input type="checkbox" name="majorElectrical" checked="yes" /></p>
		<% } 
		   else {
		%>
			<p><label>Electrical:</label><input type="checkbox" name="majorElectrical" /></p>
		<% } %>
		
		<% if(c.getMajorChemical()){ %>
			<p><label>Chemical:</label><input type="checkbox" name="majorChemical" checked="yes" /></p>
		<% } 
		   else {
		%>
			<p><label>Chemical:</label><input type="checkbox" name="majorChemical" /></p>
		<% } %>
		
		<% if(c.getMajorBiological()){ %>
			<p><label>Biological:</label><input type="checkbox" name="majorBiological" checked="yes" /></p>
		<% } 
		   else {
		%>
			<p><label>Biological:</label><input type="checkbox" name="majorBiological" /></p>
		<% } %>
		
		<% if(c.getMajorIndustrial()){ %>
			<p><label>Industrial:</label><input type="checkbox" name="majorIndustrial" checked="yes" /></p>
		<% } 
		   else {
		%>
			<p><label>Industrial:</label><input type="checkbox" name="majorIndustrial" /></p>
		<% } %>
		
		<% if(c.getMajorAeronautical()){ %>
			<p><label>Aeronautical:</label><input type="checkbox" name="majorAeronautical" checked="yes" /></p>
		<% } 
		   else {
		%>
			<p><label>Aeronautical:</label><input type="checkbox" name="majorAeronautical" /></p>
		<% } %>
		
		<% if(c.getMajorManagement()){ %>
			<p><label>Management:</label><input type="checkbox" name="majorManagement" checked="yes" /></p>
		<% } 
		   else {
		%>
			<p><label>Management:</label><input type="checkbox" name="majorManagement" /></p>
		<% } %>
		
		<% if(c.getMajorMaterials()){ %>
			<p><label>Materials:</label><input type="checkbox" name="majorMaterials" checked="yes" /></p>
		<% } 
		   else {
		%>
			<p><label>Materials:</label><input type="checkbox" name="majorMaterials" /></p>
		<% } %>
		
		<p><label>Description:</label><input type="text" name="description" value="<%= c.getDescription() %>" /></p>
		<p><label>Primary POC Name:</label><input type="text" name="primaryPOCName" value="<%= c.getPrimaryPOCName() %>" /></p>
		<p><label>Primary POC Title:</label><input type="text" name="primaryPOCTitle" value="<%= c.getPrimaryPOCTitle() %>" /></p>
		<p><label>Primary POC Cell Phone:</label><input type="text" name="primaryPOCCellPhone" value="<%= c.getPrimaryPOCCellPhone() %>" /></p>
		<p><label>Primary POC Work Phone:</label><input type="text" name="primaryPOCWorkPhone" value="<%= c.getPrimaryPOCWorkPhone() %>" /></p>
		<p><label>Primary POC Email:</label><input type="text" name="primaryPOCEmail" value="<%= c.getPrimaryPOCEmail() %>" /></p>
		<p class="submit">
		<input type="hidden" name="id" value="<%= c.getKey().getId() %>">
		<input type="submit" value="Update" />
		</p>
		</fieldset>
	</form>


<% } %>
<%@ include file="footer.jsp" %>