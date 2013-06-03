/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utcn.stratego.strategogame.server.game;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FXML Controller class
 *
 * @author Dan
 */
public class IndexController implements Initializable {
    @FXML
    private AnchorPane root,logPane;
    @FXML
    private Button LobyButton,logButton;
    @FXML
    private Label restLabel;
    @FXML
    private Label incLabel;
    @FXML
    private ProgressIndicator progress;
    @FXML 
    private TextField nameField;
    @FXML
    private PasswordField passField;

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
          new LobyController(root);

      
    }
      @FXML
      public void LogIn()
      {
           Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
              //  try {Thread.sleep(2000);} catch (Exception e) {}
                return welcomeRestService.LogIn(nameField.getText(),passField.getText());
            }

            @Override
            protected void succeeded() {
                Boolean message = getValue();
                if(message)
                {
                    LobyButton.setVisible(true);
                    logPane.setVisible(false);
                }
                else
                {
                    incLabel.setVisible(true);
                    
                }
                
            }

            @Override
            protected void failed() {
                Throwable exception = getException();
                incLabel.setVisible(true);
                incLabel.setText(exception.toString());
               
            }
        };

        BooleanBinding runningBinding = task.stateProperty().isEqualTo(Task.State.RUNNING);
         nameField.disableProperty().bind(runningBinding);
        logButton.disableProperty().bind(runningBinding);
        passField.disableProperty().bind(runningBinding);
        progress.visibleProperty().bind(runningBinding);

        new Thread(task).start();
          
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
                incLabel.setVisible(true);
                incLabel.setText("Sever says: " + message.getMessage() + "\n");
            }

            @Override
            protected void failed() {
                Throwable exception = getException();
                incLabel.setVisible(true);
                incLabel.setText("Something bad happened: " + exception.getMessage() + "\n");
            }
        };

        BooleanBinding runningBinding = task.stateProperty().isEqualTo(Task.State.RUNNING);


        new Thread(task).start();
          
      }
      
    
}
