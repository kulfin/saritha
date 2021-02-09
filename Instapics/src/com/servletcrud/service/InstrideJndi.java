package com.servletcrud.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class InstrideJndi
{
    private DataSource dataSource = null;
    private Context initialContext = null;
    private Context environmentContext = null;

    public InstrideJndi(String className)
    {
        try
        {
            this.initialContext = new InitialContext();
            this.environmentContext = (Context) initialContext.lookup("java:/comp/env");
            this.dataSource = (DataSource) environmentContext.lookup("jdbc/pic_uploader");
        }
        catch(NamingException error)
        {
            System.out.println("Error With JNDI Lookup -  " + className + " - " + error.getMessage());
            error.printStackTrace();
        }
    }

    public DataSource getDataSource()
    {
        return this.dataSource;
    }

    public void closeConnection()
    {
        if (initialContext != null) try{initialContext.close();} catch(NamingException ignore) {}
        if (environmentContext != null) try{environmentContext.close();} catch(NamingException ignore) {}
    }
}