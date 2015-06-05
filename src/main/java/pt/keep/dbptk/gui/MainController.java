package pt.keep.dbptk.gui;



import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MainController implements Initializable{

	@FXML
	private Button btnMainExport;
	@FXML
	private Button btnMainImport;
	@FXML
	private Button btnExit;
	@FXML
	private Button btnCustom;
	@FXML
	private Text txtExport,txtImport,txtCustom;
	
	
	

	@FXML 
	private void btnMainExportAction(ActionEvent event)throws Exception{
		Node node= (Node) event.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setResources(ResourceBundle.getBundle(App.bundle));
        Parent root = (Parent) fxmlLoader.load(getClass().getResource("ExportPage.fxml").openStream());
        
        App.importpage = false;
        Navigator.setPageController(fxmlLoader.getController());
        Navigator.setPage("export");
        Navigator.clearNodes();
        Navigator.addNodes(App.DBMSCHOOSER);
        Navigator.loadVista("export",App.DBMSCHOOSER);
        
        
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML 
	private void btnMainImportAction(ActionEvent event) throws Exception{
		
		Node node= (Node) event.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setResources(ResourceBundle.getBundle(App.bundle));
        Parent root = (Parent) fxmlLoader.load(getClass().getResource("ImportPage.fxml").openStream());

        App.importpage = true;
        Navigator.clearNodes();
        Navigator.addNodes(App.SIARDPAGE);
        Navigator.setPageController(fxmlLoader.getController());
        Navigator.setPage("import");
        
        Navigator.loadVista("import",App.SIARDPAGE);
        
       
        
        Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML private void btnCustomAction(ActionEvent event) throws Exception{
		Node node= (Node) event.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setResources(ResourceBundle.getBundle(App.bundle));
        Parent root = (Parent) fxmlLoader.load(getClass().getResource("CustomPage.fxml").openStream());
        
        App.importpage = true;
        Navigator.setPageController(fxmlLoader.getController());
        Navigator.setPage("custom");
        Navigator.clearNodes();
        
        Navigator.addNodes(App.CUSTOMCHOOSER);
        
        Navigator.loadVista("custom",App.CUSTOMCHOOSER);
        
       
        
        Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
	}
	
	@FXML private void btnExitAction(ActionEvent event){
		Node node= (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
	    // do what you have to do
	    stage.close();
		System.out.println("Exit");
	}

	public void initialize(URL location, ResourceBundle resources) {
		//Locale.setDefault(Locale.ENGLISH);
		// TODO Auto-generated method stub
	//	bundle=resources;
		
	}

	
	
		
	

}
