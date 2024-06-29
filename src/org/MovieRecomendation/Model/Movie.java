package org.MovieRecomendation.Model;
import java.sql.*;
import java.time.LocalDate;
public class Movie extends AdminModel{

	private int mid;
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMtittle() {
		return mtittle;
	}
	public void setMtittle(String mtittle) {
		this.mtittle = mtittle;
	}
	
	private String mtittle;
  private String Drel;
  
	
	public String getDrel() {
	return Drel;
}
public void setDrel(String drel) {
	Drel = drel;
}
	public Movie()
	{
		
	}
	public Movie(int mid, String mtittle,String drel)
	{
		this.mid=mid;
		this.mtittle=mtittle;
		this.Drel=drel;
	}
   
}
