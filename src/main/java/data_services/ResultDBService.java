package data_services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import models.Result;

import java.util.List;


@ApplicationScoped
public class ResultDBService {
    private static final String GET_ALL_DATA = "select r from Result r";
    private static final String DELETE_DATA_BY_SESSION = "delete from Result r where r.sessionId = :sid";
    @PersistenceContext(unitName = "appPU")
    private EntityManager em;

    @Transactional
    public Result save(Result r) {
        em.persist(r);
        return r;
    }

    public List<Result> getAllRows() {
        return em.createQuery(GET_ALL_DATA)
                .getResultList();

    }
    @Transactional
    public int deleteAllBySession(String sessionId) {
        return em.createQuery(DELETE_DATA_BY_SESSION)
                .setParameter("sid", sessionId)
                .executeUpdate();
    }



}
