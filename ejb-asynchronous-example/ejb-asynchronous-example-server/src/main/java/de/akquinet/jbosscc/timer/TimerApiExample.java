package de.akquinet.jbosscc.timer;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Startup
public class TimerApiExample {
	private final static Logger LOG = LoggerFactory
			.getLogger(TimerApiExample.class);

	@Resource
	private TimerService timerService;

	@PostConstruct
	public void init() {
		timerService.createCalendarTimer(new ScheduleExpression().second("*/1")
				.minute("*").hour("*"));
	}

	@Timeout
	public void schedule(Timer timer) {
		LOG.info("invoke method {}", new Date());
	}

}
