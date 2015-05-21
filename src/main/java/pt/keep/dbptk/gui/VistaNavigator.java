package pt.keep.dbptk.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.hadoop.hdfs.server.namenode.FSImageFormat.Loader;

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
    private static ExportPageController exportPageController;
    private static DatabaseImportModule importCurrent;
    public static DBMSChooser dbms;
    
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
    
    public static void loadDBMS() {
    	 try {
    		String fxml ="pt/keep/dbptk/gui/DBMSChooser.fxml";
         	ClassLoader classLoader = Loader.class.getClassLoader();
         	InputStream inputStream = classLoader.getResource("pt/keep/dbptk/gui/bundle_en.properties").openStream();
     		ResourceBundle bundle1 = new PropertyResourceBundle(inputStream);
     		URL fxmlURL = classLoader.getResource(fxml);
 			FXMLLoader loader = new FXMLLoader(fxmlURL, bundle1);
 			//loader.setController(dbms);
 			Pane root = (Pane)loader.load();
 			
 			
 			String as = dbms.mysql.getFieldHost().getText();
 			
 			System.out.println(as);
 			if (as.equalsIgnoreCase("MySQLJDBC")){
 				MySQLJDBC novo = MySQLJDBC(dbms.mysql.getFieldHost().getText(),
 										   dbms.mysql.getFieldPort().getText(),
										   dbms.mysql.getFieldDatabase().getText(),
										   dbms.mysql.getFieldUsername().getText(),
										   dbms.mysql.getFieldPassword().getText());
 				
 				//DBMSNavigator.setMainController(dbms);
 				URL fxmlURL1 = classLoader.getResource("pt/keep/dbptk/gui/"+DBMSNavigator.MYSQLJDBC);
 				FXMLLoader loader1 = new FXMLLoader(fxmlURL1, bundle1);
 				loader1.setController(novo);
 				Parent root1 = loader1.load();
 				//VistaNavigator.setDbms(dbms);
 				dbms.setVista(root1);
 				
 				root.getChildren().add(root1);
 				
 				
 				
 				
 			}
 			
 			
         	//exportPageController.setVista(FXMLLoader.load(VistaNavigator.class.getResource(fxml),bundle1));
         	exportPageController.setVista(root);
         	
         } catch (IOException e) {
             e.printStackTrace();
         }
    	
    }

	private static MySQLJDBC MySQLJDBC(String text, String text2, String text3,
			String text4, String text5) {
		// TODO Auto-generated method stub
		return null;
	}
	
}