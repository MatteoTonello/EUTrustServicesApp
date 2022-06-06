package tests;

import org.junit.jupiter.api.*;
import code.eutrustservicesapplication.*;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class LabelTypeTest {
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
    void initializeOneRight() throws IOException
    {
        try{
            LabelType l=new LabelType("WAC");
            assertEquals(l.toString(), "WAC");
        }
        catch (IllegalParameters e)
        {
            fail();
        }

    }
    @Test
    void initializeOneWrong() throws IOException
    {
        try{
            new LabelType(null);
            fail();
        }
        catch (IllegalParameters e)
        {
            assertTrue(true);
        }
    }
    @Test
    void notNull() throws IOException,IllegalParameters
    {
        LabelType l=new LabelType("WAC");
        assertNotNull(l);
    }
    @Test
    void paneTest() throws IOException,IllegalParameters
    {
        LabelType l=new LabelType("WAC");
        assertNotNull(l.pane());
    }

    @AfterAll
    static void stop()
    {
        //Platform.exit();
    }
}
