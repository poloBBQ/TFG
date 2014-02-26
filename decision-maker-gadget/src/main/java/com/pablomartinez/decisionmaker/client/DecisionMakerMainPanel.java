
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
  
  private StateManager _stateManager;
  
  @Inject
  public DecisionMakerMainPanel(final EventBus eventBus, final Wave wave,
      final DecisionMakerMessages gadgetMessages) {
    
    _decisions = new HashMap<String, Decision>();
      
    _eventBus = eventBus;
    _messages = gadgetMessages;
    _wave = wave;    
    
    _mainPanel = new VerticalPanel();
    
    _stateManager = new StateManager(_wave, this);
    
    addNewDecision("Irse de camping");
    addNewDecision("Discoteca");
    addNewDecision("Salir a pasear al parque");
    itemWasSelected(null);
    
    /*String labeltext = _wave.getState().get("testkey");
    Label testLabel = new Label(); 
    if(labeltext != null && !labeltext.equals("")){
      testLabel.setText(labeltext);
    }
    else{
      _wave.getState().submitValue("testkey", "testvalue");
    }
    _mainPanel.add(testLabel);*/

    initWidget(_mainPanel);
    
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      // We run this deferred, at the end of the gadget load
      @Override
      public void execute() {
        _stateManager.updateDecisions(_decisions);
      }
    });
  }
  
  @Override
  public void onLoad() {
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        setConsistentHeight();
      }
    });
  }

  @Override
  public void itemWasSelected(String itemName) {
    if(itemName == null){
      for (Entry<String, Decision> decision : _decisions.entrySet()){
        decision.getValue().updateAspect();
      }
    }
    else{
      String itemNameLower = itemName.toLowerCase().trim();
      for (Entry<String, Decision> decision : _decisions.entrySet()){
        if(!itemNameLower.equals(decision.getKey())){
          if (decision.getValue().itemIsNotSelected()){
            _stateManager.removeVoteFromDecision(decision.getValue());
          }
        }
      }
      _stateManager.addVoteToDecision(_decisions.get(itemNameLower));
    }
  }

  @Override
  public boolean addNewDecision(String itemName) {
    String itemNameLower = itemName.toLowerCase().trim();
    synchronized (_decisions) {
      if (_decisions.containsKey(itemNameLower)) {
        return false;
      }
      try {
        Decision decision = new Decision(_eventBus, _wave, _messages);
        decision.init(itemName, this);
        _decisions.put(itemNameLower, decision);
        _mainPanel.add(decision);
        _stateManager.addDecision(decision);
        setConsistentHeight();
        return true;
      } catch (Exception e) {
        return false;
      }
    }
  }

  @Override
  public long getTotalVotes() {
    long votes = 0;
    for (Entry<String, Decision> decision : _decisions.entrySet()){
      votes += decision.getValue().getVotes();
    }
    return votes;
  }
  
  public void setConsistentHeight(){
    int height = getMaxHeight();
    if(height == 0)
      return;
    for (Entry<String, Decision> decision : _decisions.entrySet()){
      decision.getValue().setHeight(height);
    }
  }
  
  public int getMaxHeight(){
    int height = 0;
    for (Entry<String, Decision> decision : _decisions.entrySet()){
      int decisionHeight = decision.getValue().getHeight(); 
      height = decisionHeight > height ? decisionHeight : height;
    }
    return height;
  }
}
