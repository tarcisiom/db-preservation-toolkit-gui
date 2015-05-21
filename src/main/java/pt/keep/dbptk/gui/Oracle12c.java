package pt.keep.dbptk.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

@SuppressWarnings("deprecation")
public class Oracle12c {
	@FXML
	private TextField fieldHostname, fieldDatabase, fieldPort, fieldUsername;
	@FXML
	private PasswordField fieldPassword;
	
	
	public TextField getFieldHost() {
		return fieldHostname;
	}


	public void setFieldHost(TextField fieldHost) {
		this.fieldHostname = fieldHost;
	}


	public TextField getFieldDatabase() {
		return fieldDatabase;
	}


	public void setFieldDatabase(TextField fieldDatabase) {
		this.fieldDatabase = fieldDatabase;
	}


	public TextField getFieldPort() {
		return fieldPort;
	}


	public void setFieldPort(TextField fieldPort) {
		this.fieldPort = fieldPort;
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


	public boolean isInputValid() {
        String errorMessage = "";

        if (fieldHostname.getText() == null || fieldHostname.getText().length() == 0) {
            errorMessage += "Hostname field empty!\n"; 
        }
        if (fieldPort.getText() == null || fieldPort.getText().length() == 0) {
            errorMessage += "Port field empty!\n"; 
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
            // Show the error message.
            
            Stage dialogStage = new Stage();
			Button btnok = new Button("Correct invalid fields");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.setScene(new Scene(VBoxBuilder
					.create()
					.children(new Text(errorMessage),
							btnok).alignment(Pos.CENTER).build()));

			btnok.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {

					dialogStage.close();
				}
			});

			dialogStage.showAndWait();
            
            
            return false;
        }
    }
}
