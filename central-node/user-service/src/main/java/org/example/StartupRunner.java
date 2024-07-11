package org.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class StartupRunner implements CommandLineRunner {

    private final JdbcTemplate secondJdbcTemplate;

    public StartupRunner(@Qualifier("hospital1JdbcTemplate") JdbcTemplate secondJdbcTemplate) {
        this.secondJdbcTemplate = secondJdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        String sql = "SELECT * FROM condition_era LIMIT 20";
        List<Map<String, Object>> results = secondJdbcTemplate.queryForList(sql);

        System.out.println("Query Results:");
        for (Map<String, Object> row : results) {
            System.out.println(row);
        }
    }
}
