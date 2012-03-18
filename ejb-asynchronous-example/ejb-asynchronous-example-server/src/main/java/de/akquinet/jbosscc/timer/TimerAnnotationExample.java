package de.akquinet.jbosscc.timer;

import java.util.Date;

import javax.ejb.Schedule;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class TimerAnnotationExample {

	private final static Logger LOG =LoggerFactory.getLogger(TimerAnnotationExample.class);

	@Schedule(second="*/1", minute="*",hour="*", persistent=false)
    public void schedule(){
		LOG.info("invoke schedule method {}", new Date());
	}

}
