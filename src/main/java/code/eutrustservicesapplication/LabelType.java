package code.eutrustservicesapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Classe che descrive l'oggetto grafico rappresentante il tipo di servizio
*/
public class LabelType {
    @FXML
    private Label type;
    @FXML
    private AnchorPane pane;

    /**
     * Costruttore vuoto
     */
    public LabelType(){}

    /**
     * Costruttore che inizializza la grafica del tipo di servizio a seconda del parametro passato
     * @param type Tipo di servizio di cui creare la grafica
     * @throws IOException Possibili eccezioni lanciate da fxmlLoader.load()
     * @throws IllegalParameters Possibile eccezione lanciate in caso di parametri d'ingresso null
     */
    public LabelType(String type) throws IOException,IllegalParameters {
        if(type==null) throw new IllegalParameters();
        FXMLLoader fxmlLoader = new FXMLLoader(EUTrustServicesApplication.class.getResource("ListTypeOfServices.fxml"));
        pane=fxmlLoader.load();
        LabelType l=fxmlLoader.getController();
        l.type.setText(type);
        this.type=l.type;
    }

    /**
     * Ritorna il pannello caratteristico dell'oggetto
     * @return Il pannello caratteristico dell'oggetto
     */
    public AnchorPane pane(){return pane;}

    /**
     * Ritorna la descrizione del tipo di servizio in formato
     * @return Stringa contenente il nome del tipo di servizio
     */
    @Override
    public String toString(){
        return type.getText();
    }

}
