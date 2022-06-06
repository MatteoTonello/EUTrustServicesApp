package code.eutrustservicesapplication;

import java.util.List;
import java.util.Vector;

/**
 * Classe che definisce i metodi di ricerca
 */
public class ResearchMethods {
    /**
     * Metodo per filtrare, da una lista di nodi (oggetto che contiene tutte le informazioni relative a ogni singolo
     * servizio), solo quelli relativi alle nazioni che sono state selezionate
     *
     * @param country contiene le nazioni che sono state selezionate
     *
     * @param nodes vettore nodi (oggetto che contiene tutte le informazioni relative a ogni singolo servizio)
     * dal quale filtrare quelli relativi alle nazioni che ho selezionato
     *
     * @return vettore di nodi (oggetto che contiene tutte le informazioni relative a ogni singolo servizio)
     * che contiene solo i nodi relativi alle nazioni selezionate
     */
    public static Vector<Node> searchCountry(List<String> country, List<Node> nodes)  {
        Vector<Node> result=new Vector<>();
        for (Node node : nodes) {
            for (String s : country) {
                if (node.country.compareTo(s) == 0)
                    result.add(node);
            }
        }
        return result;
    }


    /**
     * Metodo per filtrare, da una lista di nodi (oggetto che contiene tutte le informazioni relative a ogni singolo
     * servizio), solo quelli relativi ai tipi di servizio che sono stati selezionati
     *
     * @param type contiene i tipi di servizio che sono stati selezionati
     *
     * @param nodes vettore nodi (oggetto che contiene tutte le informazioni relative a ogni singolo servizio)
     * dal quale filtrare quelli relativi ai tipi di servizio selezionati
     *
     * @return vettore di nodi (oggetto che contiene tutte le informazioni relative a ogni singolo servizio)
     * che contiene solo i nodi relativi ai tipi di servizio selezionati
     */
    public static Vector<Node> searchType(List<String> type, List<Node> nodes)  {
        Vector<Node> result=new Vector<>();
        for (Node node : nodes) {
            for (String s : type) {
                if (node.type_of_service.compareTo(s) == 0)
                    result.add(node);
            }
        }
        return result;
    }
    /**
     * Metodo per filtrare, da una lista di nodi (oggetto che contiene tutte le informazioni relative a ogni singolo
     * servizio), solo quelli relativi a provider che sono stati selezionati
     *
     * @param provider contiene i provider che sono stati selezionati
     *
     * @param nodes vettore nodi (oggetto che contiene tutte le informazioni relative a ogni singolo servizio)
     * dal quale filtrare quelli relativi a provider selezionati
     *
     * @return vettore di nodi (oggetto che contiene tutte le informazioni relative a ogni singolo servizio)
     * che contiene solo i nodi relativi a provider selezionati
     */
    public static Vector<Node> searchProvider(List<String> provider, List<Node> nodes)  {
        Vector<Node> result=new Vector<>();
        for (Node node : nodes) {
            for (String s : provider) {
                if (node.provider.compareTo(s) == 0)
                    result.add(node);
            }
        }
        return result;
    }
    /**
     * Metodo per filtrare, da una lista di nodi (oggetto che contiene tutte le informazioni relative a ogni singolo
     * servizio), solo quelli relativi agli stati del servizio che sono stati selezionati
     *
     * @param status contiene gli stati dei servizi che sono stati selezionati
     *
     * @param nodes ettore nodi (oggetto che contiene tutte le informazioni relative a ogni singolo servizio)
     * dal quale filtrare quelli relativi agli stati dei servizi selezionati
     *
     * @return vettore di nodi (oggetto che contiene tutte le informazioni relative a ogni singolo servizio)
     * che contiene solo i nodi relativi agli stati dei servizi selezionati
     */
    public static Vector<Node> searchStatus(List<String> status, List<Node> nodes) {
        Vector<Node> result=new Vector<>();
        for (Node node : nodes) {
            for (String s : status) {
                if (node.state.compareTo(s) == 0)
                    result.add(node);
            }
        }
        return result;
    }

}
