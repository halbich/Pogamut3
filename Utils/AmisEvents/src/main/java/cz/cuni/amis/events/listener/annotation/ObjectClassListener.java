package cz.cuni.amis.events.listener.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import object.IObjectEvent;
import cz.cuni.amis.events.IObjectBoard;

/**
 * Used by {@link AnnotationListenerRegistrator} to register level B listener 
 * ({@link IObjectBoard#addObjectListener(Class, object.IObjectEventListener)} 
 * for the annotated method. The annotated method must have 1 parameter of {@link IObjectEvent}.
 * <p><p>
 * The listeners are created by calling {@link AnnotationListenerRegistrator#addListeners()} and removed by 
 * calling {@link AnnotationListenerRegistrator#removeListeners()}.
 * 
 * @author Jimmy
 */
@Target(value = { ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ObjectClassListener {
	
	/**
	 * Object class you want to listen to. 
	 * @return
	 * @see IWorldView#addObjectListener(Class, cz.cuni.amis.pogamut.base.communication.worldview.object.IWorldObjectListener)
	 */
	Class<?> objectClass();
}