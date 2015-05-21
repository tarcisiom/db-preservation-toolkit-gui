package pt.keep.dbptk.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.hadoop.hdfs.server.namenode.FSImageFormat.Loader;

import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;

/**
 * Utility class for controlling navigation between vistas.
 *
 * All methods on the navigator are static to facilitate
 * simple access from anywhere in the application.
 */
public class VistaNavigator {

    /**
     * Convenience constants for fxml layouts managed by the navigator.
     */
    public static final String EXPORTPAGE    = "pt/keep/dbptk/gui/ExportPage.fxml";
    public static final String DBMSCHOOSER = "DBMSChooser.fxml";
    public static final String SIARD = "SIARD.fxml";
    public static final String IMPORTDATA = "ImportData.fxml";
    
   // public static final String VISTA_2 = "vista2.fxml";

    /** The main application layout controller. */
    public static ExportPageController exportPageController;
    public static DatabaseImportModule importCurrent;
    public static DBMSChooser dbms;
    public static Node previous; 
    public static DatabaseHandlerGUI exportModule;
    
    
    /**
     * Stores the main controller for later use in navigation tasks.
     *
     * @param mainController the main application layout controller.
     */
    public static void setMainController(ExportPageController mainController) {
        VistaNavigator.exportPageController = mainController;
    }

    public static DatabaseImportModule getImportCurrent() {
		return importCurrent;
	}


	public static void setImportCurrent(DatabaseImportModule importCurrent) {
		VistaNavigator.importCurrent = importCurrent;
	}
    
	public static DBMSChooser getDbms() {
		return dbms;
	}

	public static void setDbms(DBMSChooser dbms) {
		VistaNavigator.dbms = dbms;
	}

    
    public static Node getPrevious() {
		return previous;
	}

	public static void setPrevious(Node previous) {
		VistaNavigator.previous = previous;
	}

	/**
     * Loads the vista specified by the fxml file into the
     * vistaHolder pane of the main application layout.
     *
     * Previously loaded vista for the same fxml file are not cached.
     * The fxml is loaded anew and a new vista node hierarchy generated
     * every time this method is invoked.
     *
     * A more sophisticated load function could potentially add some
     * enhancements or optimizations, for example:
     *   cache FXMLLoaders
     *   cache loaded vista nodes, so they can be recalled or reused
     *   allow a user to specify vista node reuse or new creation
     *   allow back and forward history like a browser
     *
     * @param fxml the fxml file to be loaded.
     */
    public static void loadVista(String fxml,String bundle) {
        try {
        	ClassLoader classLoader = Loader.class.getClassLoader();
        	InputStream inputStream = classLoader.getResource(bundle).openStream();
    		ResourceBundle bundle1 = new PropertyResourceBundle(inputStream);
    		exportPageController.setVista(FXMLLoader.load(VistaNavigator.class.getResource(fxml),bundle1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
    public static void loadDBMS() {
    	 try {
    		String bundle = "pt/keep/dbptk/gui/bundle_en.properties";
    		DBMSNavigator.setMainController(dbms);
         	ClassLoader classLoader = Loader.class.getClassLoader();
         	InputStream inputStream = classLoader.getResource(bundle).openStream();
     		ResourceBundle bundle1 = new PropertyResourceBundle(inputStream);
     		URL fxmlURL = classLoader.getResource(DBMSNavigator.DBMSCHOOSER);
 			FXMLLoader loader = new FXMLLoader(fxmlURL, bundle1);
 			Pane root = (Pane)loader.load();
 			
 			
 		//	DBMSChooser Controller = loader.getController();
 			String as = dbms.selectedDBMS;	
 			
 			if (as.equalsIgnoreCase("MySQLJDBC")){
 				MySQLJDBC novo = new MySQLJDBC(dbms.mysql.getFieldHost().getText(), dbms.mysql.getFieldPort().getText(),
										   dbms.mysql.getFieldDatabase().getText(), dbms.mysql.getFieldUsername().getText(),
										   dbms.mysql.getFieldPassword().getText());
 			
 				URL fxmlURL1 = classLoader.getResource("pt/keep/dbptk/gui/"+DBMSNavigator.MYSQLJDBC);
 				FXMLLoader loader1 = new FXMLLoader(fxmlURL1, bundle1);
 				loader1.setController(novo);
 				
 				Pane root1 = (Pane)loader1.load();
 			
 				
 	 			exportPageController.setVista(root);
 	 			
 			}
 			
         } catch (IOException e) {
             e.printStackTrace();
         }
    	
    }

	*/
	
}