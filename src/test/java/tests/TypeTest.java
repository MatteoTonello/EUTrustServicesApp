package tests;

import org.junit.jupiter.api.*;
import code.eutrustservicesapplication.*;

import java.io.IOException;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TypeTest {
    @BeforeAll
    static void init()
    {
        //Platform.startup(() -> {});
        try{
            Api.start();
        }
        catch (Exception e)
        {
            fail();
        }
    }
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void initialize() throws IOException,IllegalParameters {
        TypeofService.initialize(Api.type_of_services());
        assertEquals(Api.type_of_services().size(), TypeofService.typeOfServicesPane().size());
    }
    @Test
    public void initializeNull() throws IOException
    {
        try
        {
            TypeofService.initialize(null);
            fail();
        }
        catch (IllegalParameters e)
        {
            assertTrue(true);
        }
    }
    @Test
    void initializeVoidVector() throws Exception,IllegalParameters{
        Vector<String> types = new Vector<>();
        TypeofService.initialize(types);
        assertEquals(0, TypeofService.typeOfServicesPane().size());
    }

    @AfterAll
    static void stop()
    {
        //Platform.exit();
    }
}