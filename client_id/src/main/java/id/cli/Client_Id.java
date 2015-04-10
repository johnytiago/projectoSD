package id.cli;

import java.util.*;
import javax.xml.ws.*;
import static javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY;
import pt.ulisboa.tecnico.sdis.id.ws.*;
import id.ws.uddi.UDDINaming;
import id.ws.*; // classes generated from WSDL


public class Client_Id {

    public static void main(String[] args) throws Exception {
        // Check arguments
        if (args.length < 2) {
            System.err.println("Argument(s) missing!");
            System.err.printf("Usage: java %s uddiURL name%n", Client_Id.class.getName());
            return;
        }

        String uddiURL = args[0];
        String name = args[1];

        System.out.printf("Contacting UDDI at %s%n", uddiURL);
        UDDINaming uddiNaming = new UDDINaming(uddiURL);
 
        System.out.printf("Looking for '%s'%n", name);
        String endpointAddress = uddiNaming.lookup(name);

        if (endpointAddress == null) {
            System.out.println("Not found!");
            return;
        }
        else {
            System.out.printf("Found %s%n", endpointAddress);
        }

        System.out.println("Creating stub ...");
        SDId_Service service = new SDId_Service();
        SDId port = service.getSDIdImplPort();

        System.out.println("Setting endpoint address ...");
        BindingProvider bindingProvider = (BindingProvider) port;
        Map<String, Object> requestContext = bindingProvider.getRequestContext();
        requestContext.put(ENDPOINT_ADDRESS_PROPERTY, endpointAddress);

        System.out.println("Remote call ...");
        int i=0;

        Scanner keyboardSc;
        String name_user;
        String email;
        byte[] reserved;


        while (i==0){
            System.out.println("---- WELCOME TO THE REMOTE CALLER ----\n  Please, choose an option: \n  1 - createUser \n  2 - renewPassword \n  3 - removeUser \n  4 - requestAuthentication \n  5 - exit \n--------------------------------------");
            keyboardSc = new Scanner(System.in);
            int  input =keyboardSc.nextInt();
            switch (input) {
            case 1: 
                    System.out.println("createUser");
                    System.out.println("UserId?");
                    keyboardSc = new Scanner(System.in);
                    name_user =keyboardSc.nextLine();

                    System.out.println("email?");
                    keyboardSc = new Scanner(System.in);
                    email=keyboardSc.nextLine();
                    
                    try{
                        /* Remote Call */
                        port.createUser(name_user,email);
                    }

                    catch(UserAlreadyExists_Exception e){
                       System.out.println(e.getMessage());
                    }
                    catch(EmailAlreadyExists_Exception e){
                       System.out.println(e.getMessage());
                    }
                    break;
            
            case 2:  
                    System.out.println("renewPassword");
                    System.out.println("UserId?");
                    keyboardSc = new Scanner(System.in);
                    name_user =keyboardSc.nextLine();

                    try{
                        /* Remote Call */
                        port.renewPassword(name_user);
                    }
                    catch(UserDoesNotExist_Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
            
            case 3:
                    System.out.println("RemoveUser");
                    System.out.println("UserId?");
                    keyboardSc = new Scanner(System.in);
                    name_user =keyboardSc.nextLine();
                   
                    try{
                        /* Remote Call */
                        port.removeUser(name_user);
                    }
                    catch(UserDoesNotExist_Exception e){
                       System.out.println(e.getMessage());
                    }
                    break;
            
            case 4:
                    System.out.println("requestAuthentication");
                    System.out.println("UserId?");
                    keyboardSc = new Scanner(System.in);
                    name_user =keyboardSc.nextLine();

                    System.out.println("reserved?");
                    keyboardSc = new Scanner(System.in);
                    reserved =keyboardSc.nextLine().getBytes();
                    try{
                    port.requestAuthentication(name_user,reserved); 
                    }
                    catch(AuthReqFailed_Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
            
            case 5:  
                    i=1;
                    break;
            }
        }
    }
}
