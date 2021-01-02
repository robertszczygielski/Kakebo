package pl.dicedev.kakebo.security;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dicedev.kakebo.repositories.entities.UserEntity;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String userName);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity set logged = true where username = :userName")
    void setLoggedIn(@Param("userName") String userName);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity set logged = false where id = :userId")
    void setLoggedOut(UUID userId);
}
