package musiquest.Users;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User Repository
 */
public interface UserRepository extends JpaRepository<User, Integer>{

	User findById(int id);
	
	void deleteById(int id);
	
	
}
