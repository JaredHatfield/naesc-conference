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
<%@ page import="com.naesc2011.conference.shared.ConferenceAttendee" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>NAESC 2011 National Conference: Upload Resume</title>
</head>
<body>
<div id="main">
	<jsp:include page="../header.jsp">
		<jsp:param value="Upload Resume" name="pagename"/>
	</jsp:include>
	<div id="body">
		<% ConferenceAttendee a = (ConferenceAttendee)request.getAttribute("attendee"); %>
		<h2><a href="/mycouncil?id=<%= request.getAttribute("id") %>">My Council</a> &rarr; <a href="/editattendee?id=<%= request.getAttribute("id") %>&m=<%= a.getKey().getId() %>">Edit Attendee</a> &rarr; Upload Resume</h2>
			
			<% BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();  %>
			<form action="<%= blobstoreService.createUploadUrl("/process/uploadresume") %>" method="post" enctype="multipart/form-data">
			<fieldset> 
			<legend>Upload Resume</legend>
			<p><label>Resume:</label><input type="file" name="<%= a.getResumeKey() %>"></p>
	    	<p class="submit">
		    	<label></label>
				<input type="hidden" name="id" value="<%= request.getAttribute("id") %>" />
				<input type="hidden" name="m" value="<%= a.getKey().getId() %>" />
				<input type="submit" value="Upload">
			</p>
			</fieldset>
		</form>
	</div>
	<div id="rightbar">
		<h3>Resume</h3>
		<!-- TODO: Put instructions here -->
	</div>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>