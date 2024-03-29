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
<%@ page import="com.naesc2011.conference.shared.ConferenceSettings" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>NAESC 2011 National Conference: Edit Attendee</title>
	<script language="javascript" type="text/javascript">
	<!--
	<% ConferenceSettings cs = (ConferenceSettings)request.getAttribute("conferencesettings"); %>
	function update(){
		<% if(!cs.isRegistrationOpen() && !(Boolean)request.getAttribute("isadmin")) { %>
			disableForms();
		<% } %>
	}
	// -->
	</script>
</head>
<body onLoad="update();">
<div id="main">
	<jsp:include page="../header.jsp">
		<jsp:param value="Edit Attendee" name="pagename"/>
	</jsp:include>
	<div id="body">
		<% ConferenceAttendee a = (ConferenceAttendee)request.getAttribute("attendee"); %>
		<% @SuppressWarnings("unchecked") List<Tour> tours = (List<Tour>)request.getAttribute("tours"); %>
		<h2><a href="/mycouncil?id=<%= request.getAttribute("id") %>">My Council</a> &rarr; Edit Attendee</h2>
			<form method="post" action="/process/saveattendee">
			<fieldset> 
			<legend>Edit Attendee</legend>
			<p><label>First Name:</label><input class="insmall" type="text" maxlength="500" name="firstName" value="<%= a.getFirstName() %>" /></p>
			<p><label>Middle Name:</label><input class="insmall" type="text" maxlength="500" name="middleName" value="<%= a.getMiddleName() %>" /></p>
			<p><label>Last Name:</label><input class="insmall" type="text" maxlength="500" name="lastName" value="<%= a.getLastName() %>" /></p>
			
			<p>
				<label>Major:</label>
				<select name="major">
				<% for(int i = 0; i < ConferenceAttendee.Majors.length; i++) { %>
					<% if(ConferenceAttendee.Majors[i].equals(a.getMajor())) { %>
						<option value="<%= ConferenceAttendee.Majors[i] %>" selected="selected"><%= ConferenceAttendee.Majors[i] %></option>
					<% } else { %>
						<option value="<%= ConferenceAttendee.Majors[i] %>"><%= ConferenceAttendee.Majors[i] %></option>
					<% } %>
				<% } %>
				</select>
			</p>
			
			<p><label>Email:</label><input class="insmall" type="text" maxlength="500" name="email" value="<%= a.getEmail() %>" /></p>
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
					<% } else if(t.hasRoom()) { %>
						<option value="<%= t.getKey().getId() %>"><%= t.getName() %></option>
					<% } %>
					
				<% } %>
				</select>
				<a href="/tourlist" onClick="return popup(this, 'Tour List')"><img src="/static/info.png" /></a>
			</p>
			
			<p><label>Emergency Contact Name:</label><input class="insmall" type="text" maxlength="500" name="ecName" value="<%= a.getEmergencyContactName() %>" /></p>
			<p><label>Emergency Contact Phone:</label><input class="insmall" type="text" maxlength="500" name="ecPhone" value="<%= a.getEmergencyContactPhone() %>" /></p>
			<p><label>Arrival Information:</label><input class="insmall" type="text" maxlength="500" name="arrivalInformation" value="<%= a.getArrivalInformation() %>" /></p>
			<p>
				<label>Vegetarian:</label>
					<% if(a.getVegetarian()) { %>
						<input type="checkbox" name="vegetarian" checked="checked" />
					<% } else { %>
						<input type="checkbox" name="vegetarian" />
					<% } %>
			</p>
			<p><label>Allergies:</label><input class="insmall" type="text" maxlength="500" name="allergies" value="<%= a.getAllergies() %>" /></p>
			<% if(cs.isRegistrationOpen() || (Boolean)request.getAttribute("isadmin")) { %>
				<p class="submit">
					<input type="hidden" name="id" value="<%= request.getAttribute("id") %>" />
					<input type="hidden" name="m" value="<%= a.getKey().getId() %>" />
					<input type="submit" value="Update" />
				</p>
			<% } %>
			</fieldset>
		</form>
		<br />
		<% if((Boolean)request.getAttribute("isadmin")) { %>
		<form method="post" action="/admin/process/deleteattendee">
			<fieldset> 
			<legend>Delete Attendee</legend>
			<p class="submit">
				<input type="hidden" name="id" value="<%= request.getAttribute("id") %>" />
				<input type="hidden" name="m" value="<%= a.getKey().getId() %>" />
				<input type="submit" value="Delete" />
			</p>
			</fieldset>
		</form>
		<br />
		<% } %>
		<form method="post" action="/process/deleteresume">
			<fieldset> 
			<legend>Resume</legend>
				<% if(!cs.isRegistrationOpen() && !(Boolean)request.getAttribute("isadmin")) { %>
					<% if(a.getResume() != null) { %>
						<p>
							<label class="widelabel">
								<a href="/downloadresume?id=<%= request.getAttribute("id") %>&m=<%= a.getKey().getId() %>">Download Resume</a>
							</label>
						</p>
					<% } else { %>
						<p>
							<label class="widelabel">
								No resume was provided.
							</label>
						</p>
					<% } %>
				<% } else if(a.getResume() == null) { %>
					<p>
						<label class="widelabel">
							<a href="/uploadresume?id=<%= request.getAttribute("id") %>&m=<%= a.getKey().getId() %>">Upload Resume</a>
						</label>
					</p>
				<% } else { %>
					<p>
						<label class="widelabel">
							<a href="/downloadresume?id=<%= request.getAttribute("id") %>&m=<%= a.getKey().getId() %>">Download Resume</a>
						</label>
					</p>
					<br />
					<p class="submit">
						<input type="hidden" name="id" value="<%= request.getAttribute("id") %>" />
						<input type="hidden" name="m" value="<%= a.getKey().getId() %>" />
						<input type="submit" value="Delete Resume" />
					</p>
				<% } %>
			</fieldset>
		</form>
	</div>
	<div id="rightbar">
		<h3>Attendee Information</h3>
		<p>
			Complete all of the fields for an attendee in order to complete the registration process.
		</p>
		<p>
			Space on each tour is limited and the list of tours is available by clicking <img src="/static/info.png" /> next to the tour drop down list.
		</p>
		<p>
			Resumes are required for each attendee and must be uploaded before registration closes.
		</p>
	</div>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>