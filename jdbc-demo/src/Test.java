import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
//			1. connect to Database
//			"jdbc:mysql://IPAddress:port/dbName","userName","password"
			Connection connectionss = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc_demo","root","huzaifa656");
			System.out.println(connectionss);
			
//			2. write and Execute SQL queries
//			create idle to run query in java
			 Statement stmt = connectionss.createStatement();//idle	         
	         stmt.executeUpdate("INSERT INTO name (first_name, last_name) VALUES('Huzaifa', 'Ansari')");
	         
//			3. close Database connection
	            stmt.close();
	            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
