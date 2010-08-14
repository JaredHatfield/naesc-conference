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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="com.naesc2011.conference.shared.*" %>
<%@ page import="com.naesc2011.conference.server.*" %>
<%  PermissionManager p = new PermissionManager(); %>
<%@ include file="header.jsp" %>
<%
    if (p.IsUserLoggedIn()) {
%>

<h2>Add Attendee</h2>

	<form method="post" action="/Process/ConferenceAttendeeAdd">
		<fieldset> 
		<legend>Add Attendee</legend>
		<p><label>First Name:</label><input type="text" name="firstName" /></p>
		<p><label>Middle Name:</label><input type="text" name="middleName" /></p>
		<p><label>Last Name:</label><input type="text" name="lastName" /></p>
		<p><label>Major:</label><input type="text" name="major" /></p>
		<p><label>Email:</label><input type="text" name="email" /></p>
		<p>
			<label>Gender:</label>
			<select  name="gender">
			<% for(ConferenceAttendee.Gender g : ConferenceAttendee.Gender.values()) { %>
				<option value="<%= g.toString() %>"><%= g.toString() %></option>
			<% } %>
			</select>
		</p>
		<p>
			<label>Shirt Size:</label>
			<select name="shirtSize">
			<% for(ConferenceAttendee.ShirtSize ss : ConferenceAttendee.ShirtSize.values()) { %>
				<option value="<%= ss.toString() %>"><%= ss.toString() %></option>
			<% } %>
			</select>
		</p>
		
		<p><label>Emergency Contact Name:</label><input type="text" name="ecName" /></p>
		<p><label>Emergency Contact Phone:</label><input type="text" name="ecPhone" /></p>
		
		<p class="submit">
		<input type="submit" value="Update" />
		</p>
		</fieldset>
	</form>

<% } %>
<%@ include file="footer.jsp" %>