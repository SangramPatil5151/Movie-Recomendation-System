package org.MovieRecomendation.Client;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.MovieRecomendation.Model.AdminModel;
import org.MovieRecomendation.Model.Geners;
import org.MovieRecomendation.Model.Movie;
import org.MovieRecomendation.Model.RatingModel;
import org.MovieRecomendation.Model.User;
import org.MovieRecomendation.Service.AdminService;
import org.MovieRecomendation.Service.MovieService;
import org.MovieRecomendation.Service.UserService;

public class MovieRecomendationClientApplication 
 {
	
	private static final String file="C:\\Users\\Sangram patil\\eclipse-workspace\\MovieRecomendationSystemApp\\src\\Password.txt"; 
	
	public static int uid;
	public static void main(String[] args) throws SQLException, ParseException 
	{
		
		String c=null;
		AdminService arservice=new AdminService();
		MovieService movservice=new MovieService();
		Movie model = new Movie();
		ArrayList <Movie> mal;
		ArrayList<Movie> mval;
		ArrayList<User> ur;
		ArrayList<Geners> ger;
		UserService us=new UserService();
		Scanner sc=new Scanner(System.in);
		
    	
    	int Achoice;
		int userchoice=0;
		int y;
		boolean b = false;
		do
		{
			System.out.println("1.Admin Menu:");
            System.out.println("2.User Menu:");
            System.out.println("==================================================================");
            System.out.print("Enter your choice: ");
            
            Achoice = sc.nextInt();
            sc.nextLine(); 
            
            if(Achoice==1)
            {
            	

        		System.out.println("Enter Admin Username and password :");
            	
            	String Adminname=sc.nextLine();
            	String Adminpassword=sc.nextLine();
            	
            	boolean isAuthenticated = authenticate(Adminname,Adminpassword);            
    	if(isAuthenticated)
    	{
    		System.out.println("Login successful...");
    		
    		int adminchoice;
    		do
        	{
        		System.out.println("1.Add New Movie");
        		System.out.println("2.Add New Geners");
        		System.out.println("3.Dispaly All geners");
        		System.out.println("4.Display All Movie");
        		System.out.println("5.Update Movie");
        		System.out.println("6.Delete Movie");
        		System.out.println("7.Exit");
        		System.out.println("==================================================================");
        		System.out.println("Enter Your Choice: ");
        		
        	    adminchoice =sc.nextInt();
        		sc.nextLine();
        		String mtitle=null;
        		switch(adminchoice)
        		{
        			case 1:
        				Movie mmodel=new Movie();
        				System.out.println("Enter a Movie Tittle:");
        				String mtittle=sc.nextLine();
//        				System.out.println("Enter a Movie Date of Relese:");
//        				String Drel=sc.nextLine();
        				int aid=movservice.getAdminID();
        				b=movservice.isAddMovie(mtittle,aid);
        				if(b)
        				{
        					System.out.println("Movie Added Sucessfully...");
        				}
        				else
        				{
        					System.out.println("Movie Not Added ...");
        				}
        			break;
        			
        			case 2:
        				System.out.println("enter the generes movie");
        			//	sc.nextLine();
        				String g1=sc.nextLine();
        				b=movservice.isAddGeneres(g1);
        				if(b)
        				{
        					System.out.println("geners Add");
        				}
        				else
        				{
        					System.out.println(" geners not Add");
        				}
        				break;
        				
        			case 3:       				
        				ger = movservice.displaygeners();
        				
        				if(ger!=null)
        				{
        					for(Geners gs:ger)
        					{
        						System.out.println(gs.getGid()+"\t"+gs.getGtitle());
        					}
        				}
        				else
        				{
        					System.out.println("generas is not present:");
        				}
        				break;
        			
        			case 4:
        				mal = movservice.displayMovie();
        				
        				if(mal!=null)
        				{
        					for(Movie m : mal)
        					{
        						System.out.println(m.getMid()+"\t"+m.getMtittle()+"\t"+m.getDrel()+"\t"+m.getAdminid());
        					}
        				}
        				else
        				{
        					System.out.println("No movie present");
        				}
        			
        				
        			break;
        			
        			case 5:
        				boolean uflag= false;
					    mval = movservice.displayMovie();

						if (mval != null) {
							for (Movie m1 : mval) {
								System.out.println(m1.getMid() + "\t" + m1.getMtittle() + "\t" + m1.getDrel());
							}
							System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
						}
						
						System.out.println("Enter mov_id to update:");
						int mov_id=sc.nextInt();
						sc.nextLine();
						for(Movie m : mval)
						{
							if(mov_id==m.getMid())
							{
								uflag=true;
								break;
							}
						}
						if(uflag)
						{
							System.out.println("Enter new movie title");
							 mtitle=sc.nextLine();
							
							System.out.println("Enter new date");
							String date=sc.nextLine();
//							
//							DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
//							LocalDate date1=LocalDate.parse(date,Formatter);
							
							//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
							
//							System.out.println(date1);
							
							model.setMid(mov_id);
							model.setMtittle(mtitle);
							model.setDrel(date);
							
							
							boolean b1 = movservice.isUpdate(model);
							if(b1)
							{
								System.out.println("updated");
							}
							else
							{
								System.out.println("not updated");
							}
							
						}
						else
						{
							System.out.println("Enter valid mov_id");
						}
						break;
        			case 6:
						System.out.println("Enter the movie id that you want to delete=");
						int mid=sc.nextInt();
						b=movservice.isDelete(mid);
						if(b)
						{
							System.out.println("movie deleted ...");
						}
						else
						{
							System.out.println("Cannot delete..");
						}
        				break;
        		}
       
        	}while(adminchoice!=7);

    	}
    	else
    	{
    		System.out.println("Wrong username and password");
    	}
            }
            else
            {
//            	sc.nextLine();
            	System.out.println("*****************************User Menu*********************************");
            	do
            	{
            		System.out.println("1.Add user:");
            		System.out.println("2.Display user:");
            		System.out.println("3.Update user:");
            		System.out.println("4.Delete User");
            		System.out.println("5.show movie List");
            		System.out.println("6: Assign movie to user");
            		System.out.println("7.Add Review And Rating");   
            		System.out.println("8.Show Total Review");
            		System.out.println("9.Show All Hits Movies");
            		System.out.println("10.Top 5 Recommeded Movie");
            		System.out.println("11.show Trending movies");
            		System.out.println("12. Exit");
            		System.out.println("Enter your choice=");
            		userchoice =sc.nextInt();
            		sc.nextLine();
            		
            		switch(userchoice)
            		{
            			case 1:
            				//   User u=new User();
            				//   sc.nextLine();
            				   System.out.println("Enter user name=");
            				   String uname=sc.nextLine();
            				   System.out.println("Enter the email=");
            				   String email=sc.nextLine();
            				   System.out.println("Enter the contact=");
            				   String contact=sc.nextLine();
            				  
            				   b=us.isAddUser(uname, email, contact);
            				   
            				   if(b)
            				   {
            					   System.out.println("User Added SuccessFully..");
            				   }
            				   else
            				   {
            					   System.out.println("User Not Added Succesfully");
            				   }
            				   
            			break;
            			
            			case 2:
            				System.out.println("Displaying users:");
            				ur=us.displayUsers();
            				if(ur!=null)
            				{
            					for(User ue:ur)
            					{
            						System.out.println(ue.getUid()+"\t"+ue.getUname()+"\t"+ue.getEmail()+"\t"+ue.getContact());
            					}
            				}
            				else
            				{
            					System.out.println("User is not present");
            				}
            				
            				
            				
            			break;
            			
            			case 3:
            				System.out.println("Updating user by name=");
            				System.out.println("Enter id on which you want to update=");
            				int ud=sc.nextInt();
            				sc.nextLine();
            				System.out.println("Enter the new user name=");
            				String newname=sc.nextLine();
            				b=us.UpdateUser(ud,newname );
            				if(b)
            				{
            				   System.out.println("user updated sucessfully....");
            				}
            				else
            				{
            					System.out.println("Cannot update");
            				}
            				break;
            				
            			case 4:
            				System.out.println("Enter the user id that you want to delete :");
            				int uid=sc.nextInt();
            				b=us.isDelete(uid);
            				if(b)
            				{
            					System.out.println("User Deleted...");
            				}
            				else
            				{
            					System.out.println("user not delete...");
            				}
            				break;
            			case 5:
            				
            				mal = movservice.displayMovie();
            				
            				if(mal!=null)
            				{
            					for(Movie m : mal)
            					{
            						System.out.println(m.getMid()+"\t"+m.getMtittle()+"\t"+m.getDrel());
            					}
            				}
            				else
            				{
            					System.out.println("No movie present");
            				}
            				break;
            			case 6:
							System.out.println("enter the movie name which you want to watch");
							String moviename = sc.nextLine();
							System.out.println("Enter USer ID:");
							int u=sc.nextInt();
							boolean b1 = movservice.assignMovie(u,moviename);
                          if(b1) {
                        	  System.out.println("movie is assign to user successfully..");
                          }else {
                        	  System.out.println("some problem is there");
                          }
							break;
            			case 7:
            				System.out.println("Add Review And Rating");
            				System.out.println("Enter Rating(up to 5) and Review for Movie:");
            				int rating=sc.nextInt();
            				sc.nextLine();
            				String Review=sc.nextLine();
            				System.out.println("Enter User ID:");
            				int userid=sc.nextInt();
            				System.out.println("Enter Movie ID:");
            				int movieid=sc.nextInt();
            				b=movservice.isaddMovieRatingReview(rating,Review,userid,movieid);
            				if(b)
            				{
            					System.out.println("Review And Rating Added SuccessFully...");
            				}
            				else
            				{
            					System.out.println("Something went wrong...");
            				}
            				break;
            			case 8:
            				System.out.println("Show Total Review");
            				List<String>reviewCounts=movservice.getAllReview();
            				for(String count:reviewCounts)
            				{
            					System.out.println(count);
            				}
            				break;
            			case 9:
            				System.out.println("Show All Hits Movies");
            				List<RatingModel>hitMovies=movservice.getAllHitsMovies();
            				if(hitMovies!=null &&!hitMovies.isEmpty())
            				{
            					System.out.println("All_Time Hit Movies:");
            					for(RatingModel mo:hitMovies)
            					{
            						System.out.println(mo.getMid()+"\t"+mo.getMtittle()+"\t"+mo.getDrel()+"\t"+mo.getRating());
            					}
            				}
            				else
            				{
            					System.out.println("No All_time hits Movies Found");
            				}
            				break;
            			case 10:
            				System.out.println("Top 5 Recommeded Movie");
            				LinkedHashMap<String,Float>map=movservice.GetOverallRating();
            				if(map!=null)
            				{
            					Set<Map.Entry<String,Float>>data=map.entrySet();
            					System.out.println("MovieName\t Overallrating");
            					int count=0;
            					for(Map.Entry<String,Float>d1:data)
            					{
            						System.out.println(d1.getKey()+"\t"+d1.getValue());
            						count++;
            						if(count==5)
            						{
            							break;
            						}
            					}	
            				}		
            				else
                			{
                				System.out.println("There is no such data Present");
                			}
            					
            				break;
            			case 11:
            				System.out.println("Trending Movies");
            				List<Movie>trendingMovies=movservice.getAllTrendingMovies();
            				if(trendingMovies.isEmpty())
            				{
            					
            					System.out.println("No trending movies found for 2023 and 2024.");
            				}
            				else
            				{
            					System.out.println("Id\t title\t Release Date\t Admin Id");
            					for(Movie mo:trendingMovies)
            					{
            						System.out.println(mo.getMid()+"\t"+mo.getMtittle()+"\t"+mo.getDrel()+"\t"+mo.getAdminid());
            					}
            				}
            				break;
            				
            			case 12:
            				System.out.println("Exit from User");
            			break;
            		}
            	
            	}while(userchoice!=12);
            

            }
		}while(Achoice!=3);
	}
	private static Date parseDate1(String date) 
	{
		return null;
	}
	private static boolean authenticate(String Adminname, String Adminpassword)
	{
		BufferedReader br = null;
		try
		{
			br=new BufferedReader(new FileReader(file));
			String line;
			while((line=br.readLine())!=null)
			{
				String []login=line.split(":");
				if(login.length==2)
				{
					String Username=login[0];
					String Password=login[1];
					
					if(Username.equals(Adminname)&&Password.equals(Adminpassword))
					{
						return true;
					}
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error is"+ex);
		}
		finally
		{
			if(br!=null)
			{
				try
				{
					br.close();
				}
				catch(Exception ex)
				{
					System.out.println("Error is"+ex);
				}
				
			}
		}
		return false;
  }
}