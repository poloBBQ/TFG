
package com.pablomartinez.videogadgettester.client;

import cc.kune.gadget.client.KuneGadgetGinInjector;

import com.google.gwt.inject.client.GinModules;
import com.pablomartinez.videogadget.client.VideoGadgetMainPanel;
import com.pablomartinez.videogadget.client.VideoGadgetMessages;


@GinModules(VideoGadgetTesterGinModule.class)
public interface VideoGadgetGinInjector extends KuneGadgetGinInjector {

  /**
   * Gets the main panel.
   * 
   * @return the main panel
   */
  VideoGadgetMainPanel getMainPanel();

  /**
   * Gets the gadget messages.
   * 
   * @return the gadget messages
   */
  VideoGadgetMessages getGadgetMessages();
}
