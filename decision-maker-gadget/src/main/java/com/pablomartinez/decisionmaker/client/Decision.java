
package com.pablomartinez.decisionmaker.client;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.shape.Rectangle;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;
import com.thezukunft.wave.connector.Participant;
import com.thezukunft.wave.connector.StateUpdateEvent;
import com.thezukunft.wave.connector.StateUpdateEventHandler;
import com.thezukunft.wave.connector.Wave;


public class Decision extends Composite {
  
  public static final int BAR_HEIGHT = 25;
  public static final int BAR_MAX_WIDTH = 150;
  public static final int LABEL_WIDTH = 80;
  
  private int _votes = 0;
  
  /** The messages. */
  private final DecisionMakerMessages messages;
  
  private HorizontalPanel _decisionPanel;
  private Label _decisionTitle;
  private Label _voteCount;
  private Rectangle _rectangle;
  private RadioButton _rb;
  private VotesPopup _popup;
  
  private DecisionManager _decisionManager;
  
  private boolean _selected = false;
  
  public Decision(){
    messages = null;
  }
  
  @Inject
  public Decision(final EventBus eventBus, final Wave wave,
      final DecisionMakerMessages gadgetMessages) {
    this.messages = gadgetMessages;
    
    _decisionPanel = new HorizontalPanel();
    //_decisionPanel.setSpacing(10);
    _decisionPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
    _rb = new RadioButton(Integer.toString(Random.nextInt()));
    DecisionClickHandler clickHandler = new DecisionClickHandler();
    _rb.addClickHandler(clickHandler);
    _decisionTitle = new Label();
    _decisionTitle.addClickHandler(clickHandler);
    DrawingArea canvas = new DrawingArea(BAR_MAX_WIDTH, BAR_HEIGHT);
    _rectangle = new Rectangle(0, 0, 60, BAR_HEIGHT);
    _rectangle.setFillColor(getColor());
    _rectangle.addClickHandler(clickHandler);
    canvas.add(_rectangle);
    _voteCount = new Label();
    _voteCount.addClickHandler(clickHandler);
    _voteCount.addMouseOverHandler(new MouseOverVotesHandler());
    _voteCount.addMouseOutHandler(new MousOutaVotesHandler());
    HorizontalPanel separator = new HorizontalPanel();
    separator.setSpacing(5);
    
    _decisionPanel.add(_rb);
    _decisionPanel.add(_decisionTitle);
    _decisionPanel.setCellWidth(_decisionTitle, Integer.toString(LABEL_WIDTH));
    _decisionPanel.add(canvas);
    _decisionPanel.setCellWidth(canvas, Integer.toString(BAR_MAX_WIDTH));
    _decisionPanel.add(separator);
    _decisionPanel.add(_voteCount);
    
    initWidget(_decisionPanel);
    
    updateAspect();
    
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      // We run this deferred, at the end of the gadget load
      @Override
      public void execute() {
        
      }
    });
  }
  
  public String getDecisionText(){
    return _decisionTitle.getText();
  }
  
  public void init(String name, DecisionManager decisionManager,
      StateManager stateManager){
    _decisionTitle.setText(name);
    _decisionManager = decisionManager;
    _popup = new VotesPopup(name, _rectangle.getFillColor(), stateManager);
  }
  
  private class DecisionClickHandler implements ClickHandler{
    @Override
    public void onClick(ClickEvent event) {
      if (!_selected) {
        _selected = true;
        _rb.setValue(true);
        _votes++;
        _decisionManager.itemWasSelected(_decisionTitle.getText());
        updateAspect();
      }
    }
  }
  
  private class MouseOverVotesHandler implements MouseOverHandler{
    @Override
    public void onMouseOver(MouseOverEvent event) {
      _popup.show(_voteCount);
    }
  }
  
  private class MousOutaVotesHandler implements MouseOutHandler{
    @Override
    public void onMouseOut(MouseOutEvent event) {
      _popup.hide();
    }
  }
  
  public void updateAspect(){
    if(_selected){
      _decisionTitle.setStyleName("Label-selected");
      _rectangle.setStrokeWidth(2);
      _voteCount.setStyleName("Label-selected");
      _rb.setValue(true);
    }
    else{
      _decisionTitle.setStyleName("Label-unselected");
      _rectangle.setStrokeWidth(0);
      _voteCount.setStyleName("Label-unselected");
      _rb.setValue(false);
    }
    _voteCount.setText(Integer.toString(_votes));
    
    if(_rectangle != null){
      int size;
      try{
        size = (int) (((float)_votes / (float)_decisionManager.getTotalVotes())
          * BAR_MAX_WIDTH);
      }
      catch(Exception e){
        size = 0;
      }
      
      _rectangle.setWidth(size);
    }
  }
  
  public int getVotes(){
    return _votes;
  }
  
  public void setVoteCount(int count){
    _votes = count;
    updateAspect();
  }
  
  /*
   * Return true if it was previously selected
   * */
  public boolean itemIsNotSelected(){
    if(_selected){
      _selected = false;
      _rb.setValue(false);
      _votes--;
      return true;
    }
    
    return false;
  }
  
  public int getHeight(){
    return _decisionTitle.getOffsetHeight();    
  }
  
  public void setHeight(int height){
    String sHeight = Integer.toString(height);
    _decisionPanel.setHeight(sHeight);
    _decisionPanel.setCellHeight(_decisionTitle, sHeight);
  }
  
  private String getColor(){
    int value = Random.nextInt(14);
    switch (value){
    case 0:
      return "red";
    case 1:
      return "blue";
    case 2:
      return "green";
    case 3:
      return "yellow";
    case 4:
      return "orange";
    case 5:
      return "fuchsia";
    case 6:
      return "purple";
    case 7:
      return "silver";
    case 8:
      return "gray";
    case 9:
      return "brown";
    case 10:
      return "lightgreen";
    case 11:
      return "orangered";
    case 12:
      return "magenta";
    case 13:
      return "cyan";
    default:
      return "black";
    }
  }
}
