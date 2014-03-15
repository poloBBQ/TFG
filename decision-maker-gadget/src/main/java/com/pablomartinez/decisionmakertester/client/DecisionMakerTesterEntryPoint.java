package com.pablomartinez.decisionmakertester.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ScriptElement;
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
    final DecisionMakerMainPanel gadget2 = gin.getMainPanel();
    final DecisionMakerMainPanel gadget3 = gin.getMainPanel();

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        RootPanel.get().add(new Label("Gadget 1"));
        RootPanel.get().add(gadget);
        RootPanel.get().add(new Label("Gadget 2"));
        RootPanel.get().add(gadget2);
        RootPanel.get().add(new Label("Gadget 3"));
        RootPanel.get().add(gadget3);
        
        gin.getEventBus().fireEvent(new StateUpdateEvent(waveMock));
      }
    });
  }

}
