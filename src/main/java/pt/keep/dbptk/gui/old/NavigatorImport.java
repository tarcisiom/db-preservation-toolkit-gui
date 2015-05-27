package pt.keep.dbptk.gui.old;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseHandler;
import pt.gov.dgarq.roda.common.convert.db.modules.DatabaseImportModule;
import pt.keep.dbptk.gui.ImportPageController;

public class NavigatorImport {
	
	/**
     * Convenience constants for fxml layouts managed by the navigator.
     */
    public final String IMPORTPAGE    = "pt/keep/dbptk/gui/ImportPage.fxml";
    public final String DBMSCHOOSERIMPORT = "DBMSChooserImport.fxml";
    public final String SIARD = "SIARDImport.fxml";
    public final String DATAIMPORT = "DataImport.fxml";
    
  
    /** The main application layout controller. */
    private static ImportPageController importPageController;
    private static DatabaseImportModule importCurrent;
    private static DatabaseHandler exportModule;
    private static Node previous; 
    private Map<String,Node> nodes = new HashMap<String,Node>();
	
    
    public NavigatorImport(){
    	
    }
    
    
    public ImportPageController getImportPageController() {
		return importPageController;
	}

	public void setImportPageController(ImportPageController importCurrent) {
		NavigatorImport.importPageController = importCurrent;
	}
	
    public DatabaseImportModule getImportCurrent() {
		return importCurrent;
	}

	public void setImportCurrent(DatabaseImportModule importCurrent) {
		NavigatorImport.importCurrent = importCurrent;
	}

	public DatabaseHandler getExportModule() {
		return exportModule;
	}

	public void setExportModule(DatabaseHandler exportModule) {
		NavigatorImport.exportModule = exportModule;
	}

	public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		NavigatorImport.previous = previous;
	}

	
    public  Map<String,Node> getNodes() {
		return nodes;
	}


	public  void setNodes(Map<String,Node> nodes) {
		this.nodes = nodes;
	}
	
	public  void addNode(String name, Node node){
		nodes.put(name, node);
	}
	
	public Node getNode(String name){
		return nodes.get(name);
	}


	public void loadVista(String fxml,String bundle) {
        try {
        	FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setResources(ResourceBundle.getBundle(bundle));
            Node node = (Node) fxmlLoader.load(getClass().getResource(fxml).openStream());
            
    		importPageController.setVista(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
