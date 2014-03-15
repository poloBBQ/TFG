
package com.pablomartinez.videogadget.client;

import cc.kune.gadget.client.AbstractKuneGadgetGinModule;

import com.google.inject.Singleton;

public class VideoGadgetGinModule extends AbstractKuneGadgetGinModule {

  @Override
  protected void configure() {
    bind(VideoGadgetMainPanel.class).in(Singleton.class);
    bind(VideoGadgetMessages.class).in(Singleton.class);
  };
}
