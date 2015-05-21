package pt.keep.dbptk.gui;


import java.io.InputStream;
import java.net.URL;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.hadoop.hdfs.server.namenode.FSImageFormat.Loader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class DBMSChooser {

	
	@FXML
	private ComboBox<String> comboChooser;
	
	@FXML
	private Pane paneFields;
	
	@FXML
	private Button btnCancel, btnNext;
	
	
	
	public void setVista(Node node) {
		
		this.paneFields.getChildren().setAll(node);
		
	}
	
	@FXML
	public void comboChangeAction(ActionEvent event) throws Exception {
		String item = (String) comboChooser.getSelectionModel().getSelectedItem();
		
		paneFields.getChildren().clear();
		
		if (item.equalsIgnoreCase("MySQLJDBC")){

			ClassLoader classLoader =  Loader.class.getClassLoader();
			URL fxmlURL = classLoader.getResource(DBMSNavigator.MYSQLJDBC);
			InputStream inputStream = classLoader.getResource("pt/keep/dbptk/gui/bundle_en.properties").openStream();
			ResourceBundle bundle = new PropertyResourceBundle(inputStream);
			FXMLLoader loader = new FXMLLoader(fxmlURL, bundle);
		
	        Pane mainPane = (Pane) loader.load();
	//        MySQLJDBC controller = loader.getController();
	        
	        paneFields.getChildren().add(mainPane);
			
			
			
		} else if (item.equalsIgnoreCase("DB2JDBC")) {
			Node node= (Node) event.getSource();
			Stage stage=(Stage) node.getScene().getWindow();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			URL fxmlURL = classLoader.getResource(DBMSNavigator.DBMSCHOOSER);
			InputStream inputStream = classLoader.getResource("pt/keep/dbptk/gui/bundle_en.properties").openStream();
			ResourceBundle bundle = new PropertyResourceBundle(inputStream);
			FXMLLoader loader = new FXMLLoader(fxmlURL, bundle);
			Parent root = loader.load();	
			//Parent root = FXMLLoader.load(getClass().getResource("/pt/keep/dbptk/gui/ExportPage.fxml"));

			DBMSChooser Controller = loader.getController();

	        DBMSNavigator.setMainController(Controller);
	        DBMSNavigator.loadVista(DBMSNavigator.DB2JDBC,"pt/keep/dbptk/gui/bundle_en.properties");

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
	        
	        
			
		} else if (item.equalsIgnoreCase("Oracle12c")) {
			
			
		} else if (item.equalsIgnoreCase("PostgreSQLJDBC")) {
			
			
		} else if (item.equalsIgnoreCase("MSAccessUCanAccess")) {


		} else if (item.equalsIgnoreCase("DBML")) {

		} else if (item.equalsIgnoreCase("SQLServerJDBC")) {

		}
	}

	@FXML
	public void btnCancelAction(ActionEvent event) throws Exception {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		ClassLoader classLoader = Loader.class.getClassLoader();
		URL fxmlURL = classLoader.getResource("pt/keep/dbptk/gui/Main.fxml");
		InputStream inputStream = classLoader.getResource("pt/keep/dbptk/gui/bundle_en.properties").openStream();
		ResourceBundle bundle = new PropertyResourceBundle(inputStream);
		FXMLLoader loader = new FXMLLoader(fxmlURL, bundle);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void btnNextAction(ActionEvent event) throws Exception {
		
		//if (isInputValid()) {     }
		
	}
	
}
