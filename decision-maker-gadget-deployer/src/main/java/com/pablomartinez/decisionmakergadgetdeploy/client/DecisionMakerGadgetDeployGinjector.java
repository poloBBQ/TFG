
package com.pablomartinez.decisionmakergadgetdeploy.client;

import cc.kune.gadget.client.KuneGadgetGinInjector;

import com.pablomartinez.decisionmaker.client.DecisionMakerMainPanel;

import com.google.gwt.inject.client.GinModules;

@GinModules(DecisionMakerGadgetDeployModule.class)
public interface DecisionMakerGadgetDeployGinjector extends
		KuneGadgetGinInjector{

	/**
	 * Gets the main panel.
	 * 
	 * @return the main panel
	 */
	DecisionMakerMainPanel getMainPanel();
}
