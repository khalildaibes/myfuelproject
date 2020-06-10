package gui;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import logic.Employee;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class ConnectDB {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) 
	{
		try 
		{
			Object obj= Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Driver definition succeed");
        } catch (Exception ex) {
        	/* handle the error*/
        	 System.out.println("Driver definition failed");
        	 }
        
        try 
        {
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test?serverTimezone=IST","root","Aa123456");
            System.out.println("SQL connection succeed");
            
            Employee emp= new Employee("12345","khalil","Cohen","askhs","hhhH","email1");
            updateEmployee(conn,emp);
            Scanner sc = new Scanner(System.in); 
        } catch (SQLException ex) 
     	    {/* handle any errors*/
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            }
   	}

	public static void updateEmployee(Connection con,Employee emp) throws SQLException
	{

		Statement stmt;
		try 
		{
			stmt = con.createStatement();
			// stmt.executeUpdate("UPDATE employeesdb SET FirstName = '"+emp.getFName()+"' LastName = '"+emp.getLName()+"', Email = '"+emp.getEmail()+"', Company='"+emp.getComp()+"', Role='"+emp.getRole()+"' WHERE EmployeeId='"+emp.getId()+"';");
			// stmt.execute("INSERT INTO employee (EmployeeId,FirstName,LastName,Email,Company,Role) VALUES ('12345','khalil' ,'asdd' ,'asddf' ,'fdg' ,'manager');");


			 System.out.println("ok");
		} catch (SQLException e) {e.printStackTrace();}
		
	}
	
	
	public static void updateEmployee2(Connection con,Employee emp) throws SQLException
	{

		Statement stmt;
		try 
		{
			stmt = con.createStatement();
			stmt.executeUpdate("UPDATE employeesdb SET FirstName = '"+emp.getFName()+"' LastName = '"+emp.getLName()+"', Email = '"+emp.getEmail()+"', Company='"+emp.getComp()+"', Role='"+emp.getRole()+"' WHERE EmployeeId='"+emp.getId()+"';");
			// stmt.execute("INSERT INTO employeesdb (EmployeeId,FirstName,LastName,Email,Company,Role) VALUES ('12345','khalil' ,'asdd' ,'asddf' ,'fdg' ,'manager');");


			 System.out.println("ok");
		} catch (SQLException e) {e.printStackTrace();}
		
	}

}
