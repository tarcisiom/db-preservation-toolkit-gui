package pt.keep.dbptk.gui;



import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;

public class Navigator {

	    
	  
	    /** The main application layout controller. */
	    private static PageController PageController;
	    private static DatabaseImportModule importModule;
	    private static DatabaseHandler exportModule;
	    private static Map<String,Node> nodes = new HashMap<String,Node>();
		private static String importFxml ;
		private static String exportFxml ;
	    
	    
	    public static void setPageController(PageController mainController) {
	        Navigator.PageController = mainController;
	    }
	  
		
		public static DatabaseImportModule getImportModule() {
			return importModule;
		}



		public static void setImportModule(DatabaseImportModule importModule1) {
			importModule = importModule1;
		}



		public static DatabaseHandler getExportModule() {
			return exportModule;
		}



		public static void setExportModule(DatabaseHandler exportModule1) {
			exportModule = exportModule1;
		}


		public static String getImportFxml() {
			return importFxml;
		}


		public static void setImportFxml(String importFxml) {
			Navigator.importFxml = importFxml;
		}


		public static String getExportFxml() {
			return exportFxml;
		}


		public static void setExportFxml(String exportFxml) {
			Navigator.exportFxml = exportFxml;
		}


		public static void addNode(String name,Node node){
			nodes.put(name, node);
		}
		
		public static Node getNode(String name){
			return nodes.get(name);
		}


		public static void loadVista(String fxml) {
	        PageController.setVista(getNode(fxml));
	        
		}
		
		public static void addNodes(String fxml) throws Exception{
		    Node node = (Node) FXMLLoader.load(Navigator.class.getResource(fxml), ResourceBundle.getBundle(App.bundle));
            addNode(fxml,node);
		}
	    public static void clearNodes(){
	    	nodes.clear();
	    }
	    
	    public static void loadAfter(Node node){
	    	PageController.setVista(node);
	    }
}


