package tests;

import javafx.application.Platform;
import org.junit.jupiter.api.*;
import code.eutrustservicesapplication.*;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class LabelTypeTest {
    static Api api;
    @BeforeAll
    static void init()
    {
        Platform.startup(() -> {}); //necessario se non è stato chiamato da precedenti test consecutivi(in caso contrario commentarlo)
        try{
            api=Api.getInstance();
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
