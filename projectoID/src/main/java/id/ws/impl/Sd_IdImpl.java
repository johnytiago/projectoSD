package id.ws.impl;


import javax.jws.*;
import id.ws.*; // classes generated from WSDL
import pt.ulisboa.tecnico.sdis.id.ws.*;

//para gerar as pass's
import java.math.*;
import java.security.SecureRandom;

//hash
import java.util.HashMap;



@WebService(
    endpointInterface="pt.ulisboa.tecnico.sdis.id.ws.SDId", 
    wsdlLocation="SD-ID.1_1.wsdl",
    name="SdId",
    portName="SDIdImplPort",
    targetNamespace="urn:pt:ulisboa:tecnico:sdis:id:ws",
    serviceName="SDId"
)
public class Sd_IdImpl implements SDId {
    HashMap<String, User> usersLog = new HashMap<String, User>();
	
	public void createUser(String userId, String emailAddress) 
            throws EmailAlreadyExists_Exception, InvalidEmail_Exception, InvalidUser_Exception, UserAlreadyExists_Exception{

        //perguntar a opinião do prof acerca disto
        SecureRandom SECURE_RANDOM = new SecureRandom();
        String pass = new BigInteger(130, SECURE_RANDOM).toString(32);
        User user = new User(userId, emailAddress, pass);
        usersLog.put(userId, user);
        System.out.println(pass);
    }

    public void renewPassword(String userId ) 
            throws UserDoesNotExist_Exception{
        User u = usersLog.get(userId);
        if(u == null)
            //throw new UserDoesNotExist_Exception();
            System.out.println("got");
        else{
            SecureRandom SECURE_RANDOM = new SecureRandom();
            String hash = new BigInteger(130, SECURE_RANDOM).toString(32);
            u.pass = hash;
            System.out.println(hash);
            
        }
    }

    public void removeUser(String userId) 
            throws UserDoesNotExist_Exception{
        usersLog.remove(userId);

    }

    public byte[] requestAuthentication(String userId, byte[] reserved)
            throws AuthReqFailed_Exception{

     	return null; 	
    }
}


