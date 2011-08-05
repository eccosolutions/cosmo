package org.osaf.cosmo;

import net.fortuna.ical4j.model.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

public class TimeZoneDefault implements InitializingBean {

	protected final Log log = LogFactory.getLog(getClass());

	// it seems if we change the default here, joda and ical4j are already instantiated
	// therefore we change this from a setter to an error
	// in eclipse this can be at the jvm level with -Duser.timezone=UTC
	public void init() {
		log.info("JDK TimeZone: "+TimeZone.getDefault());
		if (!"UTC".equals(TimeZone.getDefault().getID()))
			throw new IllegalStateException("-Duser.timezone=UTC must be specified");
	}

	public void afterPropertiesSet() throws Exception {
		init();
	}

}
