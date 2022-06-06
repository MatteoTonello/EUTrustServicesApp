package tests;

import code.eutrustservicesapplication.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class ResearchMethodsTest {
    @BeforeAll
    static void init()
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
    void searchAllCountries() {
        assertEquals(ResearchMethods.searchCountry(Api.countries(), Api.totalNodes()),Api.totalNodes());
    }
    @Test
    void searchTwoCountries()
    {
        Vector<String> tmp=new Vector<>();
        tmp.add("IT");
        tmp.add("AT");
        Vector<Node> r=ResearchMethods.searchCountry(tmp, Api.totalNodes());
        for(int i=0;i<r.size();i++) assertTrue(r.elementAt(i).country.equals("AT") || r.elementAt(i).country.equals("IT"));
    }
    @Test
    void searchNoCountry(){
        assertEquals(ResearchMethods.searchCountry(new Vector<>(), Api.totalNodes()),new Vector<>());
    }
    @Test
    void searchWrongCountry(){
        Vector<String> tmp=new Vector<>();
        tmp.add("USA");
        assertEquals(ResearchMethods.searchCountry(tmp, Api.totalNodes()),new Vector<>());
    }
    @Test
    void searchAllTypes(){
        assertEquals(ResearchMethods.searchType(Api.type_of_services(), Api.totalNodes()),Api.totalNodes());
    }
    @Test
    void searchTwoTypes()
    {
        Vector<String> tmp=new Vector<>();
        tmp.add("WAC");
        tmp.add("GenESig");
        Vector<Node> r=ResearchMethods.searchType(tmp, Api.totalNodes());
        for(int i=0;i<r.size();i++) assertTrue(r.elementAt(i).type_of_service.equals("WAC") || r.elementAt(i).type_of_service.equals("GenESig"));
    }
    @Test
    void searchNoType(){
        assertEquals(ResearchMethods.searchType(new Vector<>(), Api.totalNodes()),new Vector<>());
    }
    @Test
    void searchWrongType(){
        Vector<String> tmp=new Vector<>();
        tmp.add("WAA");
        assertEquals(ResearchMethods.searchType(tmp, Api.totalNodes()),new Vector<>());
    }

    @Test
    void searchAllProviders(){
        assertEquals(ResearchMethods.searchProvider(Api.providers(), Api.totalNodes()),Api.totalNodes());
    }
    @Test
    void searchTwoProviders()
    {
        Vector<String> tmp=new Vector<>();
        tmp.add("AR24");
        tmp.add("CIBG");
        Vector<Node> r=ResearchMethods.searchProvider(tmp, Api.totalNodes());
        for(int i=0;i<r.size();i++) assertTrue(r.elementAt(i).provider.equals("AR24") || r.elementAt(i).provider.equals("CIBG"));
    }
    @Test
    void searchNoProvider(){
        assertEquals(ResearchMethods.searchProvider(new Vector<>(), Api.totalNodes()),new Vector<>());
    }
    @Test
    void searchWrongProvider(){
        Vector<String> tmp=new Vector<>();
        tmp.add("UniPD");
        assertEquals(ResearchMethods.searchProvider(tmp, Api.totalNodes()),new Vector<>());
    }

    @Test
    void searchAllStatus(){
        assertEquals(ResearchMethods.searchStatus(Api.status(), Api.totalNodes()),Api.totalNodes());
    }
    @Test
    void searchTwoStatus()
    {
        Vector<String> tmp=new Vector<>();
        tmp.add("granted");
        tmp.add("withdrawn");
        Vector<Node> r=ResearchMethods.searchStatus(tmp, Api.totalNodes());
        for(int i=0;i<r.size();i++) assertTrue(r.elementAt(i).state.equals("granted") || r.elementAt(i).state.equals("withdrawn"));
    }
    @Test
    void searchNoStatus(){
        assertEquals(ResearchMethods.searchStatus(new Vector<>(), Api.totalNodes()),new Vector<>());
    }
    @Test
    void searchWrongStatus(){
        Vector<String> tmp=new Vector<>();
        tmp.add("not granted");
        assertEquals(ResearchMethods.searchStatus(tmp, Api.totalNodes()),new Vector<>());
    }

}