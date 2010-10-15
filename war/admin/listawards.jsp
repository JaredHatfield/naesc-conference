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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>Admin: List Awards</title>
</head>
<body>
<div id="main">
	<jsp:include page="../header.jsp">
		<jsp:param value="Award Application" name="pagename"/>
	</jsp:include>
	<div id="body">
		<h2>List of Awards</h2>
		<a href="/admin/"><img src="/static/back.png" /></a><br /><br />
		<% @SuppressWarnings("unchecked") List<Council> councils = (List<Council>)request.getAttribute("councils"); %>
		<% @SuppressWarnings("unchecked") List<Award> awards = (List<Award>)request.getAttribute("awards"); %>
		
		<table class="infotable">
			<tr class="titlerow">
				<td class="mediumcell">Name</td>
				<td class="mediumcell">University</td>
				<% for(int i = 0; i < awards.size(); i++) { %>
					<td>
						<%= awards.get(i).getName() %>
					</td>
				<% } %>
			</tr>
			<% for(int k = 0; k < councils.size(); k++) { %>
				<tr>
					<% Council council  = councils.get(k); %>
					<% String altcolor = (k % 2 == 0) ? "cellone" : "celltwo"; %>
					<td class="<%= altcolor %>"><a href="/mycouncil?id=<%= council.getKey().getId() %>"><%= council.getName() %></a></td>
					<td class="<%= altcolor %>"><%= council.getUniversity() %></td>
					<% for(int i = 0; i < awards.size(); i++) { %>
						<td class="<%= altcolor %>">
							<% Award a = awards.get(i); %>
							<% List<AwardSubmission> submitted = council.getAwardSubmissions(); %>
							<% for(int j = 0; submitted != null && j < submitted.size(); j++) { %>
								<% if(submitted.get(j).getAward().equals(a.getKey())) { %>
									<a href="/admin/displayaward?id=<%= council.getKey().getId() %>&a=<%= a.getKey().getId() %>">View</a>
									<% if(submitted.get(j).getSubmitted()){ %>
										<img src="/static/check.png" alt="Submitted" title="Submitted">
									<% } else { %>
										<img src="/static/progress.png" alt="In Progress" title="In Progress">
									<% } %>
								<% break; } %>
							<% } %>
						</td>
					<% } %>
				</tr>
			<% } %>
		</table>
	</div>
	<div id="rightbar">
		<h3>Award Application</h3>
		<p>
			The submitted awards.
		</p>
	</div>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>