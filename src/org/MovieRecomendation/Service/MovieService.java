package org.MovieRecomendation.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.MovieRecomendation.Model.Geners;
import org.MovieRecomendation.Model.Movie;
import org.MovieRecomendation.Model.RatingModel;
import org.MovieRecomendation.Model.User;
//import org.MovieRecomendation.Repositry.Arraylist;
import org.MovieRecomendation.Repositry.MovieRerpositry;
//import org.MovieRecomendation.Repositry.geners;

public class MovieService {
	MovieRerpositry mrepo=new MovieRerpositry();
	
	public boolean isAddMovie(String mtittle, int aid)
	{
		return mrepo.isAddMovie(mtittle,aid);
		
	}
	
	public int getAdminID()
	{
		return mrepo.getAdminID();
	}
	
	public ArrayList<Movie> displayMovie()
	{
		return mrepo.displayMovie();			
	}
	
	public boolean isUpdate(Movie model)
	{
		return mrepo.isUpdate(model);
	}
	public boolean isDelete(int mid)
	{
		return mrepo.isDelete(mid);
	}

	public boolean isAddGeneres(String gtitle)
	{
		return mrepo.isAddGeneres(gtitle);
	}
	public ArrayList<Geners> displaygeners()
	{
		return mrepo.displaygeners();
	}
	public boolean assignMovie(int u,String moviename)
	{
		return mrepo.assignMovie(u,moviename);
	}
   public boolean isaddMovieRatingReview(int rating,String Review,int userid,int movieid)
   {
	   return mrepo.isaddMovieRatingReview(rating,Review,userid,movieid);
   }

    public List<String> getAllReview() 
    {
	   return mrepo.getAllReview();
    }

	public List<RatingModel> getAllHitsMovies() 
	{
		return mrepo.getAllHitsMovie();
	}

	public LinkedHashMap<String, Float> GetOverallRating() 
	{
		
		return mrepo.getOverallRating();
	}

	public List<Movie> getAllTrendingMovies() 
	{
		
		return mrepo.getAllTrendingMovie();
	}
	
}
