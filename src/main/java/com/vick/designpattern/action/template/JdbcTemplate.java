package com.vick.designpattern.action.template;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.beans.IntrospectionException;
import java.sql.*;

/**
 * @author Vick Zhang
 * @date 2020/9/3
 */
public class JdbcTemplate {

    private Logger logger = LoggerFactory.getLogger(JdbcTemplate.class);

    public <T> T execute(String sql, Object[] args, RowMapper<T> rowMapper)
            throws SQLException, ClassNotFoundException, IntrospectionException {
        Connection connection = getCustomConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (args != null) {
            for (int i = 1; i <= args.length; i++) {
                Object arg = args[i - 1];
                Class clazz = getObjectClass(arg);
                if (clazz == Integer.TYPE) {
                    preparedStatement.setInt(i, (Integer) arg);
                } else if (clazz == Float.TYPE) {
                    preparedStatement.setFloat(i, (Float) arg);
                } else if (clazz == Double.TYPE) {
                    preparedStatement.setDouble(i, (Double) arg);
                } else if (clazz == Boolean.TYPE) {
                    preparedStatement.setBoolean(i, (Boolean) arg);
                } else {
                    preparedStatement.setString(i, (String) arg);
                }
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetExtractor<T> resultSetExtractor = new RowMapperResultSetExtractor(rowMapper);
        try {
            return resultSetExtractor.extractData(resultSet);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }


    private Connection getCustomConnection() throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://139.196.165.243:3306/vick_test";
        String user = "root";
        String password = "success";
        return getConnection(driver, url, user, password);
    }

    private Connection getConnection(String driver, String url, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }

    private Class getObjectClass(Object obj) {
        Class cs = null;
        if (obj != null && !obj.equals("")) {
            if (obj instanceof Integer) {
                cs = Integer.TYPE;
            } else if (obj instanceof Float) {
                cs = Float.TYPE;
            } else if (obj instanceof Double) {
                cs = Double.TYPE;
            } else if (obj instanceof Boolean) {
                cs = Boolean.TYPE;
            } else {
                cs = String.class;
            }
        }
        return cs;
    }

}
