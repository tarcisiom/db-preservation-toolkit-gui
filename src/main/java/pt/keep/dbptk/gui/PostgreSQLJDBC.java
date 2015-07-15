package pt.keep.dbptk.gui;




import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.postgreSql.in.PostgreSQLJDBCImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.postgreSql.out.PostgreSQLJDBCExportModule;


public class PostgreSQLJDBC implements Initializable, DBMSPane{
	
	@FXML
	private TextField fieldHostname, fieldDatabase, fieldPort, fieldUsername;
	@FXML
	private PasswordField fieldPassword;
	
	@FXML
	private CheckBox useEncrypt;

	public TextField getFieldHostname() {
		return fieldHostname;
	}

	public void setFieldHostname(TextField fieldHostname) {
		this.fieldHostname = fieldHostname;
	}

	public TextField getFieldPort() {
		return fieldPort;
	}

	public void setFieldPort(TextField fieldPort) {
		this.fieldPort = fieldPort;
	}

	public TextField getFieldDatabase() {
		return fieldDatabase;
	}

	public void setFieldDatabase(TextField fieldDatabase) {
		this.fieldDatabase = fieldDatabase;
	}

	public TextField getFieldUsername() {
		return fieldUsername;
	}

	public void setFieldUsername(TextField fieldUsername) {
		this.fieldUsername = fieldUsername;
	}

	public PasswordField getFieldPassword() {
		return fieldPassword;
	}

	public void setFieldPassword(PasswordField fieldPassword) {
		this.fieldPassword = fieldPassword;
	}

	public CheckBox getUseEncrypt() {
		return useEncrypt;
	}

	public void setUseEncrypt(CheckBox useEncrypt) {
		this.useEncrypt = useEncrypt;
	}
	
	public DatabaseImportModule getImportModule(){
		DatabaseImportModule importModule = null;
		if (fieldPort.getText()== null || fieldPort.getText().length() == 0) {
			importModule = new PostgreSQLJDBCImportModule(fieldHostname.getText(),fieldDatabase.getText(), fieldUsername.getText(),fieldPassword.getText(),useEncrypt.isSelected());
		} else {
			importModule = new PostgreSQLJDBCImportModule(fieldHostname.getText(), Integer.valueOf(fieldPort.getText()), fieldDatabase.getText(), fieldUsername.getText(),fieldPassword.getText(),useEncrypt.isSelected());
		}
		return importModule;
	}
	
	public DatabaseHandler getExportModule() {
		DatabaseHandler exportModule = null;
		if (fieldPort.getText()== null || fieldPort.getText().length() == 0) {
			exportModule = new PostgreSQLJDBCExportModule(fieldHostname.getText(),fieldDatabase.getText(), fieldUsername.getText(),fieldPassword.getText(),useEncrypt.isSelected());
		} else {
			exportModule = new PostgreSQLJDBCExportModule(fieldHostname.getText(), Integer.valueOf(fieldPort.getText()), fieldDatabase.getText(), fieldUsername.getText(),fieldPassword.getText(),useEncrypt.isSelected());
		}
		return exportModule;
	}
	
	
	
	public boolean isInputValid() {
        String errorMessage = "";
        
        if (fieldHostname.getText() == null || fieldHostname.getText().length() == 0) {
            errorMessage += "Hostname field empty!\n"; 
        }
        if (fieldDatabase.getText() == null || fieldDatabase.getText().length() == 0) {
            errorMessage += "Database field empty!\n"; 
        }

        if (fieldUsername.getText() == null || fieldUsername.getText().length() == 0) {
            errorMessage += "Username field empty!\n"; 
            
        } 
        if (fieldPassword.getText() == null || fieldPassword.getText().length() == 0) {
            errorMessage += "Password field empty!\n"; 
            
        }
        if (fieldPort.getText() == null || fieldPort.getText().length() != 0) {
	        try {
	            @SuppressWarnings("unused")
				int d = Integer.valueOf(fieldPort.getText());
	            
	          } catch (NumberFormatException nfe) {
	        	  errorMessage += "Port field not a number!\n"; 
	          }
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
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		fieldHostname.setText("localhost");
		fieldPort.setText("5432");
	}
	
}
