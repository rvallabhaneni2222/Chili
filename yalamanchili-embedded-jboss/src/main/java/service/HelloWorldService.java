package service;
 
/**
 * A requirement for EJB3 Session beans is that they implement an interface.
 * This interface does not specify whether it is local or remote so we won't
 * know until it is used which way to treat it. One convention is to include the
 * name "Local" or "Remote" in the name of the service.
 */
public interface HelloWorldService {
    void sayHelloTo(final String name);
}
