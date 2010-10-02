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
<%@ page import="java.util.List" %>
<%@ page import="com.naesc2011.conference.shared.Tour" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>Admin: Manage Tour</title>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<h1>Add New Tour</h1>
	<!-- TODO: Display all of the tours so we can add and edit existing tours. -->
	<form action="/admin/process/addtour" method="post"> 
		<fieldset> 
			<legend>Add Tour</legend> 
			<p><label>Tour Name:</label><input class="insmall" type="text" name="name" /></p>
			<p><label>Tour Description:</label><input class="insmall" type="text" name="description" /></p>
			<p><label>Maximum Attendees:</label><input class="insmall" type="text" name="maximum" /></p>
			<p class="submit"><input type="submit" value="Add" /></p>
		</fieldset> 
	</form>
	<h1>Manage Tours</h1>
	<% @SuppressWarnings("unchecked") List<Tour> tours = (List<Tour>)request.getAttribute("tours");
	   for(int i = 0; i < tours.size(); i++){ %>
		   <form action="/admin/process/savetour" method="post"> 
				<fieldset> 
					<legend>Update Tour</legend> 
					<p><label>Tour Name:</label><input class="insmall" type="text" name="name" value="<%= tours.get(i).getName() %>" /></p>
					<p><label>Tour Description:</label><input class="insmall" type="text" name="description" value="<%= tours.get(i).getDescription() %>" /></p>
					<p><label>Maximum Attendees:</label><input class="insmall" type="text" name="maximum" value="<%= tours.get(i).getMaximum() %>" /></p>
					<p><label>Attending:</label><%= tours.get(i).getTourMembers().size() %></p>
					<p class="submit">
						<input type="hidden" name="id" value="<%= tours.get(i).getKey().getId() %>" />
						<input type="submit" value="Update" />
					</p>
				</fieldset> 
			</form>
	<% } %>
</body>
</html>