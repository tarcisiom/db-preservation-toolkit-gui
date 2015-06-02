package pt.keep.dbptk.gui;




import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.hadoop.hdfs.server.namenode.FSImageFormat.Loader;

import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

public class PaneShow implements Panes,Initializable{

	@FXML 
	public Button btnNext, btnCancel;
	@FXML 
	public Pane paneFields;
	@FXML 
	public Label lblTitle;
	
	public DBMSPane currentPane;

	public boolean importPane = true;
	
	public Node previous;
	
	public String importTitle;
	
	@Override
	public void setVista(Node node) {
		// TODO Auto-generated method stub
		this.paneFields.getChildren().setAll(node);
	}
	
	public void setCurrentPane(DBMSPane pane){
		this.currentPane= pane;
	}
	public DBMSPane getCurrentPane(){
		return this.currentPane;
	}
	
	
	@FXML public void btnCancelAction(ActionEvent event) {
		if (importPane) {
			Navigator.loadVista(App.CUSTOMCHOOSER);
		}
		else{
			NodeImport();
			
		}
	}

	@FXML 
	public void btnNextAction(ActionEvent event) throws Exception {
		boolean sucess = false;
		if(importPane){
			
			DatabaseImportModule module = null;
			
			
			if(currentPane.isInputValid()){
				module = currentPane.getImportModule();
				sucess = true;
			}
			if(sucess){
				Navigator.setImportModule(module);
				/*
				App.importpage = true;
				importPane= false;
				ClassLoader classLoader = Loader.class.getClassLoader();
		        URL fxmlURL = classLoader.getResource(Navigator.getExportFxml());
				FXMLLoader loader = new FXMLLoader(fxmlURL);
				loader.setResources(ResourceBundle.getBundle(App.bundle));
				Parent root = null;
				try {
					root = loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				currentPane = loader.getController();
				
				lblTitle.setText(loader.getController().getClass().getSimpleName());
				setVista(root);
			*/

				previous = paneFields.getChildren().get(0);
				
				NodeExport();
			}
		}
		else{
			
			DatabaseHandler module = null;
			
			
			if(currentPane.isInputValid()){
				module = currentPane.getExportModule();
				sucess = true;
			}
			if(sucess){
				Navigator.setExportModule(module);
				Navigator.addNodes(App.IMPORTDATA);
			    Navigator.loadVista(App.IMPORTDATA);
				
			}
		}
	}
	
	

	
	public void begin() throws Exception{
		
		String fxml= Navigator.getImportFxml();
		PaneNavigator.setPageController(this);
		
		PaneNavigator.addNodes(fxml);
		
		importTitle = currentPane.getClass().getSimpleName();
		lblTitle.setText(importTitle);
		App.importpage = false;
		importPane = true;
		PaneNavigator.loadVista(fxml);
	}
	
	public void NodeImport(){
		
		String fxml= Navigator.getImportFxml();
		PaneNavigator.setPageController(this);
		ClassLoader classLoader = Loader.class.getClassLoader();
	    URL fxmlURL = classLoader.getResource(fxml);
		FXMLLoader loader = new FXMLLoader(fxmlURL);
		loader.setResources(ResourceBundle.getBundle(App.bundle));
		
		currentPane = loader.getController();
		importTitle = currentPane.getClass().getSimpleName();
		lblTitle.setText(importTitle);
		App.importpage = false;
		importPane = true;
		setVista(previous);
		
	}
	
	public void NodeExport(){
			
		String fxml= Navigator.getExportFxml();
		
		ClassLoader classLoader = Loader.class.getClassLoader();
	    URL fxmlURL = classLoader.getResource(fxml);
		FXMLLoader loader = new FXMLLoader(fxmlURL);
		loader.setResources(ResourceBundle.getBundle(App.bundle));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentPane = loader.getController();
		lblTitle.setText(currentPane.getClass().getSimpleName());
		App.importpage = true;
		importPane = false;
		setVista(root);
		
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			begin();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
