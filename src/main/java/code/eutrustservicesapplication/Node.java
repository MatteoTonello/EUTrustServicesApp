package code.eutrustservicesapplication;

/**
 * Classe che identifica la singola entità "servizio" e ne associa tutte le relative informazioni:
 * -Nazione
 * -Provider
 * -Tipo di servizio
 * -Stato
 */
public class Node {
    /**
     * Variabile stringa che contiene la sigla della nazione in cui viene fornito il servizio
     */
    public final String country;
    /**
     * Variabile stringa che contiene il nome del provider che fornisce il servizio
     */
    public final String provider;
    /**
     * Variabile stringa che contiene il nome del servizio
     */
    public final String service;
    /**
     * Variabile stringa che contiene lo stato del servizio
     */
    public final String state;
    /**
     * Variabile stringa che contiene il tipo del servizio
     */
    public final String type_of_service;

    /**
     * Costruttore della classe nodo che inizializza le variabili locali con i valori passati come parametri
     * @param c stringa che contiene la sigla della nazione in cui viene fornito il servizio
     * @param p stringa che contiene il nome del provider che fornisce il servizio
     * @param s stringa che contiene il nome del servizio
     * @param st stringa che contiene lo stato del servizio
     * @param t stringa che contiene il tipo del servizio
     */
    public Node(String c, String p, String s, String st, String t){
        country = c;
        provider = p;
        service = s;
        state = st;
        type_of_service = t;
    }

    /**
     * Ritorna la stringa che descrive il nodo nel formato "sigla_nazione provider servizio stato_servizio tipo_servizio"
     * @return Stringa che descrive il nodo nel formato "sigla_nazione provider servizio stato_servizio tipo_servizio"
     */
    @Override
    public String toString()
    {
        return country+" "+provider+" "+service+" "+state+" "+ type_of_service;
    }
}
