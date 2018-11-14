package cn.demo.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {

	private static DataSource dataSource;

	static {
		dataSource = new ComboPooledDataSource("upload");
	}

	public static Connection getConnection() throws SQLException {
		System.out.println(dataSource);
		return dataSource.getConnection();
	}


	public static void release(Connection conn){
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
