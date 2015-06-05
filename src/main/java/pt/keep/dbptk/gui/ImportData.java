package pt.keep.dbptk.gui;


import java.net.URL;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pt.gov.dgarq.roda.common.convert.db.model.exception.InvalidDataException;
import pt.gov.dgarq.roda.common.convert.db.model.exception.ModuleException;
import pt.gov.dgarq.roda.common.convert.db.model.exception.UnknownTypeException;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;

public class ImportData implements Initializable{

	
	
	@FXML 
	public Pane paneProgress;
	@FXML 
	public  ProgressBar progressBar;
	@FXML 
	public  Label lblStatus;
	@FXML 
	public  Button btnCancel, btnMain;
	

	
	public ImportData() {
		
		
	}



	@FXML 
	public void btnCancelAction(ActionEvent event) {
		
		if (App.importpage) {
			Navigator.loadVista("import",App.DBMSCHOOSER);
		}
		else{
			Navigator.loadVista("export",App.SIARDPAGE);
		}
	}
	
	

	@FXML 
	public void btnMainAction(ActionEvent event) throws Exception {
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
	
	
	
	@SuppressWarnings("unused")
	public void exportDB(){
		DatabaseImportModule impModule = Navigator.getImportModule();
		DatabaseHandlerGUI expModule = new DatabaseHandlerGUI(Navigator.getExportModule());
		if (impModule != null && expModule != null) {
			try {
				long startTime = System.currentTimeMillis();
				
				impModule.getDatabase(expModule);
				
				long duration = System.currentTimeMillis() - startTime;
			//	logger.info("Done in " + (duration / 60000) + "m "+ (duration % 60000 / 1000) + "s");
			} catch (ModuleException e) {
				if (e.getCause() != null
						&& e.getCause() instanceof ClassNotFoundException
						&& e.getCause().getMessage()
								.equals("sun.jdbc.odbc.JdbcOdbcDriver")) {
			//logger.error("Could not find the Java ODBC driver, please run this program under Windows to use the JDBC-ODBC bridge.",
			//				e.getCause());
				} else if (e.getModuleErrors() != null) {
					for (Map.Entry<String, Throwable> entry : e
							.getModuleErrors().entrySet()) {
				//		logger.error(entry.getKey(), entry.getValue());
					}
				} else {
		//			logger.error("Error while importing/exporting", e);
				}
			} catch (UnknownTypeException e) {
		//		logger.error("Error while importing/exporting", e);
			} catch (InvalidDataException e) {
	//			logger.error("Error while importing/exporting", e);
			} catch (Exception e) {
		//		logger.error("Unexpected exception", e);
			}

		} else {
			// printHelp();
			System.out.println("Mal introduzido");
		}
		Navigator.setExportModule(null);
		Navigator.setImportModule(null);
		
	
	}



	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		// exportDB();
		 new Thread() {
			
             // runnable for that thread
             public void run() {
	            	 try {
	                     // imitating work
	                     Thread.sleep(new Random().nextInt(1000));
	                 } catch (InterruptedException ex) {
	                     ex.printStackTrace();
	                 }
                 	Platform.runLater(new Runnable() {

                         public void run() {
                             exportDB();
                         }
                     });
                 
             }
         }.start();
	    
		
	}





	
	

	
}
