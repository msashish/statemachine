package learn.basics.statemachine.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class StateTransition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String currentState;
    private String nextState;
}
