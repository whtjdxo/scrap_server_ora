package sales.api.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.api.entity.TbChainEntity;

//@Repository
public interface TbChainRepository extends JpaRepository<TbChainEntity, String>, TbChainRepositoryCustom {
}
