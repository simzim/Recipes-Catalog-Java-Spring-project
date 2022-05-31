package lt.simzim.recipescatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.simzim.recipescatalog.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);

}