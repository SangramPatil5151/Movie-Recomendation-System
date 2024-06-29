package org.MovieRecomendation.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PathHelper {
	public static FileInputStream fin=null;
	public static File f=null;
	static
	{
		  f= new File(".");
		 String path=(f.getAbsolutePath().substring(0,f.getAbsolutePath().length()-1))+"src\\db.properties";
		 try {
			 fin= new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
