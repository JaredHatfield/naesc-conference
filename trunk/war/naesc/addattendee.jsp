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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.naesc2011.conference.shared.ConferenceAttendee" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NAESC 2011 National Conference: Add Attendee</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<h1>Add Attendee</h1>
		<a href="/mycouncil?id=<%= request.getAttribute("councilid") %>">Back</a>
		<form method="post" action="/process/addattendee">
		<fieldset> 
		<legend>Edit Attendee</legend>
		<p><label>First Name:</label><input type="text" name="firstName" /></p>
		<p><label>Middle Name:</label><input type="text" name="middleName" /></p>
		<p><label>Last Name:</label><input type="text" name="lastName" /></p>
		<p><label>Major:</label><input type="text" name="major" /></p>
		<p><label>Email:</label><input type="text" name="email" /></p>
		<p>
			<label>Gender:</label>
			<select name="gender">
			<% for(ConferenceAttendee.Gender g : ConferenceAttendee.Gender.values()) { %>
				<option value="<%= g.toString() %>"><%= g.toString() %></option>
			<% } %>
			</select>
		</p>
		<p>
			<label>Shirt Size:</label>
			<select name="shirtSize">
			<% for(ConferenceAttendee.ShirtSize ss : ConferenceAttendee.ShirtSize.values()) { %>
				<option value="<%= ss.toString() %>"><%= ss.toString() %></option>
			<% } %>
			</select>
		</p>
		
		<p><label>Emergency Contact Name:</label><input type="text" name="ecName" /></p>
		<p><label>Emergency Contact Phone:</label><input type="text" name="ecPhone" /></p>
		<input type="hidden" name="councilid" value="<%= request.getAttribute("councilid") %>">
		<p class="submit">
		<input type="hidden" name="id">
		<input type="submit" value="Add" />
		</p>
		</fieldset>
	</form>
</body>
</html>