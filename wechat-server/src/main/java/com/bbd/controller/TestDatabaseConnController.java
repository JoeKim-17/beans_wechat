package com.bbd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class TestDatabaseConnController {

  @Autowired
  private DataSource dataSource;

  @GetMapping("/test")
  public String testDatabaseConnection() {
    try (Connection connection = dataSource.getConnection()) {
      return "Database connection successful!";
    } catch (SQLException e) {
      return "Database connection failed: " + e.getMessage();
    }
  }
}