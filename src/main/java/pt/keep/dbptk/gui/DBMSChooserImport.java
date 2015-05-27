package pt.keep.dbptk.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;

public class DBMSChooserImport implements Initializable, Panes {
	@FXML
	public ComboBox<String> comboChooser;
	
	@FXML
	private Pane paneFields;
	
	@FXML
	private Button btnCancel, btnNext;

	public Map<String,DBMSPane> dbmspanes = new HashMap<String,DBMSPane>();
	public Map<String,String> dbmsfxml = new HashMap<String,String>();
	

	
	
	public String selectedDBMS;
	
	public void setVista(Node node) {
		paneFields.getChildren().setAll(node);
		
	}	
	
	@FXML
	public void comboChangeAction(ActionEvent event) throws Exception {
		selectedDBMS = (String) comboChooser.getSelectionModel().getSelectedItem();
		
		DBMSNavigator.setMainPane(this);
		FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setResources(ResourceBundle.getBundle(App.bundle));
        String fxml = dbmsfxml.get(selectedDBMS);
       
        Parent root = (Parent) fxmlLoader.load(getClass().getResource(fxml).openStream());
        
		dbmspanes.put(selectedDBMS, fxmlLoader.getController());
		setVista(root);
		
	}

	@FXML
	public void btnCancelAction(ActionEvent event) throws Exception {
		
		Navigator.loadVista(App.SIARDIMPORT);
	}
	
	
	
	@FXML
	public void btnNextAction(ActionEvent event) throws Exception {
		
		DatabaseHandler module = null;
		boolean sucess = false;
		
		DBMSPane dbmsPane = dbmspanes.get(selectedDBMS);
		if(dbmsPane.isInputValid()){
			module = dbmsPane.getExportModule();
			sucess =true;
		}
		if(sucess){
			Navigator.setExportModule(module);
	        Navigator.addNodes(App.DATAIMPORT);
			Navigator.loadVista(App.DATAIMPORT);
		}
		
		
	}
	
	public static Map<String, String> loadMaps() throws FileNotFoundException{
		BufferedReader reader = null;
		Map<String, String> map = new HashMap<String, String>();
		
		File file = new File("src/main/java/pt/keep/dbptk/gui/DBMSChooserImportProperties");
		
		FileReader fileReader = new FileReader(file);
		
		reader = new BufferedReader(fileReader);
		
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
