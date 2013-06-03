/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utcn.stratego.strategogame.server.game;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Dan
 */
public class SetupController {
    
    private AnchorPane root;
    private BorderPane mainP;
    private GridPane pieces,setup;
    private Cube draged;
    private double startX,startY;
     public SetupController(AnchorPane root) {
        this.root = root;
        init();
    }
     
     public void init(){
         mainP = new BorderPane();
        AnchorPane.setBottomAnchor(mainP, 0.0);
        AnchorPane.setTopAnchor(mainP, 0.0);
        AnchorPane.setLeftAnchor(mainP, 0.0);
        AnchorPane.setRightAnchor(mainP, 0.0);
         setup = new GridPane();
        AnchorPane.setBottomAnchor(setup, 0.0);
        for (int i = 0; i<10; i++){
         setup.getColumnConstraints().add(new ColumnConstraints(46));
     }
        for (int i=0; i<4; i++)
        {
            setup.getRowConstraints().add(new RowConstraints(46));
        }
        final Rotate rx = new Rotate(-10,Rotate.X_AXIS);
        final Rotate ry = new Rotate(0,Rotate.Y_AXIS);
        setup.getTransforms().addAll(rx,ry);
        setup.setGridLinesVisible(true);
        //addPieces();
        setup.setStyle("-fx-background-color : lightgrey ");
        mainP.setCenter(setup);
        root.getChildren().add(setup);
        
        pieces = new GridPane();
        for (int i = 0; i<4; i++){
         pieces.getColumnConstraints().add(new ColumnConstraints(46));
     }
        for (int i=0; i<10; i++)
        {
            pieces.getRowConstraints().add(new RowConstraints(46));
        }
        pieces.setGridLinesVisible(true);
        addPieces();
        
        pieces.setOnMousePressed(new EventHandler<MouseEvent>() {

             public void handle(MouseEvent me) {
                                    startX = me.getX();
                    startY = me.getY();
             }
         });
        pieces.setOnMouseDragged(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                   if (draged!= null)
                   {
                       draged.toFront();
                        draged.setTranslateX(me.getX()-startX);
                        draged.setTranslateY(me.getY()-startY);
                   }
                }
        });
        pieces.setOnMouseReleased(new EventHandler<MouseEvent>() {

             public void handle(MouseEvent t) {
                 if (draged!= null)
                 {
                     if (t.getSceneX()>setup.getLayoutX()&&t.getSceneX()<(setup.getLayoutX()+setup.getWidth()))
                             if (t.getSceneY()>setup.getLayoutY()&&t.getSceneY()<(setup.getLayoutY()+setup.getHeight()))
                     {
                        int x,y;
                        x= (int)(t.getSceneX()-setup.getLayoutX())/46;
                        y= (int)(t.getSceneY()-setup.getLayoutY())/46;
                    pieces.getChildren().remove(draged);
                    for (int i=x; i<4;i++)
                    {
                   
                    }
                    
                    setup.add(draged, x, y);
                    
                
                       }
         
                     
                     
                     
                     draged.setTranslateX(0);
                     draged.setTranslateY(0);
                 }
             }
         });

        
        mainP.setLeft(pieces);
        root.getChildren().add(mainP);
        
       
         
     }
     private void addPieces()
     {
         for (int i=0;i<4;i++)
         {
             for (int j=0; j<10; j++)
             {
                 Cube c = new Cube(45, Color.PINK, 45);
                 c.setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    Cube c = ((Cube)me.getSource());
                    c.toFront();
                    draged = c;
                }
            });
                 pieces.add(c, i, j);
             }
         }
     }
     
}
    

