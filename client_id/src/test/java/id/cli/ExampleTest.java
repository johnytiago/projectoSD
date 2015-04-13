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
    public void test() {
        
        try {
        port.createUser("","zewrlkgkler@gmail.com");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}