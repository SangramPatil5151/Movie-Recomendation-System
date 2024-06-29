package org.MovieRecomendation.Service;

import org.MovieRecomendation.Repositry.AdminRepositry;

public class AdminService {

	AdminRepositry rep=new AdminRepositry();
	
	public boolean  isAddAdmin(int Adminid,String Adminname,String AdminPassword) 
	{
		return rep.isAddAdmin(Adminid,Adminname,AdminPassword);
	}
}
