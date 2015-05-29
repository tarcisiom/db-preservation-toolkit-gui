package pt.keep.dbptk.gui;

import java.io.File;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.siard.in.SIARDImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.siard.out.SIARDExportModule;

public class SIARD {
	@FXML
	private ComboBox<String> outputExpansion, outputModule;
	@FXML
	private Button btnNext, btnCancel, btnBrowse;
	@FXML
	private Label labelFile;
	@FXML
	private FileChooser fileChooser = new FileChooser();
	
	
	
	@FXML
	private void btnBrowseAction(ActionEvent event) throws Exception {
		File file;
		if(App.importpage){
			
			fileChooser.setTitle("This is my file chooser");
			
			file = fileChooser.showOpenDialog(null);
			
		}
		else{
			fileChooser.setTitle("This is my file chooser");
			
			file = fileChooser.showSaveDialog(null);
		}
		if (file != null) {
			labelFile.setText(file.getPath());
		}
	}
	
	@FXML
	public void btnCancelAction(ActionEvent event) throws Exception {
		
		
		if (App.importpage) {

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
		else{
		    Navigator.loadVista(App.DBMSCHOOSER);
		}
	}
	
	@FXML
	public void btnNextAction(ActionEvent event) throws Exception {
		String filepath = (String) labelFile.getText();
	    String expansion = (String)outputExpansion.getSelectionModel().getSelectedItem();
		String module = (String) outputModule.getSelectionModel().getSelectedItem();
		String errorMessage = "";
		boolean sucess = false;
		if (App.importpage) {
			DatabaseImportModule importModule= null;
			
			if (labelFile.getText() == null || labelFile.getText().length() == 0) {
	            errorMessage += "File not selected!\n"; 
	        }
	        if (module== null || module.length() == 0) {
	            errorMessage += "Import Module not selected!\n"; 
	        }
	        if (expansion == null || expansion.length() == 0) {
	            errorMessage += "Expansion not selected!\n"; 
	        }
	        if (errorMessage.length() == 0) {
	        	if (module.equalsIgnoreCase("SIARD-E 2.0")) {
					importModule = new SIARDImportModule(new File(filepath));
				} 
	        	sucess = true;
	        } else {
	            // Show the error message.
	            
	            new DialogMessage(errorMessage);
	            
	        }
	        
	        if(sucess){
				Navigator.setImportModule(importModule);
				Navigator.addNodes(App.DBMSCHOOSER);
				Navigator.loadVista(App.DBMSCHOOSER);
				
				
	        }
		}
		else{
			DatabaseHandler exportModule = null;
			
	        if (labelFile.getText() == null || labelFile.getText().length() == 0) {
	            errorMessage += "File not selected!\n"; 
	        }
	        if ((String) outputModule.getSelectionModel().getSelectedItem() == null || outputModule.getSelectionModel().getSelectedItem().length() == 0) {
	            errorMessage += "Import Module not selected!\n"; 
	        }
	        if (errorMessage.length() == 0) {
	        	if (module.equalsIgnoreCase("SIARD-E 2.0")) {
					exportModule = new SIARDExportModule(new File(filepath));
				} 
	        	sucess = true;
	        } else {
	            // Show the error message.
	            new DialogMessage(errorMessage);
	            
	        }
			if(sucess){
	
				Navigator.setExportModule(exportModule);
				//Navigator.addNodes(App.IMPORTDATA);
				//Navigator.loadVista(App.IMPORTDATA);
				
				FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setResources(ResourceBundle.getBundle(App.bundle));
		        Node root = (Node) fxmlLoader.load(getClass().getResource(App.IMPORTDATA).openStream());
		        
				ImportData impD = fxmlLoader.getController();
				
		        Navigator.loadAfter(root);
				fxmlLoader.setController(impD);
				
				new Thread(new Runnable() {

		            @Override
		            public void run() {
		                impD.exportDB();

		            }
		        }).start();
		        
				
			}
		}
	}
	
	
	

}
