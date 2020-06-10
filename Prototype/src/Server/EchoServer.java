// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package Server;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import logic.Employee;
import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */

public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
	ArrayList<String> cmd= new ArrayList<String>(); 
	Connection conn;
  /**
   * The default port to listen on.
   */
  //final public static int DEFAULT_PORT = 5555;
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   * 
   */
 public static Employee  Employee=new Employee(null, null, null, null, null, null);

  public EchoServer(int port) 
  {
    super(port);
  }

  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   * @param 
 * @throws SQLException 
   */
  public void handleMessageFromClient  (Object msg, ConnectionToClient client)
  {
	  	cmd=(ArrayList<String>) msg;
	  	int flag=0;
	  	
	    System.out.println("Message received: " + msg + " from " + client);
	    if(cmd.get(0).equals("getEmployeeData")) {
	    	String data=getEmployeeData(msg);
	    	
	    		this.sendToRequistedClient(data, client);
	    }
	    if(cmd.get(0).equals("LogIn")) {
	    	flag=login(cmd);
	    	if(flag==1)
	    		this.sendToRequistedClient(msg, client);
	    }
	    if(cmd.get(0).equals("LogOut")) {
	    	flag=logout(cmd);
	    	if(flag==1)this.sendToAllClients("Save");
	    }
	   
	  }
    
  private String getEmployeeData(Object msg) {
	// TODO Auto-generated method stub
	  String res="";
	  try {

		  	cmd=(ArrayList<String>) msg;
	        String str=("SELECT * FROM user WHERE username= (?)  ; ");
	        PreparedStatement st= conn.prepareStatement(str);
	        st.setString(1, cmd.get(1));
	      
	        ResultSet rs=st.executeQuery();
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int columnsNumber = rsmd.getColumnCount();
	        
	        if(rs.next()) {
	        	for(int i=1;i<columnsNumber;i++) {
	        	 res+=rs.getString(i);
	        	 } 
	        }
			else
			{
		
				System.out.println("user is logged in please log out ");
return "not found ";
	  }}catch (Exception e) {
		e.printStackTrace();
	}
	  System.out.println(res);
	  return res;
			
}

/**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()//change
  {try {
	conn = DriverManager.getConnection("jdbc:mysql://localhost/test?serverTimezone=IST","root","Aa123456");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
    System.out.println ("Server listening for connections on port " + getPort());


  }
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println ("Server has stopped listening for connections.");
  }  
  
  
  public int  login(ArrayList<String> cmd){
		 try {
		     //   if(loggedin(cmd.get(1))==0) {
			 if(checkUser(cmd.get(1), cmd.get(2))==1)
		        {
				
		     String   str=("UPDATE user SET connected=1 WHERE username= (?) AND password= (?) ; ");
		     PreparedStatement st= conn.prepareStatement(str);
			       st= conn.prepareStatement(str);
			        st.setString(1, cmd.get(1));
			        st.setString(2, cmd.get(2));
			     st.executeUpdate();
		        	return 1;
		        }
				else
				{
					
					System.out.println("Employee ID  NOT Found");
	 return 0;
				}
		 //       }
			}catch(SQLException e) {
				e.printStackTrace();
				}
			return 0;
	  }
  public int  logout(ArrayList<String> cmd){
		 try {
		        if(checkUser(cmd.get(1), cmd.get(2))==1)
		        {
		        	String  str=("UPDATE users SET connected=0 WHERE username= (?) AND password= (?) ; ");
		        PreparedStatement st= conn.prepareStatement(str);
		    
			       st= conn.prepareStatement(str);
			        st.setString(1, cmd.get(1));
			        st.setString(2, cmd.get(2));
			     st.executeUpdate();
		        	return 1;
		         }
				else
				{
					
					System.out.println("Employee ID  NOT Found");
	 return 0;
				}

			}catch(SQLException e) {e.printStackTrace();};
			return 0;
	  }
  
  
  public int  checkUser(String Username,String Password){
		 try {
		        
		        String str=("SELECT username,password FROM user WHERE username= (?) AND password= (?) ; ");
		        PreparedStatement st= conn.prepareStatement(str);
		        st.setString(1, Username);
		        st.setString(2, Password);
		        ResultSet rs=st.executeQuery();
		        if(rs.next()) {
		        	//dispose();
		        	System.out.println("Employee ID Found");
		      return 1;
		        }
				else
				{
					
					System.out.println("Employee ID  NOT Found");
	 return 0;
				}

			}catch(SQLException e) {e.printStackTrace();};
			return 0;
	  }

  public int  loggedin(String Username){
		 try {
		        
		        String str=("SELECT connected FROM user WHERE username= (?)  ; ");
		        PreparedStatement st= conn.prepareStatement(str);
		        st.setString(1, Username);
		       
		        ResultSet rs=st.executeQuery();
		        ResultSetMetaData rsmd = rs.getMetaData();
		        int columnsNumber = rsmd.getColumnCount();
		        
		        if(rs.next()) {
		        	 String columnValue = rs.getString(1);
		        	 if(columnValue=="1")return 0;
		        	 else {  System.out.print("the result is   "+ columnValue );
		                return 1;
		        	 } 
		        }
				else
				{
					
					System.out.println("user is logged in please log out ");
	 return 0;
				}

			}catch(SQLException e) {e.printStackTrace();};
			return 0;
	  }
}


//End of EchoServer class
