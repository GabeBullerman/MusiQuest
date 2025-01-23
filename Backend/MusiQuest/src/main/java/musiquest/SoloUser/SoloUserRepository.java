package musiquest.SoloUser;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.TimeZone;

/**
 * SoloUser Repository
 *  @author Haakon H
 */

public interface SoloUserRepository extends JpaRepository<SoloUser, Integer> {

    SoloUser findById(int id);

    void deleteById(int id);

    String findByEmailId(String email);
}
