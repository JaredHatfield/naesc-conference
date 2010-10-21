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
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="/static/invoice.css" />
	<title>NAESC 2011 National Conference Invoice</title>
</head>
<body>
<div id="main">
	<div id="body">
		<h1>NAESC 2011 National Conference Invoice</h1>
		<% Council council = (Council)request.getAttribute("council"); %>
		<% @SuppressWarnings("unchecked") List<Tour> tours = (List<Tour>)request.getAttribute("tours"); %>
		<% @SuppressWarnings("unchecked") List<Award> awards = (List<Award>)request.getAttribute("awards"); %>
		<% ConferenceSettings cs = (ConferenceSettings)request.getAttribute("conferencesettings"); %>
		<table id="addressesinfo">
			<tr>
				<td>
					<p class="paymentname">Pay To:</p>
					<p class="paymentaddress"><%= cs.getAddress().replace("\n","<br />") %></p>
				</td>
				<td>
					<p class="paymentname">Payee:</p>
					<p class="paymentaddress" id="paymentcounciladdress">
						<%= council.getName() %><br />
						<%= council.getUniversity() %><br />
						<%= council.getLocation() %><br />
						<%= council.getContact() %>
					</p>
				</td>
			</tr>
		</table>
		<br />
		<table id="invoiceinfo" align="right">
			<tr>
				<td class="shaded">Invoice #</td>
				<td class="content"><%= council.getKey().getId() %></td>
			</tr>
			<tr>
				<td class="shaded">Date Printed</td>
				<td class="content"><%= ConferenceSettings.getTodaysDate() %></td>
			</tr>
		</table>
		<table class="infotable">
			<tr class="titlerow">
				<td class="shaded">Attendee Name</td>
				<td class="shaded" id="datecol">Date</td>
				<td class="shaded" id="regcol">Registration</td>
				<td class="shaded" id="costcol">Cost</td>
			</tr>
			<% for(int i = 0; i < council.getAttendees().size(); i++) { %>
				<tr>
					<% ConferenceAttendee att = council.getAttendees().get(i); %>
					<td class="content"><%= att.getFirstName() %> <%= council.getAttendees().get(i).getLastName() %></td>
					<td><%= att.getAddedFormatted() %></td>
					<td class="content">
						<% if(att.getRegistartionFee() < cs.getLateRegistrationFee()) { %>
							Early
						<% } else { %>
							Standard
						<% } %>
					</td>
					<td class="content">$<%= (int)att.getRegistartionFee() %></td>
				</tr>
			<% } %>
			<tr>
				<td colspan="3" class="footercolinfo">Total</td>
				<td class="content">$<%= (int)council.getAttendeeCost() %></td>
			</tr>
			<tr>
				<td colspan="3" class="footercolinfo">Amount Paid</td>
				<td class="content">$<%= (int)council.getAmountPaid() %></td>
			</tr>
			<tr>
				<td colspan="3" class="footercolinfo">Balance Due</td>
				<td class="content" id="balancedue">$<%= (int)council.getAttendeeCost() - (int)council.getAmountPaid() %></td>
			</tr>
		</table>
		<table class="infotable" id="paymentnotes">
			<tr>
				<td class="shaded" id="paynote">Payment Notes:</td>
				<td class="content"><%= council.getPaymentNotes() %></td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>