package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import db.model.master.DynamicDatabaseInformation;
import db.repository.master.DynamicDatabaseInformationRepository;

public class DynamicDataSource extends BasicDataSource {
    private static final String URL = "jdbc:postgresql://%s:%d/%s";
    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";

    @Autowired
    private DynamicDatabaseInformationRepository dynamicDatabaseInformationRepository;

    private Map<Integer, DataSource> map = new HashMap<Integer, DataSource>();

    ThreadLocal<Integer> holder = new ThreadLocal<>();

    public void connect(Integer dbId) {
        holder.set(dbId);
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (holder.get() == null)
            return super.getConnection();
        else
            return getDB().getConnection();
    }

    private DataSource getDB() {
        Integer id = holder.get();
        if (id == null) {
            return null;
        }
        DataSource dataSource = map.get(id);
        if (dataSource == null) {
            dataSource = initDataSource(id);
        }
        if (dataSource != null) {
            map.put(holder.get(), dataSource);
        }
        return dataSource;
    }

    private DataSource initDataSource(Integer dbId) {
        DynamicDatabaseInformation dbInformation = findByDbId(dbId);
        DataSourceProperties properties = new DataSourceProperties();
        properties.setType(BasicDataSource.class);
        properties.setUrl(String.format(URL, dbInformation.getDbAddress(), dbInformation.getDbPort(), dbInformation.getDbName()));
        properties.setDriverClassName(DRIVER_CLASS_NAME);
        properties.setUsername(dbInformation.getDbUserName());
        properties.setPassword(dbInformation.getDbPassword());
        return properties.initializeDataSourceBuilder().build();
    }

    private DynamicDatabaseInformation findByDbId(Integer dbId) {
        return dynamicDatabaseInformationRepository.findByDbId(dbId);
    }
}