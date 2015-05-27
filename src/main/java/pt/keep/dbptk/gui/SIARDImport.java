package pt.keep.dbptk.gui;

import java.io.File;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.gov.dgarq.roda.common.convert.db.model.exception.InvalidDataException;
import pt.gov.dgarq.roda.common.convert.db.model.exception.ModuleException;
import pt.gov.dgarq.roda.common.convert.db.model.exception.UnknownTypeException;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.mySql.out.MySQLJDBCExportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.siard.in.SIARDImportModule;

public class SIARDImport {
	
	@FXML
	private ComboBox<String> outputExpansion, outputModule;
	@FXML
	private Button btnNext, btnCancel, btnBrowse;
	@FXML
	private Label labelFile;
	@FXML
	private FileChooser fileChooser = new FileChooser();
	
	private DatabaseImportModule importModule;
	
	
	@FXML
	private void btnBrowseAction(ActionEvent event) throws Exception {
		fileChooser.setTitle("This is my file chooser");
		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);
		if (file != null) {
			labelFile.setText(file.getPath());
		}
	}
	
	@FXML
	public void btnCancelAction(ActionEvent event) throws Exception {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setResources(ResourceBundle.getBundle(App.bundle));
        Parent root = (Parent) fxmlLoader.load(getClass().getResource("Main.fxml").openStream());
        Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void btnNextAction(ActionEvent event) throws Exception {
		
		String filepath = (String) labelFile.getText();
	//  String expansion = (String)outputExpansion.getSelectionModel().getSelectedItem();
		String module = (String) outputModule.getSelectionModel().getSelectedItem();
		
		
		String errorMessage = "";
		boolean sucess = false;
        if (labelFile.getText() == null || labelFile.getText().length() == 0) {
            errorMessage += "File not selected!\n"; 
        }
        if ((String) outputModule.getSelectionModel().getSelectedItem() == null || outputModule.getSelectionModel().getSelectedItem().length() == 0) {
            errorMessage += "Import Module not selected!\n"; 
        }
        if (errorMessage.length() == 0) {
        	if (module.equalsIgnoreCase("SIARD-E 2.0")) {
				importModule = new SIARDImportModule(new File(filepath));
			} 
        	sucess = true;
        } else {
            // Show the error message.
            
            new DialogMessage(errorMessage);
            
            
           
        }
        
        if(sucess){
			Navigator.setImportModule(importModule);
			Navigator.addNodes(App.DBMSCHOOSERIMPORT);
			Navigator.loadVista(App.DBMSCHOOSERIMPORT);
        }
		
		
	}
	
	public void teste(){
		try{
			/*  import pt.gov.dgarq.roda.common.convert.db.modules.mySql.out.MySQLJDBCExportModule;
				import pt.gov.dgarq.roda.common.convert.db.modules.siard.in.SIARDImportModule;
				
			   -i SIARD novo -o  MySQLJDBC localhost 8889 tabelaArquivos root root
			*/
			
			String path = "/Users/boombz/Desktop/novo";
			DatabaseImportModule importModule = new SIARDImportModule(new File(path));
			int port = 8889;
			DatabaseHandler exportModule = new MySQLJDBCExportModule("localhost",port,"tabela1Arquivos","root","root");
			try {
				importModule.getDatabase(exportModule);
			} catch (UnknownTypeException | InvalidDataException e) {
				e.printStackTrace();
			}
		}
		catch(ModuleException e){
			
		}
		
	}
	
	
	
}
