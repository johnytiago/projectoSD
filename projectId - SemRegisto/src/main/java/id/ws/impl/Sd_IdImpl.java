package example.ws.impl;

import javax.jws.*;
import example.ws.*; // classes generated from WSDL
import pt.ulisboa.tecnico.sdis.id.ws.*;

@WebService(
    endpointInterface="pt.ulisboa.tecnico.sdis.id.ws.SDId", 
    wsdlLocation="SD-ID.1_1.wsdl",
    name="SdId",
    portName="SDIdImplPort",
    targetNamespace="urn:pt:ulisboa:tecnico:sdis:id:ws",
    serviceName="SDId"
)
public class Sd_IdImpl implements SDId {
	
	public void createUser(String userId, String emailAddress) 
            throws EmailAlreadyExists_Exception, InvalidEmail_Exception, InvalidUser_Exception, UserAlreadyExists_Exception{

	}

    public void renewPassword(String userId ) 
            throws UserDoesNotExist_Exception{

    }

    public void removeUser(String userId) 
            throws UserDoesNotExist_Exception{

    }

    public byte[] requestAuthentication(String userId, byte[] reserved)
            throws AuthReqFailed_Exception{

     	return null; 	
    }
}


