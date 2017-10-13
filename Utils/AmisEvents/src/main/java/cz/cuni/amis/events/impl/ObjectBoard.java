package cz.cuni.amis.events.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import object.IObject;
import object.IObjectEvent;
import object.IObjectEventListener;
import object.IObjectId;
import cz.cuni.amis.events.IObjectBoard;
import cz.cuni.amis.events.event.IEvent;
import cz.cuni.amis.events.event.IEventListener;
import cz.cuni.amis.utils.ClassUtils;
import cz.cuni.amis.utils.listener.ListenersMap;
import cz.cuni.amis.utils.maps.LazyMap;

/**
 * Object board is implementing {@link IObjectBoard} interface exteding the functionality of simple {@link EventBoard}.
 * It allows you to distinguish between "anonymous" events and object events (events that are generated by concrete instance
 * of some {@link IObject} class).
 * <p><p>
 * Note that there is a big advantage in how the listeners are called and how objects are stored.<p>
 * The event notifying method ({@link ObjectBoard#processEvent(IEvent)}) is respecting 
 * the class/interface hierarchy thus informing listeners hooked on all levels of the hierarchy.
 * <p><p>
 * The items are stored according the the class/interface hierarchy as well!
 * 
 * @author Jimmy
 */
@SuppressWarnings("unchecked")
public class ObjectBoard extends EventBoard implements IObjectBoard {
	
	// ======================================
	// IObjectBoard IMPLEMENTATION
	// ======================================
	
	/**
	 * Level B Listeners
	 * <p><p>
	 * Map of the object class to the object listeners.
	 */
	protected ListenersMap<Class> objectsListeners = new ListenersMap<Class>();
	
	/**
	 * Level C listeners
	 * <p><p>
	 * Map of event listeners on some object class.
	 * <p><p>
	 * First key is eventClass, second key is objectClass.
	 */
	private Map<Class, ListenersMap<Class>> objectEventListeners = 
		Collections.synchronizedMap(
			new LazyMap<Class, ListenersMap<Class>>(){
				@Override
				protected ListenersMap<Class> create(Class key) {
					ListenersMap<Class> listeners = new ListenersMap<Class>();
					return listeners;
				}
			}
		);
		
	/**
	 * Level D Listeners
	 * <p><p>
	 * Listeners listening on all events on a specific object.
	 */
	private ListenersMap<IObjectId> specificObjectListeners = new ListenersMap<IObjectId>();
	
	/**
	 * Level E Listeners
	 * <p><p>
	 * Listeners listening for some specific event on some specific object.
	 * <p><p>
	 * Map of (IWorldObjectId, class of IWorldEvent or desc.).  
	 */
	private Map<IObjectId, ListenersMap<Class>> specificObjectEventListeners = 
		Collections.synchronizedMap(
			new LazyMap<IObjectId, ListenersMap<Class>>() {
				@Override
				protected ListenersMap<Class> create(IObjectId key) {
					ListenersMap<Class> listeners = new ListenersMap<Class>();
					return listeners;
				}
			}
		);
	
	//
	// OBJECT EVENT LISTENERS
	// 
	
	@Override
	public void addObjectListener(Class<?> objectClass, IObjectEventListener<?> listener) {
		objectsListeners.add(objectClass, listener);
	}

	@Override
	public void addObjectListener(Class<?> objectClass, Class<?> eventClass, IObjectEventListener<?> listener) {
		ListenersMap<Class> listeners = objectEventListeners.get(eventClass);
		listeners.add(objectClass, listener);
	}
	
	@Override
	public void addObjectListener(IObjectId objectId, IObjectEventListener<?> listener) {
		specificObjectListeners.add(objectId, listener);
	}
	
	@Override
	public void addObjectListener(IObjectId objectId, Class<?> eventClass, IObjectEventListener<?> listener) {
		ListenersMap<Class> listeners = specificObjectEventListeners.get(objectId);
		listeners.add(eventClass, listener);
	}
	
	@Override
	public boolean isListening(Class<?> objectClass, IObjectEventListener<?> listener) {
		return objectsListeners.isListening(listener);
	}

	@Override
	public boolean isListening(Class<?> objectClass, Class<?> eventClass, IObjectEventListener<?> listener) {
		if (objectEventListeners.containsKey(objectClass)) { // be careful not to create unnecessary ListenersMap
			return objectEventListeners.get(eventClass).isListening(objectClass, listener);
		} else {
			return false;
		}
	}

	@Override
	public boolean isListening(IObjectId objectId, IObjectEventListener<?> listener) {
		return specificObjectListeners.isListening(objectId, listener);
	}

	@Override
	public boolean isListening(IObjectId objectId, Class<?> eventClass, IObjectEventListener<?> listener) {
		if (specificObjectEventListeners.containsKey(objectId)) { // be careful not to create unnecessary ListenersMap
			return specificObjectEventListeners.get(objectId).isListening(eventClass, listener);
		} else {
			return false;
		}		
	}
	
