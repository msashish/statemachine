package learn.basics.statemachine.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import java.sql.Timestamp;


// EntityState has id which corresponds to each unique state.
// It also has 1-to-1 relationship of Entity with that StateMachine

@Entity
@Getter
@Setter
public class EntityState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long entityId;
    private String entityType;
    private String currentState;
    private Timestamp startTime;
    private Boolean finalState;

    @OneToOne
    private StateMachine stateMachine;


}
