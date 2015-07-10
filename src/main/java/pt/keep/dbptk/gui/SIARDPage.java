package pt.keep.dbptk.gui;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.siard.in.SIARDImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.siard.out.SIARDExportModule;

public class SIARDPage implements Initializable{
	@FXML
	private ComboBox<String> outputExpansion, outputModule;
	@FXML
	private Button btnNext, btnCancel, btnBrowse;
	@FXML
	private Label labelFile;
	@FXML
	private FileChooser fileChooser = new FileChooser();
	
	public Map<String,DBMSPane> dbmspanes = new HashMap<String,DBMSPane>();
	
	
	
	@FXML
	private void btnBrowseAction(ActionEvent event) throws Exception {
		File file;
		if(App.importpage){
			
			fileChooser.setTitle("Choose a SIARD Databse");
			
			file = fileChooser.showOpenDialog(null);
			
		}
		else{
			fileChooser.setTitle("Save a SIARD databese");
			
			file = fileChooser.showSaveDialog(null);
		}
		if (file != null) {
			labelFile.setText(file.getPath());
		}
	}
	
	@FXML
	public void btnCancelAction(ActionEvent event) throws Exception {
		
		
		if (App.importpage) {

			Navigator.setCurrentButton("btn1",App.GREEN);
			Navigator.setCurrentButton("btn2",App.GREY);
			Navigator.setCurrentButton("btn3",App.GREY);
			
			
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
		else{
			Navigator.setCurrentButton("btn1",App.GREEN);
			Navigator.setCurrentButton("btn2",App.GREY);
			Navigator.setCurrentButton("btn3",App.GREY);
			
			
		    Navigator.loadVista("export",App.DBMSCHOOSER);
		    
		}
	}
	
	@FXML
	public void btnNextAction(ActionEvent event) throws Exception {
		String filepath = (String) labelFile.getText();
	    String expansion = (String)outputExpansion.getSelectionModel().getSelectedItem();
		String module = (String) outputModule.getSelectionModel().getSelectedItem();
		String errorMessage = "";
		boolean sucess = false;
		if (App.importpage) {
			DatabaseImportModule importModule= null;
			
			if (labelFile.getText() == null || labelFile.getText().length() == 0) {
	            errorMessage += "File not selected!\n"; 
	        }
	        if (module== null || module.length() == 0) {
	            errorMessage += "Import Module not selected!\n"; 
	        }
	        if (expansion == null || expansion.length() == 0) {
	            errorMessage += "Expansion not selected!\n"; 
	        }
	        if (errorMessage.length() == 0) {
	        	if (module.equalsIgnoreCase("SIARD-E 2.0")) {
	    			importModule = new SIARDImportModule(new File(filepath));
				} 
	        	sucess = true;
	    		
	        } else {
	            // Show the error message.
	            
	        	new DialogMessage(errorMessage,"Correct Invalid Fields");
	            
	        }
	        
	        if(sucess){
	        	
				Navigator.setImportModule(importModule);
				Navigator.addNodes(App.DBMSCHOOSER);
				Navigator.loadVista("import",App.DBMSCHOOSER);
				Navigator.setCurrentButton("btn1",App.GREY);
				Navigator.setCurrentButton("btn2",App.GREEN);
				Navigator.setCurrentButton("btn3",App.GREY);
				
	        }
		}
		else{
			DatabaseHandler exportModule = null;
			
	        if (filepath == null || labelFile.getText().length() == 0) {
	            errorMessage += "File not selected!\n"; 
	        }
	        if (expansion == null || outputModule.getSelectionModel().getSelectedItem().length() == 0) {
	            errorMessage += "Import Module not selected!\n"; 
	        }
	        if (errorMessage.length() == 0) {
	        	if (module.equalsIgnoreCase("SIARD-E 2.0")) {
	        		boolean cp =false;
	        		if (expansion.equals("Compressed ZIP")) {
	        			cp = true;
	        		}
	        		exportModule = new SIARDExportModule(new File(filepath),cp);
				} 
	        	sucess = true;
	        } else {
	            // Show the error message.
	        	new DialogMessage(errorMessage,"Correct Invalid Fields");
	            
	        }
			if(sucess){
				FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setResources(ResourceBundle.getBundle(App.bundle));
		        Node root = (Node) fxmlLoader.load(getClass().getResource(App.IMPORTDATA).openStream());
		        
				ImportData impD = fxmlLoader.getController();
				
				DatabaseHandlerGUI expD = new DatabaseHandlerGUI(exportModule);
				expD.registerObserver(impD);
				Navigator.setExportModule(expD);
				Navigator.setCurrentButton("btn1",App.GREY);
				Navigator.setCurrentButton("btn2",App.GREY);
				Navigator.setCurrentButton("btn3",App.GREEN);
				Navigator.loadAfter(root,"export",App.IMPORTDATA);
				fxmlLoader.setController(impD);
			}
		}
	}

	//Se for necessario mudar a pagina de escolha do siard
	public static ComboBox<String> loadMaps(ComboBox<String> out){
		out.getItems().clear();
		String mods = (String) App.props.get("siardModules");
		String[] modules =  mods.split(", ");
		for (int i = 0; i < modules.length; i++) {
		   out.getItems().add(App.props.getProperty(modules[i]+".label"));
		   /*
		   ClassLoader classLoader = Loader.class.getClassLoader();
	       URL fxmlURL = classLoader.getResource(App.props.getProperty(modules[i]+".fxml"));
	       FXMLLoader loader = new FXMLLoader(fxmlURL);
		   loader.setResources(ResourceBundle.getBundle(App.bundle));
		   try {
				Parent root = loader.load();
		   } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		   }
		   dbmspanes.put(App.props.getProperty(modules[i]+".label"), loader.getController());
		   */
		}
		return out;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if (App.importpage) {

			Navigator.setCurrentButton("btn1",App.GREEN);
			Navigator.setCurrentButton("btn2",App.GREY);
			Navigator.setCurrentButton("btn3",App.GREY);
			
		} else {
			Navigator.setCurrentButton("btn1",App.GREY);
			Navigator.setCurrentButton("btn2",App.GREEN);
			Navigator.setCurrentButton("btn3",App.GREY);
		}
		
	}
	
	
	

}