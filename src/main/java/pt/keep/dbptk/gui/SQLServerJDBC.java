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
import pt.gov.dgarq.roda.common.convert.db.modules.sqlServer.in.SQLServerJDBCImportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.sqlServer.out.SQLServerJDBCExportModule;


public class SQLServerJDBC implements Initializable, DBMSPane{
	
	@FXML
	private TextField fieldServerName, fieldPort, fieldDatabase, fieldUsername;
	@FXML
	private PasswordField fieldPassword;
	
	@FXML
	private CheckBox useEncrypt, useSec;

	public TextField getFieldServerName() {
		return fieldServerName;
	}

	public void setFieldServerName(TextField fieldServerName) {
		this.fieldServerName = fieldServerName;
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

	public CheckBox getUseSec() {
		return useSec;
	}

	public void setUseSec(CheckBox useSec) {
		this.useSec = useSec;
	}
	
	public DatabaseImportModule getImportModule(){
		DatabaseImportModule importModule = null;
		if (fieldPort.getText()== null || fieldPort.getText().length() == 0) {
			importModule = new SQLServerJDBCImportModule(fieldServerName.getText(),fieldDatabase.getText(), fieldUsername.getText(),fieldPassword.getText(),useEncrypt.isSelected(),useSec.isSelected());
		} else {
			importModule = new SQLServerJDBCImportModule(fieldServerName.getText(), Integer.valueOf(fieldPort.getText()), fieldDatabase.getText(), fieldUsername.getText(),fieldPassword.getText(),useEncrypt.isSelected(),useSec.isSelected());
		}
		return importModule;
	}
	
	public DatabaseHandler getExportModule() {
		DatabaseHandler exportModule = null;
		if (fieldPort.getText()== null || fieldPort.getText().length() == 0) {
			exportModule = new SQLServerJDBCExportModule(fieldServerName.getText(),fieldDatabase.getText(), fieldUsername.getText(),fieldPassword.getText(),useEncrypt.isSelected(),useSec.isSelected());
		} else {
			exportModule = new SQLServerJDBCExportModule(fieldServerName.getText(), Integer.valueOf(fieldPort.getText()), fieldDatabase.getText(), fieldUsername.getText(),fieldPassword.getText(),useEncrypt.isSelected(),useSec.isSelected());
		}
		return exportModule;
	}
	
	public boolean isInputValid() {
        String errorMessage = "";

        if (fieldServerName.getText() == null || fieldServerName.getText().length() == 0) {
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
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
         	new DialogMessage(errorMessage,"Correct Invalid Fields");
            return false;
        }
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		fieldServerName.setText("localhost");
		fieldPort.setText("1433");
	}

	
	
}
