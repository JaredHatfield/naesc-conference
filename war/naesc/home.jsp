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
<%@ page import="com.naesc2011.conference.shared.Award" %>
<%@ page import="com.naesc2011.conference.shared.Council" %>
<%@ page import="com.naesc2011.conference.shared.AttendeePermission" %>
<%@ page import="com.naesc2011.conference.shared.ConferenceSettings" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>NAESC 2011 National Conference: Home</title>
</head>
<body>
<div id="main">
	<jsp:include page="../header.jsp">
		<jsp:param value="Home" name="pagename"/>
	</jsp:include>
	<div id="body">
		<h2>Home</h2>
		<% ConferenceSettings cs = (ConferenceSettings)request.getAttribute("conferencesettings"); %>
		<% if(!(Boolean)request.getAttribute("authenticated")){ %>
			You must log in to register or change your registration information.
		<% } else { %>
			<% @SuppressWarnings("unchecked") List<AttendeePermission> ap = (List<AttendeePermission>)request.getAttribute("ap");%>
			<fieldset>
				<legend>Attendee</legend>
				<% if(ap.size() == 0) { %>
					Your email address is not registered as attending.
				<% } else { %>
					<% for(int i = 0; i < ap.size(); i++) { %>
						<a href="/editattendee?id=<%= ap.get(i).getCouncil().getId() %>&m=<%= ap.get(i).getAttendee().getId() %>">Manage my Attendee Information</a><br />
					<% } %>
				<% } %>
			</fieldset>
			<br /><br />
			<fieldset>
				<legend>Council</legend>
				<% if((Boolean)request.getAttribute("nocouncil") ){ %>
					<% if(cs.isRegistrationOpen()) { %>
						<a href="/register">Register New Council</a>
					<% } else { %>
						Registration is closed.
					<% } %>
				<% } else { %>
					<% @SuppressWarnings("unchecked") List<Council> councils = (List<Council>)request.getAttribute("councils");
					   for(int i = 0; i < councils.size(); i++){ %>
					   	<a href="/mycouncil?id=<%= councils.get(i).getKey().getId() %>">
					   		<b>Manage Council:</b> <%= councils.get(i).getName() %>
				   		</a>
					   	
					<% } %>
				<% } %>
			</fieldset>
		<% } %>
	</div>
	<div id="rightbar">
		<h3>Conference Information</h3>
		<p><b>Early Registration Date:</b> <%= cs.getEarlyRegistrationDateString() %></p>
		<p><b>Early Registration Fee:</b> $<%= (int)cs.getEarlyRegistrationFee() %></p>
		<p><b>Late Registration Date:</b> <%= cs.getLateRegistrationDateString() %></p>
		<p><b>Late Registration Fee:</b> $<%= (int)cs.getLateRegistrationFee() %></p>
		<h3>Tour List</h3>
		<% @SuppressWarnings("unchecked") List<Tour> tours = (List<Tour>)request.getAttribute("tours"); %>
		<% for(int i = 0; i < tours.size(); i++) { %>
			<% Tour t = tours.get(i); %>
			<% if(!t.hasRoom()){ %>
				<p style="color: darkgrey;">
			<% } else { %>
				<p>
			<% } %>
				<% if(t.getUrl().length() == 0) { %>
					<b><%= t.getName() %></b>
				<% } else { %>
					<b><a href="<%= t.getUrl() %>"><%= t.getName() %></a></b>
				<% } %>
				<i><%= t.getDescription() %></i>
				(<%= t.getTourMembers().size() %> of <%= t.getMaximum() %>)
			</p>
		<% } %>
		<h3>Award List</h3>
		<% @SuppressWarnings("unchecked") List<Award> awards = (List<Award>)request.getAttribute("awards"); %>
		<ul>
			<% for(int i = 0; i < awards.size(); i++) { %>
				<li><%= awards.get(i).getName() %>
					<ul>
						<li><i><%= awards.get(i).getQuestion1() %></i></li>
						<li><i><%= awards.get(i).getQuestion2() %></i></li>
						<li><i><%= awards.get(i).getQuestion3() %></i></li>
						<li><i><%= awards.get(i).getQuestion4() %></i></li>
					</ul>
				</li>
			<% } %>
		</ul>
	</div>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>