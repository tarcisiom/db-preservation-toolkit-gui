package pt.keep.dbptk.gui;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application
{

	
	public static final String bundle = "pt/keep/dbptk/gui/bundle";
	
	public static String DBMSCHOOSER = "DBMSChooser.fxml";
	public static String SIARDPAGE = "SIARDPage.fxml";
	public static String IMPORTDATA = "ImportData.fxml";
	public static String CUSTOMCHOOSER = "CustomChooser.fxml";
	public static String PANESHOW = "PaneShow.fxml";
	public static Properties props;
	
	public static boolean importpage = false;
	
	
	@Override
	public void start(Stage primaryStage) {
		Locale locale = new Locale("en","EN");
	
		try {
			loadProperties();
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
	
	public void loadProperties() throws IOException{
		props = new Properties();
		String propFileName = "pt/keep/dbptk/gui/db_modules.properties";
 
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
		if (inputStream != null) {
			props.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
 
		
	}
	
	
	
	
	public static void main( String[] args )
    {
	
    	launch(args);
    	
    }
}
