package dbconn;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection
{
  public Connection getConnection()throws Exception
  {
	  Connection con=null;
	  try{
     Class.forName("oracle.jdbc.driver.OracleDriver");

     con= DriverManager.getConnection("jdbc:oracle:thin:@ipoghome:1521:orcl","scott","tiger");

  }catch(Exception e){e.printStackTrace();}
  return con;
   }
}
