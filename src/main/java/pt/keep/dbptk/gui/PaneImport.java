package pt.keep.dbptk.gui;

import java.io.IOException;
import java.net.URL;
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

import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;

public class PaneImport implements Panes,Initializable{
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
		
		Navigator.loadVista("custom",App.CUSTOMCHOOSER);
		Navigator.setCurrentButton("btn1",App.GREEN);
		Navigator.setCurrentButton("btn2",App.GREY);
		Navigator.setCurrentButton("btn3",App.GREY);
		Navigator.setCurrentButton("btn4",App.GREY);
	}
	
	public void btnNextAction(ActionEvent event) throws Exception {
		boolean sucess = false;
		DatabaseImportModule module = null;
		
		
		if(dbmspane.isInputValid()){
			module = dbmspane.getImportModule();
			sucess = true;
		}
		if(sucess){
			Navigator.setImportModule(module);
			Navigator.addNodes(App.PANEEXPORT);
			App.importpage=true;
		    Navigator.loadVista("custom",App.PANEEXPORT);

    		Navigator.setCurrentButton("btn1",App.GREY);
			Navigator.setCurrentButton("btn2",App.GREY);
			Navigator.setCurrentButton("btn3",App.GREEN);
			Navigator.setCurrentButton("btn4",App.GREY);
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
			loadPane(Navigator.getImportFxml());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Navigator.setCurrentButton("btn1",App.GREY);
		Navigator.setCurrentButton("btn2",App.GREEN);
		Navigator.setCurrentButton("btn3",App.GREY);
		Navigator.setCurrentButton("btn4",App.GREY);
	}
	

}
