import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Q1_StoreDataToSQL {
	
	static Connection connector; 
    static Scanner sc = new Scanner(System.in);  // Single Scanner for entire program

	public static void main(String[] args) {
		try {
            // 1. Connect to Database
			connector = DriverManager.getConnection(
					"jdbc:mysql://localhost/jdbc_demo",
					"root",
					"huzaifa656"
			); 
			
            System.out.println("DATABASE Connected ✅");
            
//            System.out.println("0 to continue & 1 to exit");
//            Scanner sc = new Scanner(System.in);
            
            int n = 0;
                      
            while(true) {
            
            System.out.println("\n========= MENU =========");
            System.out.println("1.Insert data");
            System.out.println("2.Check data");
            System.out.println("3.Update data");
            System.out.println("4.Delete data");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");
            
            n = sc.nextInt();
            sc.nextLine(); // consume newline
            
            switch (n) {
			case 1:
//	            INSERTING/CREATE method 'C'
	            insertUser();
				break;
			case 2:
//	            READ/CHECK method 'R'
	            checkUser();
				break;
			case 3:
//	            UPDATE/MODIDY method 'U'
	            updateUser();
				break;
			case 4:      
//	            DELETE/DROP method 'D'
	            deleteUser();
				break;
            case 5:
                connector.close();
                System.out.println("✅ Connection closed. Exiting...");
                return; // exit program
				
			default:
                System.out.println("⚠ Invalid choice! Please try again.");
			}
          }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            sc.close();  // Close scanner only when program exits
        }	
	}
	
	public static void insertUser() {
		try {
			System.out.println("---INSERTING DATA---");
			System.out.println("Enter your name: ");
			String name = sc.nextLine();
			
			System.out.println("Enter your email: ");
			String emailId = sc.nextLine();
			
			System.out.println("Enter you phone no: ");
			String phoneNo = sc.nextLine();
			
			String sql = "INSERT INTO name (first_name, email_id, phone_no)"+
	        		"VALUES ('"+name+"', '"+emailId+"', '"+phoneNo+"')";
			

			Statement statementIdle = connector.createStatement();
			statementIdle.executeUpdate(sql);
	        statementIdle.close();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}        
		
	}
	
	private static void checkUser() {
	    try {
			System.out.println("---DISPLAYING DATA---");

	        String sql = "SELECT * FROM name";
	        Statement stmt = connector.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);

	        while (rs.next()) {
	            System.out.println(
	                "Name: " + rs.getString("first_name") +
	                ", Email: " + rs.getString("email_id") +
	                ", Phone: " + rs.getString("phone_no")
	            );
	        }

	        rs.close();
	        stmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private static void updateUser() {
	    try {
			System.out.println("---UPDATING DATA---");
	        
	        System.out.print("Enter email of user you want to update: ");
	        String emailId = sc.nextLine();
	        
	    	System.out.print("Enter new name: ");
	        String name = sc.nextLine();

	        System.out.print("Enter new phone number: ");
	        String phoneNo = sc.nextLine();

	        
	        String sql = "UPDATE name SET first_name = '" + name + 
	                "', phone_no = '" + phoneNo + 
	                "' WHERE email_id = '" + emailId + "'";

	        Statement stmt = connector.createStatement();
	        int rowsUpdated = stmt.executeUpdate(sql);

	        if (rowsUpdated > 0) {	
	        	System.out.println("✅ User updated successfully!");
	        } else {
	        	System.out.println("⚠ No user found with that email.");
	        }

	        stmt.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	private static void deleteUser() {
		try {
			System.out.println("---DELETING DATA---");

			System.out.println("Enter your email: ");
			String emailId = sc.nextLine();
			
			String sql = "DELETE FROM name WHERE email_id = '" + emailId + "'";

			
			Statement statementIdle = connector.createStatement();
			statementIdle.executeUpdate(sql);
	        statementIdle.close();	
			
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}

}
