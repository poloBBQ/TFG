
package com.pablomartinez.videogadget.client;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;
import com.thezukunft.wave.connector.StateUpdateEvent;
import com.thezukunft.wave.connector.StateUpdateEventHandler;
import com.thezukunft.wave.connector.Wave;


public class VideoGadgetMainPanel extends Composite {

  /** The messages. */
  private final VideoGadgetMessages messages;
  private final EventBus _eventBus;
  private final Wave _wave;
  private final HorizontalPanel _mainPanel;
  private Frame _frame = null;
  
  boolean isBrowserCompatible = false;
  VideoGadgetMainPanel _videoGadgetMainPanel;
  
  @Inject
  public VideoGadgetMainPanel(final EventBus eventBus, final Wave wave,
      final VideoGadgetMessages gadgetMessages) {
    _videoGadgetMainPanel = this;
    this.messages = gadgetMessages;
    _eventBus = eventBus;
    _wave = wave;
    
    _mainPanel = new HorizontalPanel();
    initWidget(_mainPanel);
    
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      // We run this deferred, at the end of the gadget load
      @Override
      public void execute() {
        //videoLibInit(_videoGadgetMainPanel);
        tryCreateFrame();
        if(_frame == null){
          final TextBox textBox = new TextBox();
          textBox.setText(messages.insert_room());
          //textBox.setText(Boolean.toString(isBrowserCompatible));
          //TODO: if !isBrowserCompatible show warning
          
          Button okButton = new Button(messages.accept());
          okButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
              _wave.getState().submitValue("source", textBox.getText());
            }
          });
          _mainPanel.add(textBox);
          _mainPanel.add(okButton);
        }
        
        _eventBus.addHandler(StateUpdateEvent.TYPE, new StateUpdateEventHandler() {
          @Override
          public void onUpdate(StateUpdateEvent event) {
            tryCreateFrame();
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
        /*videoLibInit(_videoGadgetMainPanel);
        PopupPanel ppp = new PopupPanel();
        ppp.setTitle("Loaded: " + Boolean.toString(isBrowserCompatible));
        ppp.show();*/
      }
    });
  }
  
  private native void videoLibInit(VideoGadgetMainPanel vg) /*-{
    API.isAppearinCompatible(function (data) {
      if (data.isSupported) {
        vg.@com.pablomartinez.videogadget.client.VideoGadgetMainPanel::isBrowserCompatible = true;
      }
      else {
        vg.@com.pablomartinez.videogadget.client.VideoGadgetMainPanel::isBrowserCompatible = false;
      }
    });
  }-*/;
  
  public void tryCreateFrame(){
    String source = _wave.getState().get("source");
    if(source != null && !source.equals("")){
      String url = formatSource(source);
      if(url != null){
        _frame = new Frame(url);
        _frame.setWidth("500px");
        _frame.setHeight("500px");
        _mainPanel.clear();
        _mainPanel.add(_frame);
      }
    }
  }
  
  public String formatSource(String source){
    String invalid = "[^a-zA-Z0-9]";
    String formattedSource = source.replaceAll(invalid, "");
    if(formattedSource.length() > 0){
      return ("https://appear.in/" + source + "?lite");
    }
    else{
      return null;
    }
  }
}
