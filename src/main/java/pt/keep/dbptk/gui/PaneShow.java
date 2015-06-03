package pt.keep.dbptk.gui;




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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import org.apache.hadoop.hdfs.server.namenode.FSImageFormat.Loader;

import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;

public class PaneShow implements Panes,Initializable{

	@FXML 
	public Button btnNext, btnCancel;
	@FXML 
	public Pane paneFields;
	@FXML 
	public Label lblTitle;
	
	

	public boolean importPane = true;
	
	public Node previous;
	
	public String importTitle;
	
	public Map<String,DBMSPane> dbmspanes = new HashMap<String,DBMSPane>();
	public  Map<String,Node> nodes = new HashMap<String,Node>();
	
	@Override
	public void setVista(Node node) {
		// TODO Auto-generated method stub
		this.paneFields.getChildren().setAll(node);
	}
	
	
	
	@FXML 
	public void btnCancelAction(ActionEvent event) {
		if (importPane) {
			Navigator.loadVista(App.CUSTOMCHOOSER);
		}
		else{
			importPane = true;
			loadVista(Navigator.getImportFxml());
			
		}
	}

	
	
	@FXML 
	public void btnNextAction(ActionEvent event) throws Exception {
		boolean sucess = false;
		DBMSPane currentPane;
		if(importPane){
			
			DatabaseImportModule module = null;
			
			currentPane = dbmspanes.get(Navigator.getImportFxml());
			if(currentPane.isInputValid()){
				module = currentPane.getImportModule();
				sucess = true;
			}
			if(sucess){
				Navigator.setImportModule(module);
								
				loadPane(Navigator.getExportFxml(), true, false);
			}
		}
		else{
			
			DatabaseHandler module = null;
			
			currentPane = dbmspanes.get(Navigator.getExportFxml());
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
	

	

	
	public void loadPane(String fxml,boolean page,boolean pane) throws IOException{
		
		ClassLoader classLoader = Loader.class.getClassLoader();
        URL fxmlURL = classLoader.getResource(fxml);
		FXMLLoader loader = new FXMLLoader(fxmlURL);
		loader.setResources(ResourceBundle.getBundle(App.bundle));
		Node root = loader.load();
		App.importpage = page;
		importPane = pane;
		
		lblTitle.setText(loader.getController().getClass().getSimpleName());
		dbmspanes.put(fxml, loader.getController());
		addNode(fxml, root);
		setVista(root);
	}
	
	public void loadVista(String fxml) {
        setVista(getNode(fxml));
    }
	
	public  void addNode(String name,Node node){
		nodes.put(name, node);
	}
	
	public  Node getNode(String name){
		return nodes.get(name);
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			loadPane(Navigator.getImportFxml(),false,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
