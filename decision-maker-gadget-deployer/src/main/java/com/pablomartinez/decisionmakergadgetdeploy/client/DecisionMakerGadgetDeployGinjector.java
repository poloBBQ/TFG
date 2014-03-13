/*
 *
 * Copyright (C) 2007-2013 Licensed to the Comunes Association (CA) under 
 * one or more contributor license agreements (see COPYRIGHT for details).
 * The CA licenses this file to you under the GNU Affero General Public 
 * License version 3, (the "License"); you may not use this file except in 
 * compliance with the License. This file is part of kune.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.pablomartinez.decisionmakergadgetdeploy.client;

import cc.kune.gadget.client.KuneGadgetGinInjector;

import com.pablomartinez.decisionmaker.client.DecisionMakerMainPanel;
import com.pablomartinez.decisionmakertester.client.DecisionMakerGinInjector;

import com.google.gwt.inject.client.GinModules;

// TODO: Auto-generated Javadoc
/**
 * The Interface KuneGadgetSampleDeployGinjector.
 * 
 * @author vjrj@ourproject.org (Vicente J. Ruiz Jurado)
 */
@GinModules(DecisionMakerGadgetDeployModule.class)
public interface DecisionMakerGadgetDeployGinjector extends
		KuneGadgetGinInjector {

	/**
	 * Gets the main panel.
	 * 
	 * @return the main panel
	 */
	DecisionMakerMainPanel getMainPanel();
}
