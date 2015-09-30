
package com.bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.connect.dbConnect;

public class registerBean
{
    private String username;
    private String password;
    private String amount;
    private String address;
    private String phone;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getAddress() {
        return address;
    }

    public String getAmount() {
        return amount;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

   

    public String insert()
    {
        try
        {
            Connection con=dbConnect.getConnect();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from record where username='"+username+"'");
            if(rs.next())
            {
            	return "error";
            }
                        
            int x=st.executeUpdate("insert into record values('"+username+"','"+password+"',"+amount+",'"+address+"','"+phone+"')");

            if(x>0)
            {
                return "register";
            }
            else
            	return "error.jsp";
           
           }catch(Exception e)
           {
               return "error.jsp";
           }
    }
}
