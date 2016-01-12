package com.d4s.dil.conn;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public final class PersMgrFact {
	private static final PersistenceManagerFactory pmfInstance = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");

	private PersMgrFact() {
	}

	public static PersistenceManagerFactory get() {
		return pmfInstance;
	}
}
