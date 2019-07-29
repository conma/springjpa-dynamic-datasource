package db.repository.primary;

import db.model.primary.PrimaryTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimaryTableRepository extends JpaRepository<PrimaryTable, Integer> {
}
