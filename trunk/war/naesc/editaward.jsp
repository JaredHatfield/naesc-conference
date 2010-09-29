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
<%@ page import="com.naesc2011.conference.shared.AwardApplication" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>NAESC 2011 National Conference: Edit Award</title>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<% Award a = (Award)request.getAttribute("award"); %>
	<% AwardApplication app = (AwardApplication)request.getAttribute("application"); %>
	<h1>Edit Award</h1>
	<a href="/mycouncil?id=<%= request.getAttribute("councilid") %>">Back</a><br />
	<br />
	
	<form action="/process/saveaward" method="post"> 
		<fieldset> 
			<legend><%= a.getName() %></legend> 
			<% if(app == null) { %>
				<p><label class="widelabel"><%= a.getQuestion1() %></label></p>
				<p><textarea cols="70" rows="8" name="q1"></textarea></p>
				<p><label class="widelabel"><%= a.getQuestion2() %></label></p>
				<p><textarea cols="70" rows="8" name="q2"></textarea></p>
				<p><label class="widelabel"><%= a.getQuestion3() %></label></p>
				<p><textarea cols="70" rows="8" name="q3"></textarea></p>
				<p><label class="widelabel"><%= a.getQuestion4() %></label></p>
				<p><textarea cols="70" rows="8" name="q4"></textarea></p>
			<% } else { %>
				<p><label class="widelabel"><%= a.getQuestion1() %></label></p>
				<p><textarea cols="70" rows="8" name="q1"><%= app.getQuestion1() %></textarea></p>
				<p><label class="widelabel"><%= a.getQuestion2() %></label></p>
				<p><textarea cols="70" rows="8" name="q2"><%= app.getQuestion2() %></textarea></p>
				<p><label class="widelabel"><%= a.getQuestion3() %></label></p>
				<p><textarea cols="70" rows="8" name="q3"><%= app.getQuestion3() %></textarea></p>
				<p><label class="widelabel"><%= a.getQuestion4() %></label></p>
				<p><textarea cols="70" rows="8" name="q4"><%= app.getQuestion4() %></textarea></p>
			<% } %>
			<br />
			<input type="hidden" name="id" value="<%= request.getAttribute("councilid") %>" />
			<input type="hidden" name="a" value="<%= a.getKey().getId() %>" />
			<p class="submit">
				<label>Save Current Responses</label>
				<input type="submit" value="Save Progress" />
			</p>
		</fieldset> 
	</form>
	
	
	
</body>
</html>