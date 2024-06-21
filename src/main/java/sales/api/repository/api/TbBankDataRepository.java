package sales.api.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sales.api.entity.TscBankDataEntity;

@Repository
public interface TbBankDataRepository extends JpaRepository <TscBankDataEntity, Long>, TbBankDataRepositoryCustom {
}
