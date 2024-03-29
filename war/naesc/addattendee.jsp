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
<%@ page import="com.naesc2011.conference.shared.Council" %>
<%@ page import="com.naesc2011.conference.shared.ConferenceAttendee" %>
<%@ page import="com.naesc2011.conference.shared.ConferenceSettings" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>NAESC 2011 National Conference: Add Attendee</title>
	<script language="JavaScript" type="text/JavaScript">
	<!--//
	function checkme() {
		if (!document.addattendee.authorization.checked) {
			missinginfo = "You must agree to the payment terms before adding an attendee.";
			alert(missinginfo);
			return false;
		}
		else { 
			return true;
		}
	}
	// --->
	</script>
</head>
<body>
<div id="main">
	<jsp:include page="../header.jsp">
		<jsp:param value="Add Attendee" name="pagename"/>
	</jsp:include>
	<div id="body">
		<% @SuppressWarnings("unchecked") List<Tour> tours = (List<Tour>)request.getAttribute("tours"); %>
		<% Council council = (Council)request.getAttribute("council"); %>
		<% ConferenceSettings cs = (ConferenceSettings)request.getAttribute("conferencesettings"); %>
		
		<h2><a href="/mycouncil?id=<%= request.getAttribute("councilid") %>">My Council</a> &rarr; Add Attendee</h2>
			
			<form name="addattendee" method="post" action="/process/addattendee" onSubmit="return checkme();">
			<fieldset> 
			<legend>Add Attendee</legend>
			<p><label>First Name:</label><input class="insmall" type="text" maxlength="500" name="firstName" /></p>
			<p><label>Middle Name:</label><input class="insmall" type="text" maxlength="500" name="middleName" /></p>
			<p><label>Last Name:</label><input class="insmall" type="text" maxlength="500" name="lastName" /></p>
			
			<p>
				<label>Major:</label>
				<select name="major">
				<% for(int i = 0; i < ConferenceAttendee.Majors.length; i++) { %>
					<option value="<%= ConferenceAttendee.Majors[i] %>"><%= ConferenceAttendee.Majors[i] %></option>
				<% } %>
				</select>
			</p>
			
			<p><label>Email:</label><input class="insmall" type="text" maxlength="500" name="email" /></p>
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
			
			<p>
				<label>Tour:</label>
				<select name="tour">
					<option value="-1">Select a Tour From the Following</option>
				<% for(Tour t : tours) { %>
					<% if(t.hasRoom()) { %>
						<option value="<%= t.getKey().getId() %>"><%= t.getName() %></option>
					<% } %>
				<% } %>
				</select>
				<a href="/tourlist" onClick="return popup(this, 'Tour List')"><img src="/static/info.png" /></a>
			</p>
			
			<p><label>Emergency Contact Name:</label><input class="insmall" type="text" maxlength="500" name="ecName" /></p>
			<p><label>Emergency Contact Phone:</label><input class="insmall" type="text" maxlength="500" name="ecPhone" /></p>
			<p><label>Arrival Information:</label><input class="insmall" type="text" maxlength="500" name="arrivalInformation" /></p>
			<p><label>Vegetarian:</label><input type="checkbox" name="vegetarian" /></p>
			<p><label>Allergies:</label><input class="insmall" type="text" maxlength="500" name="allergies" /></p>
			<p class="confirmbox">
				<label class="widelabel">
					<input type="checkbox" name="authorization" />By checking this box, your council and/or the member attending agrees to and is obligated to pay the $<%= (int)cs.getRegistrationFee() %> registration fee unless a cancellation request is submitted before <%= cs.getLateRegistrationDateString() %>.
				</label>
			</p>
			<p class="submit">
				<input type="hidden" name="councilid" value="<%= request.getAttribute("councilid") %>">
				<input type="hidden" name="id">
				<input type="submit" value="Add" />
			</p>
			<hr />
			<p>
				<% int additional = cs.getMaxAttendees() - council.getAttendees().size(); %>
				<label class="widelabel">Note: <%= additional %> additional attendees can be added.</label>
			</p>
			</fieldset>
		</form>
	</div>
	<div id="rightbar">
		<h3>Adding Attendees</h3>
		<p>
			Each memmber attending the conference needs to be added and registered.
			Attendees will be able to log in with the email address provided and modify their own information.
			All of the fields to not need to be filled out when an attendee is added.
		</p>
		<p>
			Once an attendee has been added, you will be able to upload a resume.
		</p>
		<p>
			You must agree to the payment terms before an attendee can be added.
		</p>
	</div>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>