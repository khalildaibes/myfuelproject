/**
 * 
 */
/**
 * @author Moustafa
 *
 */
package logic;

public class Employee extends User {

	
	private String company;
	private String role;
		/**
	 * @param id
	 * @param name
	 * @param name2
	 * @param comp
	 * @param role
	 */
	public Employee(String id, String Fname, String Lname,String email, String comp,String role) {
		super(id,Fname,Lname,email);
		
		company=comp;
		this.role=role;
		
	}
	
	/**
	 * @return the company
	 */
	public String getComp() {
		return company;
	}
	/**
	 * @param set company
	 */
	public void setComp(String comp) {
		this.company = comp;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param name the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	public String toString(){
		return String.format(super.toString()+" %s %s\n",company,role);
	}
	
}
