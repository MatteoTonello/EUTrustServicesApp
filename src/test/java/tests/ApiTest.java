package tests;

import code.eutrustservicesapplication.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiTest {
    static Api api;
    @BeforeAll
    public static void init()
    {
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
    public void initialize()
    {
        Node n=new Node("AT","A-Trust Gesellschaft für Sicherheitssysteme im elektronischen Datenverkehr","GmbH TrustSign-Sig-01 (key no. 1)","withdrawn","QCertESig");
        assertEquals(n.toString(),api.totalNodes().get(0).toString());
        assertEquals(api.name_countries().size(), api.countries().size());
    }
    @Test
    public void initialLength()
    {
        for(int i=0;i<api.countries().size();i++){
            assertEquals(2, api.countries().get(i).length());
        }
    }
    @Test
    public void oneElementRight()
    {
        assertTrue(api.countries().contains("IT"));
        assertTrue(api.name_countries().contains("Italy"));
    }
    @Test
    public void oneElementWrong()
    {
        assertFalse(api.countries().contains("USA"));
        assertFalse(api.name_countries().contains("United States"));
    }
    @Test
    public void notNull()
    {
        assertNotNull(api.countries());
        assertNotNull(api.type_of_services());
        assertNotNull(api.providers());
        assertNotNull(api.status());
        assertNotNull(api.totalNodes());
    }
}