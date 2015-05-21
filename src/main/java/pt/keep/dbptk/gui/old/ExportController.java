package pt.keep.dbptk.gui.old;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.hadoop.hdfs.server.namenode.FSImageFormat.Loader;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Vector;

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
import pt.gov.dgarq.roda.common.convert.db.model.data.Row;
import pt.gov.dgarq.roda.common.convert.db.model.exception.InvalidDataException;
import pt.gov.dgarq.roda.common.convert.db.model.exception.ModuleException;
import pt.gov.dgarq.roda.common.convert.db.model.exception.UnknownTypeException;
import pt.gov.dgarq.roda.common.convert.db.model.structure.DatabaseStructure;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.db2.in.DB2JDBCImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.db2.out.DB2JDBCExportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.dbml.in.DBMLImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.dbml.out.DBMLExportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.msAccess.in.MsAccessUCanAccessImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.mySql.in.MySQLJDBCImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.mySql.out.MySQLJDBCExportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.mySql.out.PhpMyAdminExportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.oracle.in.Oracle12cJDBCImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.postgreSql.in.PostgreSQLJDBCImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.postgreSql.out.PostgreSQLJDBCExportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.siard.in.SIARDImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.siard.out.SIARDExportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.sqlServer.in.SQLServerJDBCImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.sqlServer.out.SQLServerJDBCExportModule;

@SuppressWarnings("deprecation")
public class ExportController {

	@FXML
	private TextField fieldHost, fieldDatabase, fieldPort, fieldUsername,
			fieldMSAcess;
	@FXML
	private PasswordField fieldPassword;
	@FXML
	private Label lblHost, lblPort, lblDatabase, lblUsername, lblPassword,
			lblEncrypt, lblUseSec;
	@FXML
	private Label lblset1, lblset2, lblset3, lblset4, lblset5, lblset8;
	@FXML
	private Button btnBack, btnNext;
	@FXML
	private ComboBox<String> exportCombo;
	@FXML
	private Pane paneFields, paneBig, paneOutput, paneProgress;
	@FXML
	private CheckBox useEncrypt, useSec;

	// selectOutput
	@FXML
	private ComboBox<String> outputExpansion, outputModule;
	@FXML
	private Button btnNext2, btnBack2, btnBrowse;
	@FXML
	private Label labelFile;
	@FXML
	private FileChooser fileChooser = new FileChooser();

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
	// private File file;

	// private String currentHost, currentDatabase, currentPort,
	// currentUsername, current, current, current ;

	private static final Logger logger = Logger.getLogger(ExportController.class);

