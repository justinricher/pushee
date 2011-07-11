<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.js"></script>
<title>Delete Client</title>
</head>
<script type="text/javascript">
$(document).ready(function() {

	$('#yes').click(function(event) {
		event.preventDefault();
		var data = {};
		
		data.clientId = $('#clientId').val();
		
		$.post('../api/delete', data)
		.success(function () {
			console.log("Success!");
			window.location.replace("../");
		})
		.error(function () {
			console.log("Error!");
			alert("The client was not deleted");
			window.location.replace("../");
		});
		
	});
	
	$('#no').click(function(event) {
		event.preventDefault();
		window.location.replace("../");		
		
	});

});
</script>
<body>
You are about to delete this client:

<div>
Client: <a href="edit/${client.clientId}">${client.clientId}</a><br />
&nbsp;&nbsp;&nbsp;&nbsp;Secret: ${client.clientSecret}<br />
&nbsp;&nbsp;&nbsp;&nbsp;authorizedGrantTypes: ${client.authorizedGrantTypes}<br />
&nbsp;&nbsp;&nbsp;&nbsp;webServerRedirectUri: ${client.webServerRedirectUri}<br />
&nbsp;&nbsp;&nbsp;&nbsp;Scope: ${client.scope}<br />
</div>

This action cannot be undone. Are you sure?

<f:form modelAttribute="client">
	<f:hidden path="clientId"/>
	
	<button id="yes">Yes</button>
	
	<button id="no">No</button>
</f:form>
</body>
</html>