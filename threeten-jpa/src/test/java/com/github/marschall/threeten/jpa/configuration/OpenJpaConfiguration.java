package com.github.marschall.threeten.jpa.configuration;

import static com.github.marschall.threeten.jpa.Constants.PERSISTENCE_UNIT_NAME;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.OpenJpaDialect;
import org.springframework.orm.jpa.vendor.OpenJpaVendorAdapter;

@Configuration
public class OpenJpaConfiguration {
  
  @Autowired
  private Environment environment;
  
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManager(DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
    bean.setPersistenceUnitName(environment.getProperty(PERSISTENCE_UNIT_NAME));
    bean.setJpaDialect(jpaDialect());
    bean.setJpaVendorAdapter(new OpenJpaVendorAdapter());
    bean.setDataSource(dataSource);
    return bean;
  }

  @Bean
  public JpaDialect jpaDialect() {
    return new OpenJpaDialect();
  }

}
