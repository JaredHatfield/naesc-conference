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
<%@ page import="com.naesc2011.conference.shared.ConferenceSettings" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>Admin: Conference Settings</title>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<h2>Conference Settings</h2>
	<% ConferenceSettings cs = (ConferenceSettings)request.getAttribute("conferencesettings"); %>
	<a href="/admin/"><img src="/static/back.png" /></a><br /><br />
	
	<form action="/admin/process/saveconferencesettings" method="post"> 
		<fieldset> 
			<legend>Conference Settings</legend> 
			<p><label>Conference Name:</label><input class="insmall" type="text" maxlength="500" name="conferenceName" value="<%= cs.getConferenceName() %>" /></p>
			<br />
			<p><label>Early Registration Date:</label><input class="insmall" type="text" maxlength="500" name="earlyDate" value="<%= cs.getEarlyRegistrationDateString() %>" /></p>
			<p><label>Early Registration Fee:</label><input class="insmall" type="text" maxlength="500" name="earlyFee" value="<%= cs.getEarlyRegistrationFee() %>" /></p>
			<br />
			<p><label>Late Registration Date:</label><input class="insmall" type="text" maxlength="500" name="lateDate" value="<%= cs.getLateRegistrationDateString() %>" /></p>
			<p><label>Late Registration Fee:</label><input class="insmall" type="text" maxlength="500" name="lateFee" value="<%= cs.getLateRegistrationFee() %>" /></p>
			<br />
			<p><label>Maximum Attendees Per Council:</label><input class="insmall" type="text" maxlength="500" name="maxattendees" value="<%= cs.getMaxAttendees() %>" /></p>
			<p class="submit"><input type="submit" value="Update" /></p>
		</fieldset> 
	</form>
</body>
</html>