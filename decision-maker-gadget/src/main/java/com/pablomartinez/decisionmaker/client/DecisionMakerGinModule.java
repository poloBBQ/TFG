
package com.pablomartinez.decisionmaker.client;

import cc.kune.gadget.client.AbstractKuneGadgetGinModule;

import com.google.inject.Singleton;

public class DecisionMakerGinModule extends AbstractKuneGadgetGinModule {

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.inject.client.AbstractGinModule#configure()
   */
  @Override
  protected void configure() {
    bind(DecisionMakerMainPanel.class).in(Singleton.class);
    bind(DecisionMakerMessages.class).in(Singleton.class);
  };
}
