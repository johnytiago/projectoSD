package id.ws.impl;


import javax.jws.*;
import id.ws.*; // classes generated from WSDL
import pt.ulisboa.tecnico.sdis.id.ws.*;

//para gerar as pass's
import java.math.*;
import java.security.SecureRandom;

//hash
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import java.util.Arrays;



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

        ////////////////////////////////////Checkar se o email e valido//////////////////////////////////////////
        int count=0;
        for (int i = 0; i < emailAddress.length(); i++){

            char c = emailAddress.charAt(i);
            int ascii = (int)c;
            if (!((ascii>=65 && ascii<=90) || (ascii>=97 && ascii<=122) ||ascii==46)){

                if(ascii==64 && i!=emailAddress.length()-1 && i!=0 && count==0){
                    count++;
                    continue;
                }
                 System.out.println("email invalido");
                 InvalidEmail faultInfo = new InvalidEmail();
                 faultInfo.setEmailAddress(emailAddress);
                 throw new InvalidEmail_Exception("The EmailAddress: The email " + emailAddress+ " is Invalid.", faultInfo);
       
                
            }   

            
        }
        if(count==0){
            System.out.println("email invalido");
            InvalidUser faultInfo = new InvalidUser();
                faultInfo.setUserId(userId);
                throw new InvalidUser_Exception("The EmailAddress: The email " + emailAddress+ " is Invalid.", faultInfo);
        }



        if (userId==null || userId.equals("")){
            InvalidEmail faultInfo = new InvalidEmail();
                 faultInfo.setEmailAddress(emailAddress);
                 throw new InvalidEmail_Exception("The userId: The user " + userId+ " is Invalid.", faultInfo);


        }




        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        for (User  user: usersLog.values()) {
            if(user._emailAddress.equals(emailAddress)){
                System.out.println("email repetido->"+ emailAddress);

                EmailAlreadyExists faultInfo = new EmailAlreadyExists();
                faultInfo.setEmailAddress(emailAddress);
                throw new EmailAlreadyExists_Exception("The EmailAddress: The email " + emailAddress+ " already exist.", faultInfo);
            }
            if(user._userId.equals(userId)){
                UserAlreadyExists faultInfo = new UserAlreadyExists();
                faultInfo.setUserId(userId);
                throw new UserAlreadyExists_Exception("The User: The user " + userId + " already exists.", faultInfo);
            }
        }

        System.out.println("email ->"+ emailAddress);

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
            throw new UserDoesNotExist_Exception("The user: " + userId + " does not exist.", faultInfo);
        }
        SecureRandom SECURE_RANDOM = new SecureRandom();
        String hash = new BigInteger(130, SECURE_RANDOM).toString(32);
        u._password = hash;
        System.out.println(hash);
    }

    public void removeUser(String userId) 
            throws UserDoesNotExist_Exception{
        User u = usersLog.get(userId);
        if(u == null){
            UserDoesNotExist faultInfo = new UserDoesNotExist();
            faultInfo.setUserId(userId);
            throw new UserDoesNotExist_Exception("The user you are trying to remove does not exists.", faultInfo);
        }
        usersLog.remove(userId);
    }

    public byte[] requestAuthentication(String userId, byte[] reserved)
            throws AuthReqFailed_Exception{
        
        /* Verifies if the user exists */
        User u = usersLog.get(userId);
        if(u == null){
            AuthReqFailed faultInfo = new AuthReqFailed();
            faultInfo.setReserved(reserved);
            throw new AuthReqFailed_Exception("AUTHENTICATION FAILED: User " + userId +" does not exists.", faultInfo);
        }

        /* Verifies if the passwords match */
        if(!Arrays.equals(u._password.getBytes(),reserved)){
            AuthReqFailed faultInfo = new AuthReqFailed();
            faultInfo.setReserved(reserved);
            throw new AuthReqFailed_Exception("AUTHENTICATION FAILED: Wrong password.", faultInfo);   
        }
     	return "OK".getBytes(); 	
    }
}


