package pt.keep.dbptk.gui.old;


import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;








import org.apache.hadoop.hdfs.server.namenode.FSImageFormat.Loader;
import org.apache.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.gov.dgarq.roda.common.convert.db.model.exception.InvalidDataException;
import pt.gov.dgarq.roda.common.convert.db.model.exception.ModuleException;
import pt.gov.dgarq.roda.common.convert.db.model.exception.UnknownTypeException;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.db2.out.DB2JDBCExportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.dbml.out.DBMLExportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.mySql.out.MySQLJDBCExportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.postgreSql.out.PostgreSQLJDBCExportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.siard.in.SIARDImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.sqlServer.out.SQLServerJDBCExportModule;


 
@SuppressWarnings("deprecation")
public class ImportController {
	
	@FXML
	private Pane paneInput, paneConnect;
	@FXML 
	private ComboBox<String> inputExpansion, inputModule;
	@FXML
	private Button btnNext, btnCancel, btnBrowse;
	@FXML
	private Label labelFile, lblTitle, lblSubtitle;
	@FXML
	private FileChooser fileChooser = new FileChooser(); 
	
	//select database
	
	@FXML
	private TextField fieldHost, fieldDatabase, fieldPort, fieldUsername, fieldMSAcess;
	@FXML
	private PasswordField fieldPassword;
	@FXML
	private Label lblHost, lblPort, lblDatabase, lblUsername, lblPassword, lblEncrypt , lblUseSec;	
	@FXML
	private Label lblset1, lblset2, lblset3, lblset4, lblset5, lblset8;
	@FXML
	private Button btnBack2, btnNext2;
	@FXML
	private ComboBox<String> importCombo;
	@FXML
	private Pane paneFields, paneBig, paneOutput, paneProgress;
	@FXML
	private CheckBox useEncrypt, useSec;
	
	// progress
	@FXML
	private Button btnCancel3;
	@FXML
	private Button btnNext3;
	@FXML
	private Label lblStatus;
	@FXML
	private ProgressBar progressBar;
	
	private DatabaseImportModule importModule;
	private DatabaseHandler exportModule;
	
