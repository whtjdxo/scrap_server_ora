package sales.api.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sales.api.entity.TscCardSalesDataEntity;

@Repository
public interface TbCardSalesDataRepository extends JpaRepository<TscCardSalesDataEntity, Long> {
}
