package logic;

public class Person extends User {
public Person(String id, String name, String name2, String email) {
		super(id, name, name2, email);
		// TODO Auto-generated constructor stub
	}
private String Fname;
private String Lname;
private String id;
private String email;
public String getFname() {
	return Fname;
}
public void setFname(String fname) {
	Fname = fname;
}
public String getLname() {
	return Lname;
}
public void setLname(String lname) {
	Lname = lname;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
}
