package pt.keep.dbptk.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class PageController {
	
	@FXML
	private Pane mainPane, btnPane;
	
	
	
	@FXML
	private Button btn1, btn2, btn3, btn4;

	public Map<String,List<String>> buttons = new HashMap<String,List<String>>();

	

	public void setButtons(String Page, String fxml){
		
		if (Page.equals("import")) {
			if(fxml.equals("SIARDPage.fxml")){
				btn1.setDisable(false);
				btn2.setDisable(true);
				btn3.setDisable(true);
			}
			else if(fxml.equals("DBMSChooser.fxml")){
				btn1.setDisable(false);
				btn2.setDisable(false);
				btn3.setDisable(true);
			}
			else{
				btn1.setDisable(false);
				btn2.setDisable(false);
				btn3.setDisable(false);
			}
		} 
		else if(Page.equals("export")){
			if(fxml.equals("DBMSChooser.fxml")){
				btn1.setDisable(false);
				btn2.setDisable(true);
				btn3.setDisable(true);
			}
			else if(fxml.equals("SIARDPage.fxml")){
				btn1.setDisable(false);
				btn2.setDisable(false);
				btn3.setDisable(true);
			}
			else{
				btn1.setDisable(false);
				btn2.setDisable(false);
				btn3.setDisable(false);
			}
		}
		else {
			if(fxml.equals("CustomChooser.fxml")){
				btn1.setDisable(false);
				btn2.setDisable(true);
				btn3.setDisable(true);
				btn4.setDisable(true);
			}
			else if(fxml.equals("PaneImport.fxml")){
				btn1.setDisable(false);
				btn2.setDisable(false);
				btn3.setDisable(true);
				btn4.setDisable(true);
			}
			else if(fxml.equals("PaneExport.fxml")){
				btn1.setDisable(false);
				btn2.setDisable(false);
				btn3.setDisable(false);
				btn4.setDisable(true);
			}
			else{
				btn1.setDisable(false);
				btn2.setDisable(false);
				btn3.setDisable(false);
				btn4.setDisable(false);
			}
		}
		
	}
	
	public void setVista(Node node) {
		
		this.mainPane.getChildren().setAll(node);
		
	}

	@FXML 
	public void btn1Action(ActionEvent event) {
		String page = Navigator.getPage();
		
		if (page.equals("import")) {
			Navigator.loadVista("import",App.SIARDPAGE);
		}
		else if (page.equals("export")) {
			Navigator.loadVista("export",App.DBMSCHOOSER);
		}
		else{
			Navigator.loadVista("custom", App.CUSTOMCHOOSER);
		}
	}

	@FXML 
	public void btn2Action(ActionEvent event) throws IOException {
		String page = Navigator.getPage();
		if (page.equals("export")) {
			Navigator.loadVista("export",App.DBMSCHOOSER);
		}
		else if (page.equals("import")) {
			Navigator.loadVista("import",App.SIARDPAGE);
		}
		else{
			Navigator.loadVista("custom", App.PANEIMPORT);
		}
	}

	@FXML 
	public void btn3Action(ActionEvent event) {
		String page = Navigator.getPage();
		if (page.equals("import")) {
			
		}
		else if (page.equals("export")) {
			
		}
		else{
			Navigator.loadVista("custom", App.PANEEXPORT);
		}
	}

	@FXML 
	public void btn4Action(ActionEvent event) {
		
	}
	
	
}
