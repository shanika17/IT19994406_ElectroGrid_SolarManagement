<%@page import="com.Solar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Solar Management</title>
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery.min.js"></script>
		<script src="Components/solar.js"></script>
	</head>

	<body> 
		<div class="container"><div class="row"><div class="col-6"> 
		<h1>Solar Management </h1>
		
			<form id="formSolar" name="formSolar">
			
 				Customer Name : 
 				<input id="customerName" name="customerName" type="text" 
 				class="form-control form-control-sm"> <br>
			    
			    Customer Address : 
 				<input id="customerAddress" name="customerAddress" type="text" 
 				class="form-control form-control-sm"><br> 
 				
 				Capacity(kW) : 
 				<input id="capacity" name="capacity" type="text" 
				class="form-control form-control-sm"><br>
 				
 				No of Solar Panels : 
 				<input id="noOfSolarPanels" name="noOfSolarPanels" type="text" 
 				class="form-control form-control-sm"><br> 
 				
 				Type : 
 				<input id="type" name="type" type="text" 
 				class="form-control form-control-sm"><br>
 				
 				<input id="btnSave" name="btnSave" type="button" value="Save" 
 				class="btn btn-primary">
 				<input type="hidden" id="hidSolarIDSave" 
				name="hidSolarIDSave" value="">
				
			</form>
			
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		
		<div id="divSolarGrid">
 		<%
 		Solar solarObj = new Solar(); 
 		 		out.print(solarObj.readSolarDetails());
 		%>
	</div>
	
	</div> </div> </div> 
</body>
</html>
