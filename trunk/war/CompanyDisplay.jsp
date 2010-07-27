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
<%@ page import="java.util.List;" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="com.naesc2011.conference.shared.*" %>
<%@ page import="com.naesc2011.conference.server.*" %>
<%  PermissionManager p = new PermissionManager(); %>
<%@ include file="header.jsp" %>
<%
    if (p.IsUserLoggedIn()) {
%>

<a href="/CompanyList.jsp">Company List</a>


<%
	if(request.getParameter("id") == null){
    	String redirectURL = "/CompanyList.jsp";
    	response.sendRedirect(redirectURL);
    }
%>

<%		PersistenceManager pm = PMF.get().getPersistenceManager();
		CorporateCompany c = CorporateCompany.GetCompany(pm, request.getParameter("id"));
%>
		
<%		pm.close(); %>
	<br /><br >
	<table>
		<tr>
		<td valign=top>
		
		<table border=1 cellpadding=4 cellspacing=0>
			<tr>
				<td>Name:</td>
				<td><%= c.getName() %></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><%= c.getAddress() %></td>
			</tr>
			<tr>
				<td>Pledged:</td>
				<td><%= c.getPledged() %></td>
			</tr>
			<tr>
				<td>Pledge Date:</td>
				<td><%= c.getPledgeDate() %></td>
			</tr>
			<tr>
				<td>Sector:</td>
				<td><%= c.getSector() %></td>
			</tr>
			<tr>
				<td>Products:</td>
				<td><%= c.getProducts() %></td>
			</tr>
			<tr>
				<td>Description:</td>
				<td><%= c.getDescription() %></td>
			</tr>
		</table>
		
		</td>
		<td valign=top>
		
		<table border=1 cellpadding=4 cellspacing=0>
			<tr>
				<td>Mechanical:</td>
				<td><% if(c.getMajorMechanical()){ %>Yes<% } else { %>No<% } %></td>
			</tr>
			<tr>
				<td>Civil:</td>
				<td><% if(c.getMajorCivil()){ %>Yes<% } else { %>No<% } %></td>
			</tr>
			<tr>
				<td>Computer:</td>
				<td><% if(c.getMajorComputer()){ %>Yes<% } else { %>No<% } %></td>
			</tr>
			<tr>
				<td>Electrical:</td>
				<td><% if(c.getMajorElectrical()){ %>Yes<% } else { %>No<% } %></td>
			</tr>
			<tr>
				<td>Chemical:</td>
				<td><% if(c.getMajorChemical()){ %>Yes<% } else { %>No<% } %></td>
			</tr>
			<tr>
				<td>Biological:</td>
				<td><% if(c.getMajorBiological()){ %>Yes<% } else { %>No<% } %></td>
			</tr>
			<tr>
				<td>Industrial:</td>
				<td><% if(c.getMajorIndustrial()){ %>Yes<% } else { %>No<% } %></td>
			</tr>
			<tr>
				<td>Aeronautical:</td>
				<td><% if(c.getMajorAeronautical()){ %>Yes<% } else { %>No<% } %></td>
			</tr>
			<tr>
				<td>Management:</td>
				<td><% if(c.getMajorManagement()){ %>Yes<% } else { %>No<% } %></td>
			</tr>
			<tr>
				<td>Materials:</td>
				<td><% if(c.getMajorMaterials()){ %>Yes<% } else { %>No<% } %></td>
			</tr>
		</table>
		
		</td>
		<td valign=top>
		
		<table border=1 cellpadding=4 cellspacing=0>
			<tr>
				<td>Primary POC Name:</td>
				<td><%= c.getPrimaryPOCName() %></td>
			</tr>
			<tr>
				<td>Primary POC Title:</td>
				<td><%= c.getPrimaryPOCTitle() %></td>
			</tr>
			<tr>
				<td>Primary POC Cell Phone:</td>
				<td><%= c.getPrimaryPOCCellPhone() %></td>
			</tr>
			<tr>
				<td>Primary POC Work Phone:</td>
				<td><%= c.getPrimaryPOCWorkPhone() %></td>
			</tr>
			<tr>
				<td>Primary POC Email:</td>
				<td><%= c.getPrimaryPOCEmail() %></td>
			</tr>
		</table>
		
		</td></tr>
	</table>
	
	<br />
	
<%	List<CorporateCorrespondence> ccd = c.getCorrespondence(); %>
	
	<table border=1>
		<tr>
			<td>Outcome</td>
			<td>Next Steps</td>
			<td>Notes</td>
		</tr>
<%		for(int i = 0; i < ccd.size(); i++)
		{
			CorporateCorrespondence cc =  ccd.get(i);
%>
			<tr>
				<td><%= (i+1) %></td>
				<td><%= cc.getOutcome() %></td>
				<td><%= cc.getNextSteps() %></td>
				<td><%= cc.getNotes() %></td>
			</tr>
<%		} %>
	</table>

	<br />

	<form action="/Process/CorrespondenceAdd" method="post">
		<fieldset> 
		<legend>Add Correspondence</legend>
		<p><label>Outcome:</label><input type="text" name="outcome" /></p>
		<p><label>Next Steps:</label><input type="text" name="nextSteps" /></p>
		<p><label>Notes:</label><input type="text" name="notes" /></p>
		<input type="hidden" name="companyId" value="<%= c.getKey().getId() %>">
		<p class="submit"><input type="submit" value="Submit" /></p>
		</fieldset>
	</form>
<% } %>
<%@ include file="footer.jsp" %>