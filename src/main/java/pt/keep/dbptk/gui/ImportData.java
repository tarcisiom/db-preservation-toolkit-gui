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
	public  Label lblStatus,lblTableName, lblTableRow, lblFinish, lblDone;
	@FXML 
	public  Button  btnMain, btnCancel;

	private long startTime;
	
	
	

	
	public ImportData() {
		
		
	}



	@FXML 
	public void btnCancelAction(ActionEvent event) {
			
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
				
				
				//progressBar.progressProperty().set(0);
				//task.cancel();
			    
				
			} catch (ModuleException e) {
				if (e.getCause() != null
						&& e.getCause() instanceof ClassNotFoundException
						&& e.getCause().getMessage().equals("sun.jdbc.odbc.JdbcOdbcDriver")) {
					new DialogMessage("Could not find the Java ODBC driver, please run this program under Windows to use the JDBC-ODBC bridge."
						+e.getCause(),"Close");
				} else if (e.getModuleErrors() != null) {
					for (Map.Entry<String, Throwable> entry : e
							.getModuleErrors().entrySet()) {
				
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
	public void update(String tableName){
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	
	        	StringProperty other = new SimpleStringProperty(tableName);
	    		lblTableName.textProperty().bind(other);
	        }
	    });
	}

	@Override
	public void updateRowcount(String table, String arg1, String arg2,
			String arg3) {
		// TODO Auto-generated method stub

		int currentTableRows = Integer.parseInt(table);
		int currentTotalRow = Integer.parseInt(arg1);
		int totalRows = Integer.parseInt(arg2);
		int totalTableRows = Integer.parseInt(arg3);
		double progressB = (double) currentTotalRow/totalRows;
		double progressT = Math.round (((double) currentTableRows/totalTableRows)*100);
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	progressBar.setProgress(progressB);
	        	StringProperty overall = new SimpleStringProperty("Overall Status ("+Math.round(progressB*100)+"%)");
	        	StringProperty other = new SimpleStringProperty(table +" rows processed of "+arg3+ "("
	        			+progressT+" %)");
	        	lblStatus.textProperty().bind(overall);
	    		lblTableRow.textProperty().bind(other);
	        }
	    });
	}


	@Override
	public void finish(String finish) {
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	long duration = System.currentTimeMillis() - startTime;
	        	StringProperty other = new SimpleStringProperty("Done in " + (duration / 60000) + "m "+ (duration % 60000 / 1000) + "s");
	    		lblFinish.textProperty().bind(other);
	        }
	    });
		
		
	}


	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		/*  
        task = new Task<Void>() {
    	 	@Override
			public Void call() throws Exception {
				// TODO Auto-generated method stub
				final int max = 100;
    	        for (int i=1; i<=max; i++) {
    	        	if (isCancelled()) {
    	                break;
    	            }
    	            updateProgress(i, max);
    	        }
				return null;
			}
    	};
    	progressBar.progressProperty().bind(task.progressProperty());
    	*/
    	new Thread(){
    		public void run() {
                    exportDB();		
           }
    	}.start();
		 
	}



	
	


	
}
