package pt.keep.dbptk.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pt.gov.dgarq.roda.common.convert.db.model.exception.ModuleException;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.dbml.in.DBMLImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.dbml.out.DBMLExportModule;



public class DBML  implements DBMSPane{
	
	@FXML 
	private TextField fieldData;
	
	
	public DatabaseImportModule getImportModule(){
		DatabaseImportModule importModule = null;
		
		try {
			importModule = new DBMLImportModule(new File(fieldData.getText()));
		} catch (ModuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return importModule;
	}
	
	public DatabaseHandler getExportModule() {
		DatabaseHandler exportModule = null;
		try {
			exportModule = new DBMLExportModule(new File(fieldData.getText()));
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exportModule;
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
            
        	new DialogMessage(errorMessage);
            
            
            return false;
        }
    }
}
