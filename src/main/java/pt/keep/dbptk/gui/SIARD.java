package pt.keep.dbptk.gui;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import pt.gov.dgarq.roda.common.convert.db.model.exception.ModuleException;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.siard.in.SIARDImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.siard.out.SIARDExportModule;

public class SIARD implements DBMSPane {

	
	@FXML
	private Button btnBrowse;
	@FXML
	private Label labelFile;
	@FXML
	private ComboBox<String> outputExpansion;

	@FXML
	private FileChooser fileChooser = new FileChooser();
	
	@Override
	public boolean isInputValid() {
		// TODO Auto-generated method stub
		String errorMessage = "";
        if (labelFile.getText() == null || labelFile.getText().length() == 0) {
            errorMessage += "Wrong Filepath!\n"; 
        }
        
        if (errorMessage.length() == 0) {
        	
            return true;
        } else {
            // Show the error message.
            
        	new DialogMessage(errorMessage,"Correct Invalid Fields");
            
            
            return false;
        }
		
		
	}

	@Override
	public DatabaseImportModule getImportModule() {
		DatabaseImportModule importModule = null;
		
		try {
			importModule = new SIARDImportModule(new File(labelFile.getText()));
		} catch (ModuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return importModule;
	}

	@Override
	public DatabaseHandler getExportModule() {
		DatabaseHandler exportModule = null;
		String comp = (String)outputExpansion.getSelectionModel().getSelectedItem();
		boolean cp =false;
		if (comp.equals("Compressed ZIP")) {
			cp = true;
		}
		try {
			exportModule = new SIARDExportModule(new File(labelFile.getText()),cp);
			//exportModule = new SIARDExportModule(new File(labelFile.getText()),(String)outputExpansion.getSelectionModel().getSelectedItem());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return exportModule;
	}
	
	@FXML
	private void btnBrowseAction(ActionEvent event) throws Exception {
		File file;
		if(App.importpage){
			fileChooser.setTitle("Save a SIARD databese");
			
			file = fileChooser.showSaveDialog(null);
			
			
		}
		else{
			
			fileChooser.setTitle("Choose a SIARD Databse");
			
			file = fileChooser.showOpenDialog(null);
		}
		if (file != null) {
			labelFile.setText(file.getPath());
		}
	}

}
