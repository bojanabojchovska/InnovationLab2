package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DataController
{
    @Autowired
    private JdbcTemplate secondJdbcTemplate;

    @GetMapping("/api/data")
    public List<Map<String, Object>> getData()
    {
        String sql = "SELECT * FROM condition_era LIMIT 20";
        List<Map<String, Object>> results = secondJdbcTemplate.queryForList(sql);
        return results;
    }
}
