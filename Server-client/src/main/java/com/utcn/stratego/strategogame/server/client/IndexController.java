/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utcn.stratego.strategogame.server.client;

import com.utcn.stratego.strategogame.server.common.WelcomeMessage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FXML Controller class
 *
 * @author Dan
 */
public class IndexController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Button LobyButton;
    @FXML
    private Label restLabel;

        @Autowired
    private WelcomeRestService welcomeRestService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
     public Parent getRoot() {
        return root;
     }
   
      @FXML
    public void LobyMatch() {
          root.getChildren().clear();
        //  new LobyController(root);

      
    }
      @FXML
      public void restTest()
      {
            
        Task<WelcomeMessage> task = new Task<WelcomeMessage>() {
            @Override
            protected WelcomeMessage call() throws Exception {
                // simulate a slow server with: try {Thread.sleep(2000);} catch (Exception e) {}
                return welcomeRestService.sayHello("Dan");
            }

            @Override
            protected void succeeded() {
                WelcomeMessage message = getValue();
                restLabel.setText("Sever says: " + message.getMessage() + "\n");
            }

            @Override
            protected void failed() {
                Throwable exception = getException();
                
                restLabel.setText("Something bad happened: " + exception.getMessage() + "\n");
            }
        };

        BooleanBinding runningBinding = task.stateProperty().isEqualTo(Task.State.RUNNING);


        new Thread(task).start();
          
      }
      
    
}
