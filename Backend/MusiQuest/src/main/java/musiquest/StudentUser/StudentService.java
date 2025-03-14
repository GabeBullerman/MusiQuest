package musiquest.StudentUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StudentUser service used with StudentController
 */
@Service
public class StudentService {

	private final StudentUserRepository studentUserRepository;

    @Autowired
    public StudentService(StudentUserRepository studentUserRepository) {
        this.studentUserRepository = studentUserRepository;
    }

    // Implement methods for student-specific operations
	
}
