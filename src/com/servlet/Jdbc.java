package com.servlet;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Jdbc {

	public void Jdbc() {
	};

	public void print(String user, String password) {
		System.out.print("进来了");
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/userdb", "root", "admin");

			Statement sta = (Statement) con.createStatement();
			int n = sta.executeUpdate("insert users values (id,'" + user
					+ "','" + password + "') ");

			if (n > 0) {

				System.out.print("成功");

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.print("没找到");

		} catch (SQLException sqle) {

			System.out.print("连接异常");
		}

	}
	 public void chaxun(String user, String password) {
		 
	 Connection con = null;
	 try {
	 Class.forName("com.mysql.jdbc.Driver");
	 con = (Connection) DriverManager.getConnection(
	 "jdbc:mysql://127.0.0.1:3306/userdb", "root", "admin");
	
	 Statement sta = (Statement) con.createStatement();
	 ResultSet n = sta.executeQuery("SELECT * FROM userdb.users");
	 while (n.next()) {
	 int id = n.getInt(1);
	 String name = n.getString(2);
	 String pass = n.getString(3);
	if(name.equals(user)&&pass.equals(password)){
		
	}
	 }
	
	 } catch (ClassNotFoundException e) {
	 e.printStackTrace();
	 System.out.print("没找到");
	
	 } catch (SQLException sqle) {
	
	 System.out.print("连接异常");
	 }
	
	
	 }
}
