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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
//import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;

public class ExportController  implements Initializable{

	@FXML
	private Button btnBack,btnBack2;
	@FXML
	private ComboBox<String> exportCombo;
	
	//private DatabaseHandler dbhandler;
	
	
	@FXML private void comboChangeAction(ActionEvent event) throws Exception{
		System.out.println("changing");
	}
	
	
	
	@FXML private void btnCancelAction(ActionEvent event) throws Exception{
		Node node= (Node) event.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL fxmlURL = classLoader.getResource("pt/keep/dbptk/gui/Main.fxml");
		InputStream inputStream = classLoader.getResource("pt/keep/dbptk/gui/bundle_en.properties").openStream();
		ResourceBundle bundle = new PropertyResourceBundle(inputStream);
		
		FXMLLoader loader = new FXMLLoader(fxmlURL, bundle);
		Parent root = loader.load();
		
		//Parent root = FXMLLoader.load(getClass().getResource("/pt/keep/dbptk/gui/Main.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}



	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		exportCombo.getItems().removeAll(exportCombo.getItems());
		exportCombo.getItems().addAll("SQLServerJDBC", "PostgreSQLJDBC", "MySQLJDBC", "DB2JDBC","Oracle12c" ,"MSAccessUCanAccess" ,"DBML");
	    /*
	      <String fx:value="SQLServerJDBC" />
          <String fx:value="PostgreSQLJDBC" />
          <String fx:value="MySQLJDBC" />
          <String fx:value="DB2JDBC" />
          <String fx:value="Oracle12c" />
          <String fx:value="MSAccessUCanAccess" />
          <String fx:value="DBML"/>
	     */
	}
	
	

	

	
}
