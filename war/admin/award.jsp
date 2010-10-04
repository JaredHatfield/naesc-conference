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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>Admin: Award</title>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<h1>Award</h1>
	<a href="/admin/">Back</a><br />
	
	<form action="/admin/process/addaward" method="post"> 
		<fieldset> 
			<legend>Add Award</legend> 
			<p><label>Award Name:</label><input class="insmall" type="text" maxlength="500" name="name" /></p>
			<p><label>Question 1:</label><input class="insmall" type="text" maxlength="500" name="q1" /></p>
			<p><label>Question 2:</label><input class="insmall" type="text" maxlength="500" name="q2" /></p>
			<p><label>Question 3:</label><input class="insmall" type="text" maxlength="500" name="q3" /></p>
			<p><label>Question 4:</label><input class="insmall" type="text" maxlength="500" name="q4" /></p>
			<p class="submit"><input type="submit" value="Add" /></p>
		</fieldset> 
	</form>
	<h1>Manage Awards</h1>
	<% @SuppressWarnings("unchecked") List<Award> awards = (List<Award>)request.getAttribute("awards");
	   for(int i = 0; i < awards.size(); i++){ %>
		   <form action="/admin/process/saveaward" method="post"> 
				<fieldset> 
					<legend>Update Award</legend> 
					<p><label>Award Name:</label><input class="insmall" type="text" maxlength="500" name="name" value="<%= awards.get(i).getName() %>" /></p>
					<p><label>Question 1:</label><input class="insmall" type="text" maxlength="500" name="q1" value="<%= awards.get(i).getQuestion1() %>" /></p>
					<p><label>Question 2:</label><input class="insmall" type="text" maxlength="500" name="q2" value="<%= awards.get(i).getQuestion2() %>" /></p>
					<p><label>Question 3:</label><input class="insmall" type="text" maxlength="500" name="q3" value="<%= awards.get(i).getQuestion3() %>" /></p>
					<p><label>Question 4:</label><input class="insmall" type="text" maxlength="500" name="q4" value="<%= awards.get(i).getQuestion4() %>" /></p>
					<p class="submit">
						<input type="hidden" name="id" value="<%= awards.get(i).getKey().getId() %>" />
						<input type="submit" value="Update" />
					</p>
				</fieldset> 
			</form>
	<% } %>
</body>
</html>