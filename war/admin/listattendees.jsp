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
<%@ page import="com.naesc2011.conference.shared.Council" %>
<%@ page import="com.naesc2011.conference.shared.ConferenceAttendee" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>Admin: List Council</title>
</head>
<body>
<div id="main">
	<jsp:include page="../header.jsp">
		<jsp:param value="Award Application" name="pagename"/>
	</jsp:include>
	<div id="body">
		<h2>List of Registered Councils</h2>
		<a href="/admin/"><img src="/static/back.png" /></a><br /><br />
		<% @SuppressWarnings("unchecked") List<Council> councils = (List<Council>)request.getAttribute("councils"); %>
		<table class="infotable">
			<tr class="titlerow">
				<td class="mediumcell">Council</td>
				<td class="mediumcell">Name</td>
				<td class="mediumcell">Resume</td>
			</tr>
			<% for(int j = 0; j < councils.size(); j++) { %>
				<% Council council  = councils.get(j); %>
				<% List<ConferenceAttendee> attendees = council.getAttendees(); %>
				<% for(int k = 0; k < attendees.size(); k++) { %>
					<tr>
						<% ConferenceAttendee attendee = attendees.get(k); %>
						<% String altcolor = (k % 2 == 0) ? "cellone" : "celltwo"; %>
						<td class="<%= altcolor %>">
							<a href="/mycouncil?id=<%= council.getKey().getId() %>"><%= council.getName() %></a>
						</td>
						<td class="<%= altcolor %>">
							<a href="/editattendee?id=<%= council.getKey().getId() %>&m=<%= attendee.getKey().getId() %>">
								<%= attendee.getLastName() %>, <%= attendee.getFirstName() %>
							</a>
						</td>
						<td class="<%= altcolor %>">
							<% if(attendee.getResume() != null) { %>
								<a href="/downloadresume?id=<%= council.getKey().getId() %>&m=<%= attendee.getKey().getId() %>&file.pdf">Download Resume</a>
							<% } else { %>
								No Resume
							<% } %>
						</td>
					</tr>
				<% } %>
			<% } %>
		</table>
	</div>
	<div id="rightbar">
		<h3>Attendees</h3>
		<p>
			All of the attendees and links to their resumes.
		</p>
	</div>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html> 