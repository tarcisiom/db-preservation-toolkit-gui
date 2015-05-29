package pt.keep.dbptk.gui;


import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application
{

	
	public static final String bundle = "pt/keep/dbptk/gui/bundle";
	
	public static final String DBMSCHOOSER = "DBMSChooser.fxml";
	public static final String SIARD = "SIARD.fxml";
	public static final String IMPORTDATA = "ImportData.fxml";
	public static final String CUSTOMCHOOSER = "CustomChooser.fxml";
	
	
	public static boolean importpage = false;
	
	
	@Override
	public void start(Stage primaryStage) {
		Locale locale = new Locale("en","EN");
		try {
			
			Locale.setDefault(locale);
			FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setResources(ResourceBundle.getBundle(bundle));
	        Parent root = (Parent) fxmlLoader.load(getClass().getResource("Main.fxml").openStream());
	        Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main( String[] args )
    {
    	launch(args);
    }
}
