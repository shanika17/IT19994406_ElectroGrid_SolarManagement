package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


	@WebServlet("/SolarAPI")
	public class SolarAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Solar solarObj = new Solar();

	public SolarAPI() {
	super();
	}

	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request)
		{
		 Map<String, String> map = new HashMap<String, String>();
		try
		 {
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		 String queryString = scanner.hasNext() ?
		 scanner.useDelimiter("\\A").next() : "";
		 scanner.close();
		 String[] params = queryString.split("&");
		 for (String param : params)
		 { 
			 String[] p = param.split("=");
			 map.put(p[0], p[1]);
			 }
			 }
			catch (Exception e)
			 {
			 }
			return map;
			}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String output = solarObj.insertSolarDetails(request.getParameter("customerName"),
					request.getParameter("customerAddress"),
					request.getParameter("capacity"),
			        request.getParameter("noOfSolarPanels"),
			        request.getParameter("type"));
					response.getWriter().write(output); 

		}

		protected void doPut(HttpServletRequest request, HttpServletResponse response)
				 throws ServletException, IOException
				{
				 Map paras = getParasMap(request);
				 String output = solarObj.updateSolarDetails(paras.get("hidSolarIDSave").toString(),
				 paras.get("customerName").toString(),
				 paras.get("customerAddress").toString(),
				 paras.get("capacity").toString(),
				 paras.get("noOfSolarPanels").toString(),
				 paras.get("type").toString());
				 response.getWriter().write(output);
				} 

		protected void doDelete(HttpServletRequest request, HttpServletResponse response)
				 throws ServletException, IOException
				{
				 Map paras = getParasMap(request);
				 String output = solarObj.deleteSolarDetails(paras.get("solarID").toString());
				response.getWriter().write(output);
				}

	}
