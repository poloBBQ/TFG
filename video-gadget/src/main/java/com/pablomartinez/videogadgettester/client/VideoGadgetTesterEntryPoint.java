package com.pablomartinez.videogadgettester.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.FrameElement;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.pablomartinez.videogadget.client.VideoGadgetMainPanel;
import com.pablomartinez.videogadget.client.VideoGadgetMessages;
import com.thezukunft.wave.connector.ModeChangeEvent;
import com.thezukunft.wave.connector.StateUpdateEvent;
import com.thezukunft.wave.connectormock.WaveMock;

public class VideoGadgetTesterEntryPoint implements EntryPoint {

  native static public void createFrame()/*-{
    //document.write('<iframe src="http://iswebrtcready.appear.in/lite.html" frameborder="0" height="0" width="0" id="webrtc-compatability-tester"></iframe>');
    //document.close();
    console.log("test");
  }-*/;
  
  @Override
  public void onModuleLoad() {
    Element head = Document.get().getElementsByTagName("head").getItem(0);
    ScriptElement sceVideo = Document.get().createScriptElement();
    //sceVideo.setSrc("http://iswebrtcready.appear.in/apiv2.js");
    sceVideo.setSrc("http://pablomartinez.co.nf/webcam/apiv2.js");
    
    ScriptElement sceJQ = Document.get().createScriptElement();
    sceJQ.setSrc("http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js");
    
    //head.appendChild(sceJQ);
    //head.appendChild(sceVideo);
    Document.get().getBody().appendChild(sceJQ);
    Document.get().getBody().appendChild(sceVideo);
    
    Element iframeTest = Document.get().createElement("iframe");
    iframeTest.setAttribute("src", "http://iswebrtcready.appear.in/lite.html");
    iframeTest.setAttribute("id", "webrtc-compatability-tester");
    iframeTest.setAttribute("frameborder", "0");
    iframeTest.setAttribute("height", "0");
    iframeTest.setAttribute("width", "0");
    Document.get().getBody().appendChild(iframeTest);
    
    final VideoGadgetGinInjector gin = GWT.create(VideoGadgetGinInjector.class);

    final WaveMock waveMock = (WaveMock) gin.getWave();

    // We initialize some participants
    waveMock.initRandomParticipants();

    // We have to create the gadget using gin so it can use injection of its
    // dependencies (evenBus, etc)

    final VideoGadgetMainPanel gadget1 = gin.getMainPanel();

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        RootPanel.get().add(gadget1);
        gin.getEventBus().fireEvent(new StateUpdateEvent(waveMock));
      }
    });
  }
}
