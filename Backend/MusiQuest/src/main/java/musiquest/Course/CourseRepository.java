package musiquest.Course;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository for Courses
 */
public interface CourseRepository extends JpaRepository<Course, Integer> {

	 Course findById(int id);

	 void deleteById(int id);
	
}
