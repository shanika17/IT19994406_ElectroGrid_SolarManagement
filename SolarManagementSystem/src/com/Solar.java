package com;
import java.sql.*;

public class Solar {
	
	public Connection connect()
	{
			Connection con = null;
			try
			{
			
				Class.forName("com.mysql.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "");
			
				//For testing
				System.out.print("Successfully connected");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return con;
	}
	
public String insertSolarDetails(String customerName, String customerAddress, String capacity, String noOfSolarPanels, String type)
		{
			String output = "";
			try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into solarpower (`solarID`,`customerName`,`customerAddress`,`capacity`,`noOfSolarPanels`,`type`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customerName); 
			preparedStmt.setString(3, customerAddress); 
			preparedStmt.setDouble(4, Double.parseDouble(capacity)); 
			preparedStmt.setInt(5, Integer.parseInt(noOfSolarPanels));
			preparedStmt.setString(6, type);


			// execute the statement
			preparedStmt.execute();
			con.close();
			String newSolar = readSolarDetails();
			output = "{\"status\":\"success\", \"data\": \"" +newSolar + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the solar details.\"}";
			System.err.println(e.getMessage());
		}
			
		return output;
	}

public String readSolarDetails()
{
		String output = "";
		try
		{
				Connection con = connect();
					if (con == null)
					{
						return "Error while connecting to the database for reading.";
					}

					//Prepare the HTML table to be displayed
					output = "<table border='3'>"
							+ "<tr><th>Customer Name</th>"
							+"<th>Customer Address</th>"
							+ "<th>Capacity(kW)</th>"
							+ "<th>No of Solar Panels</th>"
							+ "<th>Type</th>"
							+ "<th>Update</th><th>Remove</th></tr>";
					
					String query = "select * from solarpower";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);

					// iterate through the rows in the result set
					while (rs.next())
					{
						String solarID = Integer.toString(rs.getInt("solarID"));
						String customerName = rs.getString("customerName");
						String customerAddress = rs.getString("customerAddress");
						String capacity = Double.toString(rs.getDouble("capacity")); 
						String noOfSolarPanels = Integer.toString(rs.getInt("noOfSolarPanels"));
						String type = rs.getString("type");



						// Add a row into the html table
						output += "<tr><td><input solarID='hidSolarIDUpdate'name='hidSolarIDUpdate'type='hidden' value='" + solarID  + "'>"+ customerName  + "</td>";
						output += "<td>" + customerAddress + "</td>";
						output += "<td>" + capacity + "</td>";
						output += "<td>" + noOfSolarPanels + "</td>";
						output += "<td>" + type + "</td>";


						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-solarid='" + solarID + "'></td>"
								+"<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-solarid='" + solarID + "'></td></tr>"; 
					}
					con.close();


					// Complete the html table
					output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the solar details.";
				System.err.println(e.getMessage());
			}
			return output;
		}

	public String deleteSolarDetails(String solarID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting.";
			}


			// create a prepared statement
			String query = "delete from solarpower where solarID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(solarID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			//output = "Deleted successfully";
			String newSolar = readSolarDetails();
			output = "{\"status\":\"success\", \"data\": \"" +newSolar + "\"}";
			}
		catch (Exception e)
		{
			//output = "Error while deleting the customer.";
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the solar details.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	//method to update solar panel details in DB
	public String updateSolarDetails(String solarID, String customerName, String customerAddress, String capacity, String noOfSolarPanels, String type)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}
				
			// create a prepared statement
			String query = "UPDATE solarpower SET customerName=?,customerAddress=?,capacity=?,noOfSolarPanels=?,type=? WHERE solarID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, customerName); 
			preparedStmt.setString(2, customerAddress); 
			preparedStmt.setDouble(3, Double.parseDouble(capacity)); 
			preparedStmt.setInt(4, Integer.parseInt(noOfSolarPanels));
			preparedStmt.setString(5, type);
			preparedStmt.setInt(6, Integer.parseInt(solarID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Updated solar details successfully";
			String newSolar = readSolarDetails();
			output = "{\"status\":\"success\", \"data\": \"" +newSolar + "\"}"; }
		catch (Exception e)
		{
			//output = "Error while updating the Solar Details.";
			output = "{\"status\":\"error\", \"data\":\"Error while updating the solar details.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}


}

