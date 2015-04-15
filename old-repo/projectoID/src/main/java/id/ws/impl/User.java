package id.ws.impl;

public class User{
	protected String _userId;
	protected String _emailAddress;
	protected String _password;
	protected String _certificate;

	public User(String user, String email, String pass){
		_userId = user;
		_emailAddress = email;
		_password = pass;
	}

	public String getUserId(){
		return _userId;
	}
}