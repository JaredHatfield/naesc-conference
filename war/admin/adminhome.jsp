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
	<%@ include file="../header.jsp" %>
	<h1>Admin</h1>
	<!-- TODO: Display the admin interface for the application. -->
	
	<h2><a href="/admin/managetour">Manage Tours</a></h2>
	
	<h2><a href="/admin/award">Manage Awards</a></h2>
	
	<h2><a href="/admin/listcouncil">List All Councils</a></h2>
	
	<h2><a href="/admin/council.csv">Export Councils</a></h2>
	
	<h2><a href="/admin/attendee.csv">Export Attendees</a></h2>
	
</body>
</html>