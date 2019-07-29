package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "db.repository.master", entityManagerFactoryRef = "masterEntityManager", transactionManagerRef = "masterTransactionManager")
public class MasterDataSourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSourceProperties masterProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Autowired
    @FlywayDataSource
    public DataSource masterDataSource(@Qualifier("masterProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Autowired
    public LocalContainerEntityManagerFactoryBean masterEntityManager(EntityManagerFactoryBuilder builder, @Qualifier("masterDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("db.model.master").persistenceUnit("master").build();
    }

    @Bean
    @Autowired
    public JpaTransactionManager masterTransactionManager(@Qualifier("masterEntityManager") LocalContainerEntityManagerFactoryBean masterEntityManager) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(masterEntityManager.getObject());
        return transactionManager;
    }
}
