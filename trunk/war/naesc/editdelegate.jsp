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
<%@ page import="com.naesc2011.conference.shared.ConferenceAttendee" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>NAESC 2011 National Conference: Edit Delegate</title>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<% Council council = (Council)request.getAttribute("council"); %>
	<h1><%= council.getName() %> Delegates</h1>
	<a href="/mycouncil?id=<%= council.getKey().getId() %>">Back</a><br />
	<form action="/process/savedelegate" method="post"> 
		<fieldset> 
			<legend>Select Delegates</legend> 
			<p>
				<label>Voting Delegate</label>
				<select name="vote">
					<option id="-1">Select Voting Delegate</option>
					<% for(int i = 0; i < council.getAttendees().size();i++) { %>
						<% ConferenceAttendee att = council.getAttendees().get(i); %>
						<% ConferenceAttendee.VoteStatus vote = att.getVoteStatus(); %>
						<% if(vote != null && vote.equals(ConferenceAttendee.VoteStatus.VOTING)) { %>
							<option value="<%= att.getKey().getId() %>" selected="selected">
						<% } else { %>
							<option value="<%= att.getKey().getId() %>">
						<% } %>
						<%= att.getFullName() %>
						</option>
					<% } %>
				</select>
			</p>
			<p>
				<label>Alternate Delegate</label>
				<select name="alternate">
					<option id="-1">Select Alternate Delegate</option>
					<% for(int i = 0; i < council.getAttendees().size();i++) { %>
						<% ConferenceAttendee att = council.getAttendees().get(i); %>
						<% ConferenceAttendee.VoteStatus vote = att.getVoteStatus(); %>
						<% if(vote != null && vote.equals(ConferenceAttendee.VoteStatus.ALTERNATE)) { %>
							<option value="<%= att.getKey().getId() %>" selected="selected">
						<% } else { %>
							<option value="<%= att.getKey().getId() %>">
						<% } %>
						<%= att.getFullName() %>
						</option>
					<% } %>
				</select>
			</p>
			<input type="hidden" name="id" value="<%= council.getKey().getId() %>">
			<p class="submit"><input type="submit" value="Submit" /></p>
		</fieldset> 
	</form>
</body>
</html>