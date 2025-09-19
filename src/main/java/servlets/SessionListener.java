package servlets;

import data_services.CollectionManager;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Inject CollectionManager collectionManager;
    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        String jSessionId = session.getId();
        collectionManager.deleteAllElementWithSessionId(jSessionId);
        System.out.println(jSessionId + " was destroyed!");
    }

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        System.out.println(sessionEvent.getSession().getId() + " was created!");
    }
}
