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
<h2>Add A Company</h2>
	<form action="/Process/CompanyAdd" method="post">
		<div>Name: <input type="text" name="name" /></div>
		<div>Address: <input type="text" name="address" /></div>
		<div>Pledged: <input type="text" name="pledged" /></div>
		<div>Pledge Date: <input type="text" name="pledgeDate" /></div>
		<div>Sector: <input type="text" name="sector" /></div>
		<div>Products: <input type="text" name="products" /></div>
		<div>Mechanical: <input type="checkbox" name="majorMechanical" /></div>
		<div>Civil: <input type="checkbox" name="majorCivil" /></div>
		<div>Computer: <input type="checkbox" name="majorComputer" /></div>
		<div>Electrical: <input type="checkbox" name="majorElectrical" /></div>
		<div>Chemical: <input type="checkbox" name="majorChemical" /></div>
		<div>Biological: <input type="checkbox" name="majorChemical" /></div>
		<div>Industrial: <input type="checkbox" name="majorIndustrial" /></div>
		<div>Aeronautical: <input type="checkbox" name="majorAeronautical" /></div>
		<div>Management: <input type="checkbox" name="majorManagement" /></div>
		<div>Materials: <input type="checkbox" name="majorMaterials" /></div>
		<div>Description: <input type="text" name="description" /></div>
		<div>Primary POC Name: <input type="text" name="primaryPOCName" /></div>
		<div>Primary POC Title: <input type="text" name="primaryPOCTitle" /></div>
		<div>Primary POC CellPhone: <input type="text" name="primaryPOCCellPhone" /></div>
		<div>Primary POC WorkPhone: <input type="text" name="primaryPOCWorkPhone" /></div>
		<div>Primary POC Email: <input type="text" name="primaryPOCEmail" /></div>
		<div><input type="submit" value="Submit" /></div>
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