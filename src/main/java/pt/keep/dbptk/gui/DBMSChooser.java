package pt.keep.dbptk.gui;




import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
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

import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;



public class DBMSChooser implements Initializable, Panes{

	
	@FXML
	public ComboBox<String> comboChooser;
	
	@FXML
	private Pane paneFields;
	
	@FXML
	private Button btnCancel, btnNext;

	
	public Map<String,DBMSPane> dbmspanes = new HashMap<String,DBMSPane>();
	public Map<String,String> dbmsfxml = new HashMap<String,String>();
	
	
	private String bundle = "pt/keep/dbptk/gui/bundle_en.properties";
	
	
	public String selectedDBMS;
	
	public void setVista(Node node) {
		paneFields.getChildren().setAll(node);
		
	}	
	
	@FXML
	public void comboChangeAction(ActionEvent event) throws Exception {
		selectedDBMS = (String) comboChooser.getSelectionModel().getSelectedItem();
		
		DBMSNavigator.setMainPane(this);
		ClassLoader classLoader = Loader.class.getClassLoader();
        InputStream inputStream = classLoader.getResource(bundle).openStream();
		ResourceBundle bundle1 = new PropertyResourceBundle(inputStream);
		URL fxmlURL = classLoader.getResource(dbmsfxml.get(selectedDBMS));
		FXMLLoader loader = new FXMLLoader(fxmlURL, bundle1);
		Parent root = loader.load();
		
		dbmspanes.put(selectedDBMS, loader.getController());
		setVista(root);
		
		
	}

	@FXML
	public void btnCancelAction(ActionEvent event) throws Exception {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		ClassLoader classLoader = Loader.class.getClassLoader();
		URL fxmlURL = classLoader.getResource("pt/keep/dbptk/gui/Main.fxml");
		InputStream inputStream = classLoader.getResource(bundle).openStream();
		ResourceBundle bundle1 = new PropertyResourceBundle(inputStream);
		FXMLLoader loader = new FXMLLoader(fxmlURL, bundle1);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void btnNextAction(ActionEvent event) throws Exception {
		
		DatabaseImportModule module = null;
		boolean sucess = false;
		
		DBMSPane dbmsPane = dbmspanes.get(selectedDBMS);
		if(dbmsPane.isInputValid()){
			module = dbmsPane.getImportModule();
			sucess = true;
		}
		if(sucess){
			
			
			Navigator.setImportModule(module);
			//Navigator.setPrevious(paneFields.getParent());
			Navigator.addNodes(App.SIARD);
		      
		       
			Navigator.loadVista(App.SIARD);
			
		}
		
		
	}
	
	
	public static Map<String, String> loadMaps() throws FileNotFoundException{
		BufferedReader reader = null;
		File file = new File("src/main/java/pt/keep/dbptk/gui/DBMSChooserProperties");
		reader = new BufferedReader(new FileReader(file));
		Map<String, String> map = new HashMap<String, String>();
		
        String line = null;
        try {
			while ((line = reader.readLine()) != null) {
			    if (line.contains(";")) {
			        String[] strings = line.split("; ");
			        map.put(strings[0], strings[1]);
			        
			    }
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return map;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			dbmsfxml.putAll(loadMaps());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboChooser.getItems().clear();
		for (String key : dbmsfxml.keySet()) {
			comboChooser.getItems().add(key);
		}
     
        
		
	}
	
}
