package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("repository")
@ComponentScan({"services", "util"})
@PropertySource("classpath:db.properties")
public class AppConfig {
    @Resource
    private Environment env;

    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

// For Hypersonic database DataSource
//        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
//        dataSource.setUrl("jdbc:hsqldb:mem:app-db");
//        dataSource.setUsername("sa");
//        dataSource.setPassword("");

        // For PostgreSQL DataSource
        dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
        dataSource.setUrl(env.getRequiredProperty("db.url"));
        dataSource.setUsername(env.getRequiredProperty("db.username"));
        dataSource.setPassword(env.getRequiredProperty("db.password"));

        return dataSource;
    }

// For JNDI Data Pool DataSource
//    @Bean
//    public DataSource dataSource() throws NamingException {
//        return (DataSource) new JndiTemplate().lookup("jdbc/StorePool");
//    }

    private Properties hibernateProp() {
        Properties properties = new Properties();

//  For PostgreSQL Properties
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

// For Hypersonic database properties
//        properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
//        properties.put("hibernate.hbm2ddl.auto", "create-drop");

        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();
        emFactory.setDataSource(dataSource());
        emFactory.setPackagesToScan("entity");
        emFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emFactory.setJpaProperties(hibernateProp());
        return emFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}
