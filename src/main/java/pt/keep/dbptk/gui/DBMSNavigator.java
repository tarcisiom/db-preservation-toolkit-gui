package pt.keep.dbptk.gui;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;

public class DBMSNavigator {
	
 
   
    /** The main application layout controller. */
    private static Panes controller;
  
	
	public static void setMainPane(Panes pane){
		DBMSNavigator.controller = pane;
	}


	public static void loadVista(String fxml,String bundle) {
        try {
        	
    		controller.setVista(FXMLLoader.load(Navigator.class.getResource(fxml), ResourceBundle.getBundle(App.bundle)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	/*
	public static void loadVistaImport(String fxml,String bundle) {
        try {
        	ClassLoader classLoader = Loader.class.getClassLoader();
        	InputStream inputStream = classLoader.getResource(bundle).openStream();
    		ResourceBundle bundle1 = new PropertyResourceBundle(inputStream);
    		
    		dbmsImport.setVista(FXMLLoader.load(DBMSNavigator.class.getResource(fxml),bundle1));
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	*/


	

	
}
