package learn.basics.statemachine.service;

import learn.basics.statemachine.model.EntityState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
public class PersistenceService<T> {

    @Autowired
    PersistenceRepository repo;

    public Collection<Long> findAllEntitiesToProcess(){
        return repo.findAllEntitiesToProcess();
    }

    public Collection<EntityState> findLatestEntityState(Long id){
        return repo.findLatestEntityState(id);
    }

    @Transactional
    public Collection<T> findAll(String type) {
        return repo.findAll(type);
    }

    @Transactional
    public T find(Class<T> type, String id){
        return repo.find(type, Long.parseLong(id));
    }

    @Transactional
    public T findByQuery(Class<T> type, String query){
        return repo.find(type, query);
    }

    @Transactional
    public void save(Class<T> type, T object) {
        repo.save(type, object);
    }

    @Transactional
    public void saveAll(Class<T> type, List<T> objects) {
        for (T object: objects) {
            repo.save(type, object);
        }
    }

    @Transactional
    public void update(Class<T> type, T object){
        repo.update(type, object);
    }



}
