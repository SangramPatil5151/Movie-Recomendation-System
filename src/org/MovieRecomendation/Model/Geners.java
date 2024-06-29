package org.MovieRecomendation.Model;

public class Geners 
{
private int gid;
public int getGid() {
	return gid;
}
public void setGid(int gid) {
	this.gid = gid;
}
public String getGtitle() {
	return gtitle;
}
public void setGtitle(String gtitle) {
	this.gtitle = gtitle;
}
private String gtitle;


public Geners()
{
	
}
public Geners(int gid,String gtitle)
{
	this.gid=gid;
	this.gtitle=gtitle;
}
}
