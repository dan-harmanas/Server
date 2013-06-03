/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utcn.stratego.strategogame.server.server.controller;

import com.utcn.stratego.strategogame.server.common.WelcomeMessage;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Dan
 */
@Controller
@RequestMapping("/game")
public class GameController {
     private static final Logger log = LoggerFactory.getLogger(GameController.class);

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody WelcomeMessage sayHello(@RequestBody String name,@RequestParam String name1) {

        log.info("Saying hello to '{}'", name);

        String message;
        if (name != null && name.trim().length() > 0) {
            message = "Hello " + name + "name 1 "+name1;
        } else {
            message = "Hello mysterious person";
        }
        return new WelcomeMessage(message, new Date());
    }
    
}
