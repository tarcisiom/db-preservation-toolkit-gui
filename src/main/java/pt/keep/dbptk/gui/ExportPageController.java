package pt.keep.dbptk.gui;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class ExportPageController {

		
	@FXML
	private Pane exportMainPane;

	
	public void setVista(Node node) {
		
		this.exportMainPane.getChildren().setAll(node);
		
	}
	
	
	
	
}
