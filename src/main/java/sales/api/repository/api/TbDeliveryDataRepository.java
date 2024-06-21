package sales.api.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.api.entity.TscDeliveryDataEntity;

//@Repository
public interface TbDeliveryDataRepository extends JpaRepository<TscDeliveryDataEntity, Long> {
}
