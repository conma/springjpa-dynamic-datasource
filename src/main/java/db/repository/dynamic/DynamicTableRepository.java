package db.repository.dynamic;

import db.model.dynamic.DynamicTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DynamicTableRepository extends JpaRepository<DynamicTable, Integer> {

    @Query(value = "select dt from DynamicTable dt where dt.id = :id")
    DynamicTable findDynamicTable(@Param("id") Integer id);
}