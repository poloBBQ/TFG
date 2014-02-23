
package com.pablomartinez.decisionmaker.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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


public class DecisionMakerMainPanel extends Composite
    implements DecisionManager {
  
  private final Map<String, Decision> _decisions;
  
  private final DecisionMakerMessages _messages;
  private final EventBus _eventBus;
  private final Wave _wave;
  
  private final VerticalPanel _mainPanel;
  
  @Inject
  public DecisionMakerMainPanel(final EventBus eventBus, final Wave wave,
      final DecisionMakerMessages gadgetMessages) {
    
    _decisions = new HashMap<String, Decision>();
      
    _eventBus = eventBus;
    _messages = gadgetMessages;
    _wave = wave;    
    
    _mainPanel = new VerticalPanel();
    _mainPanel.setSpacing(10);
    addNewDecision("Irse de camping");
    addNewDecision("Discoteca");
    addNewDecision("Salir a pasear al parque");
    
    initWidget(_mainPanel);
    
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      // We run this deferred, at the end of the gadget load
      @Override
      public void execute() {
        
      }
    });
  }

  @Override
  public void itemWasSelected(String itemName) {
    String itemNameLower = itemName.toLowerCase();
    for (Entry<String, Decision> decision : _decisions.entrySet()){
      if(!itemNameLower.equals(decision.getKey())){
        decision.getValue().itemIsNotSelected();
      }
    }
  }

  @Override
  public boolean addNewDecision(String itemName) {
    String itemNameLower = itemName.toLowerCase();
    synchronized (_decisions) {
      if (_decisions.containsKey(itemNameLower)) {
        return false;
      }
      try {
        Decision decision = new Decision(_eventBus, _wave, _messages);
        decision.init(itemName, this);
        _decisions.put(itemNameLower, decision);
        _mainPanel.add(decision);
        return true;
      } catch (Exception e) {
        return false;
      }
    }
  }
}
