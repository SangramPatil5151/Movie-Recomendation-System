package org.MovieRecomendation.Config;
import java.sql.*;
public class DBHelper {

	protected DBConfig DB=DBConfig.getDBIntance();
	protected Connection conn=DBConfig.getConnection();//drivermanagerclass method
	protected PreparedStatement stmt=DBConfig.getStatement();
	protected ResultSet rs=DBConfig.getResultSet();
}
