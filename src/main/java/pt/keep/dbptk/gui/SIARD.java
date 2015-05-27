package pt.keep.dbptk.gui;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.siard.out.SIARDExportModule;

public class SIARD {
	@FXML
	private ComboBox<String> outputExpansion, outputModule;
	@FXML
	private Button btnNext, btnCancel, btnBrowse;
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
		
		
	//	NavigatorExport.exportPageController.setVista(NavigatorExport.previous);
		Navigator.loadVista(App.DBMSCHOOSER);
	}
	
	@FXML
	public void btnNextAction(ActionEvent event) throws Exception {
		DatabaseHandler exportModule = null;
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
				exportModule = new SIARDExportModule(new File(filepath));
			} 
        	sucess = true;
        } else {
            // Show the error message.
            new DialogMessage(errorMessage);
            
        }
		if(sucess){

			Navigator.setExportModule(exportModule);
			Navigator.addNodes(App.IMPORTDATA);
		       
			Navigator.loadVista(App.IMPORTDATA);
		}
		
	}
	
	
	

}
