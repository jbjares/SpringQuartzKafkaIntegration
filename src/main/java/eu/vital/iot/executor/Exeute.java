package eu.vital.iot.executor;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exeute {
	
    public static void main(String args[]){
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:quartz-context.xml");
    }

}
