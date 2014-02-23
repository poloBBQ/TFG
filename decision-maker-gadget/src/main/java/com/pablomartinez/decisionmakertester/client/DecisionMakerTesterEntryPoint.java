package com.pablomartinez.decisionmakertester.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.pablomartinez.decisionmaker.client.DecisionMakerMainPanel;
import com.pablomartinez.decisionmaker.client.DecisionMakerMessages;
import com.thezukunft.wave.connector.ModeChangeEvent;
import com.thezukunft.wave.connector.StateUpdateEvent;
import com.thezukunft.wave.connectormock.WaveMock;

public class DecisionMakerTesterEntryPoint implements EntryPoint {
  
  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
   */
  @Override
  public void onModuleLoad() {
    final DecisionMakerGinInjector gin = GWT.create(DecisionMakerGinInjector.class);

    final WaveMock waveMock = (WaveMock) gin.getWave();

    // We initialize some participants
    waveMock.initRandomParticipants();
    
    // We have to create the gadget using gin so it can use injection of its
    // dependencies (evenBus, etc)

    final DecisionMakerMainPanel gadget = gin.getMainPanel();

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        RootPanel.get().add(gadget);
        gin.getEventBus().fireEvent(new StateUpdateEvent(waveMock));
      }
    });
  }

}
