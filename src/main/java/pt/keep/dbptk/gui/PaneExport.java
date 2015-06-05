package pt.keep.dbptk.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.hadoop.hdfs.server.namenode.FSImageFormat.Loader;

import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class PaneExport implements Panes,Initializable{
	@FXML 
	public Button btnNext, btnCancel;
	@FXML 
	public Pane paneFields;
	@FXML 
	public Label lblTitle;
	
	public DBMSPane dbmspane;
	
	
	@Override
	public void setVista(Node node) {
		// TODO Auto-generated method stub
		this.paneFields.getChildren().setAll(node);
	}
	
	@FXML 
	public void btnCancelAction(ActionEvent event) {
		
		Navigator.loadVista("custom",Navigator.getImportFxml());
		
	}
	
	public void btnNextAction(ActionEvent event) throws Exception {
		boolean sucess = false;
		DatabaseHandler module = null;
		
		
		if(dbmspane.isInputValid()){
			module = dbmspane.getExportModule();
			sucess = true;
		}
		if(sucess){
			Navigator.setExportModule(module);
			Navigator.addNodes(App.IMPORTDATA);
		    Navigator.loadVista("custom",App.IMPORTDATA);
			
		}
	
	}
	
	public void loadPane(String fxml) throws IOException{
		
		ClassLoader classLoader = Loader.class.getClassLoader();
        URL fxmlURL = classLoader.getResource(fxml);
		FXMLLoader loader = new FXMLLoader(fxmlURL);
		loader.setResources(ResourceBundle.getBundle(App.bundle));
		Node root = loader.load();
		
		lblTitle.setText(loader.getController().getClass().getSimpleName());
		dbmspane = loader.getController();
		setVista(root);
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			loadPane(Navigator.getExportFxml());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
