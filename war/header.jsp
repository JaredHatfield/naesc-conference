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
<html>
  <body>
<%
    if (p.IsUserLoggedIn()) {
%>
<p>Hello, <%= p.getUser().getNickname() %>! (You can
<a href="<%= p.getUserService().createLogoutURL(request.getRequestURI()) %>">sign out</a>.)</p>

<a href="/CompanyList.jsp">Company List</a> | <a href="/ConferenceAttendeeList.jsp">Attendee List</a><br />

<%
    } else {
%>
<p>Hello!
<a href="<%= p.getUserService().createLoginURL(request.getRequestURI()) %>">Sign in</a>
to include your name with greetings you post.</p>
<%
    }
%>

