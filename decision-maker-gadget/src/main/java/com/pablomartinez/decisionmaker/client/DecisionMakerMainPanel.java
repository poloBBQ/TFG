
package com.pablomartinez.decisionmaker.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.thezukunft.wave.connector.StateUpdateEvent;
import com.thezukunft.wave.connector.StateUpdateEventHandler;
import com.thezukunft.wave.connector.Wave;


public class DecisionMakerMainPanel extends Composite
    implements DecisionManager {
  
  private final DecisionManager _decisionManager = this;
  
  private final Map<String, Decision> _decisions;
  
  public static DecisionMakerMessages messages;
  private final EventBus _eventBus;
  private final Wave _wave;
  
  private final VerticalPanel _mainPanel;
  private final VerticalPanel _verticalPanel;
  
  private final Button _addButton;
  private final TextBox _addBox;
  private final HorizontalPanel _addPanel;
  
  private StateManager _stateManager;
  
  private Timer _resetColorTimer = new Timer() {
    public void run() {
      _addBox.getElement().getStyle().setBackgroundColor("white");
    }
  };
  
  @Inject
  public DecisionMakerMainPanel(final EventBus eventBus, final Wave wave,
      final DecisionMakerMessages gadgetMessages) {
    
    _decisions = new HashMap<String, Decision>();
      
    _eventBus = eventBus;
    messages = gadgetMessages;
    _wave = wave;    
    
    _mainPanel = new VerticalPanel();
    _mainPanel.setStyleName("Panel-styled");
    
    _verticalPanel = new VerticalPanel();
    _verticalPanel.setStyleName("Panel-margin");
    _verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    
    _mainPanel.add(_verticalPanel);
    
    _stateManager = new StateManager(_wave, this);
    
    addNewDecision("Irse de camping", true);
    addNewDecision("Discoteca", true);
    addNewDecision("Salir a pasear al parque", true);
    itemWasSelected(null);
    
    _addButton = new Button(messages.add_button());
    
    _addButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        requestToAddDecision();
      }
    });
    _addBox = new TextBox();
    _addBox.addKeyPressHandler(new KeyPressHandler() {
      @Override
      public void onKeyPress(KeyPressEvent event) {
        if(event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER){
          requestToAddDecision();
        }
      }
    });
    _addPanel = new HorizontalPanel();
    _addPanel.getElement().getStyle().setMarginTop(10, Unit.PX);
    _addPanel.add(_addBox);
    _addPanel.add(_addButton);
    _addPanel.setCellHorizontalAlignment(
        _addBox, HasHorizontalAlignment.ALIGN_LEFT);
    _addPanel.setCellHorizontalAlignment(
        _addButton, HasHorizontalAlignment.ALIGN_RIGHT);
    
    _verticalPanel.add(_addPanel);

    initWidget(_mainPanel);
    
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      // We run this deferred, at the end of the gadget load
      @Override
      public void execute() {
        _stateManager.updateDecisions(_decisions);
        
        eventBus.addHandler(StateUpdateEvent.TYPE, new StateUpdateEventHandler() {
          @Override
          public void onUpdate(StateUpdateEvent event) {
            _stateManager.updateDecisions(_decisions);
          }
        });
      }
    });
  }
  
  @Override
  public void onLoad() {
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        setConsistentHeight();
        _addPanel.setWidth(
            Integer.toString(_verticalPanel.getOffsetWidth()) + "px");
        _addBox.setWidth(Integer.toString(
            _addPanel.getOffsetWidth() - _addButton.getOffsetWidth() - 15) +
            "px");
      }
    });
  }

  @Override
  public void itemWasSelected(String itemName) {
    if (itemName != null){
      for (Entry<String, Decision> decision : _decisions.entrySet()){
        if(!itemName.equals(decision.getKey())){
          if (decision.getValue().itemIsNotSelected()){
            _stateManager.removeVoteFromDecision(
                decision.getValue(), _wave.getViewer().getId());
          }
        }
      }
      _stateManager.addVoteToDecision(
          _decisions.get(itemName), _wave.getViewer().getId());
    }
    for (Entry<String, Decision> decision : _decisions.entrySet()){
      decision.getValue().updateAspect();
    }
  }

  @Override
  public boolean addNewDecision(String itemName, boolean notifyStateChanged) {
    synchronized (_decisions) {
      if (_decisions.containsKey(itemName)) {
        return false;
      }
      try {
        Decision decision = new Decision(_eventBus, _wave, messages);
        decision.init(itemName, this, _stateManager);
        _decisions.put(itemName, decision);
        if(_addPanel != null && _verticalPanel.remove(_addPanel)){
          _verticalPanel.add(decision);
          _verticalPanel.add(_addPanel);          
        }
        else{
          _verticalPanel.add(decision);
        }
        
        if(notifyStateChanged){
          _stateManager.addDecision(decision);
        }
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
  
  private void requestToAddDecision(){
    String trimmed = _addBox.getText().trim();
    String toLower = trimmed.toLowerCase();
    
    boolean valid = _stateManager.isValidFormat(trimmed);
    
    if(valid){
      for (Entry<String, Decision> decision : _decisions.entrySet()){
        if(decision.getKey().toLowerCase().equals(toLower)){
          valid = false;
          break;
        }
      }
    }
    
    if(valid){
      _decisionManager.addNewDecision(trimmed, true);
      _addBox.setText("");
      _addBox.getElement().getStyle().setBackgroundColor("lightgreen");
      
      _resetColorTimer.schedule(2000);
    }
    else{
      _addBox.getElement().getStyle().setBackgroundColor("red");
      _resetColorTimer.schedule(2000);
    }
  }
}
