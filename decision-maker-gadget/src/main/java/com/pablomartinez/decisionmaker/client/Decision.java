
package com.pablomartinez.decisionmaker.client;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.shape.Rectangle;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;
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
  
  private Label _decisionTitle;
  private Label _voteCount;
  private Rectangle _rectangle;
  
  private DecisionManager _decisionManager;
  
  private boolean _selected = false;
  
  @Inject
  public Decision(final EventBus eventBus, final Wave wave,
      final DecisionMakerMessages gadgetMessages) {
    this.messages = gadgetMessages;
    
    HorizontalPanel decisionPanel = new HorizontalPanel();
    decisionPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
    RadioButton rb = new RadioButton("Decision");
    rb.addClickHandler(new DecisionClickHandler());
    _decisionTitle = new Label();
    _decisionTitle.setStyleName("Label-unselected");
    //barPanel.getElement().getStyle().setBackgroundColor("Blue");
    DrawingArea canvas = new DrawingArea(BAR_MAX_WIDTH, BAR_HEIGHT);
    _rectangle = new Rectangle(0, 0, 60, BAR_HEIGHT);
    _rectangle.setFillColor(getColor());
    _rectangle.setStrokeWidth(0);
    canvas.add(_rectangle);
    _voteCount = new Label(Integer.toString(_votes));
    _voteCount.setStyleName("Label-unselected");
    decisionPanel.add(rb);
    decisionPanel.add(_decisionTitle);
    decisionPanel.setCellWidth(_decisionTitle, Integer.toString(LABEL_WIDTH));
    decisionPanel.add(canvas);
    decisionPanel.setCellWidth(canvas, Integer.toString(BAR_MAX_WIDTH));
    decisionPanel.add(_voteCount);
    
    initWidget(decisionPanel);
    
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      // We run this deferred, at the end of the gadget load
      @Override
      public void execute() {
        
      }
    });
  }
  
  public void init(String name, DecisionManager decisionManager){
    _decisionTitle.setText(name);
    _decisionManager = decisionManager;
  }
  
  private class DecisionClickHandler implements ClickHandler{
    @Override
    public void onClick(ClickEvent event) {
      //_decisionTitle.removeStyleName("Label-unselected");
      _decisionTitle.setStyleName("Label-selected");
      _voteCount.setStyleName("Label-selected");
      _selected = true;
      _decisionManager.itemWasSelected(_decisionTitle.getText());
      _rectangle.setStrokeWidth(2);
    }
  }
  
  public void itemIsNotSelected(){
    if(_selected){
      _selected = false;
      _decisionTitle.setStyleName("Label-unselected");
      _rectangle.setStrokeWidth(0);
      _voteCount.setStyleName("Label-unselected");
    }
    // else do nothing
  }
  
  private String getColor(){
    String text = _decisionTitle.getText();
    int value = Random.nextInt(8);
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
    default:
      return "black";
    }
  }
}
