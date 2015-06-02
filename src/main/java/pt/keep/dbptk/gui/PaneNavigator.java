package pt.keep.dbptk.gui;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;


public class PaneNavigator {

	  
    /** The main application layout controller. */
    private static PaneShow mainController;
    private static Map<String,Node> nodes = new HashMap<String,Node>();
    private static Map<String,DBMSPane> controllers = new HashMap<String,DBMSPane>();
	
    private static String importFxml ;
	private static String exportFxml ;
    
    
    public static void setPageController(PaneShow mainController) {
    	PaneNavigator.mainController = mainController;
    }
  
	public static String getImportFxml() {
		return importFxml;
	}


	public static void setImportFxml(String importFxml) {
		PaneNavigator.importFxml = importFxml;
	}


	public static String getExportFxml() {
		return exportFxml;
	}


	public static void setExportFxml(String exportFxml) {
		PaneNavigator.exportFxml = exportFxml;
	}


	public static DBMSPane getController(String fxml) {
		return controllers.get(fxml);
	}

	public static void addController(String fxml, DBMSPane controllers) {
		PaneNavigator.controllers.put(fxml, controllers);
	}

	public static void addNode(String name,Node node){
		nodes.put(name, node);
	}
	
	public static Node getNode(String name){
		return nodes.get(name);
	}


	public static void loadVista(String fxml) {
        mainController.setVista(getNode(fxml));
    }
	
	public static void addNodes(String fxml) throws Exception{
	    Node node = (Node) FXMLLoader.load(Navigator.class.getResource(fxml), ResourceBundle.getBundle(App.bundle));
	    
	   // DBMSPane pane = 
        addNode(fxml,node);
	}
    public static void clearNodes(){
    	nodes.clear();
    }
    
    public static void loadAfter(Node node){
    	mainController.setVista(node);
    }
}
