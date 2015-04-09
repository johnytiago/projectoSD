package id.ws.impl;

public class User{
	protected String userId;
	protected String emailAddress;
	protected String pass;
	protected String certificate;

	public User(String userId, String emailAddress , String pass){
		userId=userId;
		emailAddress=emailAddress;
		pass=pass;
	}
}