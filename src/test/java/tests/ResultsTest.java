package tests;

import code.eutrustservicesapplication.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class ResultsTest {
    static Api api;
    @BeforeAll
    static void init()
    {
        //Platform.startup(() -> {});
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
    void initialize() throws Exception,IllegalParameters{
        List<Node> final_results=api.totalNodes();
        Results.initialize(final_results);
        assertEquals(final_results.size(),Results.resultsPane().size());
    }
    @Test
    void initializeNull() throws Exception{
        try{
            Results.initialize(null);
            fail();
        }
        catch (IllegalParameters e)
        {
            assertTrue(true);
        }
    }
    @Test
    void initializeVoidVector() throws Exception,IllegalParameters{
        Vector<Node> final_results = new Vector<>();
        Results.initialize(final_results);
        assertEquals(0, Results.resultsPane().size());
    }

    @AfterAll
    static void stop()
    {
        //Platform.exit();
    }
}