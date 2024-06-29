package org.MovieRecomendation.Service;

import java.util.ArrayList;

import org.MovieRecomendation.Model.User;
import org.MovieRecomendation.Repositry.UserRepository;

public class UserService 
{
	UserRepository ur=new UserRepository();
	public boolean isAddUser(String uname,String email,String contact)
	{
		return ur.isAddUser( uname, email, contact);
	}
	 public ArrayList<User>  displayUsers()
	 {
		 return ur.displayUsers();
	 }
	 public boolean UpdateUser(int uid,String uname)
	 {
		 return ur.UpdateUser(uid, uname);
	 }
	 public boolean isDelete(int uid)
	 {
		 return ur.isDelete(uid);
	 }
	
}
