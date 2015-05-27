package pt.keep.dbptk.gui;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class ImportPageController implements Initializable, Page{
	
	@FXML
	private Pane importMainPane;
	
	
	
	public void setVista(Node node) {
		
		this.importMainPane.getChildren().clear();
		this.importMainPane.getChildren().add(node);
		
		
	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
	}



	@Override
	public void setScreenParent(Navigator navigator) {
		// TODO Auto-generated method stub
		
	}

	
}