	private static final Logger logger = Logger.getLogger(ImportController.class);
	
   
	@FXML private void btnCancelAction(ActionEvent event) throws Exception{
		Node node= (Node) event.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
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
	
	@FXML private void btnNextAction(ActionEvent event) throws Exception{
		String filepath = labelFile.getText();
		//String expansion = (String)inputExpansion.getSelectionModel().getSelectedItem();
		String module = inputModule.getSelectionModel().getSelectedItem();
		//DatabaseHandler imp = null;
		String empty = inputModule.getSelectionModel().getSelectedItem();
		if(empty.equals(null)){
			System.out.println("vazio");
		}
		if(!filepath.equals(null) || !module.equals(null) ){
			if(module.equalsIgnoreCase("SIARD-E 2.0")){
				importModule = new SIARDImportModule(new File(filepath));
			}
			else if(module.equalsIgnoreCase("DBML")){
				
				
				
			}
			else if(module.equalsIgnoreCase("DB2JDBC")){
				
				
				
			}
		}
		else{
			Stage dialogStage = new Stage();
			Button btnok = new Button("Try again");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.setScene(new Scene(VBoxBuilder.create().
			    children(new Text("Try again. You have empty fields"), btnok).
			    alignment(Pos.CENTER).build()));
			
			btnok.setOnAction( new EventHandler<ActionEvent>() {
		        @Override public void handle( ActionEvent e ) {
		        	
		            dialogStage.close();
		        }
		    } );
			
			dialogStage.showAndWait();
			throw new Exception("Campos Vazios");
			
						
			
			
		}
		paneInput.setVisible(false);
		paneConnect.setVisible(true);
		lblTitle.setText("2.Connect to database");
		lblSubtitle.setText("Select your database management system (DBMS) and configure the connection");
		
	}
	
	
	@FXML private void btnBrowseAction(ActionEvent event) throws Exception{
		fileChooser.setTitle("This is my file chooser");
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        if(file!=null){
            labelFile.setText(file.getPath());
    	}
	}
	
	@FXML private void comboChangeAction(ActionEvent event) throws Exception{
		String item = (String)importCombo.getSelectionModel().getSelectedItem();
		if(item.equalsIgnoreCase("MySQLJDBC") || item.equalsIgnoreCase("DB2JDBC"))
		{	
			lblset1.setText("Hostname");
			lblset1.setVisible(true);
			lblset2.setVisible(true);
			lblset3.setVisible(true);
			lblset4.setVisible(true);
			lblset5.setVisible(true);
			lblset8.setVisible(false);
			
			lblEncrypt.setVisible(false);
			lblUseSec.setVisible(false);
			
			fieldHost.setVisible(true);
			fieldDatabase.setVisible(true);
			fieldPort.setVisible(true);
			fieldUsername.setVisible(true);
			fieldPassword.setVisible(true);
			useEncrypt.setVisible(false);
			useSec.setVisible(false);
			fieldMSAcess.setVisible(false);
		}
		else if(item.equalsIgnoreCase("PostgreSQLJDBC"))
		{
			lblset1.setText("Hostname");
			lblset1.setVisible(true);
			lblset2.setVisible(true);
			lblset3.setVisible(true);
			lblset4.setVisible(true);
			lblset5.setVisible(true);
			lblset8.setVisible(false);

			lblEncrypt.setVisible(true);
			lblUseSec.setVisible(false);
			
			fieldHost.setVisible(true);
			fieldDatabase.setVisible(true);
			fieldPort.setVisible(true);
			fieldUsername.setVisible(true);
			fieldPassword.setVisible(true);
			useEncrypt.setVisible(true);
			useSec.setVisible(false);
			fieldMSAcess.setVisible(false);
		}
		else if(item.equalsIgnoreCase("DBML"))
		{
			lblset1.setVisible(false);
			lblset2.setVisible(false);
			lblset3.setVisible(false);
			lblset4.setVisible(false);
			lblset5.setVisible(false);
			lblset8.setVisible(true);
			lblset8.setText("BaseDir");
			
			lblEncrypt.setVisible(false);
			lblUseSec.setVisible(false);
			
			fieldHost.setVisible(false);
			fieldDatabase.setVisible(false);
			fieldPort.setVisible(false);
			fieldUsername.setVisible(false);
			fieldPassword.setVisible(false);
			useEncrypt.setVisible(false);
			useSec.setVisible(false);
			fieldMSAcess.setVisible(true);
		}
		else if(item.equalsIgnoreCase("SQLServerJDBC"))
		{
			lblset1.setText("ServerName");
			lblset1.setVisible(true);
			lblset2.setVisible(true);
			lblset3.setVisible(true);
			lblset4.setVisible(true);
			lblset5.setVisible(true);
			lblset8.setVisible(false);
			
			lblEncrypt.setVisible(true);
			lblUseSec.setVisible(true);
			
			fieldHost.setVisible(true);
			fieldDatabase.setVisible(true);
			fieldPort.setVisible(true);
			fieldUsername.setVisible(true);
			fieldPassword.setVisible(true);
			useEncrypt.setVisible(true);
			useSec.setVisible(true);
			fieldMSAcess.setVisible(false);
			
		}
	}
	
	@FXML private void connectAction( final ActionEvent event) throws Exception{
		String DBML = importCombo.getSelectionModel().getSelectedItem();
		if(DBML.equalsIgnoreCase("MySQLJDBC")){
			String hostname = fieldHost.getText();
			String port = fieldPort.getText();
			String database = fieldDatabase.getText();
			String username = fieldUsername.getText();
			String password = fieldPassword.getText();
			if(!hostname.equals("")|| !database.equals("")|| !username.equals("")|| !password.equals("")){
				if(port.equals("")){
					exportModule = new MySQLJDBCExportModule(hostname,database,username,password);
				}
				else {
					exportModule = new MySQLJDBCExportModule(hostname,Integer.valueOf(port),database,username,password);
				}
			}
			else{
				
				//erro campos Vazios
				throw new Exception("Campos Vazios");
			}
		}
		else if(DBML.equalsIgnoreCase("DB2JDBC")){
			String hostname = fieldHost.getText();
			String port = fieldPort.getText();
			String database = fieldDatabase.getText();
			String username = fieldUsername.getText();
			String password = fieldPassword.getText();
			if(!hostname.equals(null) ||!port.equals(null) || !database.equals(null)|| !username.equals(null)|| !password.equals(null)){
				exportModule = new DB2JDBCExportModule(hostname,Integer.valueOf(port),database,username,password);
			}
			else{
				
				//erro campos vazios
			}
			
		}
		else if(DBML.equalsIgnoreCase("PostgreSQLJDBC")){
			String hostname = fieldHost.getText();
			String port = fieldPort.getText();
			String database = fieldDatabase.getText();
			String username = fieldUsername.getText();
			String password = fieldPassword.getText();
			boolean encrypt = useEncrypt.isSelected();
			if(!hostname.equals(null)|| !database.equals(null)|| !username.equals(null)|| !password.equals(null)){
				if(port.equals(null)){
					exportModule = new PostgreSQLJDBCExportModule(hostname,database,username,password, encrypt);
				}
				else {
					exportModule = new PostgreSQLJDBCExportModule(hostname,Integer.valueOf(port),database,username,password, encrypt);
				}
			}
			else{
				
				//erro campos vazios
			}
			
			
		}
		else if(DBML.equalsIgnoreCase("SQLServerJDBC")){
			String hostname = fieldHost.getText();
			String port = fieldPort.getText();
			String database = fieldDatabase.getText();
			String username = fieldUsername.getText();
			String password = fieldPassword.getText();
			boolean encrypt = useEncrypt.isSelected();
			boolean sec = useSec.isSelected();
			if(!hostname.equals(null)|| !database.equals(null)|| !username.equals(null)|| !password.equals(null)){
				if(port.equals(null)){
					exportModule = new SQLServerJDBCExportModule(hostname,database,username,password, encrypt,sec);
				}
				else {
					exportModule = new SQLServerJDBCExportModule(hostname,Integer.valueOf(port),database,username,password, encrypt,sec);
				}
			}
			else{
				
				//erro campos vazios
			}
			
			
		}
		else if(DBML.equalsIgnoreCase("DBML"))
		{
			String filename = fieldMSAcess.getText();
			if(!filename.equals(null)){
				exportModule = new DBMLExportModule(new File(filename));
			}
			else{
				
				//erro campos vazios
			}
		}
		importDB();
		
	}
	
	@FXML private void btnCancel2Action(ActionEvent event) throws Exception{
		paneConnect.setVisible(false);
		paneInput.setVisible(true);

		lblTitle.setText("1. Select SIARD input");
		lblSubtitle.setText("Select the SIARD details and input location");
	}
	
	@FXML private void btnCancel3Action(ActionEvent event) throws Exception{
		paneConnect.setVisible(true);
		paneProgress.setVisible(false);
		lblTitle.setText("2.Connect to database");
		lblSubtitle.setText("Select your database management system (DBMS) and configure the connection");
	}
	
	private void importDB(){
		paneConnect.setVisible(false);
		paneProgress.setVisible(true);
		lblTitle.setText("3.Import data");
		lblSubtitle.setText("Monitor and manage the import data progress");
		
		new Thread(() -> {
            int counter = 10;
            for (int i = 0; i < counter; i++) {
                try {
                	//Double perc =  1.0 * i / (counter - 1);
                   // lblStatus.setText(perc+"%");
                    progressBar.setProgress(1.0 * i / (counter - 1));
                    Thread.sleep(1000); //<-------- do more useful stuff here
                } catch (InterruptedException ex) {
                }
            }
            
        }).start();
		
		// progress bar e todo esse patamar
		if (importModule != null && exportModule != null) {
			try {
				long startTime = System.currentTimeMillis();
				logger.info("Translating database: "+importModule.getClass().getSimpleName() 
						   +" to "+ exportModule.getClass().getSimpleName());
				importModule.getDatabase(exportModule);
				long duration = System.currentTimeMillis() - startTime;
				logger.info("Done in " + (duration / 60000) + "m "+ (duration % 60000 / 1000) + "s");
			} catch (ModuleException e) {
				if (e.getCause() != null
						&& e.getCause() instanceof ClassNotFoundException
						&& e.getCause().getMessage().equals("sun.jdbc.odbc.JdbcOdbcDriver")) 
				{
					logger.error("Could not find the Java ODBC driver, please run this program under Windows "
							   + "to use the JDBC-ODBC bridge.",e.getCause());
				} else if (e.getModuleErrors() != null) {
							for (Map.Entry<String, Throwable> entry : e.getModuleErrors().entrySet()) {
								logger.error(entry.getKey(), entry.getValue());
							}
				} else {
					logger.error("Error while importing/exporting", e);
				}
			} catch (UnknownTypeException e) {
				logger.error("Error while importing/exporting", e);
			} catch (InvalidDataException e) {
				logger.error("Error while importing/exporting", e);
			} catch (Exception e) {
				logger.error("Unexpected exception", e);
			}
	
		} else {
			//printHelp();
			System.out.println("Mal introduzido");
		}

	}
	
}
