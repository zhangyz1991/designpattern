package com.vick.designpattern.action.template;

import com.sun.istack.internal.Nullable;

import java.beans.IntrospectionException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Vick Zhang
 * @date 2020/9/3
 */
@FunctionalInterface
public interface RowMapper<T> {
    @Nullable
    T mapRow(ResultSet rs, int rowNum) throws SQLException, IntrospectionException;
}
