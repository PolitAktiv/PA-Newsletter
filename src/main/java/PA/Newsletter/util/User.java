package PA.Newsletter.util;

/**
 * 
 * @author EcM
 *
 */
public class User {
	
	private final String USER_ID;
	private final String USER_EMAIL;
	
	public User(String userId, String userEmail) {
		this.USER_ID = userId;
		this.USER_EMAIL = userEmail;
	}
	
	public String getUserId() {
		return this.USER_ID;
	}
	
	public String getUserEmail() {
		return this.USER_EMAIL;
	}

}
