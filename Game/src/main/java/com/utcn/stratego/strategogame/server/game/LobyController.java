/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utcn.stratego.strategogame.server.game;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Dan
 */
public class LobyController {
    private AnchorPane root,sp1left,sp1right;
    private Label name;
    private SplitPane sp1;
    private Accordion ac;
    private BorderPane allPP,allFP;   
    private Button b11,b22;
    private TitledPane allPlayers,friends;
    private ListView<String> allPlayersList,friendsList;
    public static final ObservableList allP = 
        FXCollections.observableArrayList();
    public static final ObservableList allF = 
        FXCollections.observableArrayList();


    public LobyController(AnchorPane root) {
        this.root = root;
        init();
    }
    private void init()
    {
        sp1 = new SplitPane();
        sp1.setOrientation(Orientation.HORIZONTAL);
        sp1left = new AnchorPane();
        allPlayers = new TitledPane();
        allPlayers.setText("All Players Loby");
        fillAllPlayers();
        friends = new TitledPane();
        friends.setText("Friends Loby");
        fillFriend();
        ac = new Accordion();
        ac.getPanes().addAll(allPlayers,friends);
        AnchorPane.setBottomAnchor(ac, 0.0);
        AnchorPane.setTopAnchor(ac, 0.0);
        AnchorPane.setLeftAnchor(ac, 0.0);
        AnchorPane.setRightAnchor(ac, 0.0);
        sp1left.getChildren().add(ac);
        sp1.getItems().add(sp1left);
        sp1right = new AnchorPane();
        name =new Label();
        name.setLayoutX(100);
        name.setLayoutY(115);
        name.setFont(Font.font("Verdana", 30));
        name.setVisible(false);
        sp1right.getChildren().add(name);
        Cube c =new Cube(45,Color.PINK,1);
        c.setTranslateX(100);
        c.setTranslateY(100);
         Cube c2 =new Cube(45,Color.PINK,1);
        c2.setTranslateX(150);
        c2.setTranslateY(100);
        sp1right.getChildren().addAll(c,c2);
        sp1.getItems().add(sp1right);
        sp1.setStyle("-fx-background-color: rgba(0, 100, 100, 0.1); -fx-background-radius: 10;");
        AnchorPane.setBottomAnchor(sp1, 0.0);
        AnchorPane.setTopAnchor(sp1, 0.0);
        AnchorPane.setLeftAnchor(sp1, 0.0);
        AnchorPane.setRightAnchor(sp1, 0.0);
        sp1.setDividerPositions(0.3f);
        root.getChildren().add(sp1);
        
    }
    private void fillFriend()
    {
        allFP = new BorderPane();
          friendsList = new ListView<String>();
        allF.addAll("Dan");
        friendsList.setItems(allF);
        friendsList.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> ov, 
                    String old_val, String new_val) {
                              name.setText(new_val);
                        name.setVisible(true);
                                name.setTextFill(Color.YELLOW);
            }
        });
        allFP.setCenter(friendsList);
        b22 = new Button("Join Game");
        b22.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent t) {
               root.getChildren().clear();
          new SetupController(root);
            }
        });
        allFP.setBottom(b22);
        friends.setContent(allFP);
    }
    private void fillAllPlayers()
    {
        allPP = new BorderPane();
          allPlayersList = new ListView<String>();
        allP.addAll("Dan","Mircea","Emanuel");
        allPlayersList.setItems(allP);
        allPlayersList.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> ov, 
                    String old_val, String new_val) {
                              name.setText(new_val);
                        name.setVisible(true);
                                name.setTextFill(Color.GREEN);
            }
        });
        allPP.setCenter(allPlayersList);
        b11 = new Button("Join Game");
        b11.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent t) {
               
            }
        });
        allPP.setBottom(b11);
        allPlayers.setContent(allPP);
    }
     

    
}
