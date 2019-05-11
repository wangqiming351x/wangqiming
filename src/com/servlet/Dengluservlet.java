package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Dengluservlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");// 设置字符编码
		PrintWriter out = response.getWriter();
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/userdb", "root", "admin");

			Statement sta = (Statement) con.createStatement();
			ResultSet n = sta
					.executeQuery("SELECT * FROM userdb.users where usersname = '" + user
							+ "'");
			while (n.next()) {
				int id = n.getInt(1);
				String name = n.getString(2);
				String pass = n.getString(3);
				if (name.equals(user)) {
					if (pass.equals(password)) {
						request.getRequestDispatcher("Chenggong").forward(
								request, response);// 转发到成功页面
					} else {
						response.sendRedirect("Denglu.html");// 密码错误,重定向到登录页面
					}

				} else {
					out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
					out.println("<HTML>");
					out.println("  <HEAD><meta charset='UTF-8'><title>登录页面</title></HEAD>");
					out.println("  <BODY>");
					out.println("没有此用户");
					// out.println("<a href='Zhuce.html'>去注册</a><br/>");
					out.println("  </BODY>");
					out.println("</HTML>");
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.print("没找到");

		} catch (SQLException sqle) {

			System.out.print("连接异常");
		}

		out.flush();
		out.close();
	}

}
