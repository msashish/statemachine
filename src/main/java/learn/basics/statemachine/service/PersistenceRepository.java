package learn.basics.statemachine.service;

import learn.basics.statemachine.model.EntityState;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

@Repository
public class PersistenceRepository {

    @PersistenceContext
    EntityManager em;

    final String FIND_ALL_ENTITIES = "SELECT DISTINCT entityId FROM EntityState WHERE finalState IS FALSE";

    final String FIND_ENTITY_STATE = "SELECT es FROM EntityState es WHERE entityId = ?1 ORDER BY id desc";

    // Query based methods
    public Collection<Long> findAllEntitiesToProcess() {
        Query q = em.createQuery(FIND_ALL_ENTITIES);
        return (Collection<Long>) q.getResultList();
    }

    public Collection<EntityState> findLatestEntityState(Long entityId) {
        Query q = em.createQuery(FIND_ENTITY_STATE);
        q.setParameter(1, entityId);
        return (Collection<EntityState>) q.getResultList();
    }

    public <T> Collection<T> findAll(String type) {
        Query q = em.createQuery("SELECT e FROM "+type+" e");
        return (Collection<T>) q.getResultList();
    }

    public <T> T find(Class<T> clazz, String query) {
        Query q = em.createQuery(query);
        return ((List<T>) q.getResultList()).get(0);
    }

    // Direct EM based methods
    public <T> T find(Class<T> clazz, Long id){
        return em.find(clazz, id);
    }

    public <T> T save(Class<T> clazz, T t){
        em.persist(t);
        return t;
    }

    public <T> void update(Class<T> clazz, T t){
        em.merge(t);
    }



}
