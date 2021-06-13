package learn.basics.statemachine.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class StateMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<StateTransition> stateTransitions=new ArrayList<>();

    public StateMachine(){

    }

    public StateMachine(String name, List<StateTransition> transitions){
        this.name = name;
        stateTransitions.addAll(transitions);
    }

    public boolean isValidState(String state){
        for(StateTransition sm: stateTransitions){
            if(sm.getCurrentState().equals(state)||sm.getNextState().equals(state)){
                return true;
            }
        }
        return false;
    }

    // If a state is a currentState in any StateTransition then it cant be a final state
    // Final state is never currentState in any StateTransition
    public boolean isFinalState(String state){
        for(StateTransition sm: stateTransitions){
            if(sm.getCurrentState().equals(state)){
                return false;
            }
        }
        return true;
    }
}
