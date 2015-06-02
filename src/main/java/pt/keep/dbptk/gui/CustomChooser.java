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
import javafx.stage.Stage;

public class CustomChooser implements Initializable{

	@FXML 
	public Button btnCancel, btnNext;
	@FXML 
	public ComboBox<String> exportChooser;
	@FXML
	public ComboBox<String> importChooser;
	
	public Map<String,String> fxmlImport = new HashMap<String,String>();
	
	public Map<String,String> fxmlExport = new HashMap<String,String>();
	
	
	
	
	@FXML
	public void btnCancelAction(ActionEvent event) throws Exception {
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
	
	
	
	@FXML
	public void btnNextAction(ActionEvent event) throws Exception {
		
		
		String selectedImport = (String) importChooser.getSelectionModel().getSelectedItem();
		String selectedExport = (String) exportChooser.getSelectionModel().getSelectedItem();
		String errorMessage = "";
		
		if ((String) selectedImport == null || selectedImport.length() == 0) {
            errorMessage += "Import Module not selected!\n"; 
        }
        if ((String) selectedExport == null || selectedExport.length() == 0) {
            errorMessage += "Export Module not selected!\n"; 
        }
        if (errorMessage.length() == 0) {
        	// TODO novo Pane vai suportar DBMSPane
        	Navigator.setImportFxml(fxmlImport.get(selectedImport));
        	Navigator.setExportFxml(fxmlExport.get(selectedExport));
        	Navigator.addNodes(App.PANESHOW);
        	
        	
    		Navigator.loadVista(App.PANESHOW);
			
        	
			//DBMSNavigator.setMainPane(loader.getController());
			
			
        
        } else {
            // Show the error message.
            
            new DialogMessage(errorMessage);
            
        }
        
        
		
		
	}

	public static Map<String, String> loadMaps(boolean importP) throws FileNotFoundException{
		Map<String, String> map = new HashMap<String, String>();
		String mods;
		if(importP){
			mods = (String) App.props.get("importModules");
		}
		else{
			mods = (String) App.props.get("exportModules");
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
			fxmlImport.putAll(loadMaps(true));
			fxmlExport.putAll(loadMaps(false));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		importChooser.getItems().clear();
		for (String key : fxmlImport.keySet()) {
			importChooser.getItems().add(key);
		}
		exportChooser.getItems().clear();
		for (String key : fxmlExport.keySet()) {
			exportChooser.getItems().add(key);
		}
	
	}
	

}
