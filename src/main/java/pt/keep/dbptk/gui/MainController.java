package pt.keep.dbptk.gui;



import java.io.InputStream;
import java.net.URL;
import java.util.PropertyResourceBundle;
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
	
	//private ResourceBundle bundle;
	
	

	@FXML private void btnMainExportAction(ActionEvent event)throws Exception{
		Node node= (Node) event.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL fxmlURL = classLoader.getResource("pt/keep/dbptk/gui/Export.fxml");
		InputStream inputStream = classLoader.getResource("pt/keep/dbptk/gui/bundle_en.properties").openStream();
		ResourceBundle bundle = new PropertyResourceBundle(inputStream);
		
		FXMLLoader loader = new FXMLLoader(fxmlURL, bundle);
		Parent root = loader.load();
		
		//Parent root = FXMLLoader.load(getClass().getResource("/pt/keep/dbptk/gui/Export.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML private void btnMainImportAction(ActionEvent event) throws Exception{
		Node node= (Node) event.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL fxmlURL = classLoader.getResource("pt/keep/dbptk/gui/Import.fxml");
		InputStream inputStream = classLoader.getResource("pt/keep/dbptk/gui/bundle_en.properties").openStream();
		ResourceBundle bundle = new PropertyResourceBundle(inputStream);
		
		FXMLLoader loader = new FXMLLoader(fxmlURL, bundle);
		Parent root = loader.load();
		
		//Parent root = FXMLLoader.load(getClass().getResource("/pt/keep/dbptk/gui/Import.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML private void btnCustomAction(ActionEvent event){

		System.out.println("About");
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
