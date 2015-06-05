package pt.keep.dbptk.gui;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

@SuppressWarnings("deprecation")
public class DialogMessage {

	public  DialogMessage(String errorMessage) {
		// TODO Auto-generated constructor stub
		Stage dialogStage = new Stage();
		Button btnok = new Button("Correct invalid fields");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setScene(new Scene(VBoxBuilder
				.create()
				.children(new Text(errorMessage), btnok).alignment(Pos.CENTER).build()));

		btnok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				dialogStage.close();
			}
		});

		dialogStage.showAndWait();
	}
	
	
	
	
	
	
	
}
