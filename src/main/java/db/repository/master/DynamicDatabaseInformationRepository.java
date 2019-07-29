package db.repository.master;

import db.model.master.DynamicDatabaseInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DynamicDatabaseInformationRepository extends JpaRepository<DynamicDatabaseInformation, Integer> {

    @Query(value = "select d from DynamicDatabaseInformation d where d.id = :id")
    DynamicDatabaseInformation findByDbId(@Param("id") Integer id);
}