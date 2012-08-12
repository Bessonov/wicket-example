/*******************************************************************************
 * Copyright (c) 2012 Anton Bessonov.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Creative Commons
 * Attribution 3.0 License which accompanies this distribution,
 * and is available at
 * http://creativecommons.org/licenses/by/3.0/
 * 
 * Contributors:
 *     Anton Bessonov - initial API and implementation
 ******************************************************************************/
package de.bessonov;

import org.junit.Test;

import de.bessonov.page.HomePage;

public class TestHomePage extends BaseTest { 

	@Test
	public void testRenderHomePage() {
		// start and render the test page
		tester.startPage(HomePage.class);

		// assert rendered page class
		tester.assertRenderedPage(HomePage.class);

		// assert rendered label component
		tester.assertLabel("userGreeting", "Guten Tag, Anonymous!");
	}
}
