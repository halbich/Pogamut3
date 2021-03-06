
	 	/**
         IMPORTANT !!!

         DO NOT EDIT THIS FILE. IT IS GENERATED FROM approriate xml file in xmlresources/gbcommands BY
         THE JavaClassesGenerator.xslt. MODIFY THAT FILE INSTEAD OF THIS ONE.
         
         Use Ant task process-gb-messages after that to generate .java files again.
         
         IMPORTANT END !!!
        */
 	package cz.cuni.amis.pogamut.ut2004.communication.messages.gbcommands;import java.util.*;import javax.vecmath.*;import cz.cuni.amis.pogamut.base.communication.messages.*;import cz.cuni.amis.pogamut.base.communication.worldview.*;import cz.cuni.amis.pogamut.base.communication.worldview.event.*;import cz.cuni.amis.pogamut.base.communication.worldview.object.*;import cz.cuni.amis.pogamut.multi.communication.worldview.object.*;import cz.cuni.amis.pogamut.base.communication.translator.event.*;import cz.cuni.amis.pogamut.multi.communication.translator.event.*;import cz.cuni.amis.pogamut.base3d.worldview.object.*;import cz.cuni.amis.pogamut.base3d.worldview.object.event.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.*;import cz.cuni.amis.pogamut.ut2004.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004.communication.translator.itemdescriptor.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.ItemType.Category;import cz.cuni.amis.pogamut.unreal.communication.messages.UnrealId;import cz.cuni.amis.utils.exception.*;import cz.cuni.amis.pogamut.base.communication.translator.event.IWorldObjectUpdateResult.Result;import cz.cuni.amis.utils.SafeEquals;import cz.cuni.amis.pogamut.base.agent.*;import cz.cuni.amis.pogamut.multi.agent.*;import cz.cuni.amis.pogamut.multi.communication.worldview.property.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.property.*;import cz.cuni.amis.utils.token.*;import cz.cuni.amis.utils.*;
 		/**
 		 * Representation of the GameBots2004 command RESPAWN.
 		 *
 		 * 
		Use this to kill bot and force him to respawn, you can specify
		start location and rotation. Work also for human players and
		spectators in the game. This command can be issued also by bot
		on the bot itself (in this case Id attribute is not parsed).
	
         */
 	public class Respawn 
		extends CommandMessage
	        {
	        	
		        
    	/** Example how the message looks like - used during parser tests. */
    	public static final String PROTOTYPE =
    		" {Id unreal_id}  {StartLocation 0,0,0}  {StartRotation 0,0,0} ";
    
		/**
		 * Creates new instance of command Respawn.
		 * 
		Use this to kill bot and force him to respawn, you can specify
		start location and rotation. Work also for human players and
		spectators in the game. This command can be issued also by bot
		on the bot itself (in this case Id attribute is not parsed).
	
		 * Corresponding GameBots message for this command is
		 * RESPAWN.
		 *
		 * 
		 *    @param Id 
			Id of the bot to be respawned (not used if command sent to
			bot).
		
		 *    @param StartLocation 
			Where bot respawns. If you want to respawn bot at random,
			don't specify StartLocation.
		
		 *    @param StartRotation Initial rotation of the bot.
		 */
		public Respawn(
			UnrealId Id,  Location StartLocation,  Rotation StartRotation
		) {
			
				this.Id = Id;
            
				this.StartLocation = StartLocation;
            
				this.StartRotation = StartRotation;
            
		}

		
			/**
			 * Creates new instance of command Respawn.
			 * 
		Use this to kill bot and force him to respawn, you can specify
		start location and rotation. Work also for human players and
		spectators in the game. This command can be issued also by bot
		on the bot itself (in this case Id attribute is not parsed).
	
			 * Corresponding GameBots message for this command is
			 * RESPAWN.
			 * <p></p>
			 * WARNING: this is empty-command constructor, you have to use setters to fill it up with data that should be sent to GameBots2004!
		     */
		    public Respawn() {
		    }
			
		
		/**
		 * Cloning constructor.
		 *
		 * @param original
		 */
		public Respawn(Respawn original) {
		   
		        this.Id = original.Id;
		   
		        this.StartLocation = original.StartLocation;
		   
		        this.StartRotation = original.StartRotation;
		   
		}
    
	        /**
	        
			Id of the bot to be respawned (not used if command sent to
			bot).
		 
	        */
	        protected
	         UnrealId Id =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Id of the bot to be respawned (not used if command sent to
			bot).
		 
         */
        public UnrealId getId()
 	
	        {
	            return
	        	 Id;
	        }
	        
	        
	        
 		
 		/**
         * 
			Id of the bot to be respawned (not used if command sent to
			bot).
		 
         */
        public Respawn 
        setId(UnrealId Id)
 	
			{
				this.Id = Id;
				return this;
			}
		
	        /**
	        
			Where bot respawns. If you want to respawn bot at random,
			don't specify StartLocation.
		 
	        */
	        protected
	         Location StartLocation =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Where bot respawns. If you want to respawn bot at random,
			don't specify StartLocation.
		 
         */
        public Location getStartLocation()
 	
	        {
	            return
	        	 StartLocation;
	        }
	        
	        
	        
 		
 		/**
         * 
			Where bot respawns. If you want to respawn bot at random,
			don't specify StartLocation.
		 
         */
        public Respawn 
        setStartLocation(Location StartLocation)
 	
			{
				this.StartLocation = StartLocation;
				return this;
			}
		
	        /**
	        Initial rotation of the bot. 
	        */
	        protected
	         Rotation StartRotation =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * Initial rotation of the bot. 
         */
        public Rotation getStartRotation()
 	
	        {
	            return
	        	 StartRotation;
	        }
	        
	        
	        
 		
 		/**
         * Initial rotation of the bot. 
         */
        public Respawn 
        setStartRotation(Rotation StartRotation)
 	
			{
				this.StartRotation = StartRotation;
				return this;
			}
		
 	    public String toString() {
            return toMessage();
        }
 	
 		public String toHtmlString() {
			return super.toString() + "[<br/>" +
            	
            	"<b>Id</b> = " +
            	String.valueOf(getId()
 	) +
            	" <br/> " +
            	
            	"<b>StartLocation</b> = " +
            	String.valueOf(getStartLocation()
 	) +
            	" <br/> " +
            	
            	"<b>StartRotation</b> = " +
            	String.valueOf(getStartRotation()
 	) +
            	" <br/> " +
            	 
            	"<br/>]"
            ;
		}
 	
		public String toMessage() {
     		StringBuffer buf = new StringBuffer();
     		buf.append("RESPAWN");
     		
						if (Id != null) {
							buf.append(" {Id " + Id.getStringId() + "}");
						}
					
					    if (StartLocation != null) {
					        buf.append(" {StartLocation " +
					            StartLocation.getX() + "," +
					            StartLocation.getY() + "," +
					            StartLocation.getZ() + "}");
					    }
					
					    if (StartRotation != null) {
					        buf.append(" {StartRotation " +
					            StartRotation.getPitch() + "," +
					            StartRotation.getYaw() + "," +
					            StartRotation.getRoll() + "}");
					    }
					
   			return buf.toString();
   		}
 	
 		// --- Extra Java from XML BEGIN (extra/code/java)
        	
		// --- Extra Java from XML END (extra/code/java)
 	
	        }
    	