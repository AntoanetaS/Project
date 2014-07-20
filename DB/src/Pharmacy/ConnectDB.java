package Pharmacy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Connect to database of pharmacy
public class ConnectDB  {

	static Connection conn = null;
	
	public ConnectDB ()
	{
		getConnection();

	}
	
	private void getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
            String username = "root";
            String Password = "";//Paste pass here
            String connectionUrl="jdbc:mysql://localhost:3306/pharmacy";
            conn = DriverManager.getConnection(connectionUrl,username,Password);
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
