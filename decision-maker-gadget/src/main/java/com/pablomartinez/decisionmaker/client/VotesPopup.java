package com.pablomartinez.decisionmaker.client;

import java.util.List;

import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment.VerticalAlignmentConstant;
import com.thezukunft.wave.connector.Participant;

public class VotesPopup extends PopupPanel{
  
  public static String AVATAR_SIZE = "30px";
  
  private StateManager _stateManager;
  private VerticalPanel _vp;
  private VerticalPanel _participantsPanel;
  private String _decisionTitle;
  
  public VotesPopup(){}
  
  public VotesPopup(String decisionTitle, String color,
      StateManager stateManager){
    
    _stateManager = stateManager;
    _decisionTitle = decisionTitle;
    _vp = new VerticalPanel();
    _vp.setSpacing(5);
    _vp.setStyleName("Panel-styled");
    _participantsPanel = new VerticalPanel();
    Label title = new Label(decisionTitle);
    title.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    title.setStyleName("Label-selected");
    _vp.add(title);
    _vp.add(_participantsPanel);
   
    setWidget(_vp);
    _vp.getElement().getStyle().setBorderColor(color);
  }

  public void show(Label _voteCount) {
    List<Participant> participants = _stateManager.getVoters(_decisionTitle);
    
    _participantsPanel.clear();
    
    if(participants.size() > 0){
      for(Participant p : participants){
        HorizontalPanel hp = new HorizontalPanel();
        hp.setSpacing(8);
        hp.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        Image avatar = new Image();
        avatar.setSize(AVATAR_SIZE, AVATAR_SIZE);
        avatar.setUrl(p.getThumbnailUrl());
        hp.add(avatar);
        hp.add(new Label(p.getDisplayName()));
        _participantsPanel.add(hp);
      }
    }
    else{
      _participantsPanel.add(
          new Label(DecisionMakerMainPanel.messages.no_votes()));      
    }
    
    this.showRelativeTo(_voteCount);
  }
}
