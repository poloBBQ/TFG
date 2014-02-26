package com.pablomartinez.decisionmaker.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class VotesPopup extends PopupPanel{
  public VotesPopup(String decisionTitle, String color){
    VerticalPanel vp = new VerticalPanel();
    Label title = new Label(decisionTitle);
    title.setStyleName("Label-selected");
    vp.add(title);
    vp.add(new Label("Not implemented yet!"));
    setWidget(vp);
    this.getElement().getStyle().setBackgroundColor(color);
  }
}
