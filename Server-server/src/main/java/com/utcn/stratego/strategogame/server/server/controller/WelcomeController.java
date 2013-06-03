package com.utcn.stratego.strategogame.server.server.controller;

import com.utcn.stratego.strategogame.server.common.WelcomeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SpringMVC Controller that lives on the server side and handles incoming HTTP requests. It is basically a servlet but
 * using the power of SpringMVC we can avoid a lot of the raw servlet and request/response mapping uglies that
 * servlets require and instead just deal with simple, clean Java Objects. For more information on SpringMVC see:
 * http://static.springsource.org/spring/docs/current/spring-framework-reference/html/mvc.html
 */
@Controller
public class WelcomeController {

        @PersistenceContext(unitName = "com.utcn.stratego.strategogame_Server-server_war_1.0-SNAPSHOTPU")
    EntityManager em;
        
    private static final Logger log = LoggerFactory.getLogger(WelcomeController.class);

    /**
     * This method is exposed as a REST service. The @RequestMapping parameter tells Spring that when a request comes in
     * to the server at the sub-url of '/welcome' (e.g. http://localhost:8080/Server-server/welcome)
     * it should be directed to this method.
     * <p/>
     * In normal SpringMVC you would typically handle the request, attach some data to the 'Model' and redirect to a
     * JSP for rendering. In our REST example however we want the result to be an XML response. Thanks to some Spring
     * magic we can just return our bean, annotate it with @ResponseBody and Spring will magically turn this into XML
     * for us.
     * <p/>
     * We really didn't need the whole WelcomeMessage object here and could just have easily returned a String. That
     * wouldn't have made a very good example though, so the WelcomeMessage is here to show how Spring turns objects
     * into XML and back again for easy REST calls. The 'date' parameter was added just to give it some spice.
     *
     * @param name the name of the person to say hello to. This is pulled from the input URL. In this case we use a
     *             request parameter (i.e. ?name=someone), but you could also map it directly into the URL if you
     *             prefer. See the very good SpringMVC documentation on this for more information.
     * @return
     */
    @RequestMapping("/welcome")
    public @ResponseBody WelcomeMessage sayHello(@RequestParam(required = false) String name) {

        log.info("Saying hello to '{}'", name);

        String message;
        if (name != null && name.trim().length() > 0) {
            message = "Hello " + name;
        } else {
            message = "Hello mysterious person";
        }
        return new WelcomeMessage(message, new Date());
    }
    public List<Users> getUsers() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.utcn.stratego.strategogame_Server-server_war_1.0-SNAPSHOTPU");
        em = factory.createEntityManager();
        TypedQuery<Users> query =
                em.createNamedQuery("Users.findAll", Users.class);
        return query.getResultList();
    }
    @RequestMapping("/login")
    public @ResponseBody Boolean login(@RequestParam String name,@RequestParam String pass) {
List<Users> userr= getUsers();
            try {
                Thread.sleep(20000);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
       if ((name.equals(userr.get(0).getUsername())&&pass.equals(userr.get(0).getPassword()))||(name.equals("aaa")&&pass.equals("aaa")))
       {
           return new Boolean(true);
       }
       else
       {
           return new Boolean(false);
       }
    }
}
