package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@EnableJpaRepositories(basePackages = "db.repository.dynamic", entityManagerFactoryRef = "dynamicEntityManager", transactionManagerRef = "dynamicTransactionManager")
public class DynamicDataSourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.dynamic")
    public DataSourceProperties dynamicProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Autowired
    public DataSource dynamicDataSource(@Qualifier("dynamicProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Autowired
    public LocalContainerEntityManagerFactoryBean dynamicEntityManager(EntityManagerFactoryBuilder builder, @Qualifier("dynamicDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("db.model.dynamic").persistenceUnit("dynamic")
                .build();
    }

    @Bean
    @Autowired
    public JpaTransactionManager dynamicTransactionManager(@Qualifier("dynamicEntityManager") LocalContainerEntityManagerFactoryBean dynamicEntityManager) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(dynamicEntityManager.getObject());
        return transactionManager;
    }
}