package cz.cuni.amis.pogamut.base.component.bus;

import java.util.logging.Level;

import junit.framework.Assert;

import org.junit.Test;

import cz.cuni.amis.pogamut.base.agent.IAgentId;
import cz.cuni.amis.pogamut.base.agent.impl.AgentId;
import cz.cuni.amis.pogamut.base.component.bus.event.IFatalErrorEvent;
import cz.cuni.amis.pogamut.base.component.bus.event.IStartedEvent;
import cz.cuni.amis.pogamut.base.component.bus.event.IStoppedEvent;
import cz.cuni.amis.pogamut.base.component.bus.event.impl.PausedEvent;
import cz.cuni.amis.pogamut.base.component.bus.event.impl.PausingEvent;
import cz.cuni.amis.pogamut.base.component.bus.event.impl.ResumedEvent;
import cz.cuni.amis.pogamut.base.component.bus.event.impl.ResumingEvent;
import cz.cuni.amis.pogamut.base.component.bus.event.impl.StartedEvent;
import cz.cuni.amis.pogamut.base.component.bus.event.impl.StartingEvent;
import cz.cuni.amis.pogamut.base.component.bus.event.impl.StoppedEvent;
import cz.cuni.amis.pogamut.base.component.bus.event.impl.StoppingEvent;
import cz.cuni.amis.pogamut.base.component.stub.component.AutoCheckComponent;
import cz.cuni.amis.pogamut.base.component.stub.component.CheckEvent;
import cz.cuni.amis.pogamut.base.component.stub.component.ManualCheckComponent;
import cz.cuni.amis.pogamut.base.utils.logging.AgentLogger;
import cz.cuni.amis.pogamut.base.utils.logging.IAgentLogger;

import cz.cuni.amis.tests.BaseTest;
				
public class Test04_ComponentBus extends BaseTest {
	
