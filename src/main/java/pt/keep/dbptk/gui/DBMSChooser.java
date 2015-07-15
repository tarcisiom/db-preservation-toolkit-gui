package pt.keep.dbptk.gui;




import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.apache.hadoop.hdfs.server.namenode.FSImageFormat.Loader;

import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;

public class DBMSChooser implements Initializable, Panes{

	
	@FXML
	public ComboBox<String> comboChooser;
	
	@FXML
	private Pane paneFields;
	
	@FXML
	private Button btnCancel, btnNext ;
	
	public Map<String,DBMSPane> dbmspanes = new HashMap<String,DBMSPane>();
	public Map<String,String> dbmsfxml = new HashMap<String,String>();
	public String selectedDBMS;

	public void setVista(Node node) {
		paneFields.getChildren().setAll(node);
		
	}	
	
	@FXML
	public void comboChangeAction(ActionEvent event) throws Exception {
		selectedDBMS = (String) comboChooser.getSelectionModel().getSelectedItem();		
		ClassLoader classLoader = Loader.class.getClassLoader();
        URL fxmlURL = classLoader.getResource(dbmsfxml.get(selectedDBMS));
		FXMLLoader loader = new FXMLLoader(fxmlURL);
		loader.setResources(ResourceBundle.getBundle(App.bundle));
		Parent root = loader.load();
		dbmspanes.put(selectedDBMS, loader.getController());
		setVista(root);
	}

	@FXML
	public void btnCancelAction(ActionEvent event) throws Exception {
		if(App.importpage){
			Navigator.loadVista("import",App.SIARDPAGE);
		}
		else {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setResources(ResourceBundle.getBundle(App.bundle));
	        Parent root = (Parent) fxmlLoader.load(getClass().getResource("Main.fxml").openStream());
	     	Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		}
	}
	
	@FXML
	public void btnNextAction(ActionEvent event) throws Exception {
		boolean sucess = false;
		DBMSPane dbmsPane = dbmspanes.get(selectedDBMS);
		if(App.importpage){
			DatabaseHandler module = null;
			if(dbmsPane.isInputValid()){
				module = dbmsPane.getExportModule();
				sucess =true;
			}
			if(sucess){
				FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setResources(ResourceBundle.getBundle(App.bundle));
		        Node root = (Node) fxmlLoader.load(getClass().getResource(App.IMPORTDATA).openStream());
		 		ImportData impD = fxmlLoader.getController();
				DatabaseHandlerGUI expD = new DatabaseHandlerGUI(module);
				expD.registerObserver(impD);
				Navigator.setExportModule(expD);
				Navigator.loadAfter(root,"import",App.IMPORTDATA);
				fxmlLoader.setController(impD);
			}
		}
		else{
			
			DatabaseImportModule module = null;
			if(dbmsPane.isInputValid()){
				module = dbmsPane.getImportModule();
				sucess = true;
			}
			if(sucess){
				Navigator.setImportModule(module);
				Navigator.setCurrentNode(App.SIARDPAGE);
				Navigator.addNodes(App.SIARDPAGE);
			    Navigator.loadVista("export",App.SIARDPAGE);
			}
		}
	}
	
	
	public static Map<String, String> loadMaps() throws FileNotFoundException{
		Map<String, String> map = new HashMap<String, String>();
		String mods;
		if(App.importpage){
			mods = (String) App.props.get("exportModules");
		}
		else{
			mods = (String) App.props.get("importModules");
		}
		String[] modules =  mods.split(", ");
		for (int i = 0; i < modules.length; i++) {
		   map.put(App.props.getProperty(modules[i]+".label"),App.props.getProperty(modules[i]+".fxml"));
		}
        return map;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			dbmsfxml.putAll(loadMaps());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		comboChooser.getItems().clear();
		for (String key : dbmsfxml.keySet()) {
			comboChooser.getItems().add(key);
		}  
	}
	
}
