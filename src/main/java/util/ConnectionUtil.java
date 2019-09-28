package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {
	public static Connection getConnection() {
		String driverClassName = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://trainingdb.ck1ayq0lncmp.ap-south-1.rds.amazonaws.com/jayakrishna_db";
		String username = "jayakrishna";
		String password = "jayakrishna";

		Connection con = null;
		try {
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("unable to load the driver class");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("unable to load DB exception");
		}
		return con;
	}

	public static void close(Connection con, PreparedStatement pst, ResultSet rs) {

		try {
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void close(Connection con, PreparedStatement pst) {

		try {
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
