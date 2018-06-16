package ua.danit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author others
 * @author Alex Ignatenko
 *
 */

public class ConnectionToDB
{
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/tinder";
	private static final String USERNAME = "postgres";
	private static final String USER_PASS = "Partizan1979";

	/**
	 *
	 * @return Connection as result of...
	 */
	protected static Connection getConnection(){

		Connection connection = null;

		try
		{
			connection = DriverManager.getConnection(DB_URL, USERNAME, USER_PASS);
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}

		return connection;
	}
}
