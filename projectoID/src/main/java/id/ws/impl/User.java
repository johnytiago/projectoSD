package id.ws.impl;

public class User{
	protected String userId;
	protected String emailAddress;
	protected String password;
	protected String certificate;

	public User(String userId, String emailAddress, String password){
		userId = userId;
		emailAddress = emailAddress;
		password = password;
	}
}