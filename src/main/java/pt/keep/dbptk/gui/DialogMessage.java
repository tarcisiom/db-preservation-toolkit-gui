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
		// TODO Auto-generated constructor stub
		Stage dialogStage = new Stage();
		Button btnok = new Button(buttonMessage);
		dialogStage.initModality(Modality.WINDOW_MODAL);
//		dialogStage.setScene(new Scene(VBoxBuilder
//				.create()
//				.children(new Text(errorMessage), btnok).alignment(Pos.CENTER).build()));
		VBox vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new javafx.geometry.Insets(15, 10, 10, 15));
        vBox.setAlignment(Pos.CENTER);
        Node texto = new Text(errorMessage);
//        VBox.setMargin(texto, new javafx.geometry.Insets(0.5));
//        VBox.setMargin(btnok, new javafx.geometry.Insets(0.5));
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
