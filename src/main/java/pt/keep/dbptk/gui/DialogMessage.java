package pt.keep.dbptk.gui;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DialogMessage {

	public DialogMessage(String errorMessage, String buttonMessage) {
		Stage dialogStage = new Stage();
		Button btnok = new Button(buttonMessage);
		dialogStage.initModality(Modality.WINDOW_MODAL);
		VBox vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new javafx.geometry.Insets(15, 10, 10, 15));
        vBox.setAlignment(Pos.CENTER);
        Node texto = new Text(errorMessage);
        vBox.getChildren().addAll(texto, btnok);
        dialogStage.setScene(new Scene(vBox));
		btnok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				dialogStage.close();
			}
		});

		dialogStage.showAndWait();
	}
	
}