	@FXML
	private void comboChangeAction(ActionEvent event) throws Exception {
		String item = (String) exportCombo.getSelectionModel()
				.getSelectedItem();
		if (item.equalsIgnoreCase("MySQLJDBC")
				|| item.equalsIgnoreCase("DB2JDBC")
				|| item.equalsIgnoreCase("Oracle12c")) {
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
		} else if (item.equalsIgnoreCase("PostgreSQLJDBC")) {
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
		} else if (item.equalsIgnoreCase("MSAccessUCanAccess")) {
			lblset1.setVisible(false);
			lblset2.setVisible(false);
			lblset3.setVisible(false);
			lblset4.setVisible(false);
			lblset5.setVisible(false);
			lblset8.setVisible(true);
			lblset8.setText("Database.mdb or accdb");

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

		} else if (item.equalsIgnoreCase("DBML")) {
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
		} else if (item.equalsIgnoreCase("SQLServerJDBC")) {
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

	@FXML
	private void connectNextAction(ActionEvent event) throws Exception {
		String DBML = exportCombo.getSelectionModel().getSelectedItem();

		if (DBML.equalsIgnoreCase("MySQLJDBC")) {
			String hostname = fieldHost.getText();
			String port = fieldPort.getText();
			String database = fieldDatabase.getText();
			String username = fieldUsername.getText();
			String password = fieldPassword.getText();
			if (!hostname.equals("") || !database.equals("")
					|| !username.equals("") || !password.equals("")) {
				if (port.equals("")) {
					importModule = new MySQLJDBCImportModule(hostname,
							database, username, password);
				} else {
					importModule = new MySQLJDBCImportModule(hostname,
							Integer.valueOf(port), database, username, password);
				}
			} else {

				Stage dialogStage = new Stage();
				Button btnok = new Button("Try again");
				dialogStage.initModality(Modality.WINDOW_MODAL);
				dialogStage.setScene(new Scene(VBoxBuilder
						.create()
						.children(new Text("Try again. You have empty fields"),
								btnok).alignment(Pos.CENTER).build()));

				btnok.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {

						dialogStage.close();
					}
				});

				dialogStage.showAndWait();
				throw new Exception("Campos Vazios");

			}
		} else if (DBML.equalsIgnoreCase("DB2JDBC")) {
			String hostname = fieldHost.getText();
			String port = fieldPort.getText();
			String database = fieldDatabase.getText();
			String username = fieldUsername.getText();
			String password = fieldPassword.getText();
			if (!hostname.equals(null) || !port.equals(null)
					|| !database.equals(null) || !username.equals(null)
					|| !password.equals(null)) {
				importModule = new DB2JDBCImportModule(hostname,
						Integer.valueOf(port), database, username, password);
			} else {

				// erro campos vazios
			}

		} else if (DBML.equalsIgnoreCase("Oracle12c")) {
			String hostname = fieldHost.getText();
			String port = fieldPort.getText();
			String database = fieldDatabase.getText();
			String username = fieldUsername.getText();
			String password = fieldPassword.getText();
			if (!hostname.equals(null) || !port.equals(null)
					|| !database.equals(null) || !username.equals(null)
					|| !password.equals(null)) {
				importModule = new Oracle12cJDBCImportModule(hostname,
						Integer.valueOf(port), database, username, password);
			} else {

				// erro campos vazios
			}
		} else if (DBML.equalsIgnoreCase("PostgreSQLJDBC")) {
			String hostname = fieldHost.getText();
			String port = fieldPort.getText();
			String database = fieldDatabase.getText();
			String username = fieldUsername.getText();
			String password = fieldPassword.getText();
			boolean encrypt = useEncrypt.isSelected();
			if (!hostname.equals(null) || !database.equals(null)
					|| !username.equals(null) || !password.equals(null)) {
				if (port.equals(null)) {
					importModule = new PostgreSQLJDBCImportModule(hostname,
							database, username, password, encrypt);
				} else {
					importModule = new PostgreSQLJDBCImportModule(hostname,
							Integer.valueOf(port), database, username,
							password, encrypt);
				}
			} else {

				// erro campos vazios
			}

		} else if (DBML.equalsIgnoreCase("SQLServerJDBC")) {
			String hostname = fieldHost.getText();
			String port = fieldPort.getText();
			String database = fieldDatabase.getText();
			String username = fieldUsername.getText();
			String password = fieldPassword.getText();
			boolean encrypt = useEncrypt.isSelected();
			boolean sec = useSec.isSelected();
			if (!hostname.equals(null) || !database.equals(null)
					|| !username.equals(null) || !password.equals(null)) {
				if (port.equals(null)) {
					importModule = new SQLServerJDBCImportModule(hostname,
							database, username, password, encrypt, sec);
				} else {
					importModule = new SQLServerJDBCImportModule(hostname,
							Integer.valueOf(port), database, username,
							password, encrypt, sec);
				}
			} else {

				// erro campos vazios
			}

		} else if (DBML.equalsIgnoreCase("MSAccessUCanAccess")) {
			String filename = fieldMSAcess.getText();
			if (!filename.equals(null)) {
				importModule = new MsAccessUCanAccessImportModule(new File(
						filename));

			} else {

				// erro campos vazios
			}
		} else if (DBML.equalsIgnoreCase("DBML")) {
			String filename = fieldMSAcess.getText();
			if (!filename.equals(null)) {
				importModule = new DBMLImportModule(new File(filename));
			} else {

				// erro campos vazios
			}
		}

		paneBig.setVisible(false);
		paneOutput.setVisible(true);
	}

	@FXML
	private void btnBrowseAction(ActionEvent event) throws Exception {
		fileChooser.setTitle("This is my file chooser");
		// Show open file dialog
		File file = fileChooser.showSaveDialog(null);
		if (file != null) {
			labelFile.setText(file.getPath());
		}
	}

