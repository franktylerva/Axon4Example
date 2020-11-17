package nl.avthart.todo.app;

import java.util.ArrayList;
import java.util.List;

import nl.avthart.todo.app.flags.Monitor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Todo App using Axon and Spring Boot
 *
 * @author albert
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "org.axonframework.eventsourcing.eventstore.jpa", // DomainEventEntry & SnapshotEventEntry
        "org.axonframework.modelling.saga.repository.jpa", // SagaEntry & AssociationValueEntry
        "org.axonframework.eventhandling.tokenstore.jpa", // TokenEntry
        "nl.avthart.todo.app"})
public class Application {

    public static void main( String[] args ) {
        if ( args != null ) {
            List<String> others = new ArrayList<>();
            for ( String arg : args ) {
                if ( "monitor".equalsIgnoreCase( arg ) ) {
                    Monitor.activate();
                } else {
                    others.add( arg );
                }
            }
            args = others.toArray( new String[0] );
        }
        SpringApplication.run( Application.class, args );
    }
}
