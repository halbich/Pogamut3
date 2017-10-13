package cz.cuni.amis.pogamut.ut2004.communication.messages.gbinfomessages;
 		
 		// --- IMPORTS FROM /messages/settings/javasettings/javaimport BEGIN
			import java.util.*;import javax.vecmath.*;import cz.cuni.amis.pogamut.base.communication.messages.*;import cz.cuni.amis.pogamut.base.communication.worldview.*;import cz.cuni.amis.pogamut.base.communication.worldview.event.*;import cz.cuni.amis.pogamut.base.communication.worldview.object.*;import cz.cuni.amis.pogamut.multi.communication.worldview.object.*;import cz.cuni.amis.pogamut.base.communication.translator.event.*;import cz.cuni.amis.pogamut.multi.communication.translator.event.*;import cz.cuni.amis.pogamut.base3d.worldview.object.*;import cz.cuni.amis.pogamut.base3d.worldview.object.event.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.*;import cz.cuni.amis.pogamut.ut2004.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004.communication.translator.itemdescriptor.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.ItemType.Category;import cz.cuni.amis.pogamut.unreal.communication.messages.UnrealId;import cz.cuni.amis.utils.exception.*;import cz.cuni.amis.pogamut.base.communication.translator.event.IWorldObjectUpdateResult.Result;import cz.cuni.amis.utils.SafeEquals;import cz.cuni.amis.pogamut.base.agent.*;import cz.cuni.amis.pogamut.multi.agent.*;import cz.cuni.amis.pogamut.multi.communication.worldview.property.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.property.*;import cz.cuni.amis.utils.token.*;import cz.cuni.amis.utils.*;
		// --- IMPORTS FROM /messages/settings/javasettings/javaimport END
		
		
		// --- IMPORTS FROM extra/code/java/javapart/classcategory[@name='all'] BEGIN
				
		// --- IMPORTS FROM extra/code/java/javapart/classcategory[@name='all'] END
		
		// --- IMPORTS FROM extra/code/java/javapart/classcategory[@name=event]+classtype[@name=impl] BEGIN
		
		// --- IMPORTS FROM extra/code/java/javapart/classcategory[@name=event]+classtype[@name=impl] END
    
 		/**
         *  
         			Definition of the event HRP.
         		
         *
         *  <p></p><p></p>
         *  Complete message documentation:               
         *  
		Synchronous message - sent in synchronous batch (events are cached and then exported in the batch due
		to performance issues). 
		You hear someone pick up an object from	the ground. If the very same sound is exported repeatedly (same sound, same source), 
		it won't be exported more than once per second.
	
         */
 	public class HearPickup 
  				extends InfoMessage
    			implements IWorldEvent, IWorldChangeEvent
    			
	    {
 	
    	/** Example how the message looks like - used during parser tests. */
    	public static final String PROTOTYPE =
    		"HRP {Source unreal_id}  {Type text}  {Rotation 0,0,0}  {Distance 0} ";
    
    	
    	
    	/**
    	 * Parameter-less contructor for the message.
    	 */
		public HearPickup()
		{
		}
	
    	
    	
    	
    	/**
		 * Creates new instance of the message HearPickup.
		 * 
		Synchronous message - sent in synchronous batch (events are cached and then exported in the batch due
		to performance issues). 
		You hear someone pick up an object from	the ground. If the very same sound is exported repeatedly (same sound, same source), 
		it won't be exported more than once per second.
	
		 * Corresponding GameBots message
		 *   
		 *   is
		 *   HRP.
		 * 
 	  	 * 
		 *   
		 *     @param Source Unique Id of an object picked up.
		 *   
		 * 
		 *   
		 *     @param Type Class of the picked up actor.
		 *   
		 * 
		 *   
		 *     @param Rotation 
			How should bot rotate if it would like to be in the
			direction of the pickuped actor
		
		 *   
		 * 
		 *   
		 *     @param Distance 
			How far the noise source is. 
		
		 *   
		 * 
		 */
		public HearPickup(
			UnrealId Source,  String Type,  Rotation Rotation,  double Distance
		) {
			
					this.Source = Source;
				
					this.Type = Type;
				
					this.Rotation = Rotation;
				
					this.Distance = Distance;
				
		}
    
    	/**
		 * Cloning constructor from the full message.
		 *
		 * @param original
		 */
		public HearPickup(HearPickup original) {		
			
					this.Source = original.getSource()
 	;
				
					this.Type = original.getType()
 	;
				
					this.Rotation = original.getRotation()
 	;
				
					this.Distance = original.getDistance()
 	;
				
			this.SimTime = original.getSimTime();			
		}
		
	   		
			protected long SimTime;
				
			/**
			 * Simulation time in MILLI SECONDS !!!
			 */	
			@Override
			public long getSimTime() {
				return SimTime;
			}
						
			/**
			 * Used by Yylex to slip correct time of the object or programmatically.
			 */
			protected void setSimTime(long SimTime) {
				this.SimTime = SimTime;
			}
	   	
    	
	    /**
         * Unique Id of an object picked up. 
         */
        protected
         UnrealId Source =
       	null;
	
 		/**
         * Unique Id of an object picked up. 
         */
        public  UnrealId getSource()
 	 {
    					return Source;
    				}
    			
    	
	    /**
         * Class of the picked up actor. 
         */
        protected
         String Type =
       	null;
	
 		/**
         * Class of the picked up actor. 
         */
        public  String getType()
 	 {
    					return Type;
    				}
    			
    	
	    /**
         * 
			How should bot rotate if it would like to be in the
			direction of the pickuped actor
		 
         */
        protected
         Rotation Rotation =
       	null;
	
 		/**
         * 
			How should bot rotate if it would like to be in the
			direction of the pickuped actor
		 
         */
        public  Rotation getRotation()
 	 {
    					return Rotation;
    				}
    			
    	
	    /**
         * 
			How far the noise source is. 
		 
         */
        protected
         double Distance =
       	0;
	
 		/**
         * 
			How far the noise source is. 
		 
         */
        public  double getDistance()
 	 {
    					return Distance;
    				}
    			
 		
 	    public String toString() {
            return
            	super.toString() + "[" +
            	
		              			"Source = " + String.valueOf(getSource()
 	) + " | " + 
		              		
		              			"Type = " + String.valueOf(getType()
 	) + " | " + 
		              		
		              			"Rotation = " + String.valueOf(getRotation()
 	) + " | " + 
		              		
		              			"Distance = " + String.valueOf(getDistance()
 	) + " | " + 
		              		
				"]";           		
        }
 	
 		
 		public String toHtmlString() {
 			return super.toString() + "[<br/>" +
            	
		              			"<b>Source</b> = " + String.valueOf(getSource()
 	) + " <br/> " + 
		              		
		              			"<b>Type</b> = " + String.valueOf(getType()
 	) + " <br/> " + 
		              		
		              			"<b>Rotation</b> = " + String.valueOf(getRotation()
 	) + " <br/> " + 
		              		
		              			"<b>Distance</b> = " + String.valueOf(getDistance()
 	) + " <br/> " + 
		              		
				"<br/>]";     
		}
 	
 	    public String toJsonLiteral() {
            return "hearpickup( "
            		+
									(getSource()
 	 == null ? "null" :
										"\"" + getSource()
 	.getStringId() + "\"" 
									)
								+ ", " + 
									(getType()
 	 == null ? "null" :
										"\"" + getType()
 	 + "\"" 
									)
								+ ", " + 
									(getRotation()
 	 == null ? "null" :
										"[" + getRotation()
 	.getPitch() + ", " + getRotation()
 	.getYaw() + ", " + getRotation()
 	.getRoll() + "]" 
									)								    
								+ ", " + 
								    String.valueOf(getDistance()
 	)									
								
                   + ")";
        }
 	
 		
 		// --- Extra Java from XML BEGIN (extra/code/java/javapart/classcategory[@name=all]) ---
        	
		// --- Extra Java from XML END (extra/code/java/javapart/classcategory[@name=all]) ---
		
	    // --- Extra Java from XML BEGIN (extra/code/java/javapart/classcategory[@name=event+classtype[@name=impl]) ---
	        
	    // --- Extra Java from XML END (extra/code/java/javapart/classcategory[@name=event+classtype[@name=impl]) ---        	            	
 	
		}
 	