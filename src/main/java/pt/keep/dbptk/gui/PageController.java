package pt.keep.dbptk.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PageController {
	
	@FXML
	private Pane mainPane, btnPane;
	
	
	
	@FXML
	private Button btn1, btn2, btn3;

	public Map<String,List<String>> buttons = new HashMap<String,List<String>>();



	
	public void setButtons(String Page){
		if (Page.equals("import")) {
			
			
		} 
		if(Page.equals("export")){
			
		}
		else {

		}
		
	}
	
	public void setVista(Node node) {
		
		this.mainPane.getChildren().setAll(node);
		
	}

	@FXML 
	public void btn1Action(ActionEvent event) {
		if (App.importpage) {
			
		}
		else{
			
		}
	}

	@FXML 
	public void btn2Action(ActionEvent event) throws IOException {
		if (App.importpage) {
			Navigator.loadVista(App.SIARDPAGE);
		}
		else{
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
	public void btn3Action(ActionEvent event) {
		if (App.importpage) {
			
		}
		else{
			
		}
	}
	
	
}
