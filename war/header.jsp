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

<div id="headbar">
	<div style="text-align: left; float: left;">
		<a href="/">Home</a>
	</div>
	<div style="text-align: right; float: right;">
		<% if((Boolean)request.getAttribute("authenticated")){ %>
			<%= request.getAttribute("username") %> | 
			<% if((Boolean)request.getAttribute("isadmin")) { %>
				<a href="/admin/">Admin</a> | 
			<% } %>
			<a href="<%= request.getAttribute("logouturl") %>">Logout</a>
		<% } else { %>
			<a href="<%= request.getAttribute("loginurl") %>">Login</a>
		<% } %>
	</div>
</div>



<h1>NAESC 2011 National Conference Registration</h1>

