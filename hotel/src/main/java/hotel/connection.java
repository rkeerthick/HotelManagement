package hotel;

import java.sql.*;

public class connection {
	public static Connection getConnect() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelData", "root", "#Keer2003#");
        return con;
	}
}
