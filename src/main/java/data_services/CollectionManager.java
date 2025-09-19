package data_services;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import models.Result;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@ApplicationScoped
public class CollectionManager {
    @Inject ResultDBService resultDBService;
    private Map<String, List<Result>> collection;


    @PostConstruct
    void init() {
        collection = new ConcurrentHashMap<>();
        resultDBService.getAllRows().forEach(e -> {
            collection
                    .computeIfAbsent(e.getSessionId(),
                            k -> Collections.synchronizedList(new LinkedList<>()))
                    .add(e);
        });
    }

    public void addNewElement(Result r) {
        Result saved = resultDBService.save(r);
        collection
                .computeIfAbsent(saved.getSessionId(),
                        k -> Collections.synchronizedList(new LinkedList<>()))
                .add(saved);
    }

    public List<Result> getAllElementWithSessionId(String sessionId) {
        return collection.getOrDefault(sessionId, List.of());
    }

    public void deleteAllElementWithSessionId(String sessionId) {
        resultDBService.deleteAllBySession(sessionId);
        collection.remove(sessionId);
    }


}