	@FXML
	private void btnCancelAction(ActionEvent event) throws Exception {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		ClassLoader classLoader = Loader.class.getClassLoader();
		URL fxmlURL = classLoader.getResource("pt/keep/dbptk/gui/Main.fxml");
		InputStream inputStream = classLoader.getResource(
				"pt/keep/dbptk/gui/bundle_en.properties").openStream();
		ResourceBundle bundle = new PropertyResourceBundle(inputStream);
		FXMLLoader loader = new FXMLLoader(fxmlURL, bundle);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(
				getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void btnCancel2Action(ActionEvent event) throws Exception {
		paneBig.setVisible(true);
		paneOutput.setVisible(false);
	}

	@FXML
	private void btnCancel3Action(ActionEvent event) throws Exception {
		paneProgress.setVisible(false);
		paneOutput.setVisible(true);
	}

	@FXML
	private void btnNext2Action(ActionEvent event) throws Exception {
		String filepath = (String) labelFile.getText();
		// String expansion =
		// (String)outputExpansion.getSelectionModel().getSelectedItem();
		String module = (String) outputModule.getSelectionModel()
				.getSelectedItem();
		// DatabaseHandler exp = null;
		if (module.equalsIgnoreCase("SIARD-E 2.0")) {
			try {
				exportModule = new SIARDExportModule(new File(filepath));
			} catch (FileNotFoundException e) {
				logger.error("Could not find file for DBML export", e);
			}

		} else if (module.equalsIgnoreCase("DBML")) {

		} else if (module.equalsIgnoreCase("DB2JDBC")) {

		}

		exportDB();

	}

	private void exportDB() {
		paneOutput.setVisible(false);
		paneProgress.setVisible(true);
		new Thread(() -> {
			int counter = 10;
			for (int i = 0; i < counter; i++) {
				try {

					progressBar.setProgress(1.0 * i / (counter - 1));
					Thread.sleep(1000); // <-------- do more useful stuff here
			} catch (InterruptedException ex) {
			}
		}

	}	).start();

		// progress bar e todo esse patamar
		if (importModule != null && exportModule != null) {
			try {
				long startTime = System.currentTimeMillis();
				logger.info("Translating database: "
						+ importModule.getClass().getSimpleName() + " to "
						+ exportModule.getClass().getSimpleName());
				DatabaseHandlerGUI exp = new DatabaseHandlerGUI(exportModule);
				importModule.getDatabase(exp);
				long duration = System.currentTimeMillis() - startTime;
				logger.info("Done in " + (duration / 60000) + "m "
						+ (duration % 60000 / 1000) + "s");
			} catch (ModuleException e) {
				if (e.getCause() != null
						&& e.getCause() instanceof ClassNotFoundException
						&& e.getCause().getMessage()
								.equals("sun.jdbc.odbc.JdbcOdbcDriver")) {
					logger.error(
							"Could not find the Java ODBC driver, please run this program under Windows "
									+ "to use the JDBC-ODBC bridge.",
							e.getCause());
				} else if (e.getModuleErrors() != null) {
					for (Map.Entry<String, Throwable> entry : e
							.getModuleErrors().entrySet()) {
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
			// printHelp();
			System.out.println("Mal introduzido");
		}

	}

	public void func() {
		List<String> importModuleArgs = new Vector<String>();
		List<String> exportModuleArgs = new Vector<String>();

		DatabaseImportModule importModule = null;
		DatabaseHandler exportModule = null;

		if (importModuleArgs.size() > 0) {
			importModule = getImportModule(importModuleArgs);
		}

		if (exportModuleArgs.size() > 0) {
			exportModule = getExportModule(exportModuleArgs);
		}

		if (importModule != null && exportModule != null) {
			try {
				long startTime = System.currentTimeMillis();
				logger.info("Translating database: "
						+ importModule.getClass().getSimpleName() + " to "
						+ exportModule.getClass().getSimpleName());
				importModule.getDatabase(exportModule);
				long duration = System.currentTimeMillis() - startTime;
				logger.info("Done in " + (duration / 60000) + "m "
						+ (duration % 60000 / 1000) + "s");
			} catch (ModuleException e) {
				if (e.getCause() != null
						&& e.getCause() instanceof ClassNotFoundException
						&& e.getCause().getMessage()
								.equals("sun.jdbc.odbc.JdbcOdbcDriver")) {
					logger.error("Could not find the Java ODBC driver, "
							+ "please run this program under Windows "
							+ "to use the JDBC-ODBC bridge.", e.getCause());
				} else if (e.getModuleErrors() != null) {
					for (Map.Entry<String, Throwable> entry : e
							.getModuleErrors().entrySet()) {
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
			// printHelp();
			System.out.println("Mal introduzido");
		}
	}

	private static DatabaseImportModule getImportModule(
			List<String> importModuleArgs) {
		DatabaseImportModule importModule = null;

		if (importModuleArgs.get(0).equalsIgnoreCase("SQLServerJDBC")) {
			if (importModuleArgs.size() == 7) {
				importModule = new SQLServerJDBCImportModule(
						importModuleArgs.get(1), importModuleArgs.get(2),
						importModuleArgs.get(3), importModuleArgs.get(4),
						importModuleArgs.get(5).equals("true"),
						importModuleArgs.get(6).equals("true"));
			} else if (importModuleArgs.size() == 8) {
				try {
					importModule = new SQLServerJDBCImportModule(
							importModuleArgs.get(1), Integer.valueOf(
									importModuleArgs.get(2)).intValue(),
							importModuleArgs.get(3), importModuleArgs.get(4),
							importModuleArgs.get(5), importModuleArgs.get(6)
									.equals("true"), importModuleArgs.get(7)
									.equals("true"));
				} catch (NumberFormatException e) {
					importModule = new SQLServerJDBCImportModule(
							importModuleArgs.get(1), importModuleArgs.get(2),
							importModuleArgs.get(3), importModuleArgs.get(4),
							importModuleArgs.get(5), importModuleArgs.get(6)
									.equals("true"), importModuleArgs.get(7)
									.equals("true"));
				}
			} else {
				logger.error("Wrong argument number for "
						+ "SQLServerJDBC import module: "
						+ importModuleArgs.size());
			}
		} else if (importModuleArgs.get(0).equalsIgnoreCase("PostgreSQLJDBC")) {
			if (importModuleArgs.size() == 6) {
				importModule = new PostgreSQLJDBCImportModule(
						importModuleArgs.get(1), importModuleArgs.get(2),
						importModuleArgs.get(3), importModuleArgs.get(4),
						importModuleArgs.get(5).equals("true"));
			} else if (importModuleArgs.size() == 7) {
				importModule = new PostgreSQLJDBCImportModule(
						importModuleArgs.get(1), Integer.valueOf(
								importModuleArgs.get(2)).intValue(),
						importModuleArgs.get(3), importModuleArgs.get(4),
						importModuleArgs.get(5), importModuleArgs.get(6)
								.equals("true"));
			} else {
				logger.error("Wrong argument number for "
						+ "PostgreSQLJDBC import module: "
						+ importModuleArgs.size());
			}
		} else if (importModuleArgs.get(0).equalsIgnoreCase("DB2JDBC")) {
			if (importModuleArgs.size() == 6) {
				importModule = new DB2JDBCImportModule(importModuleArgs.get(1),
						Integer.valueOf(importModuleArgs.get(2)),
						importModuleArgs.get(3), importModuleArgs.get(4),
						importModuleArgs.get(5));
			} else {
				logger.error("Wrong argument number for DB2JDBC import module: "
						+ importModuleArgs.size());
			}
		} else if (importModuleArgs.get(0).equalsIgnoreCase("MySQLJDBC")) {
			if (importModuleArgs.size() == 5) {
				importModule = new MySQLJDBCImportModule(
						importModuleArgs.get(1), importModuleArgs.get(2),
						importModuleArgs.get(3), importModuleArgs.get(4));
			} else if (importModuleArgs.size() == 6) {
				importModule = new MySQLJDBCImportModule(
						importModuleArgs.get(1), Integer.valueOf(
								importModuleArgs.get(2)).intValue(),
						importModuleArgs.get(3), importModuleArgs.get(4),
						importModuleArgs.get(5));
			} else {
				logger.error("Wrong argument number for "
						+ "MySQLJDBC import module: " + importModuleArgs.size());
			}
		} else if (importModuleArgs.get(0).equals("DBML")) {
			if (importModuleArgs.size() == 2) {
				try {
					importModule = new DBMLImportModule(new File(
							importModuleArgs.get(1)));
				} catch (ModuleException e) {
					logger.error("Error creating DBML import module", e);
				}
			} else {
				logger.error("Wrong argument number for "
						+ "DBML import module: " + importModuleArgs.size());
			}
		} else if (importModuleArgs.get(0).equalsIgnoreCase("SIARD")) {
			if (importModuleArgs.size() == 2) {
				try {
					importModule = new SIARDImportModule(new File(
							importModuleArgs.get(1)));
				} catch (ModuleException e) {
					logger.error("Error creating SIARD import module", e);
				}
			} else {
				logger.error("Wrong argument number for "
						+ "SIARD import module: " + importModuleArgs.size());
			}
		} else if (importModuleArgs.get(0).equalsIgnoreCase("Oracle12cJDBC")) {
			if (importModuleArgs.size() == 6) {
				importModule = new Oracle12cJDBCImportModule(
						importModuleArgs.get(1), Integer.valueOf(
								importModuleArgs.get(2)).intValue(),
						importModuleArgs.get(3), importModuleArgs.get(4),
						importModuleArgs.get(5));
			} else {
				logger.error("Wrong argument number for "
						+ "Oracle12c import module: " + importModuleArgs.size());
			}

		} else if (importModuleArgs.get(0).equals("MSAccessUCanAccess")) {
			if (importModuleArgs.size() == 2) {
				importModule = new MsAccessUCanAccessImportModule(new File(
						importModuleArgs.get(1)));
			} else {
				logger.error("Wrong argument number for "
						+ "MSAccessExp import module: "
						+ importModuleArgs.size());
			}

		} else {
			logger.error("Unrecognized import module: "
					+ importModuleArgs.get(0));
		}
		return importModule;
	}

	private static DatabaseHandler getExportModule(List<String> exportModuleArgs) {
		DatabaseHandler exportModule = null;
		if (exportModuleArgs.get(0).equals("DBML")) {
			if (exportModuleArgs.size() == 2) {
				try {
					exportModule = new DBMLExportModule(new File(
							exportModuleArgs.get(1)));
				} catch (FileNotFoundException e) {
					logger.error("Could not find file for DBML export", e);
				} catch (UnsupportedEncodingException e) {
					logger.error("Unsupported encoding", e);
				}
			} else {
				logger.error("Wrong argument number for "
						+ "DBML export module: " + exportModuleArgs.size());
			}
		} else if (exportModuleArgs.get(0).equalsIgnoreCase("SIARD")) {
			if (exportModuleArgs.size() == 2) {
				try {
					exportModule = new SIARDExportModule(new File(
							exportModuleArgs.get(1)));
				} catch (FileNotFoundException e) {
					logger.error("Could not find file for SIARD export", e);
				}
			} else {
				logger.error("Wrong argument number for SIARD export module: "
						+ exportModuleArgs.size());
			}
		} else if (exportModuleArgs.get(0).equalsIgnoreCase("DB2JDBC")) {
			if (exportModuleArgs.size() == 6) {
				exportModule = new DB2JDBCExportModule(exportModuleArgs.get(1),
						Integer.valueOf(exportModuleArgs.get(2)),
						exportModuleArgs.get(3), exportModuleArgs.get(4),
						exportModuleArgs.get(5));
			} else {
				logger.error("Wrong argument number for DB2JDBC export module: "
						+ exportModuleArgs.size());
			}
		} else if (exportModuleArgs.get(0).equalsIgnoreCase("PostgreSQLJDBC")) {
			if (exportModuleArgs.size() == 6) {
				exportModule = new PostgreSQLJDBCExportModule(
						exportModuleArgs.get(1), exportModuleArgs.get(2),
						exportModuleArgs.get(3), exportModuleArgs.get(4),
						exportModuleArgs.get(5).equals("true"));
			} else if (exportModuleArgs.size() == 7) {
				exportModule = new PostgreSQLJDBCExportModule(
						exportModuleArgs.get(1), Integer.valueOf(
								exportModuleArgs.get(2)).intValue(),
						exportModuleArgs.get(3), exportModuleArgs.get(4),
						exportModuleArgs.get(5), exportModuleArgs.get(6)
								.equals("true"));
			} else {
				logger.error("Wrong argument number for "
						+ "PostgreSQLJDBC export module: "
						+ exportModuleArgs.size());
			}

		} else if (exportModuleArgs.get(0).equalsIgnoreCase("MySQLJDBC")) {
			if (exportModuleArgs.size() == 5) {
				exportModule = new MySQLJDBCExportModule(
						exportModuleArgs.get(1), exportModuleArgs.get(2),
						exportModuleArgs.get(3), exportModuleArgs.get(4));
			} else if (exportModuleArgs.size() == 6) {
				exportModule = new MySQLJDBCExportModule(
						exportModuleArgs.get(1), Integer.valueOf(
								exportModuleArgs.get(2)).intValue(),
						exportModuleArgs.get(3), exportModuleArgs.get(4),
						exportModuleArgs.get(5));
			} else {
				logger.error("Wrong argument number for "
						+ "MySQLJDBC export module: " + exportModuleArgs.size());
			}
		} else if (exportModuleArgs.get(0).equals("PhpMyAdmin")) {
			if (exportModuleArgs.size() == 5) {
				exportModule = new PhpMyAdminExportModule(
						exportModuleArgs.get(1), exportModuleArgs.get(2),
						exportModuleArgs.get(3), exportModuleArgs.get(4));
			} else if (exportModuleArgs.size() == 6) {
				exportModule = new PhpMyAdminExportModule(
						exportModuleArgs.get(1), Integer.valueOf(
								exportModuleArgs.get(2)).intValue(),
						exportModuleArgs.get(3), exportModuleArgs.get(4),
						exportModuleArgs.get(5));
			} else {
				logger.error("Wrong argument number for "
						+ "PhpMyAdmin export module: "
						+ exportModuleArgs.size());
			}
		} else if (exportModuleArgs.get(0).equalsIgnoreCase("SQLServerJDBC")) {
			if (exportModuleArgs.size() == 7) {
				exportModule = new SQLServerJDBCExportModule(
						exportModuleArgs.get(1), exportModuleArgs.get(2),
						exportModuleArgs.get(3), exportModuleArgs.get(4),
						exportModuleArgs.get(5).equals("true"),
						exportModuleArgs.get(6).equals("true"));
			} else if (exportModuleArgs.size() == 8) {
				try {
					exportModule = new SQLServerJDBCExportModule(
							exportModuleArgs.get(1), Integer.valueOf(
									exportModuleArgs.get(2)).intValue(),
							exportModuleArgs.get(3), exportModuleArgs.get(4),
							exportModuleArgs.get(5), exportModuleArgs.get(6)
									.equals("true"), exportModuleArgs.get(7)
									.equals("true"));
				} catch (NumberFormatException e) {
					exportModule = new SQLServerJDBCExportModule(
							exportModuleArgs.get(1), exportModuleArgs.get(2),
							exportModuleArgs.get(3), exportModuleArgs.get(4),
							exportModuleArgs.get(5), exportModuleArgs.get(6)
									.equals("true"), exportModuleArgs.get(7)
									.equals("true"));
				}
			} else {
				logger.error("Wrong argument number for "
						+ "SQLServerJDBC import module: "
						+ exportModuleArgs.size());
			}
		} else {
			logger.error("Unrecognized export module: "
					+ exportModuleArgs.get(0));
		}
		return exportModule;
	}

	class DatabaseHandlerGUI implements DatabaseHandler {

		private DatabaseHandler delegate;

		private int rowCount = 0;

		public DatabaseHandlerGUI(DatabaseHandler delegate) {
			super();
			this.delegate = delegate;
		}

		@Override
		public void finishDatabase() throws ModuleException {
			delegate.finishDatabase();
		}

		@Override
		public void handleDataCloseTable(String arg0) throws ModuleException {
			delegate.handleDataCloseTable(arg0);
			rowCount = 0;
		}

		@Override
		public void handleDataOpenTable(String arg0) throws ModuleException {
			rowCount = 0;
			// TODO update interface with table opened.
			delegate.handleDataOpenTable(arg0);
		}

		@Override
		public void handleDataRow(Row arg0) throws InvalidDataException,
				ModuleException {
			rowCount++;
			if (rowCount % 100 == 0) {
				// TODO update interface with row count
			}

			delegate.handleDataRow(arg0);

		}

		@Override
		public void handleStructure(DatabaseStructure arg0)
				throws ModuleException, UnknownTypeException {
			// TODO Auto-generated method stub

		}

		@Override
		public void initDatabase() throws ModuleException {
			// TODO Auto-generated method stub

		}

		@Override
		public void setIgnoredSchemas(Set<String> arg0) {
			// TODO Auto-generated method stub

		}

	}

}
