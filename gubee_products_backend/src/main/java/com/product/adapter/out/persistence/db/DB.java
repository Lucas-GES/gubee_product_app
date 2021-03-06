package com.product.adapter.out.persistence.db;

import com.product.adapter.out.persistence.db.exceptions.DbException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DB implements Database{

    private static Connection conn = null;

    @Override
    public Connection connection(){
        if(conn == null){
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
               throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    public Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("h2.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }
}
