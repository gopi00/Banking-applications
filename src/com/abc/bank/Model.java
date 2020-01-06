package com.abc.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
	private String name;
	private int accno;
	private int balance;
	private String custid;
	private String pwd;
	private String email;
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet res=null;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	Model()
	{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "system", "system");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean login()
	{
		try {
			pstmt=con.prepareStatement("SELECT * FROM SWISS_BANK WHERE CUSTID=? AND PWD=?");
			pstmt.setString(1, custid);
			pstmt.setString(2,pwd);
			res=pstmt.executeQuery();

			if(res.next())
			{
				accno=res.getInt("ACCNO");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(res!=null)
					res.close();
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			}
			catch(SQLException e) {
			}
		}
		return false;
	}	
	public boolean CheckBalance()
	{
		try {
			
			pstmt=con.prepareStatement("SELECT * FROM SWISS_BANK WHERE ACCNO=?");
			pstmt.setInt(1, accno);
			res=pstmt.executeQuery();
			if(res.next())
			{
				balance=res.getInt("BALANCE");
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(res!=null)
					res.close();
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	
	
	
	
	
}
