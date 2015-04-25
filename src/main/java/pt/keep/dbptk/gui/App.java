package pt.keep.dbptk.gui;


import java.io.InputStream;
import java.net.URL;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;








import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application
{
    
	
	@Override
	public void start(Stage primaryStage) {
		//Locale locale = new Locale("en", "EN");
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			URL fxmlURL = classLoader.getResource("pt/keep/dbptk/gui/Main.fxml");
			InputStream inputStream = classLoader.getResource("pt/keep/dbptk/gui/bundle_en.properties").openStream();
			ResourceBundle bundle = new PropertyResourceBundle(inputStream);
			
			FXMLLoader loader = new FXMLLoader(fxmlURL, bundle);
			Parent root = loader.load();
			
            
			//Parent root = FXMLLoader.load(getClass().getResource("/pt/keep/dbptk/gui/Main.fxml"));
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
