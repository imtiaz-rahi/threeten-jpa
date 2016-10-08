package com.github.marschall.threeten.jpa.jdbc42.hibernate.configuration;

import static com.github.marschall.threeten.jpa.jdbc42.hibernate.Constants.PERSISTENCE_UNIT_NAME;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class HibernateConfiguration {

  @Autowired
  private Environment environment;


  @Bean
  public LocalContainerEntityManagerFactoryBean entityManager(DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
    bean.setPersistenceUnitName(environment.getProperty(PERSISTENCE_UNIT_NAME));
    bean.setJpaDialect(jpaDialect());
    bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    bean.setDataSource(dataSource);
    return bean;
  }


  @Bean
  public JpaDialect jpaDialect() {
    return new HibernateJpaDialect();
  }

}
