package com.jie.demo.demodbmove.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MoveService {

    @Resource
    private JdbcTemplate fromJdbcTemplate;

    @Resource
    private JdbcTemplate toJdbcTemplate;

    public void move() {
        List<String> allTables = getAllTables();

        allTables.forEach(this::moveTable);
    }

    public List<String> getAllTables() {
        List<Map<String, Object>> show_tables = fromJdbcTemplate.queryForList("show tables");
        return show_tables.stream()
                .map(item -> (String)item.values().toArray()[0])
                .collect(Collectors.toList());
    }

    private int getTableCount(String tableName) {
        String sql = String.format("select count(*) as count from %s", tableName);
        return fromJdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt(1);
            }
        });
    }

    public void moveTable(String tableName) {
        String sql = String.format("select * from %s", tableName);

        List<Map<String, Object>> results = fromJdbcTemplate.queryForList(sql);
        if (results.isEmpty()) {
            return;
        }

        StringBuilder columnStr = new StringBuilder();
        StringBuilder valueStr = new StringBuilder();
        for (Map.Entry<String, Object> entry : results.get(0).entrySet()) {
            if (columnStr.length() > 0){
                columnStr.append(",");
                valueStr.append(",");
            }

            columnStr.append(entry.getKey());
            valueStr.append("?");
        }

        String sqlInsert = String.format("insert into %s(%s) values(%s)", tableName, columnStr.toString(), valueStr.toString());
        System.out.println(sqlInsert);
        List<Object[]> args = results.stream()
                .map(entry -> entry.values().toArray())
                .collect(Collectors.toList());

        toJdbcTemplate.batchUpdate(sqlInsert, args);
    }
}
