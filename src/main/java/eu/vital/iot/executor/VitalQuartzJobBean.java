package eu.vital.iot.executor;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class VitalQuartzJobBean extends QuartzJobBean{
 
    @Autowired
    private EventGenerator eventGenerator; 
     
     
    @Override
    protected void executeInternal(JobExecutionContext jec)
            throws JobExecutionException {
    	try {
			eventGenerator.doStuff();
		} catch (Exception e) {
			throw new RuntimeException("ERROR: "+e.getMessage());
		} 
    }


	public EventGenerator getEventGenerator() {
		return eventGenerator;
	}


	public void setEventGenerator(EventGenerator eventGenerator) {
		this.eventGenerator = eventGenerator;
	}
 

 
}
