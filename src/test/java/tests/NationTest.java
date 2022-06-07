package tests;

import org.junit.jupiter.api.*;
import code.eutrustservicesapplication.*;
import javafx.application.Platform;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class NationTest {
    static Api api;
    @BeforeAll
    static void init()
    {
        Platform.startup(() -> {});
        try{
            api=Api.getInstance();
        }
        catch (Exception e)
        {
            fail();
        }
        try{
            ImagesNations.initialize(api.countries());
        }
        catch (IllegalParameters e)
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
    public void initialize() throws IOException,IllegalParameters
    {
        Nations.initialize(api.name_countries(),api.countries());
        assertEquals("AT Austria", Nations.nations.elementAt(0).toString());
        assertEquals(0, Nations.selected_nations.size());
        assertEquals(Nations.nations.size(), api.countries().size());
    }
    @Test
    public void initializeNull() throws IOException
    {
        try
        {
            Nations.initialize(null,null);
            fail();
        }
        catch (IllegalParameters e)
        {
            assertTrue(true);
        }

    }
    @Test
    public void initializeWrong() throws IOException
    {
        List<String> n=new Vector<>();
        List<String> s=new Vector<>();
        n.add("Italy");
        try
        {
            Nations.initialize(n,s);
            fail();
        }
        catch (IllegalParameters e)
        {
            assertTrue(true);
        }

    }
    @Test
    void initializeVoidVector() throws Exception,IllegalParameters{
        List<String> n=new Vector<>();
        List<String> s=new Vector<>();
        Nations.initialize(n,s);
        assertEquals(0, Nations.nations.size());
        assertEquals(0, Nations.selected_nations.size());
    }
    @Test
    public void notNull()
    {
        assertNotNull(Nations.nations);
        assertNotNull(Nations.selected_nations);
    }
    @AfterAll
    static void stop()
    {
        //Platform.exit();
    }
}