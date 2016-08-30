package implementations;

import requests.LoginRequest;

public class LoginImpl {
	
	public int login(String username, char[] charPassword){
		//String password = charPassword.toString();
		String password = new String(charPassword);
		LoginRequest loginRequest = new LoginRequest();
		return loginRequest.sendLoginRequest(username, password);
	}

}
