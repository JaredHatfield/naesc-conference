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
<%@ page import="com.naesc2011.conference.shared.Tour" %>
<%@ page import="com.naesc2011.conference.shared.Council" %>
<%@ page import="com.naesc2011.conference.shared.ConferenceAttendee" %>
<%@ page import="com.naesc2011.conference.shared.ConferenceSettings" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>NAESC 2011 National Conference: My Council</title>
</head>
<body>
<div id="main">
	<jsp:include page="../header.jsp">
		<jsp:param value="My Council" name="pagename"/>
	</jsp:include>
	<div id="body">
		<h2>My Council</h2>
		<fieldset>
			<legend>Information</legend>
			<% Council council = (Council)request.getAttribute("council"); %>
			<% @SuppressWarnings("unchecked") List<Tour> tours = (List<Tour>)request.getAttribute("tours"); %>
			<% @SuppressWarnings("unchecked") List<Award> awards = (List<Award>)request.getAttribute("awards"); %>
			<% ConferenceSettings cs = (ConferenceSettings)request.getAttribute("conferencesettings"); %>
			
			<table class="infotable">
				<tr>
					<td>
						<a href="/invoice?id=<%= council.getKey().getId() %>">View Invoice</a>
					</td>
					<td style="border-width: 0px; text-align: right;">
						<a href="/editcouncil?id=<%= council.getKey().getId() %>"><img src="/static/edit.png" /></a>
					</td>
				</tr>
				<tr>
					<td class="titlecol">Name</td>
					<td class="cellone"><%= council.getName() %></td>
				</tr>
				<tr>
					<td class="titlecol">University</td>
					<td class="celltwo"><%= council.getUniversity() %></td>
				</tr>
				<tr>
					<td class="titlecol">Location</td>
					<td class="cellone"><%= council.getLocation() %></td>
				</tr>
				<tr>
					<td class="titlecol">Contact</td>
					<td class="celltwo"><%= council.getContact() %></td>
				</tr>
				<tr>
					<td class="titlecol">Website</td>
					<td class="cellone"><%= council.getWebsite() %></td>
				</tr>
				<tr>
					<td class="titlecol">Amount Due</td>
					<td class="celltwo">$<%= (int)council.getAttendeeCost() %></td>
				</tr>
				<tr>
					<td class="titlecol">Amount Paid</td>
					<td class="cellone">$<%= (int)council.getAmountPaid() %></td>
				</tr>
				<tr>
					<td class="titlecol">Payment Notes</td>
					<td class="celltwo">
						<p style="width: 23em;">
							<%= council.getPaymentNotes() %>
						</p>
					</td>
				</tr>
			</table>
		</fieldset>
		<br /><br />
		<fieldset>
			<legend>Award Applications</legend>
			<% List<AwardSubmission> submitted = council.getAwardSubmissions(); %>
			<table class="infotable">
				<tr class="titlerow">
					<td>Award Name</td>
					<td class="smallcell">Submitted</td>
				</tr>
				<% for(int i = 0; i < awards.size(); i++) { %>
					<tr>
						<% String altcolor = (i % 2 == 0) ? "cellone" : "celltwo"; %>
						<td class="<%= altcolor %>">
							<% Award a = awards.get(i); %>
						<a href="/editaward?id=<%= council.getKey().getId() %>&a=<%= a.getKey().getId() %>"><%= a.getName() %></a>
						</td>
						<td class="<%= altcolor %>">
							<% for(int j = 0; submitted != null && j < submitted.size(); j++) { %>
								<% if(submitted.get(j).getAward().equals(a.getKey())) { %>
									<% if(submitted.get(j).getSubmitted()){ %>
										<img src="/static/check.png" alt="Submitted" title="Submitted" class="center">
									<% } else { %>
										<img src="/static/progress.png" alt="In Progress" title="In Progress" class="center">
									<% } %>
								<% break; } %>
							<% } %>
						</td>
					</tr>
				<% } %>
			</table>
		</fieldset>
		<br /><br />
		<fieldset>
			<legend>Attending Members</legend>
			<table class="infotable">
				<tr>
					<td colspan="8" style="border-width: 0px; text-align: right;">
						<% if(council.getAttendees().size() == cs.getMaxAttendees()) { %>
							You have reached the maximum number of attendees.
						<% } %>
					</td>
				</tr>
				<tr class="titlerow">
					<td class="minicell">
						<% if(council.getAttendees().size() < cs.getMaxAttendees() && (cs.isRegistrationOpen() || (Boolean)request.getAttribute("isadmin"))) { %>
							<a href="/addattendee?id=<%= council.getKey().getId() %>"><img src="/static/add.png" class="center" /></a>
						<% } %>
					</td>
					<td class="mediumcell">Name</td>
					<td class="mediumcell">Email</td>
					<td class="mediumcell">Tour <a href="/tourlist" onClick="return popup(this, 'Tour List')"><img src="/static/info.png" /></a></td>
					<td class="mediumcell">Delegate <a href="/editdelegate?id=<%= council.getKey().getId() %>"><img src="/static/edit.png" /></a></td>
					<td class="smallcell">Registration</td>
					<td class="minicell">Resume</td>
					<td>Complete</td>
				</tr>
				<% for(int i = 0; i < council.getAttendees().size(); i++) { %>
					<tr>
						<% String altcolor = (i % 2 == 0) ? "cellone" : "celltwo"; %>
						<% ConferenceAttendee att = council.getAttendees().get(i); %>
						<td class="<%= altcolor %>"><a href="/editattendee?id=<%= council.getKey().getId() %>&m=<%= att.getKey().getId() %>"><img src="/static/edit.png" class="center" /></a></td>
						<td class="<%= altcolor %>"><%= att.getFirstName() %> <%= council.getAttendees().get(i).getLastName() %></td>
						<td class="<%= altcolor %>"><%= att.getEmail() %></td>
						<td class="<%= altcolor %>">
							<% for(int j = 0; j < tours.size(); j++) { %>
								<% if(tours.get(j).getKey().equals(att.getTour())) { %>
									<%= tours.get(j).getName() %>
								<% break; } %>
							<% } %>
						</td>
						<td class="<%= altcolor %>">
							<% if(att.getVoteStatus() == null) { %>
							<% } else if(att.getVoteStatus().equals(ConferenceAttendee.VoteStatus.VOTING)) { %>
								Voting
							<% } else if(att.getVoteStatus().equals(ConferenceAttendee.VoteStatus.ALTERNATE)) { %>
								Alternate
							<% } %>
						</td>
						<td class="<%= altcolor %>">
							$<%= (int)att.getRegistartionFee() %>
						</td>
						<td class="<%= altcolor %>">
							<% if(att.getResume() != null) { %>
								<img src="/static/document.png" alt="Resume Uploaded" title="Resume Uploaded" class="center">
							<% } %>
						</td>
						<td class="<%= altcolor %>">
							<% if(att.isAttendeeComplete()) { %>
								<img src="/static/check.png" alt="Complete" title="Complete" class="center">
							<% } %>
						</td>
					</tr>
				<% } %>
			</table>
		</fieldset>
	</div>
	<div id="rightbar">
		<h3>Manage your Council</h3>
		<p>
			In order to complete the registration process your council information needs to be provided.  
			The general information about your council can be modified by clicking <img src="/static/edit.png" /> above the information table.
		</p>
		<p>
			The amount due is based on the cost of individual attendees based on when the attendee was registered.
			When your payment has been submitted and processed the payment information will be updated.
		</p>
		<h3>Awards</h3>
		<p>
			Your council is able to apply for awards that will be awarded at the conference.
			The award applications can be saved while they are in progress as indicated by the <img src="/static/progress.png" /> icon.
			In order to be considered the application must be submitted which is indicated by the <img src="/static/check.png" /> icon.
		</p>
		<h3>Attendees</h3>
		<p>
			You can add up to <b><%= cs.getMaxAttendees() %></b> conference attendees by click the <img src="/static/add.png" /> image under the <b>Attending Members</b> heading.
			Attendee information can be changed until registration closes by clicking <img src="/static/edit.png" /> next to each attendee.
		</p>
		<p>
			The attendee will be able to log in with their email address that matches the one provided and will be able to edit their information.
			Resumes are required and can be uploaded by the council admin or the conference attendee.
		</p>
		<p>
			The <img src="/static/document.png" /> icon indicates that a resume has been uploaded.
			When all of the required information for an attendee has been provided <img src="/static/check.png" /> will appear next to the attendee.
		</p>
		<h3>Delegates</h3>
		<p>
			To select your council's delegates, click the <img src="/static/edit.png" /> image next to the <b>Delegate</b> heading.
			You will be able to select one voting delegate and one alternate delegate from your list of conference attendees.
		</p>
	</div>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>