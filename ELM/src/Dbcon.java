import java.sql.*;

public class Dbcon {

	private String name = "com.mysql.jdbc.Driver";
	public Connection con;
	
	// Database Connection
	public Connection connect() {

		try {

			Class.forName(name);
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/elm", "root", "4287");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}
