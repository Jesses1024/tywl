package com.puxintech.tywl.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author yanhai
 *
 */
public abstract class ProfileUtils {

	private static Environment env;

	private static final String PROD_PROFILE = "prod";

	public static boolean acceptsProfiles(String... profiles) {
		return env.acceptsProfiles(profiles);
	}

	public static boolean isProduction() {
		return ProfileUtils.acceptsProfiles(PROD_PROFILE);
	}

	public static boolean isDevelopment() {
		return !isProduction();
	}

	@Component
	public static class Initialize {

		@Autowired
		public Initialize(Environment env) {
			ProfileUtils.env = env;
		}

	}
}
