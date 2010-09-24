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
<%@ page import="com.naesc2011.conference.shared.ConferenceAttendee" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>NAESC 2011 National Conference: Edit Attendee</title>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<% ConferenceAttendee a = (ConferenceAttendee)request.getAttribute("attendee"); %>
	<% @SuppressWarnings("unchecked") List<Tour> tours = (List<Tour>)request.getAttribute("tours"); %>
	
	<h1>Edit Attendee</h1>
		<a href="/mycouncil?id=<%= request.getAttribute("id") %>">Back</a>
		<form method="post" action="/process/saveattendee">
		<fieldset> 
		<legend>Edit Attendee</legend>
		<p><label>First Name:</label><input type="text" name="firstName" value="<%= a.getFirstName() %>" /></p>
		<p><label>Middle Name:</label><input type="text" name="middleName" value="<%= a.getMiddleName() %>" /></p>
		<p><label>Last Name:</label><input type="text" name="lastName" value="<%= a.getLastName() %>" /></p>
		<p><label>Major:</label><input type="text" name="major" value="<%= a.getMajor() %>" /></p>
		<p><label>Email:</label><input type="text" name="email" value="<%= a.getEmail() %>" /></p>
		<p>
			<label>Gender:</label>
			<select name="gender">
			<% for(ConferenceAttendee.Gender g : ConferenceAttendee.Gender.values()) { %>
				<% if(g.equals(a.getGender())) { %>
					<option value="<%= g.toString() %>" selected="selected"><%= g.toString() %></option>
				<% } else { %>
					<option value="<%= g.toString() %>"><%= g.toString() %></option>
				<% } %>
				
			<% } %>
			</select>
		</p>
		<p>
			<label>Shirt Size:</label>
			<select name="shirtSize">
			<% for(ConferenceAttendee.ShirtSize ss : ConferenceAttendee.ShirtSize.values()) { %>
				<% if(ss.equals(a.getShirtSize())) { %>
					<option value="<%= ss.toString() %>" selected="selected"><%= ss.toString() %></option>
				<% } else { %>
					<option value="<%= ss.toString() %>"><%= ss.toString() %></option>
				<% } %>
				
			<% } %>
			</select>
		</p>
		
		<p>
			<label>Tour:</label>
			<select name="tour">
				<option value="-1">Select a Tour From the Following</option>
			<% for(Tour t : tours) { %>
				<% if(a.getTour() != null && t.getKey().equals(a.getTour())) { %>
					<option value="<%= t.getKey().getId() %>" selected="selected"><%= t.getName() %></option>
				<% } else { %>
					<option value="<%= t.getKey().getId() %>"><%= t.getName() %></option>
				<% } %>
				
			<% } %>
			</select>
		</p>
		
		<p><label>Emergency Contact Name:</label><input type="text" name="ecName" value="<%= a.getEmergencyContactName() %>" /></p>
		<p><label>Emergency Contact Phone:</label><input type="text" name="ecPhone" value="<%= a.getEmergencyContactPhone() %>" /></p>
		<p><label>Arrival Information:</label><input type="text" name="arrivalInformation" value="<%= a.getArrivalInformation() %>" /></p>
		<p>
			<label>Vegetarian:</label>
				<% if(a.getVegetarian()) { %>
					<input type="checkbox" name="vegetarian" checked="checked" />
				<% } else { %>
					<input type="checkbox" name="vegetarian" />
				<% } %>
		</p>
		<p><label>Allergies:</label><input type="text" name="allergies" value="<%= a.getAllergies() %>" /></p>
		
		<p class="submit">
		<input type="hidden" name="id" value="<%= request.getAttribute("id") %>" />
		<input type="hidden" name="m" value="<%= a.getKey().getId() %>" />
		<input type="submit" value="Update" />
		</p>
		</fieldset>
	</form>
	<br />
	<form method="post" action="/process/deleteresume">
		<fieldset> 
		<legend>Resume</legend>
			<% if(a.getResume() == null) { %>
				<a href="/uploadresume?id=<%= request.getAttribute("id") %>&m=<%= a.getKey().getId() %>">Upload Resume</a>
			<% } else { %>
				<a href="/downloadresume?id=<%= request.getAttribute("id") %>&m=<%= a.getKey().getId() %>">Download Resume</a>
				<p class="submit">
				<input type="hidden" name="id" value="<%= request.getAttribute("id") %>" />
				<input type="hidden" name="m" value="<%= a.getKey().getId() %>" />
				<input type="submit" value="Delete Resume" />
				</p>
				
			<% } %>
		</fieldset>
	
	</form>
</body>
</html>