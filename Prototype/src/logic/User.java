package logic;

public class User {
	private String id;
	private String fName;
	private String lName;
	private String email;
	public User(String id, String name, String name2,String email) {
		
		this.id = id;
		fName = name;
		lName = name2;
		this.email=email;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String em) {
		this.email = em;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the lName
	 */
	public String getLName() {
		return lName;
	}
	/**
	 * @param name the lName to set
	 */
	public void setLName(String name) {
		lName = name;
	}
	/**
	 * @return the fName
	 */
	public String getFName() {
		return fName;
	}
	/**
	 * @param name the pName to set
	 */
	public void setFName(String name) {
		fName = name;
	}
	public String toString(){
		return String.format("%s %s %s %s\n",id,fName,lName,email);
	}
	
}
