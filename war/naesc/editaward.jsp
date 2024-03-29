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
<%@ page import="com.naesc2011.conference.shared.ConferenceSettings" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>NAESC 2011 National Conference: Edit Award</title>
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
		<jsp:param value="Edit Award" name="pagename"/>
	</jsp:include>
	<div id="body">
		<% Award a = (Award)request.getAttribute("award"); %>
		<% AwardApplication app = (AwardApplication)request.getAttribute("application"); %>
		<% AwardSubmission sub = (AwardSubmission)request.getAttribute("submission"); %>
		<h2><a href="/mycouncil?id=<%= request.getAttribute("councilid") %>">My Council</a> &rarr; Edit Award</h2>
			
		<form action="/process/saveaward" method="post"> 
			<fieldset> 
				<legend><%= a.getName() %></legend>
				<p style="font-style : italic; color: grey;">
					<label class="widelabel" style=""><%= a.getDescription() %></label>
				</p>
				<br />
				<% if(app == null) { %>
					<p><label class="widelabel"><%= a.getQuestion1() %></label></p>
					<p><textarea cols="70" rows="8" name="q1"></textarea></p>
					<p><label class="widelabel"><%= a.getQuestion2() %></label></p>
					<p><textarea cols="70" rows="8" name="q2"></textarea></p>
					<p><label class="widelabel"><%= a.getQuestion3() %></label></p>
					<p><textarea cols="70" rows="8" name="q3"></textarea></p>
					<p><label class="widelabel"><%= a.getQuestion4() %></label></p>
					<p><textarea cols="70" rows="8" name="q4"></textarea></p>
				<% } else if(sub != null && sub.getSubmitted()) { %>
					<p style="font-weight: bold; text-align: center;">Submitted on <%= sub.getSubmittedOn() %></p>
					<p><label class="widelabel"><%= a.getQuestion1() %></label></p><br />
					<p style="font-size: 70%"><%= app.getQuestion1().getValue().replace("\n", "<br />") %></p>
					<p><label class="widelabel"><%= a.getQuestion2() %></label></p><br />
					<p style="font-size: 70%"><%= app.getQuestion2().getValue().replace("\n", "<br />") %></p>
					<p><label class="widelabel"><%= a.getQuestion3() %></label></p><br />
					<p style="font-size: 70%"><%= app.getQuestion3().getValue().replace("\n", "<br />") %></p>
					<p><label class="widelabel"><%= a.getQuestion4() %></label></p><br />
					<p style="font-size: 70%"><%= app.getQuestion4().getValue().replace("\n", "<br />") %></p>
				<% } else { %>
					<p><label class="widelabel"><%= a.getQuestion1() %></label></p>
					<p><textarea cols="70" rows="8" name="q1"><%= app.getQuestion1().getValue() %></textarea></p>
					<p><label class="widelabel"><%= a.getQuestion2() %></label></p>
					<p><textarea cols="70" rows="8" name="q2"><%= app.getQuestion2().getValue() %></textarea></p>
					<p><label class="widelabel"><%= a.getQuestion3() %></label></p>
					<p><textarea cols="70" rows="8" name="q3"><%= app.getQuestion3().getValue() %></textarea></p>
					<p><label class="widelabel"><%= a.getQuestion4() %></label></p>
					<p><textarea cols="70" rows="8" name="q4"><%= app.getQuestion4().getValue() %></textarea></p>
				<% } %>
				<% if((sub == null || !sub.getSubmitted()) && (cs.isRegistrationOpen() || (Boolean)request.getAttribute("isadmin"))) { %>
					<br />
					<input type="hidden" name="id" value="<%= request.getAttribute("councilid") %>" />
					<input type="hidden" name="a" value="<%= a.getKey().getId() %>" />
					<p>
						<label style="width: 20em;">Save your responses.<br />You will be able to edit your responses.</label>
						<input type="submit" name="submitbutton" value="Save" />
					</p>
					<br />
					<p>
						<label style="width: 20em;">Submit application for consideration.<br />You will not be able to edit your responses.</label>
						<input type="submit" name="submitbutton" value="Submit" />
					</p>
				<% } %>
			</fieldset> 
		</form>
	</div>
	<div id="rightbar">
		<h3>Award Applications</h3>
		<p>
			Award applications must have all parts completed.
			If you are not finished with the application, you are able to save your progress and return later to finish the application.
			Once you have completed the application it can be submitted and will no longer be able to be edited.
		</p>
		<p>
			The application must be submitted before registration closes.
		</p>
	</div>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>