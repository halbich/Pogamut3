
	 	/**
         IMPORTANT !!!

         DO NOT EDIT THIS FILE. IT IS GENERATED FROM approriate xml file in xmlresources/gbcommands BY
         THE JavaClassesGenerator.xslt. MODIFY THAT FILE INSTEAD OF THIS ONE.
         
         Use Ant task process-gb-messages after that to generate .java files again.
         
         IMPORTANT END !!!
        */
 	package cz.cuni.amis.pogamut.ut2004.communication.messages.gbcommands;import java.util.*;import javax.vecmath.*;import cz.cuni.amis.pogamut.base.communication.messages.*;import cz.cuni.amis.pogamut.base.communication.worldview.*;import cz.cuni.amis.pogamut.base.communication.worldview.event.*;import cz.cuni.amis.pogamut.base.communication.worldview.object.*;import cz.cuni.amis.pogamut.multi.communication.worldview.object.*;import cz.cuni.amis.pogamut.base.communication.translator.event.*;import cz.cuni.amis.pogamut.multi.communication.translator.event.*;import cz.cuni.amis.pogamut.base3d.worldview.object.*;import cz.cuni.amis.pogamut.base3d.worldview.object.event.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.*;import cz.cuni.amis.pogamut.ut2004.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004.communication.translator.itemdescriptor.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.ItemType.Category;import cz.cuni.amis.pogamut.unreal.communication.messages.UnrealId;import cz.cuni.amis.utils.exception.*;import cz.cuni.amis.pogamut.base.communication.translator.event.IWorldObjectUpdateResult.Result;import cz.cuni.amis.utils.SafeEquals;import cz.cuni.amis.pogamut.base.agent.*;import cz.cuni.amis.pogamut.multi.agent.*;import cz.cuni.amis.pogamut.multi.communication.worldview.property.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.property.*;import cz.cuni.amis.utils.token.*;import cz.cuni.amis.utils.*;
 		/**
 		 * Representation of the GameBots2004 command SETGAMESPEED.
 		 *
 		 * Will set speed of the game.
         */
 	public class SetGameSpeed 
		extends CommandMessage
	        {
	        	
		        
    	/** Example how the message looks like - used during parser tests. */
    	public static final String PROTOTYPE =
    		" {Speed 0} ";
    
		/**
		 * Creates new instance of command SetGameSpeed.
		 * Will set speed of the game.
		 * Corresponding GameBots message for this command is
		 * SETGAMESPEED.
		 *
		 * 
		 *    @param Speed 
			Can range from 0.1 to 50. 1 is normal game speed. The
			reasonable speeding up is around 10. The game engine stops
			catching up at higher values.
		
		 */
		public SetGameSpeed(
			Double Speed
		) {
			
				this.Speed = Speed;
            
		}

		
			/**
			 * Creates new instance of command SetGameSpeed.
			 * Will set speed of the game.
			 * Corresponding GameBots message for this command is
			 * SETGAMESPEED.
			 * <p></p>
			 * WARNING: this is empty-command constructor, you have to use setters to fill it up with data that should be sent to GameBots2004!
		     */
		    public SetGameSpeed() {
		    }
			
		
		/**
		 * Cloning constructor.
		 *
		 * @param original
		 */
		public SetGameSpeed(SetGameSpeed original) {
		   
		        this.Speed = original.Speed;
		   
		}
    
	        /**
	        
			Can range from 0.1 to 50. 1 is normal game speed. The
			reasonable speeding up is around 10. The game engine stops
			catching up at higher values.
		 
	        */
	        protected
	         Double Speed =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Can range from 0.1 to 50. 1 is normal game speed. The
			reasonable speeding up is around 10. The game engine stops
			catching up at higher values.
		 
         */
        public Double getSpeed()
 	
	        {
	            return
	        	 Speed;
	        }
	        
	        
	        
 		
 		/**
         * 
			Can range from 0.1 to 50. 1 is normal game speed. The
			reasonable speeding up is around 10. The game engine stops
			catching up at higher values.
		 
         */
        public SetGameSpeed 
        setSpeed(Double Speed)
 	
			{
				this.Speed = Speed;
				return this;
			}
		
 	    public String toString() {
            return toMessage();
        }
 	
 		public String toHtmlString() {
			return super.toString() + "[<br/>" +
            	
            	"<b>Speed</b> = " +
            	String.valueOf(getSpeed()
 	) +
            	" <br/> " +
            	 
            	"<br/>]"
            ;
		}
 	
		public String toMessage() {
     		StringBuffer buf = new StringBuffer();
     		buf.append("SETGAMESPEED");
     		
						if (Speed != null) {
							buf.append(" {Speed " + Speed + "}");
						}
					
   			return buf.toString();
   		}
 	
 		// --- Extra Java from XML BEGIN (extra/code/java)
        	
		// --- Extra Java from XML END (extra/code/java)
 	
	        }
    	