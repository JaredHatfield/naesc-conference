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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>Tour List</title>
</head>
<body>
	<h2>Tour List</h2>
	<% @SuppressWarnings("unchecked") List<Tour> tours = (List<Tour>)request.getAttribute("tours"); %>
	<% for(int i = 0; i < tours.size(); i++) { %>
		<% Tour t = tours.get(i); %>
		<% if(!t.hasRoom()){ %>
			<p style="color: darkgrey;">
		<% } else { %>
			<p>
		<% } %>
			<b><%= t.getName() %></b>
			<i><%= t.getDescription() %></i>
			(<%= t.getTourMembers().size() %> of <%= t.getMaximum() %>)
		</p>
	<% } %>
</body>
</html>