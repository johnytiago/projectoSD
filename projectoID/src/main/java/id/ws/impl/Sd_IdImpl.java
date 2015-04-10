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

    public Sd_IdImpl(){
        User alice = new User("alice", "alice@tecnico.pt", "Aaa1");
        usersLog.put("alice", alice);
        User bruno = new User("bruno", "bruno@tecnico.pt", "Bbb2");
        usersLog.put("bruno", bruno);
        User carla = new User("carla", "carla@tecnico.pt", "Ccc3");
        usersLog.put("carla", carla);
        User duarte = new User("duarte", "duarte@tecnico.pt", "Ddd4");
        usersLog.put("duarte", duarte);
        User eduardo = new User("eduardo", "eduardo@tecnico.pt", "Eee5");
        usersLog.put("eduardo", eduardo);
    }
	
	public void createUser(String userId, String emailAddress) 
            throws EmailAlreadyExists_Exception, InvalidEmail_Exception, InvalidUser_Exception, UserAlreadyExists_Exception{

        for (User user : usersLog) {
            
        }

        SecureRandom SECURE_RANDOM = new SecureRandom();
        String pass = new BigInteger(130, SECURE_RANDOM).toString(32);
        User user = new User(userId, emailAddress, pass);
        usersLog.put(userId, user);
        System.out.println(pass);
    }

    public void renewPassword(String userId ) 
            throws UserDoesNotExist_Exception{
        User u = usersLog.get(userId);
        if(u == null){
            UserDoesNotExist faultInfo = new UserDoesNotExist();
            faultInfo.setUserId(userId);
            throw new UserDoesNotExist_Exception("The user: " + userID + " does not exist.", faultInfo);
        }
        else{
            SecureRandom SECURE_RANDOM = new SecureRandom();
            String hash = new BigInteger(130, SECURE_RANDOM).toString(32);
            u.pass = hash;
            System.out.println(hash);
            
        }
    }

    public void removeUser(String userId) 
            throws UserDoesNotExist_Exception{
        User u = usersLog.get(userId);
        if(u == null){
            UserDoesNotExist faultInfo = new UserDoesNotExist();
            faultInfo.setUserId(userId);
            throw new UserDoesNotExist_Exception("The user you are trying to remove does not exists.", faultInfo);
        }
        else{
            usersLog.remove(userId);
        }
    }

    public byte[] requestAuthentication(String userId, byte[] reserved)
            throws AuthReqFailed_Exception{

     	return null; 	
    }
}


