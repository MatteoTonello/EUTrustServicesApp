package tests;

import code.eutrustservicesapplication.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiTest {
    @BeforeAll
    public static void init()
    {
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
    public void initialize()
    {
        Node n=new Node("AT","A-Trust Gesellschaft für Sicherheitssysteme im elektronischen Datenverkehr","GmbH TrustSign-Sig-01 (key no. 1)","withdrawn","QCertESig");
        assertEquals(n.toString(),Api.totalNodes().get(0).toString());
        assertEquals(Api.name_countries().size(), Api.countries().size());
    }
    @Test
    public void initialLength()
    {
        for(int i=0;i<Api.countries().size();i++){
            assertEquals(2, Api.countries().get(i).length());
        }
    }
    @Test
    public void oneElementRight()
    {
        assertTrue(Api.countries().contains("IT"));
        assertTrue(Api.name_countries().contains("Italy"));
    }
    @Test
    public void oneElementWrong()
    {
        assertFalse(Api.countries().contains("USA"));
        assertFalse(Api.name_countries().contains("United States"));
    }
    @Test
    public void notNull()
    {
        assertNotNull(Api.countries());
        assertNotNull(Api.type_of_services());
        assertNotNull(Api.providers());
        assertNotNull(Api.status());
        assertNotNull(Api.totalNodes());
    }
}