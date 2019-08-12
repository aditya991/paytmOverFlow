
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */

//Check commit

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.paytm.repo",
                       entityManagerFactoryRef = "DbFactory",
                       transactionManagerRef = "transactionManagerDb")
@ComponentScan(basePackages = "com.paytm")

public class DBConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(DBConfiguration.class);

    static
    {
        LOG.info("Loading DB Configuration");
    }

    @Bean
    public JpaTransactionManager transactionManagerDb() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(DbFactory().getObject());
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean DbFactory() {
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(DbDatasource());
        //entityManagerFactoryBean.setPersistenceUnitName("hibernate-persistence"); //persistence name
        entityManagerFactoryBean.setPackagesToScan("com.paytm.entity");
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setJpaProperties(getJpaProperties());
        return entityManagerFactoryBean;
    }

    private Properties getJpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");//create or update

        properties.setProperty("hibernate.transaction.jta.platform","org.hibernate.service.jta.platform.internal.JBossAppServerJtaPlatform");
        properties.setProperty("showSql", "true");

        return properties;
    }

    @Bean
    public DataSource DbDatasource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/paytmDB?createDatabaseIfNotExist=true");
        return dataSource;
    }
}