package pt.keep.dbptk.gui;


import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

public class ImportData implements Initializable, Observer{
	
	@FXML 
	public Pane paneProgress;
	@FXML 
	public  ProgressBar progressBar;
	@FXML 
	public  Label lblStatus,lblTotalTables,lblTotalRows,lblTableName, lblTableRow, lblFinish, lblDone;
	@FXML 
	public  Button  btnMain, btnCancel;

	private int totalRows;
	private int totalTables;
	private int tableRows;
	private long startTime;
	private Thread thread;
	
	@SuppressWarnings("deprecation")
	@FXML 
	public void btnCancelAction(ActionEvent event) {
		
		thread.stop();
		String page = Navigator.getPage();
		if (page.equals("import")) {
			Navigator.loadVista("import",App.DBMSCHOOSER);
		}
		else if (page.equals("export")) {
			Navigator.loadVista("export",App.SIARDPAGE);
		}
		else{
			Navigator.loadVista("custom", App.PANEEXPORT);
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
	
	public void exportDB(){
		DatabaseImportModule impModule = Navigator.getImportModule();
		DatabaseHandlerGUI expModule = Navigator.getExportModule();
		
		if (impModule != null && expModule != null) {
			try {
				startTime = System.currentTimeMillis();
				impModule.getDatabase(expModule);
			} catch (ModuleException e) {
				if (e.getCause() != null
						&& e.getCause() instanceof ClassNotFoundException
						&& e.getCause().getMessage().equals("sun.jdbc.odbc.JdbcOdbcDriver")) {
					new DialogMessage("Could not find the Java ODBC driver, please run this program under Windows to use the JDBC-ODBC bridge."
						+e.getCause(),"Close");
				} else if (e.getModuleErrors() != null) {
					for (Map.Entry<String, Throwable> entry : e.getModuleErrors().entrySet()) {
						Platform.runLater(new Runnable() {
					        @Override
					        public void run() {
					        	new DialogMessage(entry.getKey()+" "+entry.getValue()+ "\n","Close");
					        }
					    });
					}
				} else {
					Platform.runLater(new Runnable() {
				        @Override
				        public void run() {
				        	new DialogMessage("Error while importing/exporting\n"+ e.getCause() +" "+e.getMessage() ,"Close");
				        }
				    });
					
				}
			} catch (UnknownTypeException e) {
				Platform.runLater(new Runnable() {
			        @Override
			        public void run() {
			        	new DialogMessage("Error while importing/exporting\n"+ e.getCause() +" "+e.getMessage(),"Close");
			        }
			    });
				
			} catch (InvalidDataException e) {
				Platform.runLater(new Runnable() {
			        @Override
			        public void run() {
			        	new DialogMessage("Error while importing/exporting\n"+ e.getCause() +" "+e.getMessage(),"Close");
			        }
			    });
			} catch (Exception e) {
				Platform.runLater(new Runnable() {
			        @Override
			        public void run() {
			        	new DialogMessage("Error while importing/exporting\n"+ e.getCause() +" "+e.getMessage(),"Close");
			        }
			    });
			}
		} else {
			Platform.runLater(new Runnable() {
		        @Override
		        public void run() {
		        	new DialogMessage("Campos Mal introduzidos","Close");
		        }
		    });
			
		}
		Navigator.setExportModule(null);
		Navigator.setImportModule(null);
	}
	
	@Override
	public void updateTotalObs(int totalRows, int totalTables){
		this.totalRows = totalRows;
		this.totalTables = totalTables;
	}
	
	@Override
	public void updateTableObs(String tableName, int tableNumber, int tableRows){
		this.tableRows = tableRows;
		double progressInTable = Math.round (((double) tableNumber/this.totalTables)*100);
		StringProperty lTotalTables = new SimpleStringProperty("Tables: "+tableNumber+" of "+totalTables+" ("+progressInTable+"%)");
    	StringProperty lTableName = new SimpleStringProperty("Current Table: "+tableName);		
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	lblTotalTables.textProperty().bind(lTotalTables);
	    		lblTableName.textProperty().bind(lTableName);
	        }
	    });
	}


	@Override
	public void updateRowCountObs(int rowCount,int currentRow){
		
		double progressInBar =  ((double) currentRow/this.totalRows);
		double progressInTotalRow = Math.round (((double) currentRow/this.totalRows)*100);
		double progressInTableRow = Math.round (((double) rowCount/this.tableRows)*100);
		float  timePassed = Math.round((System.currentTimeMillis() - startTime)/1000F);
		double estimatedTime = ((double)(totalRows*timePassed)/currentRow);
		double timeToFinish = (double)  estimatedTime - timePassed;
		int minutes =(int) timeToFinish/60;
		int seconds = (int) timeToFinish%60;
    	StringProperty lStatus = new SimpleStringProperty("Overall Status ("+progressInTotalRow+"%)");
    	StringProperty lTotalRows = new SimpleStringProperty("Rows: "+currentRow+" of "+totalRows+" ("+progressInTotalRow+"%)");
    	StringProperty lTableRow = new SimpleStringProperty("Rows on current table: "+rowCount+
    			" of "+tableRows+" ("+progressInTableRow+"%)");
    	StringProperty lFinish = new SimpleStringProperty("Estimated time ahead: About " + minutes + "m "+ seconds + "s");

		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	progressBar.setProgress(progressInBar);
	        	lblStatus.textProperty().bind(lStatus);
	        	lblTotalRows.textProperty().bind(lTotalRows);
	    		lblTableRow.textProperty().bind(lTableRow);
	    		lblFinish.textProperty().bind(lFinish);
	        }
	    });
		
	}

	@Override
	public void finish(String finish) {
		long duration = System.currentTimeMillis() - startTime;
    	StringProperty lFinish = new SimpleStringProperty("Done in " + (duration / 60000) + "m "+ (duration % 60000 / 1000) + "s");
		
    	Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	btnMain.setDisable(false);
	        	btnCancel.setDisable(true);
	    		lblFinish.textProperty().bind(lFinish);
	        }
	    });
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnMain.setDisable(true);
    	btnCancel.setDisable(false);		
		Runnable r = new Runnable() {
    		public void run() {
                    exportDB();
    		}
    	};
    	thread = new Thread(r);
    	thread.start();
    	 
	}


	
}
