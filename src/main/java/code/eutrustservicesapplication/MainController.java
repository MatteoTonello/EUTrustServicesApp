package code.eutrustservicesapplication;

import java.io.IOException;
import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Classe principale che gestisce il funzionamento dell'applicazione e gestisce la grafica
 */
public class MainController {

    @FXML private ListView<CheckBox> list;
    @FXML private TextField search;
    @FXML private Label error, error_api, description;
    @FXML private Button select_all,deselect_all, list_of_states, list_of_type, research, next;
    @FXML private ScrollPane scrollPane1, scrollPane2;
    @FXML private TilePane itemsContainer;
    @FXML private Pane menu;
    @FXML private VBox main_vbox;
    @FXML private HBox top_box;
    private int levelSearch=0; //indica il livello della ricerca
    private List<Node> temporaryResults; //contiene i risultati temporanei della ricerca dopo ogni passaggio
    private Vector<CheckBox> checkBoxVector; //contiene le checkbox che sono inserite nella listView

    /**
     * Costruttore vuoto
     */
    public MainController()
    {
        list=new ListView<>();
        search=new TextField();
        error=new Label(); error_api=new Label(); description=new Label();
        select_all=new Button();deselect_all=new Button(); list_of_states=new Button(); list_of_type=new Button(); research=new Button(); next=new Button();
        scrollPane1=new ScrollPane(); scrollPane2=new ScrollPane();
        itemsContainer=new TilePane();
        menu=new Pane();
        main_vbox=new VBox();
        top_box=new HBox();
    }

    /**
     * Inizializza la schermata principale dell'applicazione e tutti i dati di cui essa necessita, grazie alla chiamata all'api
     * @throws IOException Possibili eccezioni lanciate da fxmlLoader.load()
     * @throws IllegalParameters Possibili eccezioni lanciate da Nations.initialize() o TypeofService.initialize()
     */
    public void initialize() throws IOException,IllegalParameters
    {
        try
        {
            Api.start();
        }catch (Exception e)
        {
            error_api.setVisible(true);
            list_of_type.setVisible(false);
            list_of_states.setVisible(false);
            research.setVisible(false);
            return;
        }
        ImagesNations.initialize(Api.countries());
        TypeofService.initialize(Api.type_of_services());
        temporaryResults = Api.totalNodes();
        checkBoxVector =new Vector<>();
        Nations.initialize(Api.name_countries(),Api.countries());
    }
    //gestisce la grafica e la creazione degli oggetti grafici rappresentanti i risultati
    private void result() throws IOException,IllegalParameters
    {
        scrollPane1.setVvalue(0);
        description.setText("RISULTATI");
        itemsContainer.getChildren().clear();
        sp1_to_sp2(false);
        Results.initialize(temporaryResults);
        for(int i=0;i<Results.resultsPane().size();i++) itemsContainer.getChildren().add(Results.resultsPane().get(i));
        select_all.setVisible(false);
        deselect_all.setVisible(false);
        next.setVisible(false);
    }
    //inizializza la checkbox list prendendo i dati da un vettore di stringhe
    private void initializeCBList(Vector<String> vector)
    {
        list.getItems().clear();
        checkBoxVector.clear();
        for(int i=0;i<vector.size();i++){
            checkBoxVector.add(new CheckBox(vector.elementAt(i)));
        }
        list.getItems().addAll(checkBoxVector);
   }

    /**
     *Metodo per la ricerca utilizzando la barra di ricerca
     */
    public void research ()
    {
        String word=search.getText();
        select_all.setVisible(word.equals(""));
        deselect_all.setVisible(word.equals(""));
        if(levelSearch==0) //schermata con nazioni
        {
            itemsContainer.getChildren().clear();
            for (int i = 0; i< Api.countries().size(); i++) {
                if(Api.countries().get(i).toUpperCase().startsWith(word.toUpperCase())|| Api.name_countries().get(i).toUpperCase().startsWith(word.toUpperCase()))
                    itemsContainer.getChildren().add(Nations.nations.elementAt(i).pane());
            }
        }
        if(levelSearch==1) //schermata con checkbox
        {
            list.getItems().clear();
            for (int i = 0; i< checkBoxVector.size(); i++){
                if(checkBoxVector.elementAt(i).getText().toUpperCase().startsWith(word.toUpperCase()))
                    list.getItems().add(checkBoxVector.elementAt(i));
            }
        }
        if(levelSearch==2) //schermata con i risultati
        {
            itemsContainer.getChildren().clear();
            for (int i = 0; i< temporaryResults.size(); i++) {
                if(temporaryResults.get(i).service.toUpperCase().startsWith(word.toUpperCase()))
                    itemsContainer.getChildren().add(Results.resultsPane().get(i));
            }
        }
    }

    /**
     *Ritorno alla schermata principale dell'applicazione
     */
    public void home()
    {
        switch_home_search(false);
        sp1_to_sp2(false);
        error.setVisible(false);
        levelSearch=0;
        temporaryResults = Api.totalNodes();
        itemsContainer.getChildren().clear();
        search.clear();
        GraphicNation.reset_total_selection();
    }

    /**
     * Metodo per selezionare i singoli checkBox
     */
    public void selectCB(){
        int ob=list.getSelectionModel().getSelectedIndices().get(0);
        list.getItems().get(ob).setSelected(!list.getItems().get(ob).isSelected());
    }

    /**
     * Selezione di tutti gli elementi con il relativo bottone
     */
    public void selectAll()
    {
        if (levelSearch == 0) { //Selezione delle nazioni
            GraphicNation.total_selection();
        }
        if (levelSearch == 1) { //Selezione di provider, tipi e stati di servizi
            for (int i = 0; i < list.getItems().size(); i++)
                list.getItems().get(i).setSelected(true);
        }
    }

