package musiquest.SoloUser;

import musiquest.Users.User;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 *  
 *  a type of User, only for extra needed methods and infomation. is a USer that has no class or teacher
 *  
 */

@Entity
@Table
public class SoloUser extends User{

//	@OneToOne
//	private User u;

	/**
	 * Constuctor for the object of a solo user
	 * @param name
	 * @param email
	 * @param password
	 */
	public SoloUser(String name, String email, String password) {
		this.name = name;
		this.emailId = email;
		this.password = password;
		
	}

	public SoloUser() {}

}	

	
