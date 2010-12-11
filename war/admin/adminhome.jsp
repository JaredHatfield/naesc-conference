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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>Admin: Home</title>
</head>
<body>
<div id="main">
	<jsp:include page="../header.jsp">
		<jsp:param value="Admin" name="pagename"/>
	</jsp:include>
	<div id="body">
		<h2>Admin</h2>
		<h3>Management</h3>
		<ul>
			<li><a href="/admin/conferencesettings">Conference Settings</a></li>
			<li><a href="/admin/managetour">Manage Tours</a></li>
			<li><a href="/admin/award">Manage Awards</a></li>
		</ul>
		<h3>Information</h3>
		<ul>
			<li><a href="/admin/listawards">List Award Applications</a></li>
			<li><a href="/admin/listcouncil">List All Councils</a></li>
			<li><a href="/admin/listattendees">List All Attendees</a></li>
		</ul>
		<h3>Export</h3>
		<ul>
			<li><a href="/admin/council.csv">Export Councils</a></li>
			<li><a href="/admin/attendee.csv">Export Attendees</a></li>
		</ul>
	</div>
	<div id="rightbar">
		<h3>Administration</h3>
		<p>
			Everything that you can do as an administrator.
		</p>
	</div>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>