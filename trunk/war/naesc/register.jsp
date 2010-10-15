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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../htmlhead.jsp" %>
	<title>NAESC 2011 National Conference: Register</title>
	<script language="JavaScript" type="text/JavaScript">
	<!--//
	function checkme() {
		if (!document.registercouncil.authorization.checked) {
			missinginfo = "You must agree to the terms before registering your council.";
			alert(missinginfo);
			return false;
		}
		else { 
			return true;
		}
	}
	// --->
	</script>
</head>
<body>
<div id="main">
	<jsp:include page="../header.jsp">
		<jsp:param value="Register" name="pagename"/>
	</jsp:include>
	<div id="body">
		<h2>Register</h2>
		<form name="registercouncil"  action="/process/register" method="post" onSubmit="return checkme();"> 
			<fieldset> 
				<legend>Register Council</legend> 
				<p><label>Council Name:</label><input class="insmall" type="text" maxlength="500" name="name" /></p>
				<p><label>University:</label><input class="insmall" type="text" maxlength="500" name="university" /></p>
				<p><label>Location:</label><input class="insmall" type="text" maxlength="500" name="location" /></p>
				<p class="confirmbox">
					<label class="widelabel">
						<input type="checkbox" name="authorization" />
						By checking this box, you agree to complete the registration process and pay the required registration fees.  Each council should only register once.  Conference attendees are added once the council has been registered.
					</label>
				</p>
				<p class="submit"><input type="submit" value="Submit" /></p>
			</fieldset> 
		</form>
	</div>
	<div id="rightbar">
		<h3>The First Step</h3>
		<p>
			One person from your council needs to register as their council admin.
			This user will be able to add attendees and submit the award applications.
		</p>
		<p>
			When an attendee is added they are able to modify their information when they log in with the same email address that is associated with that attendee.
			Individual attendees have permission to modify their information and upload a resume.
		</p>
		<p>
			If you are not the primary contact for a council and are an attendee you should not register a new council.
		</p>
	</div>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>