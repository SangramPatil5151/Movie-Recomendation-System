package org.MovieRecomendation.Model;

public class User extends Movie
{
  private int uid;
  private String uname;
  private String email;
  private String contact;
  
  public User()
  {
	  
  }
  public User(int uid,String uname,String email,String contact)
  {
	  this.uid=uid;
	  this.uname=uname;
	  this.email=email;
	  this.contact=contact;
  }
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}
public String getUname() {
	return uname;
}
public void setUname(String uname) {
	this.uname = uname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
  
  
}
