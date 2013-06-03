package com.utcn.stratego.strategogame.server.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
  log.info("Starting Hello JavaFX and Maven demonstration application");
      
       AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SimpleRestAppFactory.class);
        IndexController mainController = context.getBean(IndexController.class);
       
        
        Scene scene = new Scene(mainController.getRoot(), 800, 600);
        scene.getStylesheets().add("/styles/styles.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("JavaFX REST Client");
        stage.show();
        
//        String fxmlFile = "/fxml/index.fxml";
//        log.debug("Loading FXML for main view from: {}", fxmlFile);
//        FXMLLoader loader = new FXMLLoader();
//        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
//
//        Scene scene = new Scene(rootNode, 800, 600);
//        
//        scene.getStylesheets().add("/styles/styles.css");
//
//        stage.setTitle("Game");
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();
    }
}
