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
<%@ page import="com.naesc2011.conference.shared.Award" %>
<%@ page import="com.naesc2011.conference.shared.AwardSubmission" %>
<%@ page import="com.naesc2011.conference.shared.AwardApplication" %>
<%@ page import="com.naesc2011.conference.shared.ConferenceSettings" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>Admin: Display Award</title>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<h2>Award Application</h2>
	<a href="/admin/listawards"><img src="/static/back.png" /></a><br /><br />
	<% Award a = (Award)request.getAttribute("award"); %>
	<% AwardApplication app = (AwardApplication)request.getAttribute("application"); %>
	<% AwardSubmission sub = (AwardSubmission)request.getAttribute("submission"); %>
	<% Council council = (Council)request.getAttribute("council"); %>
	<form> 
		<fieldset> 
			<legend><%= a.getName() %> - <%= council.getName() %> at <%= council.getUniversity() %></legend> 
			<% if(sub != null && sub.getSubmitted()) { %>
				<p style="font-weight: bold; text-align: center;">Submitted on <%= sub.getSubmittedOn() %></p>
			<% } else { %>
				<p style="font-weight: bold; text-align: center;">In Progress...</p>
			<% } %>
				<p><label class="widelabel"><%= a.getQuestion1() %></label></p><br />
				<p style="font-size: 80%"><%= app.getQuestion1().getValue().replace("\n", "<br />") %></p>
				<p><label class="widelabel"><%= a.getQuestion2() %></label></p><br />
				<p style="font-size: 80%"><%= app.getQuestion2().getValue().replace("\n", "<br />") %></p>
				<p><label class="widelabel"><%= a.getQuestion3() %></label></p><br />
				<p style="font-size: 80%"><%= app.getQuestion3().getValue().replace("\n", "<br />") %></p>
				<p><label class="widelabel"><%= a.getQuestion4() %></label></p><br />
				<p style="font-size: 80%"><%= app.getQuestion4().getValue().replace("\n", "<br />") %></p>
		</fieldset> 
	</form>
</body>
</html>