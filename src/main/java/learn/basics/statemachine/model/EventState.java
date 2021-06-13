package learn.basics.statemachine.model;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// EventState is more of Entity to Statemachine relationship

@Getter
@Setter
public class EventState {

    private String stateMachineName;

    // Event has to pass a State name to be set, for given entity id, type
    private String name;
    private String entityType;
    private long entityId;
    private Date date;

    public EventState(){}

    public EventState(String stateMachineName, String name, String entityType, long entityId){
        this.stateMachineName = stateMachineName;
        this.name = name;
        this.entityType = entityType;
        this.entityId = entityId;
    }

}
