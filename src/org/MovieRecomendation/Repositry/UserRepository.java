package org.MovieRecomendation.Repositry;

import java.util.ArrayList;

import org.MovieRecomendation.Config.DBHelper;
import org.MovieRecomendation.Model.User;

public class UserRepository  extends DBHelper
{
	ArrayList<User> al=new ArrayList();
   public boolean isAddUser(String uname,String email,String contact)
   {
     try
     {
    	stmt=conn.prepareStatement("insert into user values(0,?,?,?)");
    	//stmt.setInt(1, uid);
    	stmt.setString(1, uname);
    	stmt.setString(2, email);
    	stmt.setString(3, contact);
    	int v=stmt.executeUpdate();
    	return  v>0?true:false;
     }
     catch(Exception ex)
     {
    	 System.out.println("exception is:"+ex);
    	 return false;
     }
	
	   
   }
   
   public ArrayList<User>  displayUsers()
   {
	   try
	   {
		    stmt=conn.prepareStatement("select *from User");
		    rs=stmt.executeQuery();
		    while(rs.next())
		    {
		    	User u=new User();
		    	u.setUid(rs.getInt(1));
		    	u.setUname(rs.getString(2));
		    	u.setEmail(rs.getString(3));
		    	u.setContact(rs.getString(4));
		    	al.add(u);
		    	
		    }
		    return al.size()>0?al:null;
	   }
	   catch(Exception ex)
	   {
		   System.out.println("error is:"+ex);
		   return null;
	   }
	   
   }
   public boolean UpdateUser(int uid,String uname)
   {
	   try
	   {
		   stmt=conn.prepareStatement("update User set uname=? where uid=?");
		   stmt.setString(1, uname);
		   stmt.setInt(2, uid);
		   int r=stmt.executeUpdate();
		   return r>0;
	   }
	   catch(Exception ex)
	   {
		   System.out.println("error is:"+ex);
		   return false;
	   }
   }  
   
	   public boolean isDelete(int uid)
	   {
		   try
		   {
			   stmt=conn.prepareStatement("delete from user where uid=?");
			   stmt.setInt(1, uid);
			   int ra=stmt.executeUpdate();
			   return ra>0?true:false;
		   }
		   catch(Exception ex)
		   {
			   System.out.println("Error is:"+ex);
			   return false;
		   }
	   }
   }
   
   
   
   
   
   
   
   
   
   
   
   

