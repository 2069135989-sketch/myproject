package com.innodealing.onshore.practice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据库连通性验证接口
 */
@RestController
public class DbPingController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 数据库连通性测试
     *
     * @return 当前数据库时间
     */
    @GetMapping("/db/ping")
    public Map<String, Object> dbPing() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT now() AS db_time, version() AS db_version");
        return rows.get(0);
    }
}
