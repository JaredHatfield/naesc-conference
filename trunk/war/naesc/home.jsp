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
<%@ page import="com.naesc2011.conference.shared.ConferenceSettings" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>NAESC 2011 National Conference: Home</title>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<h1>Home</h1>
	<% ConferenceSettings cs = (ConferenceSettings)request.getAttribute("conferencesettings"); %>
	
	<% if(!(Boolean)request.getAttribute("authenticated")){ %>
		You must log in to register or change your registration information.
	<% } else { %>
		You are logged in...<br />
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
			   		<b>Manage:</b> <%= councils.get(i).getName() %>
		   		</a>
			   	
			<% } %>
		<% } %>
	<% } %>
</body>
</html>