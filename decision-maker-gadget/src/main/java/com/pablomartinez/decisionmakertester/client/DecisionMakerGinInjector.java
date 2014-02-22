
package com.pablomartinez.decisionmakertester.client;

import cc.kune.gadget.client.KuneGadgetGinInjector;

import com.google.gwt.inject.client.GinModules;
import com.pablomartinez.decisionmaker.client.DecisionMakerMainPanel;
import com.pablomartinez.decisionmaker.client.DecisionMakerMessages;


@GinModules(DecisionMakerTesterGinModule.class)
public interface DecisionMakerGinInjector extends KuneGadgetGinInjector {

  /**
   * Gets the main panel.
   * 
   * @return the main panel
   */
  DecisionMakerMainPanel getMainPanel();

  /**
   * Gets the gadget messages.
   * 
   * @return the gadget messages
   */
  DecisionMakerMessages getGadgetMessages();
}
