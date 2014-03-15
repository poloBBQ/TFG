
package com.pablomartinez.videogadgetdeploy.client;

import cc.kune.gadget.client.KuneGadgetGinInjector;

import com.google.gwt.inject.client.GinModules;
import com.pablomartinez.videogadget.client.VideoGadgetMainPanel;

@GinModules(VideoGadgetDeployModule.class)
public interface VideoGadgetDeployGinjector extends KuneGadgetGinInjector {

  /**
   * Gets the main panel.
   * 
   * @return the main panel
   */
  VideoGadgetMainPanel getMainPanel();
}
