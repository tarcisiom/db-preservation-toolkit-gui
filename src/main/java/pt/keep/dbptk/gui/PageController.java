package pt.keep.dbptk.gui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class PageController {
	
	@FXML
	private Pane mainPane;


	
	public void setVista(Node node) {
		
		this.mainPane.getChildren().setAll(node);
		
	}
}