	@Override
	public void removeObjectListener(Class<?> objectClass, IObjectEventListener<?> listener) {
		objectsListeners.remove(objectClass, listener);
	}
	
	@Override
	public void removeObjectListener(Class<?> objectClass, Class<?> eventClass,	IObjectEventListener<?> listener) {
		if (objectEventListeners.containsKey(eventClass)) { // be careful not to create unnecessary ListenersMap
			objectEventListeners.get(eventClass).remove(objectClass, listener);
		}
	}

	@Override
	public void removeObjectListener(IObjectId objectId, IObjectEventListener<?> listener) {
		specificObjectListeners.remove(objectId, listener);
	}

	@Override
	public void removeObjectListener(IObjectId objectId, Class<?> eventClass, IObjectEventListener<?> listener) {
		if (specificObjectEventListeners.containsKey(objectId)) { // be careful not to create unnecessary ListenersMap
			specificObjectEventListeners.get(objectId).remove(eventClass, listener);
		}
	}
	
	// ==============================
	// EventBoard OVERRIDES
	// ==============================
	
	@Override
	public boolean isListening(IEventListener<?> listener) {
		if (super.isListening(listener)) return true;
		synchronized(objectEventListeners) {
			for (ListenersMap<Class> listeners : objectEventListeners.values()) {
				if (listeners.isListening(listener)) return true;
			}
		}
		synchronized(specificObjectEventListeners) {
			for (ListenersMap<Class> listeners : specificObjectEventListeners.values()) {
				if (listeners.isListening(listener)) return true;
			}
		}
		return false;
	}

	@Override
	public void removeListener(IEventListener<?> listener) {
		super.removeListener(listener);
		synchronized(objectEventListeners) {
			for (ListenersMap<Class> listeners : objectEventListeners.values()) {
				listeners.remove(listener);
			}
		}
		specificObjectListeners.remove(listener);
		specificObjectEventListeners.remove(listener);
	}
	
	/**
	 * Helper method used ONLY FROM {@link ObjectBoard#processEvent(IEvent)}. DO NOT USE OUTSIDE THAT METHOD!
	 * @param event
	 */
	protected void notifyLevelBListeners(IObjectEvent event) {
		IObject object = event.getObject();
		Collection<Class> objectClasses = ClassUtils.getSubclasses(object.getClass());
		notifier.setEvent(event);
		for (Class objectClass : objectClasses) {
			objectsListeners.notify(objectClass, notifier);
		}
	}

	
	/**
	 * Helper method used ONLY FROM {@link ObjectBoard#processEvent(IEvent)}. DO NOT USE OUTSIDE THAT METHOD!
	 * @param event
	 */
	protected void notifyLevelCListeners(IObjectEvent event) {
		Collection<Class> eventClasses = ClassUtils.getSubclasses(event.getClass());
		Collection<Class> objectClasses = ClassUtils.getSubclasses(event.getObject().getClass());
		notifier.setEvent(event);
		for (Class eventClass : eventClasses) {
			ListenersMap<Class> listeners = objectEventListeners.get(eventClass);
			if (listeners == null) continue;
			if (!listeners.hasListeners()) continue;
			for (Class objectClass : objectClasses) {
				listeners.notify(objectClass, notifier);			
			}
		}
	}
	
	/**
	 * Helper method used ONLY FROM {@link ObjectBoard#processEvent(IEvent)}. DO NOT USE OUTSIDE THAT METHOD!
	 * @param event
	 */
	protected void notifyLevelDListeners(IObjectEvent event) {
		notifier.setEvent(event);
		specificObjectListeners.notify(event.getObject().getId(), notifier);
	}
	
	/**
	 * Helper method used ONLY FROM {@link ObjectBoard#processEvent(IEvent)}. DO NOT USE OUTSIDE THAT METHOD!
	 * @param event
	 */
	protected void notifyLevelEListeners(IObjectEvent event) {
		notifier.setEvent(event);
		IObjectId objectId = event.getObject().getId();
		ListenersMap<Class> listeners = specificObjectEventListeners.get(objectId);
		if (listeners.hasListeners()) {
			Collection<Class> eventClasses = ClassUtils.getSubclasses(event.getClass());
			notifier.setEvent(event);
			for (Class eventClass : eventClasses) {
				listeners.notify(eventClass, notifier);			
			}
		}
	}
	
	@Override
	protected void processEvent(IEvent event) {
		super.processEvent(event);
		if (event instanceof IObjectEvent) {
			// now we may notify other listeners as well
			IObjectEvent objectEvent = (IObjectEvent)event;
			notifyLevelBListeners(objectEvent);
			notifyLevelCListeners(objectEvent);
			notifyLevelDListeners(objectEvent);
			notifyLevelEListeners(objectEvent);
		}
	}
	
}