	@Test
	public void test() {
		IAgentId agentId = new AgentId("Test04_ComponentBus");
		IAgentLogger logger = new AgentLogger(agentId);
		logger.addDefaultConsoleHandler();
		logger.setLevel(Level.ALL);
		IComponentBus bus = new ComponentBus(logger);
		
		AutoCheckComponent autoComp0 = new AutoCheckComponent(logger, bus);
		final ManualCheckComponent manualComp0 = new ManualCheckComponent(logger, bus);
		final ManualCheckComponent manualComp1 = new ManualCheckComponent(logger, bus);
		final ManualCheckComponent manualComp2 = new ManualCheckComponent(logger, bus);
		final ManualCheckComponent manualComp3 = new ManualCheckComponent(logger, bus);
		final ManualCheckComponent manualComp4 = new ManualCheckComponent(logger, bus);
		final ManualCheckComponent manualComp5 = new ManualCheckComponent(logger, bus);
		
		IComponentEvent[] events = new IComponentEvent[] {
				new StartingEvent(autoComp0),
				new StartedEvent(autoComp0),
				new PausingEvent(autoComp0),
				new PausedEvent(autoComp0),
				new ResumingEvent(autoComp0),
				new ResumedEvent(autoComp0),
				new StoppingEvent(autoComp0),
				new StoppedEvent(autoComp0),
		};
		
		CheckEvent[] checkEvents = new CheckEvent[events.length];
		
		for (int i = 0; i < events.length; ++i) {
			checkEvents[i] = new CheckEvent(events[i]);
		}
		
		autoComp0.expect(checkEvents);
		
		IComponentEventListener listener2 = new IComponentEventListener<IComponentEvent>() {
			@Override
			public void notify(IComponentEvent event) {
				manualComp0.manualNotify(event);
			}
		};
		IComponentEventListener listener3 = new IComponentEventListener<IComponentEvent>() {
			@Override
			public void notify(IComponentEvent event) {
				manualComp1.manualNotify(event);
			}
		};
		IComponentEventListener listener4 = new IComponentEventListener<IComponentEvent>() {
			@Override
			public void notify(IComponentEvent event) {
				manualComp2.manualNotify(event);
			}
		};
		IComponentEventListener listener5 = new IComponentEventListener<IComponentEvent>() {
			@Override
			public void notify(IComponentEvent event) {
				manualComp3.manualNotify(event);
			}
		};
		IComponentEventListener listener6 = new IComponentEventListener<IComponentEvent>() {
			@Override
			public void notify(IComponentEvent event) {
				manualComp4.manualNotify(event);
			}
		};
		IComponentEventListener listener7 = new IComponentEventListener<IComponentEvent>() {
			@Override
			public void notify(IComponentEvent event) {
				manualComp5.manualNotify(event);
			}
		};
		
		bus.addEventListener(IComponentEvent.class, autoComp0.getClass(), listener2);
		manualComp0.expect(checkEvents);
		
		bus.addEventListener(IStartedEvent.class, autoComp0.getClass(), listener3);
		manualComp1.expect(checkEvents[1]);
		
		bus.addEventListener(IStoppedEvent.class, autoComp0.getClass(), listener4);
		manualComp2.expect(checkEvents[7]);
		
		bus.addEventListener(IComponentEvent.class, listener5);
		bus.addEventListener(IStartedEvent.class, autoComp0.getClass(), listener6);
		bus.addEventListener(IStoppedEvent.class, autoComp0.getComponentId(), listener7);
		
		Assert.assertTrue("isListening(event) failed", bus.isListening(IComponentEvent.class, listener5));
		Assert.assertTrue("isListening(event, componentClass) failed", bus.isListening(IStartedEvent.class, autoComp0.getClass(), listener6));
		Assert.assertTrue("isListening(event) failed", bus.isListening(IStoppedEvent.class, autoComp0.getComponentId(), listener7));
		
		Assert.assertTrue("isListening(event) failed", !bus.isListening(IFatalErrorEvent.class, listener5));
		Assert.assertTrue("isListening(event, componentClass) failed", !bus.isListening(IStartedEvent.class, manualComp0.getClass(), listener6));
		Assert.assertTrue("isListening(event) failed", !bus.isListening(IStoppedEvent.class, manualComp0.getComponentId(), listener7));
		
		bus.removeEventListener(IComponentEvent.class, listener5);
		bus.removeEventListener(IStartedEvent.class, autoComp0.getClass(), listener6);
		bus.removeEventListener(IStoppedEvent.class, autoComp0.getComponentId(), listener7);
		
		Assert.assertTrue("isListening(event) failed", !bus.isListening(IComponentEvent.class, listener5));
		Assert.assertTrue("isListening(event, componentClass) failed", !bus.isListening(IStartedEvent.class, autoComp0.getClass(), listener6));
		Assert.assertTrue("isListening(event) failed", !bus.isListening(IStoppedEvent.class, autoComp0.getComponentId(), listener7));
		
		Assert.assertTrue("isListening(event) failed", !bus.isListening(IFatalErrorEvent.class, listener5));
		Assert.assertTrue("isListening(event, componentClass) failed", !bus.isListening(IStartedEvent.class, manualComp0.getClass(), listener6));
		Assert.assertTrue("isListening(event) failed", !bus.isListening(IStoppedEvent.class, manualComp0.getComponentId(), listener7));
		
		for (IComponentEvent event : events) {
			bus.event(event);
		}
		
		autoComp0.checkExpectEmpty();
		manualComp0.checkExpectEmpty();
		manualComp1.checkExpectEmpty();
		manualComp2.checkExpectEmpty();
		manualComp3.checkExpectEmpty();
		manualComp4.checkExpectEmpty();
		manualComp5.checkExpectEmpty();
		
		System.out.println("---/// TEST OK ///---");
	}
	
	public static void main(String[] args) {
		Test04_ComponentBus test = new Test04_ComponentBus();
		test.test();
	}

}
