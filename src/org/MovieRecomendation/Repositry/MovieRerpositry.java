package org.MovieRecomendation.Repositry;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import org.MovieRecomendation.Client.MovieRecomendationClientApplication;
import org.MovieRecomendation.Config.DBHelper;
import org.MovieRecomendation.Model.Geners;
import org.MovieRecomendation.Model.Movie;
import org.MovieRecomendation.Model.RatingModel;
import org.MovieRecomendation.Model.User;

public class MovieRerpositry extends DBHelper{
	
	ArrayList <Movie>al =new ArrayList();
	ArrayList<Geners>ger=new ArrayList();
	public boolean isAddMovie(String mtittle,int aid)
	{
		try
		{
			stmt=conn.prepareStatement("insert into movie values('0',?,curDate(),?)");
			stmt.setString(1, mtittle);
			//stmt.setString(2, drel);
			stmt.setInt(2, aid);
			int value=stmt.executeUpdate();
			return value>0?true:false;
		}
		catch(Exception ex)
		{
			System.out.println("Error"+ex);
			return false;
		}
	}
	public int getAdminID()
	{
		int aid=0;
		try
		{
			stmt=conn.prepareStatement("select aid from admin");
			rs=stmt.executeQuery();
			if(rs.next())
			{
				aid=rs.getInt(1);
			}
			return aid;
		}
		catch(Exception ex)
		{
			System.out.println("Error is :"+ex);
		}
		return 0;
	}
	
	public ArrayList<Movie> displayMovie()
	{
		try
		{
			
			stmt=conn.prepareStatement("select * from movie");
			rs=stmt.executeQuery();
			
			while(rs.next())
			{
				Movie m = new Movie();
				m.setMid(rs.getInt(1));
				m.setMtittle(rs.getString(2));
				m.setDrel(rs.getString(3));
				m.setAdminid(rs.getInt(4));
				
				
				al.add(m);
				
			}		
			return al.size()>0?al:null;
		}
		catch(Exception ex)
		{
			System.out.println("Error is"+ex);
			return null;
		}
		
	}
	
