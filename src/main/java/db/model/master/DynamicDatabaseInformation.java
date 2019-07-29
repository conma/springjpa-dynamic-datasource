package db.model.master;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "db_info")
@Embeddable
public class DynamicDatabaseInformation {

    @Id
    @Column(name = "db_id")
    private Integer dbId;

    @Column(name = "")
    private String dbDriver;

    @Column(name = "db_address")
    private String dbAddress;

    @Column(name = "db_port")
    private Integer dbPort;

    @Column(name = "db_name")
    private String dbName;

    @Column(name = "db_user_name")
    private String dbUserName;

    @Column(name = "db_password")
    private String dbPassword;

    public DynamicDatabaseInformation() {
    }

    public Integer getDbId() {
        return dbId;
    }

    public void setDbId(Integer dbId) {
        this.dbId = dbId;
    }

    public String getDbAddress() {
        return dbAddress;
    }

    public void setDbAddress(String dbAddress) {
        this.dbAddress = dbAddress;
    }

    public Integer getDbPort() {
        return dbPort;
    }

    public void setDbPort(Integer dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    @Override
    public String toString() {
        return "DynamicDatabaseInformation{" + "dbId=" + dbId + ", dbAddress='" + dbAddress + '\'' + ", dbPort=" + dbPort + ", dbName='" + dbName + '\'' + ", dbUserName='" + dbUserName + '\'' + ", dbPassword='" + dbPassword + '\'' + '}';
    }
}