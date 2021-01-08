package aliyun.controller;

import aliyun.view.IndexView;
import aliyun.view.RdsCreateDBView;
import aliyun.view.RdsWhiteListView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class IndexController   {


    @FXML
    private Pane myDynamicPane;

    @Autowired
    private RdsWhiteListView whiteList;
    
    @Autowired
    private RdsCreateDBView createDB;

    public void showWhiteListView(final Event e) {
        myDynamicPane.getChildren().clear();
        myDynamicPane.getChildren().add(whiteList.getView());
    }
    
    public void showCreateDBView(final Event e) {
        myDynamicPane.getChildren().clear();
        myDynamicPane.getChildren().add(createDB.getView());
    }


}
