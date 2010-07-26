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
<%@ page import="com.naesc2011.conference.server.*" %>
<%  PermissionManager p = new PermissionManager(); %>
<%@ include file="header.jsp" %>
<%
    if (p.IsUserLoggedIn()) {
%>


<a href="/CompanyList.jsp">Company List</a>
<h2>Add A Company</h2>
	<form action="/Process/CompanyAdd" method="post">
		<fieldset> 
		<legend>Add Company</legend>
		<p><label>Name:</label><input type="text" name="name" /></p>
		<p><label>Address:</label><input type="text" name="address" /></p>
		<p><label>Pledged:</label><input type="text" name="pledged" /></p>
		<p><label>Pledge Date:</label><input type="text" name="pledgeDate" /></p>
		<p><label>Sector:</label><input type="text" name="sector" /></p>
		<p><label>Products:</label><input type="text" name="products" /></p>
		<p><label>Mechanical:</label><input type="checkbox" name="majorMechanical" /></p>
		<p><label>Civil:</label><input type="checkbox" name="majorCivil" /></p>
		<p><label>Computer:</label><input type="checkbox" name="majorComputer" /></p>
		<p><label>Electrical:</label><input type="checkbox" name="majorElectrical" /></p>
		<p><label>Chemical:</label><input type="checkbox" name="majorChemical" /></p>
		<p><label>Biological:</label><input type="checkbox" name="majorChemical" /></p>
		<p><label>Industrial:</label><input type="checkbox" name="majorIndustrial" /></p>
		<p><label>Aeronautical:</label><input type="checkbox" name="majorAeronautical" /></p>
		<p><label>Management:</label><input type="checkbox" name="majorManagement" /></p>
		<p><label>Materials:</label><input type="checkbox" name="majorMaterials" /></p>
		<p><label>Description:</label><input type="text" name="description" /></p>
		<p><label>Primary POC Name:</label><input type="text" name="primaryPOCName" /></p>
		<p><label>Primary POC Title:</label><input type="text" name="primaryPOCTitle" /></p>
		<p><label>Primary POC CellPhone:</label><input type="text" name="primaryPOCCellPhone" /></p>
		<p><label>Primary POC WorkPhone:</label><input type="text" name="primaryPOCWorkPhone" /></p>
		<p><label>Primary POC Email:</label><input type="text" name="primaryPOCEmail" /></p>
		<p class="submit"><input type="submit" value="Submit" /></p>
		</fieldset>
	</form>



<% } %>
<%@ include file="footer.jsp" %>