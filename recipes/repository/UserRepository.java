package recipes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import recipes.domain.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    Optional<User> findById(Long id);
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByEmail(String email);
    User save(User user);

    UserDetails findByEmail(String email);
}
