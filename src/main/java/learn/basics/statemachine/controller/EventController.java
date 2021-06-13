package learn.basics.statemachine.controller;

import learn.basics.statemachine.model.EntityState;
import learn.basics.statemachine.model.EventState;
import learn.basics.statemachine.model.StateMachine;
import learn.basics.statemachine.service.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

@RestController
public class EventController {

    @Autowired
    PersistenceService<StateMachine> stateMachinePersistenceService;

    @Autowired
    PersistenceService<EntityState> entityStatePersistenceService;

    @PostMapping("api/stateMachineEvent")
    @ResponseBody
    public void save(@RequestBody EventState event){
        StateMachine sm = findStateMachine(event);
        EntityState es = new EntityState();
        es.setEntityId(event.getEntityId());
        es.setEntityType(event.getEntityType());
        es.setStateMachine(sm);
        es.setCurrentState(event.getName());
        es.setFinalState(sm.isFinalState(event.getName()));
        es.setStartTime(Timestamp.valueOf(LocalDateTime.now()));

        entityStatePersistenceService.save(EntityState.class, es);

    }

    @GetMapping("api/stateMachineEvent")
    @ResponseBody
    public Collection<EntityState> get(){

        return entityStatePersistenceService.findAll(EntityState.class.getName());

    }


    private StateMachine findStateMachine(EventState event){
        StateMachine sma=null;
        Collection<StateMachine> stateMachines = stateMachinePersistenceService.findAll(StateMachine.class.getName());
        for (StateMachine sm: stateMachines) {
            if (sm.getName().equals(event.getStateMachineName())) {
                sma = sm;
            }
        }
        if(sma==null){
            throw new IllegalArgumentException("State Machine not found");
        }
        if(!sma.isValidState(event.getName())){
            throw new IllegalArgumentException("Invalid state "+ event.getName());
        }

        return sma;

    }


}
