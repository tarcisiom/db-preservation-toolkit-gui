package pt.keep.dbptk.gui;

import java.io.File;

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
	private Button btnNext, btnBack, btnBrowse;
	@FXML
	private Label labelFile;
	@FXML
	private FileChooser fileChooser = new FileChooser();
	
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
		
		
		VistaNavigator.loadDBMS();
		
	}
	
	@FXML
	public void btnNextAction(ActionEvent event) throws Exception {
		VistaNavigator.loadVista(VistaNavigator.IMPORTDATA,"pt/keep/dbptk/gui/bundle_en.properties");
	}
}
