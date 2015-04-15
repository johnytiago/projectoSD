package id.cli;

import pt.ulisboa.tecnico.sdis.id.ws.*;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *  Test suite
 */
public class ExampleTest {

    // static members
    private static SDId port;



    // one-time initialization and clean-up

    @BeforeClass
    public static void oneTimeSetUp() {

        SDId_Service service = new SDId_Service();
        port = service.getSDIdImplPort();


    }

    @AfterClass
    public static void oneTimeTearDown() {
        port=null;

    }


    // members


    // initialization and clean-up for each test

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    // tests

    //registo de user 
    //e não e esperado que uma excepcao seja lancada

    @Test
    public void test01() {
       System.out.println("1");
        String test="";
        String output="";
        try {
       	
        port.createUser("paulo","fabio@gmail.com");
        }
        catch(Exception e){
           e.getMessage();
        }
         assertEquals(test,output);
        
    }

    //tentativa de registo de user que ja existe
    //e e esperado que uma excepcao seja lancada
	@Test
    public void test02() {
    	System.out.println("2");
        String test="The User: The user " + "paulo" + " already exists.";
        String output="";
        try {
       	
        port.createUser("paulo","toni@gmail.com");
        
        }
        catch(Exception p){
           output=p.getMessage();
        }
        assertEquals(test,output);
    }

    //tentativa de registo de user com um email invalido
    //e e esperado que uma excepcao seja lancada
    @Test
    public void test03() {
    	System.out.println("3");
        String test="The EmailAddress: The email @gmail.com is Invalid.";
        String output="";
        try {
       	
        port.createUser("joao","@gmail.com");

        
        }
        catch(Exception p){
           output=p.getMessage();
        }
        assertEquals(test,output);
    }

    //tentativa de registo de user com um email invalido
    //e e esperado que uma excepcao seja lancada

      @Test
    public void test04() {
    	System.out.println("4");
        String test="The EmailAddress: The email gmail@ is Invalid.";
        String output="";
        try {
       	
        port.createUser("joao","gmail@");

        
        }
        catch(Exception p){
           output=p.getMessage();
        }
        assertEquals(test,output);
    }

    //tentativa de registo de um user com um email já existente 
    //e e esperado que uma excepcao seja lancada
      @Test
    public void test05() {
       System.out.println("5");
        String test="The EmailAddress: The email " + "fabio@gmail.com"+ " already exist.";
        String output="";
        try {
       	
        port.createUser("ze","fabio@gmail.com");
        }
        catch(Exception e){
           output=e.getMessage();
        }
         assertEquals(test,output);
        
    }

    //testa uma tentativa de registo de um user sem nome ""
    //e e esperado que uma excepcao seja lancada
     @Test
    public void test06() {
       System.out.println("6");
        String test="The userId: The user " + ""+ " is Invalid.";
        String output="";
        try {
       	
        port.createUser("","joana@gmail.com");
        }
        catch(Exception e){
           output=e.getMessage();
        }
         assertEquals(test,output);
        
    }
    //pede-se uma nova pass a um user registado anteriormente 
    //e nao e esperado que uma excepcao seja lancada
       @Test
    public void test07() {
       System.out.println("7");
        String test="";
        String output="";
        try {
       	
        port.renewPassword("paulo");
        }
        catch(Exception e){
           output=e.getMessage();
        }
         assertEquals(test,output);
        
    }

    // tentativa de remover um user que nao existe 
    //e e esperado que uma excepcao seja lancada
    @Test
    public void test08() {
       System.out.println("8");
        String test="The user: " + "fabiana" + " does not exist.";
        String output="";
        try {
       	
        port.renewPassword("fabiana");
        }
        catch(Exception e){
           output=e.getMessage();
        }
         assertEquals(test,output);
        
    }

    //remove um user que existe
    //e nao e esperado que uma excepcao seja lancada
         @Test
    public void test09() {
       System.out.println("9");
        String test="";
        String output="";
        try {
       	
        port.removeUser("paulo");
        }
        catch(Exception e){
           output=e.getMessage();
        }
         assertEquals(test,output);
        
    }

    //teste em que se pede uma autenticação 
    //e e esperado que uma excepcao seja lancada
    @Test
    public void test10() {
        System.out.println("10");
        boolean test = true;
        boolean output=false;

        byte[] reserved=null;
        
        try {
       	
        reserved =port.requestAuthentication("alice", "Aaa1".getBytes());
        output = reserved[0]!=0;
        

        }
        catch(Exception e){
           e.getMessage();
        }
        assertEquals(test,output);
        
    }



    //teste em que se pede uma autenticação com uma pass errada
    //e e esperado que uma excepcao seja lancada
    @Test
    public void test11() {
        System.out.println("11");
        String test ="AUTHENTICATION FAILED: Wrong password." ;
        String output="";

        
        
        try {
        
        port.requestAuthentication("alice", "alice".getBytes());
        

        }
        catch(Exception e){
           output=e.getMessage();
        }
        assertEquals(test,output);
        
    }

     //teste em que se pede uma autenticação com um user errado
    //e e esperado que uma excepcao seja lancada
    @Test
    public void test12() {
        System.out.println("12");
        String test ="AUTHENTICATION FAILED: User " + "alic" +" does not exists.";
        String output="";

        
        
        try {
        
        port.requestAuthentication("alic", "Aaa1".getBytes());
        

        }
        catch(Exception e){
           output=e.getMessage();
        }
        assertEquals(test,output);
        
    }




    
    



}