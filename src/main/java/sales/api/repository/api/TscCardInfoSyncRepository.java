package sales.api.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sales.api.entity.TscBankDataEntity;
import sales.api.entity.TscCardInfoSyncEntity;

@Repository
public interface TscCardInfoSyncRepository extends JpaRepository<TscCardInfoSyncEntity, Long>{

}
