package sales.api.repository.api;


import org.springframework.data.jpa.repository.JpaRepository;
import sales.api.entity.TbChainVanEntity;
import sales.api.entity.TbChainVanPK;

//@Repository
public interface TbChainVanRepoistory extends JpaRepository <TbChainVanEntity, TbChainVanPK>, TbChainVanRepositoryCustom {

}
