package pt.keep.dbptk.gui;

import pt.keep.dbptk.gui.old.Page;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class CustomPage implements Page {

	
	@FXML
	private Pane customPane;
	
	
	@Override
	public void setVista(Node node) {
		// TODO Auto-generated method stub
		this.customPane.getChildren().clear();
		this.customPane.getChildren().add(node);
		
	}

	@Override
	public void setScreenParent(Navigator navigator) {
		// TODO Auto-generated method stub
		
	}

}
