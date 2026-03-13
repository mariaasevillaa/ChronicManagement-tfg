package com.tfg.wellbeing.controller;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "frontPage";
    }
    private final JdbcTemplate jdbc;

    public HomeController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping("/db/ping")
    public String ping() {
        Integer one = jdbc.queryForObject("SELECT 1", Integer.class);
        return "DB OK: " + one;
    }

    @GetMapping("/db/tables")
    public Object tables() {
        return jdbc.queryForList("SELECT name FROM sqlite_master WHERE type='table' ORDER BY name;");
    }
}
