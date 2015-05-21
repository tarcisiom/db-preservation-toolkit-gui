package pt.keep.dbptk.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import pt.gov.dgarq.roda.common.convert.db.model.exception.InvalidDataException;
import pt.gov.dgarq.roda.common.convert.db.model.exception.ModuleException;
import pt.gov.dgarq.roda.common.convert.db.model.exception.UnknownTypeException;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.siard.out.SIARDExportModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

public class SIARD {
	@FXML
	private ComboBox<String> outputExpansion, outputModule;
	@FXML
	private Button btnNext, btnCancel, btnBrowse;
	@FXML
	private Label labelFile;
	@FXML
	private FileChooser fileChooser = new FileChooser();
	
	public DatabaseHandler exportModule;
	
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
	public void btnCancelAction(ActionEvent event) throws Exception {
		
		
		
		//VistaNavigator.dbms.setVista(VistaNavigator.previous);
		//Node node = (Node) VistaNavigator.getDbms();
		VistaNavigator.exportPageController.setVista(VistaNavigator.previous);
		//VistaNavigator.loadDBMS();
		
	}
	
	@FXML
	public void btnNextAction(ActionEvent event) throws Exception {
		
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
				
			}

		} else if (module.equalsIgnoreCase("DBML")) {

		} else if (module.equalsIgnoreCase("DB2JDBC")) {

		}
		VistaNavigator.exportModule = new DatabaseHandlerGUI(exportModule);
		VistaNavigator.loadVista(VistaNavigator.IMPORTDATA,"pt/keep/dbptk/gui/bundle_en.properties");
		
	}
	
	
	

}
