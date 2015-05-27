package pt.keep.dbptk.gui;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class ExportPageController implements Page{

		
	@FXML
	private Pane exportMainPane;

	
	public void setVista(Node node) {
		
		this.exportMainPane.getChildren().setAll(node);
		
	}

	@Override
	public void setScreenParent(Navigator navigator) {
		// TODO Auto-generated method stub
		
	}


	
	
	
	
}
