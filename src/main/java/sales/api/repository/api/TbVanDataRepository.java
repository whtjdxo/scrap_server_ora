package sales.api.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sales.api.entity.TscVanDataEntity;

@Repository
public interface TbVanDataRepository extends JpaRepository<TscVanDataEntity, Long> {

}
