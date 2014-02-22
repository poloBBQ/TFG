
package com.pablomartinez.decisionmaker.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;
import com.thezukunft.wave.connector.StateUpdateEvent;
import com.thezukunft.wave.connector.StateUpdateEventHandler;
import com.thezukunft.wave.connector.Wave;


public class DecisionMakerMainPanel extends Composite {
  
  /** The messages. */
  private final DecisionMakerMessages messages;
  
  @Inject
  public DecisionMakerMainPanel(final EventBus eventBus, final Wave wave,
      final DecisionMakerMessages gadgetMessages) {
    this.messages = gadgetMessages;
    
    VerticalPanel mainPanel = new VerticalPanel();
    Decision decision1 = new Decision(eventBus, wave, gadgetMessages);
    Decision decision2 = new Decision(eventBus, wave, gadgetMessages);
    Decision decision3 = new Decision(eventBus, wave, gadgetMessages);
    mainPanel.add(decision1);
    mainPanel.add(decision2);
    mainPanel.add(decision3);
    initWidget(mainPanel);
    
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      // We run this deferred, at the end of the gadget load
      @Override
      public void execute() {
        
      }
    });
  }
}
