package org.MovieRecomendation.Model;

public class RatingModel extends Movie 
{ 
	private int rid;
	private String Review;
	private int Rating;
	private String Movie;
	public String getMovie() {
		return Movie;
	}
	public void setMovie(String movie) {
		Movie = movie;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getReview() {
		return Review;
	}
	public void setReview(String review) {
		Review = review;
	}
	public int getRating() {
		return Rating;
	}
	public void setRating(int rating) {
		Rating = rating;
	}
 

}
