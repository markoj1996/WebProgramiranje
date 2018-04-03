package projekat;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import projekat.dao.ConnManager;

public class InitListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	ConnManager.close();
    }

    public void contextInitialized(ServletContextEvent event)  {
    	System.out.println("inicijalizacija...");

    	ConnManager.open();

		System.out.println("zavrseno!");
    }

}
