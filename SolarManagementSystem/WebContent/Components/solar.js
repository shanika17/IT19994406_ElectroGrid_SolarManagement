$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
	 	$("#alertSuccess").hide();
	 }
	 	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();

// Form validation-------------------
var status = validateSolarForm();
	if (status != true)
	 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
     }
 
// If valid------------------------
var type = ($("#hidSolarIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "SolarAPI",
 type : type,
 data : $("#formSolar").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onSolarSaveComplete(response.responseText, status);
 }
 });
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidSolarIDSave").val($(this).data("solarid"));
 $("#customerName").val($(this).closest("tr").find('td:eq(0)').text());
 $("#customerAddress").val($(this).closest("tr").find('td:eq(1)').text());
 $("#capacity").val($(this).closest("tr").find('td:eq(2)').text());
 $("#noOfSolarPanels").val($(this).closest("tr").find('td:eq(3)').text());
 $("#type").val($(this).closest("tr").find('td:eq(4)').text());
});

//DELETE==========================================================
$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "SolarAPI",
 type : "DELETE",
 data : "solarID=" + $(this).data("solarid"),
 dataType : "text",
 complete : function(response, status)
 {
 onSolarDeleteComplete(response.responseText, status);
 }
 });
});

// CLIENT-MODEL================================================================
function validateSolarForm()
{
	
//cusName
if ($("#customerName").val().trim() == "")
 {
 return "Insert Customer Name.";
 }

// cusAddress
if ($("#customerAddress").val().trim() == "")
 {
 return "Insert Customer Address.";
 } 

// capacity
if ($("#capacity").val().trim() == "")
 {
 return "Insert capacity.";
 }

// noOfSolarPanels
if ($("#noOfSolarPanels").val().trim() == "")
 {
 return "Insert number Of Solar Panels.";
 }
  
 // type
if ($("#type").val().trim() == "")
 {
 return "Insert solar type.";
 }
 
return true;
}

function onSolarSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divCusGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 
$("#hidSolarIDSave").val("");
 $("#formSolar")[0].reset();
}

function onSolarDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divSolarGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}
