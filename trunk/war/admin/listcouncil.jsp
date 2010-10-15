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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>Admin: List Council</title>
</head>
<body>
<div id="main">
	<jsp:include page="../header.jsp">
		<jsp:param value="Award Application" name="pagename"/>
	</jsp:include>
	<div id="body">
		<h2>List of Registered Councils</h2>
		<a href="/admin/"><img src="/static/back.png" /></a><br /><br />
		<% @SuppressWarnings("unchecked") List<Council> councils = (List<Council>)request.getAttribute("councils"); %>
		<table class="infotable">
			<tr class="titlerow">
				<td class="mediumcell">Name</td>
				<td class="mediumcell">University</td>
				<td class="mediumcell">Location</td>
				<td class="extralargecell">Website</td>
				<td class="smallcell">Count</td>
				<td class="smallcell">Cost</td>
				<td class="smallcell">Paid</td>
			</tr>
			<% int totalAttendee = 0; %>
			<% int totalPaid = 0; %>
			<% int totalCost = 0; %>
			<% for(int k = 0; k < councils.size(); k++) { %>
				<tr>
					<% Council council  = councils.get(k); %>
					<% String altcolor = (k % 2 == 0) ? "cellone" : "celltwo"; %>
					<td class="<%= altcolor %>"><a href="/mycouncil?id=<%= council.getKey().getId() %>"><%= council.getName() %></a></td>
					<td class="<%= altcolor %>"><%= council.getUniversity() %></td>
					<td class="<%= altcolor %>"><%= council.getLocation() %></td>
					<td class="<%= altcolor %>"><a href="<%= council.getWebsite() %>"><%= council.getWebsite() %></a></td>
					<td class="<%= altcolor %>">
						<% totalAttendee += council.getAttendees().size(); %>
						<%= council.getAttendees().size() %>
					</td>
					<td class="<%= altcolor %>">
						<% totalCost += (int)council.getAttendeeCost(); %>
						$<%= (int)council.getAttendeeCost() %>
					</td>
					<td class="<%= altcolor %>">
						<% totalPaid += (int)council.getAmountPaid(); %>
						$<%= (int)council.getAmountPaid() %>
					</td>
				</tr>
			<% } %>
		</table>
		
		<h3>Statistics</h3>
		<table class="infotable">
			<tr>
				<td class="titlecol">Total Attending</td>
				<td class="cellone"><%= totalAttendee %></td>
			</tr>
			<tr>
				<td class="titlecol">Total Cost</td>
				<td class="celltwo">$<%= totalCost %></td>
			</tr>
			<tr>
				<td class="titlecol">Total Paid</td>
				<td class="cellone">$<%= totalPaid %></td>
			</tr>
			<tr>
				<td class="titlecol">Outstanding</td>
				<td class="celltwo">$<%= totalCost - totalPaid %></td>
			</tr>
		</table>
	</div>
	<div id="rightbar">
		<h3>Councils</h3>
		<!-- TODO: Put instructions here -->
	</div>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>