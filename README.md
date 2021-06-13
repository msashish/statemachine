## Models in Statemachine pattern
    
        StateTransition - consists of state id, currentState, nextState
                1, Register, Email-Sent
                2, Email-Sent, Email-Valid
                3, Email-Sent, Email-InValid
                4, Email-Valid, Address-Verified
                5, Email-Valid, Address-InValid
                6, Address-Verified, Customer-Setup
                
        StateMachine    - consists of stateMachine name, id, list of StateTransition 
        
        EntityState     - id, entityId, entityType, currentState, startTime, boolean finalState, StateMachine (1-1)
        
        EventState      - event name, entityId, entityType, name of statemachine, date
        
        
## Services in Statemachine pattern       

        PersistenceRepository  - Uses Java Persistence api (JPA) and consists of find, save, update and other find methods
        
        PersistenceService<T>  - Service calling methods of PersistenceRepository
        
        
## Controllers - endpoints        
    
        StateMachineController
            api/stateMachine - POST - creation of a statemachine
            api/stateMachine - GET  - listing all statemachines
        
        EventController

## How to test

        mvn clean package
        java -jar target/statemachine-0.0.1-SNAPSHOT.jar
            or
        mvn clean install
        mvn spring-boot:run