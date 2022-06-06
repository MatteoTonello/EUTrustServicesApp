package tests;

import code.eutrustservicesapplication.*;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GraphicResultTest {
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
        try{
            ImagesNations.initialize(Api.countries());
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
    public void initializeOneRight() throws IOException
    {
        try
        {
            GraphicResult gr=new GraphicResult(Api.totalNodes().get(0));
            assertEquals(gr.toString(),"SERVICE: TrustSign-Sig-01 (key no. 1) Country: AT Type of Service: QCertESig State: withdrawn Provider: A-Trust Gesellschaft für Sicherheitssysteme im elektronischen Datenverkehr GmbH");
        }
        catch (IllegalParameters e)
        {
            fail();
        }
    }

    @Test
    public void initializeOneWrong() throws IOException
    {
        try
        {
            new GraphicResult(new Node("prova","prova","prova","prova","prova"));
            fail();
        }
        catch (IllegalParameters e)
        {
            assertTrue(true);
        }
    }
    @Test
    public void initializeOneNull() throws IOException
    {
        try
        {
            new GraphicResult(null);
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
        GraphicResult gr=new GraphicResult(Api.totalNodes().get(0));
        assertNotNull(gr);
    }
    @Test
    void paneTest() throws IOException,IllegalParameters
    {
        GraphicResult gr=new GraphicResult(Api.totalNodes().get(0));
        assertNotNull(gr.pane());
    }
    @AfterAll
    static void stop()
    {
        //Platform.exit();
    }
}
