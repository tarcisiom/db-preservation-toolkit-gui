package pt.keep.dbptk.gui;



import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.mySql.in.MySQLJDBCImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.siard.out.SIARDExportModule;


public class MainController implements Initializable{

	@FXML
	private Button btnMainExport, btnMainImport, btnCustom;
	@FXML
	private Text txtExport,txtImport,txtCustom;
	@FXML
	public TextFlow textFooter;
	

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
        Navigator.setPageController(fxmlLoader.getController());
        Navigator.clearNodes();
        Navigator.addNodes(App.SIARDPAGE);
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
	
	@FXML
	private void btnTeste(ActionEvent event) throws IOException{
		Node node= (Node) event.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		
			
		DatabaseImportModule imp = new MySQLJDBCImportModule("localhost",8889, "Arquivos", "root", "root");
		DatabaseHandler exportModule = new SIARDExportModule(new File("/Users/boombz/Desktop/lop"),true);
		
		
		DatabaseHandlerGUI expD = new DatabaseHandlerGUI(exportModule);
		
		FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setResources(ResourceBundle.getBundle(App.bundle));
        Parent root = (Parent) fxmlLoader.load(getClass().getResource(App.IMPORTDATA).openStream());
        
		ImportData impD = fxmlLoader.getController();
		
		
		expD.registerObserver(impD);
		Navigator.setImportModule(imp);
		Navigator.setExportModule(expD);
		
		fxmlLoader.setController(impD);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
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
	

	public void initialize(URL location, ResourceBundle resources) {
		
		if (App.USELINKS) {
			footerHyperlinks();
		}
		
		
	}

	
	
		
	

}