	public boolean isUpdate(Movie model)
	{		
		try
		{
			stmt=conn.prepareStatement("update movie set mtitle=?,drel=? where mid=?");
			stmt.setString(1, model.getMtittle());
			stmt.setString(2, model.getDrel());
			stmt.setInt(3, model.getMid());
			
			
			int value=stmt.executeUpdate();
			if(value>0)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		catch(Exception ex)
		{
			System.out.println("Error is :"+ex);
			return false;
		}
	}
	public boolean isDelete(int mid)
	{
		try
		{
			stmt=conn.prepareStatement("delete from movie where mid=?");
			stmt.setInt(1, mid);
			int ra=stmt.executeUpdate();
			return ra>0?true:false;
		}
		catch(Exception ex)
		{
			System.out.println("exceptio is:"+ex);
			return false;
		}
		
	}
	
	public boolean isAddGeneres(String gtitle)
	{
		try
		{
			stmt=conn.prepareStatement("insert into geners values('0',?)");
			stmt.setString(1, gtitle);
			int v1 =stmt.executeUpdate();
			return v1>0?true:false;
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
			return false;
		}
	}
	
	public ArrayList<Geners> displaygeners()
	{
		try
		{
			stmt=conn.prepareStatement("select * from geners");
			rs=stmt.executeQuery();
			
			while(rs.next())
				{
					 Geners gs=new Geners();
					 gs.setGid(rs.getInt(1));
					 gs.setGtitle(rs.getString(2));
					 
					 ger.add(gs);
				}
			return ger.size()>0?ger:null;
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
			return null;
		}
	}
	
	public boolean assignMovie(int u,String moviename)
	{
		int mid=0;
		try 
		{
			stmt=conn.prepareStatement("select mid from movie where mtitle=?");
			stmt.setString(1, moviename);
			rs=stmt.executeQuery();
			if(rs.next())
			{
				mid=rs.getInt("mid");
			}
			else
			{
				System.out.println("Movie not Found");
			}	
			stmt=conn.prepareStatement("insert into movieuserjoin values(?,?)");
			stmt.setInt(1, mid);
			stmt.setInt(2,u);
			int value=stmt.executeUpdate();
			if(value>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
			return false;
		}	
	}
	public boolean isaddMovieRatingReview(int rating, String review, int userid, int movieid) 
	{
		try
		{
			stmt=conn.prepareStatement("insert into review values('0',?,?,?,?)");
			stmt.setInt(1, rating);
			stmt.setString(2,review);
			stmt.setInt(3, userid);
			stmt.setInt(4, movieid);
			int value=stmt.executeUpdate();
			if(value>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
		}
		return false;
	}
	public List<String> getAllReview() 
	{
		List<String>reviewCounts=new ArrayList<>();
		try
		{
			stmt=conn.prepareStatement(" select m.mtitle,count(r.rid)as total_review from movie m left join review r on m.mid=r.mid group by m.mtitle");
			rs=stmt.executeQuery();
			while(rs.next())
			{
				String mtitle=rs.getString("mtitle");
				int totalReviews=rs.getInt("total_review");
				reviewCounts.add(mtitle+":"+totalReviews+"reviews");
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
		}
		return reviewCounts;
	}
	public List<RatingModel> getAllHitsMovie() 
	{
		List<RatingModel>hitMovies=new ArrayList<>();
		try
		{
		   stmt=conn.prepareStatement("select m.mid,m.mtitle,m.drel,Avg(r.rating)as avg_rating from movie m inner join Review"
		   		+ " r on m.mid=r.mid where r.review='good' group by m.mid,m.mtitle,m.drel having avg_rating>=4");
		   rs=stmt.executeQuery();
		   while(rs.next())
		   {
			   RatingModel r=new RatingModel();
			   r.setMid(rs.getInt("mid"));
			   r.setMtittle(rs.getString("mtitle"));
			   r.setDrel(rs.getString("drel"));
			   r.setRating(rs.getInt("avg_rating"));
			   hitMovies.add(r);
		   }
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
		}
		return hitMovies;
	}
	public LinkedHashMap<String, Float> getOverallRating()
	{
	   LinkedHashMap<String,Float>map=new LinkedHashMap<String,Float>();
	   String movname=null;
	   try
	   {
		   stmt=conn.prepareStatement("select distinct mid from review");
		   rs=stmt.executeQuery();
		   while(rs.next())
		   {
			   int movid=rs.getInt(1);
			   PreparedStatement stmt1=conn.prepareStatement("select mtitle from movie where mid=?");
			   stmt1.setInt(1, movid);
			   ResultSet rs2=stmt1.executeQuery();
			   if(rs2.next())
			   {
				   movname=rs2.getString(1);
			   }
			   PreparedStatement pstmt=conn.prepareStatement("select count(rating),sum(rating)from review where mid=?");
			   pstmt.setInt(1, movid);
			   ResultSet rs1=pstmt.executeQuery();
			   while(rs1.next())
			   {
				   int usercount=rs1.getInt(1);
				   int ratingsum=rs1.getInt(2);
				   float overallrating=(float)(ratingsum)/(float)(usercount);
				   map.put(movname, overallrating);
			   }
			   
		   }
		   LinkedHashMap<String,Float>sortedMap=map.entrySet().stream().sorted(Map.Entry.<String,Float>comparingByValue().reversed())
				   .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e1,LinkedHashMap::new));
		   return sortedMap;
	   }
	   catch(Exception ex)
	   {
		   System.out.println("Error is:"+ex);
	   }
		return null;
	}
	public List<Movie> getAllTrendingMovie() 
	{
		List<Movie>trendingMovies=new ArrayList<>();
		try
		{
			stmt=conn.prepareStatement("Select * from movie where drel IN(2023,2024)");
			rs=stmt.executeQuery();
			while(rs.next())
			{
				Movie m=new Movie();
				m.setMid(rs.getInt("mid"));
				m.setMtittle(rs.getString("mtitle"));
				m.setDrel(rs.getString("drel"));
				m.setAdminid(rs.getInt("aid"));
				trendingMovies.add(m);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error is"+ex);
		}
		return trendingMovies;
	}
}