package service.impl;

import javax.ejb.Stateless;

import service.HelloWorldService;

/**
 * I'm a stateless session because of the -at- Stateless annotation. I only
 * implement one interface and that interface does not explicitly declare itself
 * to be either -at- Local or -at- Remote. Therefore, I am a Stateless session
 * bean with a local interface.
 */
@Stateless
public class HelloWorldServiceImpl implements HelloWorldService {

	public void sayHelloTo(final String name) {
		System.out.printf("Hello to %s\n", name);
	}

}