import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection
{
  public static Connection getConnection()throws Exception
  {
     Class.forName("oracle.jdbc.driver.OracleDriver");
     return DriverManager.getConnection("jdbc:oracle:thin:@ipoghome:1521:xe","scott","tiger");
   }
}
