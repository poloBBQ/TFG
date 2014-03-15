
package com.pablomartinez.videogadgettester.client;


import com.google.inject.Singleton;
import com.pablomartinez.videogadget.client.VideoGadgetGinModule;
import com.pablomartinez.videogadget.client.VideoGadgetMainPanel;
import com.thezukunft.wave.connector.Wave;
import com.thezukunft.wave.connectormock.WaveMock;

public class VideoGadgetTesterGinModule extends VideoGadgetGinModule {

  @Override
  protected void configure() {

    bind(VideoGadgetMainPanel.class);
    
    bind(Wave.class).to(WaveMock.class).in(Singleton.class);
  };
}
