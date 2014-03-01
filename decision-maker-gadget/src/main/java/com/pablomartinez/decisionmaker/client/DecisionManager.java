package com.pablomartinez.decisionmaker.client;

public interface DecisionManager {
  /*
   * Triggered when one of the decisions is selected.
   * The name of the selected decision is the parameter.
   * */
  public void itemWasSelected(String itemName);

  /*
   * Used to create a new decision.
   * The name of the decision is passed as a parameter.
   * Returns true if it was successful in adding it (i.e. an item with the same
   * name already exists). 
   * */
  public boolean addNewDecision(String itemName, boolean notifyStateChanged);
  
  /*
   * The summation of the votes from all decisions
   * */
  public long getTotalVotes();
}
