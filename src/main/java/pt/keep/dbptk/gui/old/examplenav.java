package pt.keep.dbptk.gui.old;

import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;

import pt.keep.dbptk.gui.Page;
import pt.keep.dbptk.gui.Panes;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class examplenav extends StackPane {
    //Holds the screens to be displayed

	private Page page;
    private HashMap<String, Panes> panes = new HashMap<>();
    private HashMap<String, Node> screens = new HashMap<>();
    
    
    public examplenav() {
        super();
    }

    public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	//Add the screen to the collection
    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    //Returns the Node with the appropriate name
    public Node getScreen(String name) {
        return screens.get(name);
    }

    //Loads the fxml file, add the screen to the screens collection and
    //finally injects the screenPane to the controller.
   /*
    public void loadScreen(String name, String resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent) myLoader.load();
            Page myScreenControler = ((Page) myLoader.getController());
            myScreenControler.setScreenParent(this);
            addScreen(name, loadScreen);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    */
    
    public HashMap<String, Panes> getPanes() {
		return panes;
	}

	public void setPanes(HashMap<String, Panes> panes) {
		this.panes = panes;
	}
	 public void addPanes(String name, Panes screen) {
	        panes.put(name, screen);
	    }


	public void loadVista(String fxml,String bundle) {
        try {
        	FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setResources(ResourceBundle.getBundle(bundle));
            Node node = (Node) fxmlLoader.load(getClass().getResource(fxml).openStream());
            Panes myScreenControler = fxmlLoader.getController();
        //    myScreenControler.setPage(page);
            addScreen(fxml, node);
            addPanes(fxml,myScreenControler);
    	} catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //This method tries to displayed the screen with a predefined name.
    //First it makes sure the screen has been already loaded.  Then if there is more than
    //one screen the new screen is been added second, and then the current screen is removed.
    // If there isn't any screen being displayed, the new screen is just added to the root.
    public boolean setScreen(final String name) {       
        if (screens.get(name) != null) {   //screen loaded
            final DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()) {    //if there is more than one screen
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                    	page.setVista(screens.get(name));
                        Timeline fadeIn = new Timeline(
                                new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
                        fadeIn.play();
                    }
                }, new KeyValue(opacity, 0.0)));
                fade.play();

            } else {
                setOpacity(0.0);
                //getChildren().add(screens.get(name));       //no one else been displayed, then just show
                page.setVista(screens.get(name));
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(2500), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        } else {
            System.out.println("screen hasn't been loaded!!! \n");
            return false;
        }


        /*Node screenToRemove;
         if(screens.get(name) != null){   //screen loaded
         if(!getChildren().isEmpty()){    //if there is more than one screen
         getChildren().add(0, screens.get(name));     //add the screen
         screenToRemove = getChildren().get(1);
         getChildren().remove(1);                    //remove the displayed screen
         }else{
         getChildren().add(screens.get(name));       //no one else been displayed, then just show
         }
         return true;
         }else {
         System.out.println("screen hasn't been loaded!!! \n");
         return false;
         }*/
    }

    //This method will remove the screen with the given name from the collection of screens
    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }

}
