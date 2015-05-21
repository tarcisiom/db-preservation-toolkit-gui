package pt.keep.dbptk.gui;




import java.io.InputStream;
import java.net.URL;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.hadoop.hdfs.server.namenode.FSImageFormat.Loader;

import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;
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

	
	public MySQLJDBC mysql;
	public DB2JDBC db2;
	public Oracle12c oracle;
	public PostgreSQLJDBC postgre;
	public SQLServerJDBC sqlserver;
	
	private String bundle = "pt/keep/dbptk/gui/bundle_en.properties";
	
	
	public String selectedDBMS;
	
	public void setVista(Node node) {
		this.paneFields.getChildren().setAll(node);
		
	}
	
	
	
	
	@FXML
	public void comboChangeAction(ActionEvent event) throws Exception {
		selectedDBMS = (String) comboChooser.getSelectionModel().getSelectedItem();
		
		//paneFields.getChildren().clear();
		DBMSNavigator.setMainController(this);
		ClassLoader classLoader = Loader.class.getClassLoader();
        InputStream inputStream = classLoader.getResource("pt/keep/dbptk/gui/bundle_en.properties").openStream();
		ResourceBundle bundle1 = new PropertyResourceBundle(inputStream);
		
		if (selectedDBMS.equalsIgnoreCase("MySQLJDBC")){
			URL fxmlURL = classLoader.getResource("pt/keep/dbptk/gui/"+DBMSNavigator.MYSQLJDBC);
			FXMLLoader loader = new FXMLLoader(fxmlURL, bundle1);
			Parent root = loader.load();
			mysql = loader.getController();
    		setVista(root);
			
		} else if (selectedDBMS.equalsIgnoreCase("DB2JDBC")) {
			
			URL fxmlURL = classLoader.getResource("pt/keep/dbptk/gui/"+DBMSNavigator.DB2JDBC);
			FXMLLoader loader = new FXMLLoader(fxmlURL, bundle1);
			Parent root = loader.load();
			db2 = loader.getController();
    		setVista(root);
	        
		} else if (selectedDBMS.equalsIgnoreCase("Oracle12c")) {
			
			URL fxmlURL = classLoader.getResource("pt/keep/dbptk/gui/"+DBMSNavigator.ORACLE12C);
			FXMLLoader loader = new FXMLLoader(fxmlURL, bundle1);
			Parent root = loader.load();
			oracle = loader.getController();
    		setVista(root);
			
		} else if (selectedDBMS.equalsIgnoreCase("PostgreSQLJDBC")) {
			
			URL fxmlURL = classLoader.getResource("pt/keep/dbptk/gui/"+DBMSNavigator.POSTGRE);
			FXMLLoader loader = new FXMLLoader(fxmlURL, bundle1);
			Parent root = loader.load();
			postgre = loader.getController();
    		setVista(root);
			
		} else if (selectedDBMS.equalsIgnoreCase("MSAccessUCanAccess")) {

			

		} else if (selectedDBMS.equalsIgnoreCase("DBML")) {
			
			

		} else if (selectedDBMS.equalsIgnoreCase("SQLServerJDBC")) {
			
			URL fxmlURL = classLoader.getResource("pt/keep/dbptk/gui/"+DBMSNavigator.SQLSERVER);
			FXMLLoader loader = new FXMLLoader(fxmlURL, bundle1);
			Parent root = loader.load();
			sqlserver = loader.getController();
    		setVista(root);
			
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
		
		//System.out.println("inside stuff "+mysql.isInputValid()+" ");
		DatabaseImportModule module = null;
		boolean sucess =false;
		
		if (selectedDBMS.equalsIgnoreCase("MySQLJDBC")){
			System.out.println(
			mysql.getFieldHost().getText()+" "+
			mysql.getFieldDatabase().getText()+" "+
			mysql.getFieldPort().getText()+" "+
			mysql.getFieldUsername().getText()+" "+
			mysql.getFieldPassword().getText()+" "
			);
			if(mysql.isInputValid()){
				module = mysql.getImportModule();
				sucess =true;
			}
			
			
			
		} else if (selectedDBMS.equalsIgnoreCase("DB2JDBC")) {
			
	        
	        
		} else if (selectedDBMS.equalsIgnoreCase("Oracle12c")) {
			
			
			
		} else if (selectedDBMS.equalsIgnoreCase("PostgreSQLJDBC")) {
			

			
		} else if (selectedDBMS.equalsIgnoreCase("MSAccessUCanAccess")) {

			

		} else if (selectedDBMS.equalsIgnoreCase("DBML")) {
			
			

		} else if (selectedDBMS.equalsIgnoreCase("SQLServerJDBC")) {
			
			
			
		}
		if(sucess){
			VistaNavigator.setImportCurrent(module);
			VistaNavigator.setDbms(this);
			VistaNavigator.loadVista(VistaNavigator.SIARD,bundle);
		}
		
		
	}
	
}
