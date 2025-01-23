package musiquest.TeacherUser;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * TeacherUser Repository
 */
public interface TeacherUserRepository extends JpaRepository<TeacherUser, Integer> {

	TeacherUser findById(int id);
	
	void deleteById(int id);
	
}
