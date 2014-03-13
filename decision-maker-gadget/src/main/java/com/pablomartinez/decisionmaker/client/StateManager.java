package com.pablomartinez.decisionmaker.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.core.client.JsArrayString;
import com.thezukunft.wave.connector.Participant;
import com.thezukunft.wave.connector.Wave;

public class StateManager {
  public static final String FORMAT_VOTERS = "voters";
  public static final String FORMAT_VOTE_COUNTS = "votecount";
  public static final String FORMAT_TITLE = "title"; 
  public static final char SEPARATOR = '-';
  
  private Wave _wave;
  private final DecisionManager _decisionManager;
  
  public StateManager(){
    _decisionManager = null;
  }
  
  public StateManager(Wave wave, DecisionManager decisionManager) {
    _wave = wave;
    _decisionManager = decisionManager;
  }
  
  public void removeVoteFromDecision(Decision decision, String user){
    alterDecisionVotecount(decision, -1);
    editVoters(decision, user, false);
  }
  
  public void addVoteToDecision(Decision decision, String user){
    alterDecisionVotecount(decision, +1);
    editVoters(decision, user, true);
  }
  
  private void editVoters(Decision decision, String user, boolean add) {
    String format = FORMAT_VOTERS + SEPARATOR +
        decision.getDecisionText().trim();
    String currentValue = _wave.getState().get(format);
    boolean changed = false;
    
    if(add){
      if(!currentValue.contains(SEPARATOR + user + SEPARATOR)){
        currentValue += user + SEPARATOR;
        changed = true;
      }
    }
    else{ //Remove
      if(currentValue.contains(SEPARATOR + user + SEPARATOR)){
        currentValue = currentValue.replace(
            SEPARATOR + user + SEPARATOR, Character.toString(SEPARATOR));
        changed = true;
      }
    }
    
    if(changed){
      _wave.getState().submitValue(format, currentValue);
    }
  }
  
  private void alterDecisionVotecount(Decision decision, int amount){
    String key = decision.getDecisionText().trim();
    String stateKey = null;
    
    int count = 0;
    
    JsArrayString keys = _wave.getState().getKeys();
    int i = 0;
    String formatted;
    boolean stop = false;
    while(i < keys.length() && !stop){
      formatted = FORMAT_VOTE_COUNTS + SEPARATOR + key;
      if(keys.get(i).equals(formatted)){
        stateKey = keys.get(i);
        count = Integer.parseInt(_wave.getState().get(formatted));
        stop = true;
      }
      i++;
    }
    
    count += amount;
    _wave.getState().submitValue(stateKey, Integer.toString(count));
  }
  
  public void updateDecisions(Map<String, Decision> decisions){
    JsArrayString keys = _wave.getState().getKeys();
    String format = FORMAT_VOTE_COUNTS + SEPARATOR;
    for (int i = 0; i < keys.length(); i++) {
      String key = keys.get(i);
      if (key.length() > format.length()) {
        String decisionName =
            key.substring(format.length()).trim();
        if (key.startsWith(format)) {
          if(decisions.containsKey(decisionName)){
            decisions.get(decisionName).setVoteCount(
                Integer.parseInt(_wave.getState().get(key)));
          }
          else{
            _decisionManager.addNewDecision(decisionName, false);            
          }
        }
      }
    }
    for (Entry<String, Decision> decision : decisions.entrySet()){
      decision.getValue().updateAspect();
    }
  }
  
  public List<Participant> getVoters(String decision){
    List<Participant> participants = new ArrayList<Participant>();
    String format = FORMAT_VOTERS + SEPARATOR + decision.trim();
    String value = _wave.getState().get(format);
    
    if(value != null){
      String[] users = value.split(Character.toString(SEPARATOR));
      for(String user : users){
        if(user.length() > 0){
          participants.add(_wave.getParticipantById(user));
        }
      }
    }
    
    return participants;
  }
  
  public boolean isValidFormat(String title){
    if(title.length() < 1)
      return false;
    
    if(title.contains(Character.toString(SEPARATOR)))
      return false;
    
    return true;
  }
  
  public void addDecision(Decision decision){
    boolean found = false;
    JsArrayString keys = _wave.getState().getKeys();
    String decisionName = decision.getDecisionText().trim();
    String formatVoters = FORMAT_VOTERS + SEPARATOR + decisionName;
    String formatCount = FORMAT_VOTE_COUNTS + SEPARATOR + decisionName;
    int i = 0;
    while(!found && i < keys.length()){
      if(keys.get(i).equals(formatCount)){
        found = true;
      }
      
      i++;
    }
    
    if(!found){
      _wave.getState().submitValue(formatCount, "0");
      _wave.getState().submitValue(formatVoters, Character.toString(SEPARATOR));
    }
  }
  
  public String getTitle(){
    return _wave.getState().get(FORMAT_TITLE);
  }
  
  public void setTitle(String title){
    _wave.getState().submitValue(FORMAT_TITLE, title);
  }
}
