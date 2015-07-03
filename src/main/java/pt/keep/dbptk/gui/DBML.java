package pt.keep.dbptk.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import pt.gov.dgarq.roda.common.convert.db.model.exception.ModuleException;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.dbml.in.DBMLImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.dbml.out.DBMLExportModule;



public class DBML  implements DBMSPane{
	
	
	
	
	@FXML
	private Button btnBrowse;
	@FXML
	private Label labelFile;

	@FXML
	private FileChooser fileChooser = new FileChooser();
	
	private DirectoryChooser directoryChooser = new DirectoryChooser();
		
	public DatabaseImportModule getImportModule(){
		DatabaseImportModule importModule = null;
		
		try {
			importModule = new DBMLImportModule(new File(labelFile.getText()));
		} catch (ModuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return importModule;
	}
	
	public DatabaseHandler getExportModule() {
		DatabaseHandler exportModule = null;
		try {
			exportModule = new DBMLExportModule(new File(labelFile.getText()));
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exportModule;
	}
	
	public boolean isInputValid() {
        String errorMessage = "";
        if (labelFile.getText() == null || labelFile.getText().length() == 0) {
            errorMessage += "BaseDir field empty!\n"; 
        }
        
        if (errorMessage.length() == 0) {
        	
            return true;
        } else {
            // Show the error message.
            
        	new DialogMessage(errorMessage,"Correct Invalid Fields");
            
            
            return false;
        }
    }

	
	@FXML 
	private void btnBrowseAction(ActionEvent event) throws Exception {
		
		File selectedDirectory = 
                directoryChooser.showDialog(null);
         
        if(selectedDirectory == null){
        	labelFile.setText("No Directory selected");
        }else{
        	labelFile.setText(selectedDirectory.getAbsolutePath());
        }
		
	}
}
