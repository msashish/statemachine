package learn.basics.statemachine.controller;


import learn.basics.statemachine.model.StateMachine;
import learn.basics.statemachine.service.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class StateMachineController {

    @Autowired
    PersistenceService<StateMachine> ps;

    @PostMapping("api/statemachine")
    @ResponseBody
    public void create(@RequestBody StateMachine stateMachine) {
        ps.save(StateMachine.class, stateMachine);
    }


    @GetMapping("api/statemachine")
    @ResponseBody
    public Collection<StateMachine> get(){
        return ps.findAll(StateMachine.class.getName());
    }

}
