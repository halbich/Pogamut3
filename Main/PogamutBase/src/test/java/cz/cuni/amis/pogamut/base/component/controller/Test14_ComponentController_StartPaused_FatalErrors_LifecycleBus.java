package cz.cuni.amis.pogamut.base.component.controller;

import java.util.logging.Level;

import org.junit.Test;

import cz.cuni.amis.pogamut.base.agent.IAgentId;
import cz.cuni.amis.pogamut.base.agent.impl.AgentId;
import cz.cuni.amis.pogamut.base.component.bus.IComponentEvent;
import cz.cuni.amis.pogamut.base.component.bus.event.impl.FatalErrorEvent;
import cz.cuni.amis.pogamut.base.component.bus.event.impl.PausedEvent;
import cz.cuni.amis.pogamut.base.component.bus.event.impl.PausingEvent;
import cz.cuni.amis.pogamut.base.component.bus.event.impl.ResetEvent;
import cz.cuni.amis.pogamut.base.component.bus.event.impl.ResumedEvent;
import cz.cuni.amis.pogamut.base.component.bus.event.impl.ResumingEvent;
import cz.cuni.amis.pogamut.base.component.bus.event.impl.StartingPausedEvent;
import cz.cuni.amis.pogamut.base.component.lifecyclebus.ILifecycleBus;
import cz.cuni.amis.pogamut.base.component.lifecyclebus.LifecycleBus;
import cz.cuni.amis.pogamut.base.component.stub.component.AutoCheckComponent;
import cz.cuni.amis.pogamut.base.component.stub.component.CheckEvent;
import cz.cuni.amis.pogamut.base.component.stub.component.ManualCheckComponent;
import cz.cuni.amis.pogamut.base.utils.logging.AgentLogger;
import cz.cuni.amis.pogamut.base.utils.logging.IAgentLogger;

/**
 * Testing starting_paused/resuming/stopping + failures (fatal errors) == restarts with LifecycleBus.
 * @author Jimmy
 */
import cz.cuni.amis.tests.BaseTest;
				
public class Test14_ComponentController_StartPaused_FatalErrors_LifecycleBus extends BaseTest {
	
	@Test
	public void test() {
		IAgentId agentId = new AgentId("Test14_ComponentController_StartPaused_FatalErrors_LifecycleBus");
		IAgentLogger logger = new AgentLogger(agentId);
		logger.addDefaultConsoleHandler();
		logger.setLevel(Level.ALL);
		ILifecycleBus bus = new LifecycleBus(logger);
		
		// needs to be first, to receive all events as the first component
		AutoCheckComponent autoComp0 = new AutoCheckComponent(logger, bus);
		
		ComponentControlHelper emptyCtrl = new ComponentControlHelper() {
		};
		
		ManualCheckComponent manualComp0 = new ManualCheckComponent(logger, bus);
		ManualCheckComponent manualComp1 = new ManualCheckComponent(logger, bus);
		ManualCheckComponent manualComp2 = new ManualCheckComponent(logger, bus);
		
		ComponentController manualCompCtrl1 = 
			new ComponentController(
					manualComp1, emptyCtrl, bus, logger.getCategory(manualComp1.getComponentId().getToken()),
					new ComponentDependencies(ComponentDependencyType.STARTS_WITH).add(manualComp0)
			);
		ComponentController manualCompCtrl2 = 
			new ComponentController(
					manualComp2, emptyCtrl, bus, logger.getCategory(manualComp2.getComponentId().getToken()),
					new ComponentDependencies(ComponentDependencyType.STARTS_AFTER).add(manualComp1)
			);
		
		IComponentEvent[] events = new IComponentEvent[] {
				new StartingPausedEvent(manualComp0),
				new StartingPausedEvent(manualComp1),
				new PausedEvent(manualComp1),
				new StartingPausedEvent(manualComp2),
				new PausedEvent(manualComp2),
				new PausedEvent(manualComp0),	
				
				new FatalErrorEvent(autoComp0, "Failure"),	
				new ResetEvent(bus),
				
				//////////////////////////////
				
				new StartingPausedEvent(manualComp0),
				new StartingPausedEvent(manualComp1),
				new PausedEvent(manualComp1),
				new StartingPausedEvent(manualComp2),
				new PausedEvent(manualComp2),
				new PausedEvent(manualComp0),	
				
				new ResumingEvent(manualComp0),
				new ResumingEvent(manualComp1),
				new ResumedEvent(manualComp1),
				new ResumingEvent(manualComp2),
				new ResumedEvent(manualComp2),
				new ResumedEvent(manualComp0),	
				
				new FatalErrorEvent(autoComp0, "Failure"),
				new ResetEvent(bus),
				
				//////////////////////////////
				
				new StartingPausedEvent(manualComp0),
				new StartingPausedEvent(manualComp1),
				new PausedEvent(manualComp1),
				new StartingPausedEvent(manualComp2),
				new PausedEvent(manualComp2),
				new PausedEvent(manualComp0),	
				
				new ResumingEvent(manualComp0),
				new ResumingEvent(manualComp1),
				new ResumedEvent(manualComp1),
				new ResumingEvent(manualComp2),
				new ResumedEvent(manualComp2),
				new ResumedEvent(manualComp0),	
				
				new PausingEvent(manualComp0),
				new PausingEvent(manualComp1),
				new PausingEvent(manualComp2),
				new PausedEvent(manualComp2),
				new PausedEvent(manualComp1),
				new PausedEvent(manualComp0),
				
				new FatalErrorEvent(autoComp0, "Failure"),
				new ResetEvent(bus),
				
				//////////////////////////////
							
		};
		
		CheckEvent[] checkEvents = new CheckEvent[events.length];
		
		for (int i = 0; i < events.length; ++i) {
			checkEvents[i] = new CheckEvent(events[i]);
		}
		
		autoComp0.expect(checkEvents);
		
		///////////////////////////////////////////
		
		manualComp0.manualStartPaused();
		
		bus.event(new FatalErrorEvent(autoComp0, "failure"));
		bus.reset();
		
		///////////////////////////////////////////
		
		manualComp0.manualStartPaused();
		
		manualComp0.manualResume();
				
		bus.event(new FatalErrorEvent(autoComp0, "failure"));
		bus.reset();
		
		///////////////////////////////////////////
		
		manualComp0.manualStartPaused();
		
		manualComp0.manualResume();
		
		manualComp0.manualPause();
		
		bus.event(new FatalErrorEvent(autoComp0, "failure"));
		bus.reset();
		
		///////////////////////////////////////////
				
		autoComp0.checkExpectEmpty();
		
		System.out.println("---/// TEST OK ///---");
	}

}
