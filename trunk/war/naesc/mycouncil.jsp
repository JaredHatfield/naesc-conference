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
<%@ page import="com.naesc2011.conference.shared.Council" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NAESC 2011 National Conference: My Council</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<h1>My Council</h1>
	<h2>Information</h2>
	<% Council council = (Council)request.getAttribute("council"); %>
	<a href="/editcouncil?id=<%= council.getKey().getId() %>">Edit</a><br />
	Name: <%= council.getName() %><br />
	University: <%= council.getUniversity() %><br />
	Location: <%= council.getLocation() %><br />
	
	<h2>Award Applications</h2>
	
	<h2>Attending Members</h2>
	<a href="/addattendee?id=<%= council.getKey().getId() %>">Add Attendee</a><br />
	<% if(council.getAttendees() != null && council.getAttendees().size() > 0) { %>
		<table>
		<% for(int i = 0; i < council.getAttendees().size(); i++) { %>
			<tr>
				<td><%= i %></td>
				<td><%= council.getAttendees().get(i).getFirstName() %> <%= council.getAttendees().get(i).getLastName() %></td>
				<td><a href="/editattendee?id=<%= council.getKey().getId() %>&m=<%= council.getAttendees().get(i).getKey().getId() %>">Edit</a></td>
		<% } %>
		</table>
	<% } else { %>
		No members.
	<% } %>
</body>
</html>