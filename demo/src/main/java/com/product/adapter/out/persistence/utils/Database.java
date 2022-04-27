package com.product.adapter.out.persistence.utils;

import java.sql.Connection;
import java.util.Properties;

public interface Database {
    Connection getConnection();
    Properties loadProperties();
}
