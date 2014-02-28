package com.pablomartinez.decisionmaker.client;

import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.core.client.JsArrayString;
import com.thezukunft.wave.connector.Wave;

public class StateManager {
  private Wave _wave;
  
  public StateManager(Wave wave, DecisionManager decisionManager) {
    _wave = wave;
  }
  
  public void removeVoteFromDecision(Decision decision){
    alterDecisionVotecount(decision, -1);
  }
  
  public void addVoteToDecision(Decision decision){
    alterDecisionVotecount(decision, +1);
  }
  
  private void alterDecisionVotecount(Decision decision, int amount){
    String key = decision.getDecisionText().toLowerCase().trim();
    String stateKey = null;
    
    int count = 0;
    
    JsArrayString keys = _wave.getState().getKeys();
    int i = 0;
    String formatted;
    boolean stop = false;
    while(i < keys.length() && !stop){
      formatted = "votecount-" + key;
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
    String format = "votecount-";
    for (int i = 0; i < keys.length(); i++) {
      String key = keys.get(i);
      if (key.length() > format.length()) {
        String decisionName =
            key.substring(format.length()).toLowerCase().trim();
        if (key.startsWith(format)) {
          decisions.get(decisionName).setVoteCount(
              Integer.parseInt(_wave.getState().get(key)));
        }
      }
    }
    for (Entry<String, Decision> decision : decisions.entrySet()){
      decision.getValue().updateAspect();
    }
  }
  
  public void addDecision(Decision decision){
    boolean found = false;
    JsArrayString keys = _wave.getState().getKeys();
    String format = "votecount-"
        + decision.getDecisionText().trim().toLowerCase();
    int i = 0;
    while(!found && i < keys.length()){
      if(keys.get(i).equals(format)){
        found = true;
      }
      
      i++;
    }
    
    if(!found){
      _wave.getState().submitValue(format, "0");
    }
  }
}
