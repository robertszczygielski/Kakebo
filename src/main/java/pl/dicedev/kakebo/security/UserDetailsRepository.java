package pl.dicedev.kakebo.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.dicedev.kakebo.security.entity.UserEntity;

import java.util.UUID;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserEntity, UUID> {
}