package com.vick.designpattern.action.template;

import java.beans.IntrospectionException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vick Zhang
 * @date 2020/9/3
 */
public class RowMapperResultSetExtractor<T> implements ResultSetExtractor<List<T>> {

    private final RowMapper<T> rowMapper;

    private final int rowsExpected;


    /**
     * Create a new RowMapperResultSetExtractor.
     *
     * @param rowMapper the RowMapper which creates an object for each row
     */
    public RowMapperResultSetExtractor(RowMapper<T> rowMapper) {
        this(rowMapper, 0);
    }

    /**
     * Create a new RowMapperResultSetExtractor.
     *
     * @param rowMapper    the RowMapper which creates an object for each row
     * @param rowsExpected the number of expected rows
     *                     (just used for optimized collection handling)
     */
    public RowMapperResultSetExtractor(RowMapper<T> rowMapper, int rowsExpected) {
        this.rowMapper = rowMapper;
        this.rowsExpected = rowsExpected;
    }


    @Override
    public List<T> extractData(ResultSet rs) throws SQLException, IntrospectionException {
        List<T> results = (this.rowsExpected > 0 ? new ArrayList<>(this.rowsExpected) : new ArrayList<>());
        int rowNum = 0;
        while (rs.next()) {
            results.add(this.rowMapper.mapRow(rs, rowNum++));
        }
        return results;
    }

}
