package musiquest.TeacherUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Teacher Service used with TeacherController
 */
@Service
public class TeacherService {

	private final TeacherUserRepository teacherUserRepository;

    @Autowired
    public TeacherService(TeacherUserRepository teacherUserRepository) {
        this.teacherUserRepository = teacherUserRepository;
    }

    // Implement methods for student-specific operations
	
}
