package code.eutrustservicesapplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
/**
 * Classe che definisce l'oggetto grafico che rappresenta il singolo risultato della
 * ricerca
 */
public class GraphicResult {
    @FXML
    private ImageView image_nation;
    @FXML private Label service, country, type_of_service, state, provider;
    @FXML private AnchorPane anchorItem;
    private static final Background background=new Background(new BackgroundFill(Color.valueOf("#f0f8ff"), CornerRadii.EMPTY, Insets.EMPTY));
    /**
     * Costruttore che inizializza una grafica vuota
     */
    public GraphicResult(){}

    /**
     * Costruttore che inizializza la grafica del risultato a seconda del nodo(oggetto che contiene tutte le informazioni relative a ogni singolo servizio) ricevuto come parametro
     * @param node Oggetto contenente servizio, nazione, tipo di servizio, stato, provider
     * @throws IOException Possibili eccezioni lanciate da fxmlLoader.load()
     * @throws IllegalParameters Possibile eccezione lanciate in caso di nodo in ingresso null o con una sigla della nazione associata con dimensione diversa da due cifre
     */
    public GraphicResult(Node node) throws IOException,IllegalParameters
    {
        FXMLLoader fxmlLoader = new FXMLLoader(EUTrustServicesApplication.class.getResource("ListResult.fxml"));
        anchorItem=fxmlLoader.load();
        GraphicResult graphicResult = fxmlLoader.getController();
        graphicResult.setData(node);
        image_nation=graphicResult.image_nation;
        service=graphicResult.service;
        provider=graphicResult.provider;
        country=graphicResult.country;
        type_of_service=graphicResult.type_of_service;
        state=graphicResult.state;
    }
    //Inizializza e aggiunge al vettore dei risultati l'oggetto grafico che rappresenta il singolo risultato della ricerca, contenente le caratteristiche del nodo
    private void setData(Node selected) throws IllegalParameters{
        if(selected==null) throw new IllegalParameters();
        if(selected.country.length()!=2) throw new IllegalParameters();
        image_nation.setImage(ImagesNations.map().get(selected.country));
        service.setText(service.getText()+" "+ selected.service);
        country.setText(country.getText()+" "+ selected.country);
        type_of_service.setText(type_of_service.getText()+" "+ selected.type_of_service);
        state.setText(state.getText()+" "+ selected.state);
        provider.setText(provider.getText()+" "+ selected.provider);
        anchorItem.getStyleClass().add("color-palette");
        anchorItem.setBackground(background);
    }
    /**
     * Ritorna il pannello caratteristico dell'oggetto
     * @return Il pannello caratteristico dell'oggetto
     */
    public AnchorPane pane(){return anchorItem;}

    /**
     * Ritorna la descrizione del risultato col formato "servizio sigla_nazione tipo_servizio stato provider"
     * @return Stringa contenente "servizio sigla_nazione tipo_servizio stato provider" del risultato
     */
    @Override
    public String toString(){
        return service.getText() + " " + country.getText() + " " +  type_of_service.getText() + " " + state .getText() + " " + provider.getText();
    }
}
