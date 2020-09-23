package com.vick.designpattern.action.template;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.vick.designpattern.action.template.entity.Score;

import java.beans.IntrospectionException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Vick Zhang
 * @date 2020/9/3
 */
public class JdbcTemplateTest {

    private Logger logger = LoggerFactory.getLogger(JdbcTemplateTest.class);

    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        try {
            String sql = "select * from tb_score where score = ?";
            Object[] params = new Object[]{90};
            RowMapper rowMapper = new BeanPropertyRowMapper(Score.class);

            List<Score> list = (List<Score>) jdbcTemplate.execute(sql, params, rowMapper);
            list.stream().forEach(System.out::println);
        } catch (SQLException | ClassNotFoundException | IntrospectionException e) {
            e.printStackTrace();
        }
    }
}
