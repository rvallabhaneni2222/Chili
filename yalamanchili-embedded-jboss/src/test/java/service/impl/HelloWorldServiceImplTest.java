package service.impl;
 
import info.yalamanchili.embeddedjboss.JBossUtil;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import service.HelloWorldService;
 
public class HelloWorldServiceImplTest {
    private HelloWorldService service;
 
    @BeforeClass
    public static void startDeployer() {
        // Note, we could stop the deployer when we are done but we do not since
        // the JVM will shut down and stop the deployer for us.
        JBossUtil.startDeployer();
    }
 
    @Before
    public void lookupService() {
        service = JBossUtil.lookup(HelloWorldService.class,
                "HelloWorldServiceImpl/local");
    }
 
    @Test
    public void sayHello() {
        service.sayHelloTo("Brett");
    }
}