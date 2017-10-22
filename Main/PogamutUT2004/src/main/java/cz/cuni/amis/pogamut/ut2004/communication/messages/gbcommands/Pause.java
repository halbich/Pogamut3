
	 	/**
         IMPORTANT !!!

         DO NOT EDIT THIS FILE. IT IS GENERATED FROM approriate xml file in xmlresources/gbcommands BY
         THE JavaClassesGenerator.xslt. MODIFY THAT FILE INSTEAD OF THIS ONE.
         
         Use Ant task process-gb-messages after that to generate .java files again.
         
         IMPORTANT END !!!
        */
 	package cz.cuni.amis.pogamut.ut2004.communication.messages.gbcommands;import java.util.*;import javax.vecmath.*;import cz.cuni.amis.pogamut.base.communication.messages.*;import cz.cuni.amis.pogamut.base.communication.worldview.*;import cz.cuni.amis.pogamut.base.communication.worldview.event.*;import cz.cuni.amis.pogamut.base.communication.worldview.object.*;import cz.cuni.amis.pogamut.multi.communication.worldview.object.*;import cz.cuni.amis.pogamut.base.communication.translator.event.*;import cz.cuni.amis.pogamut.multi.communication.translator.event.*;import cz.cuni.amis.pogamut.base3d.worldview.object.*;import cz.cuni.amis.pogamut.base3d.worldview.object.event.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.*;import cz.cuni.amis.pogamut.ut2004.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004.communication.translator.itemdescriptor.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.ItemType.Category;import cz.cuni.amis.pogamut.unreal.communication.messages.UnrealId;import cz.cuni.amis.utils.exception.*;import cz.cuni.amis.pogamut.base.communication.translator.event.IWorldObjectUpdateResult.Result;import cz.cuni.amis.utils.SafeEquals;import cz.cuni.amis.pogamut.base.agent.*;import cz.cuni.amis.pogamut.multi.agent.*;import cz.cuni.amis.pogamut.multi.communication.worldview.property.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.property.*;import cz.cuni.amis.utils.token.*;import cz.cuni.amis.utils.*;
 		/**
 		 * Representation of the GameBots2004 command PAUSE.
 		 *
 		 * 
		Will pause/unpause the game. The game can be paused just if
		bAllowPause = true in GameBots2004.ini (different settings for bots
		and server possible).
	
         */
 	public class Pause 
		extends CommandMessage
	        {
	        	
		        
    	/** Example how the message looks like - used during parser tests. */
    	public static final String PROTOTYPE =
    		" {PauseBots False}  {PauseAll False} ";
    
		/**
		 * Creates new instance of command Pause.
		 * 
		Will pause/unpause the game. The game can be paused just if
		bAllowPause = true in GameBots2004.ini (different settings for bots
		and server possible).
	
		 * Corresponding GameBots message for this command is
		 * PAUSE.
		 *
		 * 
		 *    @param PauseBots 
			Iif true only bots will be paused – players and spectators
			will move freely.
		
		 *    @param PauseAll 
			Everyone in the game will be paused if set to true. To
			unpause send PAUSE command with PauseAll set to false.
		
		 */
		public Pause(
			Boolean PauseBots,  Boolean PauseAll
		) {
			
				this.PauseBots = PauseBots;
            
				this.PauseAll = PauseAll;
            
		}

		
			/**
			 * Creates new instance of command Pause.
			 * 
		Will pause/unpause the game. The game can be paused just if
		bAllowPause = true in GameBots2004.ini (different settings for bots
		and server possible).
	
			 * Corresponding GameBots message for this command is
			 * PAUSE.
			 * <p></p>
			 * WARNING: this is empty-command constructor, you have to use setters to fill it up with data that should be sent to GameBots2004!
		     */
		    public Pause() {
		    }
			
		
		/**
		 * Cloning constructor.
		 *
		 * @param original
		 */
		public Pause(Pause original) {
		   
		        this.PauseBots = original.PauseBots;
		   
		        this.PauseAll = original.PauseAll;
		   
		}
    
	        /**
	        
			Iif true only bots will be paused – players and spectators
			will move freely.
		 
	        */
	        protected
	         Boolean PauseBots =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Iif true only bots will be paused – players and spectators
			will move freely.
		 
         */
        public Boolean isPauseBots()
 	
	        {
	            return
	        	 PauseBots;
	        }
	        
	        
	        
 		
 		/**
         * 
			Iif true only bots will be paused – players and spectators
			will move freely.
		 
         */
        public Pause 
        setPauseBots(Boolean PauseBots)
 	
			{
				this.PauseBots = PauseBots;
				return this;
			}
		
	        /**
	        
			Everyone in the game will be paused if set to true. To
			unpause send PAUSE command with PauseAll set to false.
		 
	        */
	        protected
	         Boolean PauseAll =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Everyone in the game will be paused if set to true. To
			unpause send PAUSE command with PauseAll set to false.
		 
         */
        public Boolean isPauseAll()
 	
	        {
	            return
	        	 PauseAll;
	        }
	        
	        
	        
 		
 		/**
         * 
			Everyone in the game will be paused if set to true. To
			unpause send PAUSE command with PauseAll set to false.
		 
         */
        public Pause 
        setPauseAll(Boolean PauseAll)
 	
			{
				this.PauseAll = PauseAll;
				return this;
			}
		
 	    public String toString() {
            return toMessage();
        }
 	
 		public String toHtmlString() {
			return super.toString() + "[<br/>" +
            	
            	"<b>PauseBots</b> = " +
            	String.valueOf(isPauseBots()
 	) +
            	" <br/> " +
            	
            	"<b>PauseAll</b> = " +
            	String.valueOf(isPauseAll()
 	) +
            	" <br/> " +
            	 
            	"<br/>]"
            ;
		}
 	
		public String toMessage() {
     		StringBuffer buf = new StringBuffer();
     		buf.append("PAUSE");
     		
						if (PauseBots != null) {
							buf.append(" {PauseBots " + PauseBots + "}");
						}
					
						if (PauseAll != null) {
							buf.append(" {PauseAll " + PauseAll + "}");
						}
					
   			return buf.toString();
   		}
 	
 		// --- Extra Java from XML BEGIN (extra/code/java)
        	
		// --- Extra Java from XML END (extra/code/java)
 	
	        }
    	