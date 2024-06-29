package org.MovieRecomendation.Model;

public class AdminModel {
	
	private int Adminid;
	public int getAdminid() {
		return Adminid;
	}
	public void setAdminid(int adminid) {
		Adminid = adminid;
	}
	public String getAdminname() {
		return Adminname;
	}
	public void setAdminname(String adminname) {
		Adminname = adminname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String Adminname;
	private String password;
	
	public AdminModel()
	{
		
	}
	public AdminModel(int Adminid, String Adminname,String password)
	{
		this.Adminid=Adminid;
		this.Adminname=Adminname;
		this.password=password;
	}

}
