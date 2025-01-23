package musiquest.SoloUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SoloUser Service user with SoloController
 */
@Service
public class SoloService {

	private final SoloUserRepository soloUserRepository;

    @Autowired
    public SoloService(SoloUserRepository soloUserRepository) {
        this.soloUserRepository = soloUserRepository;
    }

    
	
}