    /**
     * Deselezione di tutti gli elementi con il relativo bottone
     */
    public void deselectAll()
    {
        if (levelSearch == 0) { //Selezione delle nazioni
            GraphicNation.reset_total_selection();
        }
        if (levelSearch == 1) { //Selezione di provider, tipi e stati di servizi
            for (int i = 0; i < list.getItems().size(); i++)
                list.getItems().get(i).setSelected(false);
        }
    }

    /**
     * Gestisce il bottone "Avanti" e il cambio schermate, richiamando quando serve i metodi di ricerca
     * @throws IOException Possibili eccezioni lanciate da result();
     * @throws IllegalParameters Possibili eccezioni lanciate da result()
     */
    public void next() throws IOException,IllegalParameters
    {
        select_all.setVisible(true);
        deselect_all.setVisible(true);
        error.setVisible(false);
        Vector<String> tmp_vector=new Vector<>();
        if(levelSearch==0) //Gestisce la prima selezione delle nazioni
        {
            //se non è stato selezionato nulla mostra un messaggio di errore
           try {
               if(Nations.selected_nations.size()==0)
                   throw new NoElementException();
           }catch (NoElementException e) {
               select_all.setVisible(search.getText().equals(""));
               deselect_all.setVisible(search.getText().equals(""));
               error.setVisible(true);
               return;
           }
           temporaryResults= ResearchMethods.searchCountry(Nations.selected_nations, temporaryResults);
            for (Node temporaryResult : temporaryResults) {
                if (!tmp_vector.contains(temporaryResult.provider)) tmp_vector.add(temporaryResult.provider);
            }
           description.setText("PROVIDER");
           levelSearch++;
           sp1_to_sp2(true);
        }
       else if(levelSearch==1) //Gestisce selezioni successive in lista
       {
           Vector<String> selected_elements = new Vector<>();
           for(int i=0;i<checkBoxVector.size();i++) {
               if (checkBoxVector.elementAt(i).isSelected()) {
                   selected_elements.add(checkBoxVector.elementAt(i).getText());
               }
           }
           //se non è stato selezionato nulla mostra un messaggio di errore
           try {
               if(selected_elements.size()==0)
                   throw new NoElementException();
           }catch (NoElementException e) {
               error.setVisible(true);
               return;
           }
           //se i selezionati sono provider
           if(Api.providers().contains(selected_elements.elementAt(0))){
               temporaryResults= ResearchMethods.searchProvider(selected_elements, temporaryResults);
               for (Node temporaryResult : temporaryResults) {
                   if (!tmp_vector.contains(temporaryResult.type_of_service))
                       tmp_vector.add(temporaryResult.type_of_service);
               }
               description.setText("TIPO SERVIZIO");
           }
           //se i selezionati sono tipi di servizi
           if(Api.type_of_services().contains(selected_elements.elementAt(0))){
               temporaryResults= ResearchMethods.searchType(selected_elements, temporaryResults);
               for (Node temporaryResult : temporaryResults) {
                   if (!tmp_vector.contains(temporaryResult.state)) tmp_vector.add(temporaryResult.state);
               }
               description.setText("STATO SERVIZIO");
           }
           //se i selezionati sono gli stati dei servizi
           if(Api.status().contains(selected_elements.elementAt(0))){
               temporaryResults= ResearchMethods.searchStatus(selected_elements, temporaryResults);
               levelSearch++;
               result();
               return;
           }
       }
       this.initializeCBList(tmp_vector);
       search.clear();
    }

    /**
     * Cambio schermata dal menu per vedere la lista di nazioni, bottone "LISTA STATI"
     */
    public void list_nation_view()
    {
        home_to_list();
        for(int i = 0; i<Nations.nations.size(); i++) itemsContainer.getChildren().add(Nations.nations.elementAt(i).pane());
        description.setText("PAESI");
    }

    /**
     * Cambio schermata dal menu per vedere la lista dei tipi di servizi, bottone "LISTA TIPI SERVIZI"
     */
    public void list_type_view()
    {
        home_to_list();
        itemsContainer.getChildren().addAll(TypeofService.typeOfServicesPane());
        description.setText("TIPI SERVIZI");
    }

    /**
     * Cambio schermata dal menu per fare la ricerca, bottone "RICERCA"
     */
    public void search_view()
    {
        search.setVisible(true);
        switch_home_search(true);
        for(int i = 0; i<Nations.nations.size(); i++) itemsContainer.getChildren().add(Nations.nations.elementAt(i).pane());
        description.setText("PAESI");
        search.setVisible(true);
        scrollPane1.setVvalue(0);
        itemsContainer.setMouseTransparent(false);
    }
    //cambia la schermata da home a ricerca e viceversa
    private void switch_home_search(boolean choice){
        top_box.setVisible(choice);
        main_vbox.setVisible(choice);
        menu.setVisible(!choice);
        next.setVisible(choice);
        select_all.setVisible(choice);
        deselect_all.setVisible(choice);
    }
    //cambia la schermata da home a visualizzazione liste
    private void home_to_list(){
        top_box.setVisible(true);
        search.setVisible(false);
        main_vbox.setVisible(true);
        menu.setVisible(false);
        scrollPane1.setVvalue(0);
        itemsContainer.setMouseTransparent(true);
    }
    //cambia da scrollPane1(contenente gli oggetti grafici) a scrollPane2(contenente la listView)
    private void sp1_to_sp2(boolean choice){
        scrollPane1.setVisible(!choice);
        scrollPane2.setVisible(choice);
    }
}
