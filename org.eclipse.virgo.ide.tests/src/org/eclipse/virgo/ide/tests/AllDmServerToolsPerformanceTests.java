/*******************************************************************************
 * Copyright (c) 2009 SpringSource, a divison of VMware, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SpringSource, a division of VMware, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.virgo.ide.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Christian Dupuis
 */
public class AllDmServerToolsPerformanceTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Performance Tests for the dm Server Tools");
		return suite;
	}

}
