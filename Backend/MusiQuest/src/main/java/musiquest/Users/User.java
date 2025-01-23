package musiquest.Users;

import javax.persistence.*;


/**
 * Superclass for all types of users
 */
@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class User {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int ID;

	/**
	 * User's Name
	 */
	@JoinColumn(name = "Name")
	protected String name;

	@JoinColumn
	protected String emailId;

	@JoinColumn
	protected String password;

	//protected list<Users> friends;

	@JoinColumn
	@Lob
	protected String bio;

	@JoinColumn
	protected int profilePicture;
	
	/**
	 * Returns the ID of the User
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Changes the user's ID 
	 * @param int id
	 */
	public void setID(int id) {
		this.ID = id;
	}

	/**
	 * Gets the ProfilePicture
	 * @return int profilePicture
	 */
	public int getProfilePicture() {
		return profilePicture;
	}

	/**
	 * Sets the profilePicture
	 * @param int profilePicture
	 */
	public void setProfilePicture(int profilePicture) {
		this.profilePicture = profilePicture;
	}

	/**
	 * Returns the name of the user
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the User's Bio
	 * @return String bio
	 */
	public String getBio() {
		return bio;
	}
	
	/**
	 * Sets the user's bio
	 * @param String bio
	 */
	public void setBio(String bio) {
		this.bio = bio;
	}

	/**
	 * Changes the user's name
	 * @param String name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the email of the user
	 */
	public String getEmail() {
		return emailId;
	}
	
	/**
	 * Sets the user's email
	 * @param String email
	 */
	public void setEmail(String emailId) {
		this.emailId = emailId;
	}
	
	/**
	 * Sets the password of the user
	 * @param String name
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Returns the user's password (For Testing)
	 * @returns String password
	 */
	public String getPassword() {
		return password;
	}
	
	
	
	
	
	
	
}
