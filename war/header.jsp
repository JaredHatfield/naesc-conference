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
	<div id="topbarleft">
	<% if(request.getParameter("pagename") != null && request.getParameter("pagename").equals("Home")) { %>
		<b>Home</b>
	<% } else { %>
		<a href="/">Home</a>
	<% } %>
	</div>
	<div id="topbarright">
		<% if((Boolean)request.getAttribute("authenticated")){ %>
			<div style="display: inline; font-weight: bold;">
				<%= request.getAttribute("username") %>
			</div> | 
			<% if((Boolean)request.getAttribute("isadmin")) { %>
				<a href="/admin/">Admin</a> | 
			<% } %>
			<a href="<%= request.getAttribute("logouturl") %>">Sign out</a>
		<% } else { %>
			<a href="<%= request.getAttribute("loginurl") %>">Sign in</a>
		<% } %>
	</div>
</div>
<div id="headertitle">
	<img src="/static/naesc.png" id="logo" />
</div>

