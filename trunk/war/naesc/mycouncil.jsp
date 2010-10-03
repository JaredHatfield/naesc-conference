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
<%@ page import="com.naesc2011.conference.shared.Award" %>
<%@ page import="com.naesc2011.conference.shared.AwardSubmission" %>
<%@ page import="com.naesc2011.conference.shared.Tour" %>
<%@ page import="com.naesc2011.conference.shared.Council" %>
<%@ page import="com.naesc2011.conference.shared.ConferenceAttendee" %>
<%@ page import="com.naesc2011.conference.shared.ConferenceSettings" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>NAESC 2011 National Conference: My Council</title>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<h1>My Council</h1>
	<h2>Information</h2>
	<% Council council = (Council)request.getAttribute("council"); %>
	<% @SuppressWarnings("unchecked") List<Tour> tours = (List<Tour>)request.getAttribute("tours"); %>
	<% @SuppressWarnings("unchecked") List<Award> awards = (List<Award>)request.getAttribute("awards"); %>
	<% ConferenceSettings cs = (ConferenceSettings)request.getAttribute("conferencesettings"); %>
	
	<a href="/editcouncil?id=<%= council.getKey().getId() %>">Edit</a><br />
	<table class="infotable">
		<tr>
			<td class="titlecol">Name</td>
			<td class="extralargecell"><%= council.getName() %></td>
		</tr>
		<tr>
			<td class="titlecol">University</td>
			<td><%= council.getUniversity() %></td>
		</tr>
		<tr>
			<td class="titlecol">Location</td>
			<td><%= council.getLocation() %></td>
		</tr>
		<tr>
			<td class="titlecol">Website</td>
			<td><%= council.getWebsite() %></td>
		</tr>
	</table>
	
	<h2>Award Applications</h2>
	<% List<AwardSubmission> submitted = council.getAwardSubmissions(); %>
	<table class="infotable">
		<tr class="titlerow">
			<td>Award Application</td>
			<td>Submitted</td>
		</tr>
		<% for(int i = 0; i < awards.size(); i++) { %>
			<tr>
				<td class="largecell">
					<% Award a = awards.get(i); %>
				<a href="/editaward?id=<%= council.getKey().getId() %>&a=<%= a.getKey().getId() %>"><%= a.getName() %></a>
				</td>
				<td class="smallcell">
					<% for(int j = 0; submitted != null && j < submitted.size(); j++) { %>
						<% if(submitted.get(j).getAward().equals(a.getKey())) { %>
							<% if(submitted.get(j).getSubmitted()){ %>
								<img src="/static/check.png" alt="Submitted" title="Submitted" class="center">
							<% } else { %>
								<img src="/static/progress.png" alt="In Progress" title="In Progress" class="center">
							<% } %>
						<% break; } %>
					<% } %>
				</td>
			</tr>
		<% } %>
	</table>
	
	<h2>Attending Members</h2>
	<table class="infotable">
		<tr class="titlerow">
			<td class="minicell">
				<% if(council.getAttendees().size() < cs.getMaxAttendees()) { %>
					<a href="/addattendee?id=<%= council.getKey().getId() %>">Add Attendee</a>
				<% } %>
				(<%= council.getAttendees().size() %> of <%= cs.getMaxAttendees() %>)
			</td>
			<td class="mediumcell">Name</td>
			<td class="mediumcell">Email</td>
			<td class="mediumcell">Tour <a href="/tourlist" onClick="return popup(this, 'Tour List')"><img src="/static/info.png" /></a></td>
			<td class="smallcell">Delegate <a href="/editdelegate?id=<%= council.getKey().getId() %>">(Manage)</a></td>
			<td class="smallcell">Information</td>
			<td class="minicell">Resume</td>
			<td class="minicell">Complete</td>
		</tr>
		<% for(int i = 0; i < council.getAttendees().size(); i++) { %>
			<tr>
				<% ConferenceAttendee att = council.getAttendees().get(i); %>
				<td><a href="/editattendee?id=<%= council.getKey().getId() %>&m=<%= att.getKey().getId() %>">Edit</a></td>
				<td><%= att.getFirstName() %> <%= council.getAttendees().get(i).getLastName() %></td>
				<td><%= att.getEmail() %></td>
				<td>
					<% for(int j = 0; j < tours.size(); j++) { %>
						<% if(tours.get(j).getKey().equals(att.getTour())) { %>
							<%= tours.get(j).getName() %>
						<% break; } %>
					<% } %>
				</td>
				<td>
					<% if(att.getVoteStatus() == null) { %>
					<% } else if(att.getVoteStatus().equals(ConferenceAttendee.VoteStatus.VOTING)) { %>
						Voting
					<% } else if(att.getVoteStatus().equals(ConferenceAttendee.VoteStatus.ALTERNATE)) { %>
						Alternate
					<% } %>
				</td>
				<td><!-- TODO: Add the logic to display if the required fields have been completed --></td>
				<td>
					<% if(att.getResume() != null) { %>
						<img src="/static/document.png" alt="Resume Uploaded" title="Resume Uploaded" class="center">
					<% } %>
				</td>
				<td>
					<% if(att.isAttendeeComplete()) { %>
						<img src="/static/check.png" alt="Complete" title="Complete" class="center">
					<% } %>
				</td>
			</tr>
		<% } %>
	</table>
</body>
</html>