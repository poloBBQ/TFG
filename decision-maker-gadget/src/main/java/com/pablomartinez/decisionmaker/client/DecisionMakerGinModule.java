
package com.pablomartinez.decisionmaker.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class DecisionMakerGinModule extends AbstractGinModule  {

  public DecisionMakerGinModule(){
    super();
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.inject.client.AbstractGinModule#configure()
   */
  @Override
  protected void configure() {
    bind(DecisionMakerMainPanel.class).in(Singleton.class);
    bind(DecisionMakerMessages.class).in(Singleton.class);
  }
}
