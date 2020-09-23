package com.vick.designpattern.action.template;

import java.beans.IntrospectionException;
import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetExtractor<T> {

    /**
     * Implementations must implement this method to process the entire ResultSet.
     *
     * @param rs the ResultSet to extract data from. Implementations should
     *           not close this: it will be closed by the calling JdbcTemplate.
     * @return an arbitrary result object, or {@code null} if none
     * (the extractor will typically be stateful in the latter case).
     * @throws SQLException if an SQLException is encountered getting column
     *                      values or navigating (that is, there's no need to catch SQLException)
     *                      throws DataAccessException in case of custom exceptions
     */
    T extractData(ResultSet rs) throws SQLException, IntrospectionException;

}
