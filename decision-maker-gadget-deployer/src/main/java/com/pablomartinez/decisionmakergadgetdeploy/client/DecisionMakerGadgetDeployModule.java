
package com.pablomartinez.decisionmakergadgetdeploy.client;

import com.pablomartinez.decisionmaker.client.DecisionMakerGinModule;

import com.google.inject.Singleton;
import com.thezukunft.wave.connector.Wave;
import com.thezukunft.wave.connectorimpl.WaveGINWrapper;

public class DecisionMakerGadgetDeployModule extends DecisionMakerGinModule {
	
	public DecisionMakerGadgetDeployModule(){
		super();
	}

	@Override
	protected void configure() {
		super.configure();
		// As this is the real deployer, we use the real wave functionality so,
		// this can run in the wave infrastructure.
		// KuneGadgetSampleTesterGinModule
		// is similar but with a mock that allow the testing without the wave
		// infrastructure
		bind(Wave.class).to(WaveGINWrapper.class).in(Singleton.class);
	}
}
