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
<%@ page import="com.naesc2011.conference.shared.ConferenceSettings" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>NAESC 2011 National Conference: Edit Council</title>
	<script language="javascript" type="text/javascript">
	<!--
	<% ConferenceSettings cs = (ConferenceSettings)request.getAttribute("conferencesettings"); %>
	function update(){
		<% if(!cs.isRegistrationOpen() && !(Boolean)request.getAttribute("isadmin")) { %>
			disableForms();
		<% } %>
	}
	// -->
	</script>
</head>
<body onLoad="update();">
<div id="main">
	<jsp:include page="../header.jsp">
		<jsp:param value="Edit Council" name="pagename"/>
	</jsp:include>
	<div id="body">
		<% Council council = (Council)request.getAttribute("council"); %>
		<h2><a href="/mycouncil?id=<%= council.getKey().getId() %>">My Council</a> &rarr; Edit Council</h2>
		
		<form action="/process/savecouncil" method="post"> 
			<fieldset> 
				<legend>Edit Council</legend> 
				<p><label>Council Name:</label><input class="insmall" type="text" maxlength="500" name="name" value="<%= council.getName() %>" /></p>
				<p><label>University:</label><input class="insmall" type="text" maxlength="500" name="university" value="<%= council.getUniversity() %>" /></p>
				<p><label>Location:</label><input class="insmall" type="text" maxlength="500" name="location" value="<%= council.getLocation() %>" /></p>
				<p><label>Contact:</label><input class="insmall" type="text" maxlength="500" name="contact" value="<%= council.getContact() %>" /></p>
				<p><label>Website:</label><input class="insmall" type="text" maxlength="500" name="website" value="<%= council.getWebsite() %>" /></p>
				<% if((Boolean)request.getAttribute("isadmin")) { %>
					<p><label>Amount Paid:</label><input class="insmall" type="text" maxlength="500" name="amountpaid" value="<%= council.getAmountPaid() %>" /></p>
					<p><label>Payment Notes:</label><input class="insmall" type="text" maxlength="500" name="paymentnotes" value="<%= council.getPaymentNotes() %>" /></p>
				<% } %>
				<% if(cs.isRegistrationOpen() || (Boolean)request.getAttribute("isadmin")) { %>
					<p class="submit">
						<input type="hidden" name="id" value="<%= council.getKey().getId() %>">
						<input type="submit" value="Submit" />
					</p>
				<% } %>
			</fieldset> 
		</form>
	</div>
	<div id="rightbar">
		<h3>Council Information</h3>
		You can modify the attributes associated with your council.
		All of the fields are required.
	</div>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>