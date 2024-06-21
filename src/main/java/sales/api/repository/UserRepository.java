package sales.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.api.entity.TscUser;


public interface UserRepository extends JpaRepository<TscUser, String> {
//    boolean existsByUserId(String userId);
    boolean existsById(String userId);
    TscUser findByUserId(String userId);
}
