package cz.cuni.amis.pogamut.base.component.bus.event;

import cz.cuni.amis.pogamut.base.component.IComponent;
import cz.cuni.amis.pogamut.base.component.bus.IComponentEvent;

public interface IResumedEvent<SOURCE extends IComponent> extends IComponentEvent<SOURCE> {

	public String getMessage();
	
}
