package pt.keep.dbptk.gui;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.msAccess.in.MsAccessUCanAccessImportModule;





public  class MSAccessUCanAccess  implements DBMSPane{

	@FXML 
	private TextField fieldData;

	public TextField getFieldData() {
		return fieldData;
	}

	public void setFieldData(TextField fieldData) {
		this.fieldData = fieldData;
	}

	public DatabaseImportModule getImportModule(){
		DatabaseImportModule importModule = null;
		importModule = new MsAccessUCanAccessImportModule(new File(fieldData.getText()));
		return importModule;
	}

	public DatabaseHandler getExportModule(){
		DatabaseHandler importModule = null;
		return importModule;
	}
	
	
	public boolean isInputValid() {
        String errorMessage = "";
        if (fieldData.getText() == null || fieldData.getText().length() == 0) {
            errorMessage += "Hostname field empty!\n"; 
        }
        
        if (errorMessage.length() == 0) {
        	
            return true;
        } else {
            // Show the error message.
        	new DialogMessage(errorMessage,"Correct Invalid Fields");
            
            
            return false;
        }
    }
}
