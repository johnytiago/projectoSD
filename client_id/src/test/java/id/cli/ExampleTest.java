package id.cli;

import pt.ulisboa.tecnico.sdis.id.ws.*;


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

      @Test
    public void test05() {
       System.out.println("5");
        String test="";
        String output="";
        try {
       	
        port.createUser("ze","fabio@gmail.com");
        }
        catch(Exception e){
           e.getMessage();
        }
         assertEquals(test,output);
        
    }




    
    



}