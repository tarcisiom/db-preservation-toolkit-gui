package pt.keep.dbptk.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class PageController implements Initializable{
	
	@FXML
	private Pane mainPane, btnPane;
	
	@FXML
	public TextFlow textFooter;
	
	
	@FXML
	public Button btn1, btn2, btn3, btn4;

	public Map<String,Button> buttons = new HashMap<String,Button>();



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
			else if(fxml.equals("ImportData.fxml")){
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
			else if(fxml.equals("ImportData.fxml")){
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
			else if(fxml.equals("ImportData.fxml")){
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
			Navigator.setCurrentButton("btn1",App.GREEN);
			Navigator.setCurrentButton("btn2",App.GREY);
			Navigator.setCurrentButton("btn3",App.GREY);
			
			Navigator.loadVista("import",App.SIARDPAGE);
		}
		else if (page.equals("export")) {
			Navigator.setCurrentButton("btn1",App.GREEN);
			Navigator.setCurrentButton("btn2",App.GREY);
			Navigator.setCurrentButton("btn3",App.GREY);
			
			Navigator.loadVista("export",App.DBMSCHOOSER);
		}
		else{
			Navigator.setCurrentButton("btn1",App.GREEN);
			Navigator.setCurrentButton("btn2",App.GREY);
			Navigator.setCurrentButton("btn3",App.GREY);
			
			Navigator.loadVista("custom", App.CUSTOMCHOOSER);
		}
	}

	@FXML 
	public void btn2Action(ActionEvent event) throws IOException {
		String page = Navigator.getPage();
		if (page.equals("import")) {
			Navigator.setCurrentButton("btn1",App.GREY);
			Navigator.setCurrentButton("btn2",App.GREEN);
			Navigator.setCurrentButton("btn3",App.GREY);
			
			Navigator.loadVista("import",App.DBMSCHOOSER);
		}
		else if (page.equals("export")) {
			Navigator.setCurrentButton("btn1",App.GREY);
			Navigator.setCurrentButton("btn2",App.GREEN);
			Navigator.setCurrentButton("btn3",App.GREY);
			
			Navigator.loadVista("export",App.SIARDPAGE);
		}

		else{

			Navigator.setCurrentButton("btn1",App.GREY);
			Navigator.setCurrentButton("btn2",App.GREEN);
			Navigator.setCurrentButton("btn3",App.GREY);
			
			Navigator.loadVista("custom", App.PANEIMPORT);
		}
	}

	@FXML 
	public void btn3Action(ActionEvent event) {
		String page = Navigator.getPage();
		if (page.equals("import")) {

			Navigator.setCurrentButton("btn1",App.GREY);
			Navigator.setCurrentButton("btn2",App.GREY);
			Navigator.setCurrentButton("btn3",App.GREEN);
		}
		else if (page.equals("export")) {
			Navigator.setCurrentButton("btn1",App.GREY);
			Navigator.setCurrentButton("btn2",App.GREY);
			Navigator.setCurrentButton("btn3",App.GREEN);
		}
		else{
			Navigator.setCurrentButton("btn1",App.GREY);
			Navigator.setCurrentButton("btn2",App.GREY);
			Navigator.setCurrentButton("btn3",App.GREEN);
			
			Navigator.loadVista("custom", App.PANEEXPORT);
		}
	}

	@FXML 
	public void btn4Action(ActionEvent event) {
		
	}

	public void footerHyperlinks(){
		Hyperlink link1 = new Hyperlink();
		link1.setText("Portuguese National Archives");
		link1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
			//	webEngine.load("http://www.google.com");
				try {
					Desktop.getDesktop().browse(new URI("http://www.google.com"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Hyperlink link2 = new Hyperlink();
		link2.setText("EARK project");
		link2.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				try {
					Desktop.getDesktop().browse(new URI("http://www.eark-project.com"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Hyperlink link3 = new Hyperlink();
		link3.setText("KEEP SOLUTIONS");
		link3.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				try {
					Desktop.getDesktop().browse(new URI("http://www.keep.pt"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		textFooter.getChildren().clear();
		textFooter.getChildren().addAll(new Text("The Database Preservation Toolkit was created by the"), link1,
										new Text("further developed within the"), link2,
										new Text(", and is maintained and commercially supported by"),link3
									   );
		
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		if (App.USELINKS) {
			footerHyperlinks();
		}
		buttons.put("btn1", btn1);
		buttons.put("btn2", btn2);
		buttons.put("btn3", btn3);
		buttons.put("btn4", btn4);
		
	}

	
	
}
