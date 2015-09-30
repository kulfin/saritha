
package com.bean;

import java.sql.*;

import com.connect.dbConnect;

public class loginBean 
{
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    
    public String validate()
    {
        try
        {
            Connection con=dbConnect.getConnect();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from record where username='"+username+"' and password='"+password+"'");
        
            if(rs.next())
            {
                return "home.jsp";
            }
            else
            {
                return "index.jsp";
            }
           }catch(Exception e)
           {
               return "error.jsp";
           }
    }
    
}
