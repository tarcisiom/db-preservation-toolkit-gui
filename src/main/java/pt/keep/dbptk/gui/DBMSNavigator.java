package pt.keep.dbptk.gui;

import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.hadoop.hdfs.server.namenode.FSImageFormat.Loader;
import org.restlet.engine.connector.Controller;

import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;
import javafx.fxml.FXMLLoader;

public class DBMSNavigator {
	
	public static final String DBMSCHOOSER    = "pt/keep/dbptk/gui/DBMSChooser.fxml";
    public static final String MYSQLJDBC = "MySQLJDBC.fxml";
    public static final String DB2JDBC = "DB2JDBC.fxml";
    public static final String ORACLE12C = "Oracle12c.fxml";
    public static final String POSTGRE = "Postgre.fxml";
    public static final String SQLSERVER = "Sqlserver.fxml";
    
    
   

    /** The main application layout controller. */
    private static DBMSChooser dbmsController;
    
    
    
    
    public static void setMainController(DBMSChooser mainController) {
        DBMSNavigator.dbmsController = mainController;
    }

    

   
    public static void loadVista(String fxml,String bundle) {
        try {
        	ClassLoader classLoader = Loader.class.getClassLoader();
        	InputStream inputStream = classLoader.getResource(bundle).openStream();
    		ResourceBundle bundle1 = new PropertyResourceBundle(inputStream);
    		
    		dbmsController.setVista(FXMLLoader.load(DBMSNavigator.class.getResource(fxml),bundle1));
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


	

	
}
