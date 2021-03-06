package com.github.marschall.threeten.jpa.jdbc42.hibernate;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.PlatformTransactionManager;

import com.github.marschall.threeten.jpa.jdbc42.hibernate.SqlServerTest.LocalConfiguration;
import com.github.marschall.threeten.jpa.jdbc42.hibernate.configuration.HsqlConfiguration;

@ContextConfiguration(classes = {HsqlConfiguration.class, LocalConfiguration.class})
public class HsqlTest extends AbstractTransactionalJUnit4SpringContextTests {

  @Autowired
  private DataSource dataSource;

  @Test
  public void getObject() throws SQLException {
    try (Connection connection = this.dataSource.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "SELECT TIMESTAMP '1960-01-01 23:03:20+05:00' "
//                 "SELECT CAST('1960-01-01 23:03:20+05:00' AS TIMESTAMP WITH TIME ZONE) + INTERVAL '5:00' HOUR TO MINUTE "
//                 "SELECT CAST('1960-01-01 23:03:20+05:00' AS TIMESTAMP WITH TIME ZONE) + TIMEZONE() "
//                 "SELECT TIMESTAMP_WITH_ZONE(TIMESTAMP '1960-01-01 23:03:20') "
//                 "SELECT TIMESTAMP '1960-01-01 23:03:20' AT TIME ZONE INTERVAL '2:00' HOUR TO MINUTE "
               + "FROM (VALUES(0))");
         ResultSet resultSet = preparedStatement.executeQuery()) {

      OffsetDateTime expected = OffsetDateTime.parse("1960-01-01T23:03:20+05:00");
      while (resultSet.next()) {
        assertEquals(expected, resultSet.getObject(1, OffsetDateTime.class));
//        assertEquals("1960-01-01 23:03:20+02:00", resultSet.getObject(1, String.class));
      }
    }
  }

  @Configuration
  static class LocalConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    public PlatformTransactionManager txManager() {
      return new DataSourceTransactionManager(this.dataSource);
    }

  }

}
