package code.eutrustservicesapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Classe che definisce l'oggetto grafico utilizzato per rappresentare la singola nazione
 */
public class GraphicNation {
    @FXML
    private VBox box;
    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView image;

    @FXML
    private Label initial,name;

    private AtomicBoolean clicked= new AtomicBoolean(false); //boolean che indica se l'oggetto è stato cliccato

    private static final Background background1=new Background(new BackgroundFill(Color.valueOf("#f0f8ff"), CornerRadii.EMPTY, Insets.EMPTY));
    private static final Background background2=new Background(new BackgroundFill(Color.valueOf("#c0d6e4"), CornerRadii.EMPTY, Insets.EMPTY));
    /**
     * Costruttore che inizializza una grafica vuota
     */
    public GraphicNation() {}

    /**
     * Costruttore che inizializza la grafica in base alla nazione specificata nei parametri
     * @param name  Nome della nazione
     * @param initial Sigla della nazione
     * @throws IOException Possibili eccezioni lanciate da fxmlLoader.load()
     * @throws IllegalParameters Possibile eccezione lanciate in caso di parametri in ingresso sono null o sigle con piu di due lettere
     */
    public GraphicNation(String name,String initial) throws IOException,IllegalParameters
    {
        FXMLLoader fxmlLoader = new FXMLLoader(EUTrustServicesApplication.class.getResource("ListNations.fxml"));
        pane=fxmlLoader.load();
        GraphicNation graphicNation = fxmlLoader.getController();
        graphicNation.setData(name,initial);
        box=graphicNation.box;
        clicked=graphicNation.clicked;
        this.initial=graphicNation.initial;
        this.name=graphicNation.name;
        image=graphicNation.image;
    }
    //inizializzazione dei vari elementi a seconda della nazione indicata
    private void setData(String name,String initial) throws IllegalParameters{
        if(name==null) throw new IllegalParameters();
        if(initial==null) throw new IllegalParameters();
        if(initial.length()!=2) throw new IllegalParameters();
        this.name.setText(name);
        this.initial.setText(initial);
        image.setImage(ImagesNations.map().get(initial));
        box.getStyleClass().add("color-palette");
        box.setBackground(background1);
    }
    /**
     * Metodo per selezionare tutte le nazioni, aggiungendole anche alla lista che verrà poi
     * usata per eseguire la ricerca
     */
    public static void total_selection(){
        for(int i=0;i<Nations.nations.size();i++){
            Nations.nations.elementAt(i).select();
        }
        Nations.selected_nations.addAll(Api.countries());
    }
    /**
     * Metodo per deselezionare tutte le nazioni, rimuovendole anche dalla lista che verrà poi
     * usata per eseguire la ricerca
     */
    public static void reset_total_selection()
    {
        for(int i=0;i<Nations.nations.size();i++){
            Nations.nations.elementAt(i).reset_selection();
        }
        Nations.selected_nations.clear();
    }
    //deseleziona un singolo oggetto nazione
    private void reset_selection()
    {
        clicked.set(false);
        box.setBackground(background1);
    }
    //seleziona un singolo oggetto nazione
    private void select()
    {
        clicked.set(true);
        box.setBackground(background2);
    }
    /**
     * Metodo per selezionare o deselezionare l'oggetto, con un click del mouse,
     * aggiungendolo (se selezionato) o rimuovendolo (se deselezionato) anche alla lista
     * che verrà poi usata per eseguire la ricerca
     */
    public void selectNation() {
        if (!clicked.get()) {
            box.setBackground(background2);
            Nations.selected_nations.add(initial.getText());
        } else {
            box.setBackground(background1);
            Nations.selected_nations.remove(initial.getText());
            }
        clicked.set(!clicked.get());
    }

    /**
     * Ritorna il pannello caratteristico dell'oggetto
     * @return Il pannello caratteristico dell'oggetto
     */
    public AnchorPane pane() {return pane;}

    /**
     * Ritorna la descrizione della nazione col formato "sigla nome"
     * @return Stringa contenente "sigla nome" della nazione
     */
    @Override
    public String toString(){
        return initial.getText() + " " + name.getText();
    }
}


