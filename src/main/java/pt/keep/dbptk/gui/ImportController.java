package pt.keep.dbptk.gui;



import java.io.InputStream;
import java.net.URL;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.gov.dgarq.roda.common.convert.db.Main;


 
public class ImportController {
	
	@FXML
	private TextField txtImportHost1,txtImportHost2;
	@FXML
	private TextField txtImportDatabase1,txtImportDatabase2;
	@FXML
	private TextField txtImportUsername1,txtImportUsername2;
	@FXML
	private PasswordField txtImportPassword1,txtImportPassword2;
	@FXML
	private TextField txtImportFilename1,txtImportFilename2;
	@FXML
	private Button btnBack,btnBack2;
	@FXML
	private Button btnImport,btnImport2;
		
	
	@FXML private void btnImportAction1(ActionEvent event){
		String hostname = (String)txtImportHost1.getText();
		String database = (String)txtImportDatabase1.getText();
		String username = (String)txtImportUsername1.getText();
		String password = (String)txtImportPassword1.getText();
		String filename = (String)txtImportFilename1.getText();
		
		String args[]={"-i","MySQLJDBC",hostname,database,username,password,"-o","SIARD",filename};
		Main.main(args);
		

		System.out.println("Import Action");
	}
	
	@FXML private void btnImportAction2(ActionEvent event){
		String hostname = (String)txtImportHost2.getText();
		String database = (String)txtImportDatabase2.getText();
		String username = (String)txtImportUsername2.getText();
		String password = (String)txtImportPassword2.getText();
		String filename = (String)txtImportFilename2.getText();
		String args[]={"-i","DB2JDBC",hostname,database,username,password,"-o","SIARD",filename};
		Main.main(args);
		

		System.out.println("Import Action");
	}
		
	

	@FXML private void btnBackAction(ActionEvent event) throws Exception{
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

		

 

	
	
}
