
package com.pablomartinez.decisionmakertester.client;


import com.google.inject.Singleton;
import com.pablomartinez.decisionmaker.client.DecisionMakerGinModule;
import com.pablomartinez.decisionmaker.client.DecisionMakerMainPanel;
import com.thezukunft.wave.connector.Wave;
import com.thezukunft.wave.connectorimpl.WaveGINWrapper;
import com.thezukunft.wave.connectormock.WaveMock;

public class DecisionMakerTesterGinModule extends DecisionMakerGinModule {

  @Override
  protected void configure() {

    bind(DecisionMakerMainPanel.class);
    
    //bind(Wave.class).to(WaveGINWrapper.class).in(Singleton.class);
    bind(Wave.class).to(WaveMock.class).in(Singleton.class);
    
  };
}
