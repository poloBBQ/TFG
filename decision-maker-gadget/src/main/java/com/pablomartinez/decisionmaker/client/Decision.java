
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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
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
  
  /** The messages. */
  private final DecisionMakerMessages messages;
  
  @Inject
  public Decision(final EventBus eventBus, final Wave wave,
      final DecisionMakerMessages gadgetMessages) {
    this.messages = gadgetMessages;
    
    HorizontalPanel decisionPanel = new HorizontalPanel();
    RadioButton rb = new RadioButton("Decision");
    Label decisionTitle = new Label("Irse de camping");
    //barPanel.getElement().getStyle().setBackgroundColor("Blue");
    DrawingArea canvas = new DrawingArea(50, 10);
    Rectangle rect = new Rectangle(0, 0, 50, 10);
    rect.setFillColor("red");
    canvas.add(rect);
    decisionPanel.add(rb);
    decisionPanel.add(decisionTitle);
    decisionPanel.setCellWidth(decisionTitle, "30");
    decisionPanel.add(canvas);
    initWidget(decisionPanel);
    
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      // We run this deferred, at the end of the gadget load
      @Override
      public void execute() {
        
      }
    });
  }
}
