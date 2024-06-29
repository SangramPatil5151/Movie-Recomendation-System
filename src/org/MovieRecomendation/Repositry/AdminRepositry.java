package org.MovieRecomendation.Repositry;
import java.util.*;
import org.MovieRecomendation.Config.DBHelper;
import org.MovieRecomendation.Model.AdminModel;

public class AdminRepositry extends DBHelper{
	
	private List<AdminModel> list= new ArrayList<AdminModel>();	
	public boolean  isAddAdmin(int Adminid, String Adminname,String Adminpassword) 
	{
		try
		{
			stmt =conn.prepareStatement("select * from admin where aid=? and name=? and password=?");
			stmt.setInt(1, Adminid);
			stmt.setString(2, Adminname);
			stmt.setString(3, Adminpassword);

			rs=stmt.executeQuery();
			while(rs.next())
			{
//				AdminModel model = new AdminModel();
//				model.setAdminid(rs.getInt(1));
//				model.setAdminname(rs.getString(2));
//				model.setPassword(rs.getString(3));
//				list.add(model);
				return true;
			}
//			return list.size()>0? list:null;
			
		}
		catch(Exception ex)
		{
			System.out.println("Error :"+ex);
//			return null;
		}
		return false;
	}

}
