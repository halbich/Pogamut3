
	 	/**
         IMPORTANT !!!

         DO NOT EDIT THIS FILE. IT IS GENERATED FROM approriate xml file in xmlresources/gbcommands BY
         THE JavaClassesGenerator.xslt. MODIFY THAT FILE INSTEAD OF THIS ONE.
         
         Use Ant task process-gb-messages after that to generate .java files again.
         
         IMPORTANT END !!!
        */
 	package cz.cuni.amis.pogamut.ut2004.communication.messages.gbcommands;import java.util.*;import javax.vecmath.*;import cz.cuni.amis.pogamut.base.communication.messages.*;import cz.cuni.amis.pogamut.base.communication.worldview.*;import cz.cuni.amis.pogamut.base.communication.worldview.event.*;import cz.cuni.amis.pogamut.base.communication.worldview.object.*;import cz.cuni.amis.pogamut.multi.communication.worldview.object.*;import cz.cuni.amis.pogamut.base.communication.translator.event.*;import cz.cuni.amis.pogamut.multi.communication.translator.event.*;import cz.cuni.amis.pogamut.base3d.worldview.object.*;import cz.cuni.amis.pogamut.base3d.worldview.object.event.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.*;import cz.cuni.amis.pogamut.ut2004.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004.communication.translator.itemdescriptor.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.ItemType.Category;import cz.cuni.amis.pogamut.unreal.communication.messages.UnrealId;import cz.cuni.amis.utils.exception.*;import cz.cuni.amis.pogamut.base.communication.translator.event.IWorldObjectUpdateResult.Result;import cz.cuni.amis.utils.SafeEquals;import cz.cuni.amis.pogamut.base.agent.*;import cz.cuni.amis.pogamut.multi.agent.*;import cz.cuni.amis.pogamut.multi.communication.worldview.property.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.property.*;import cz.cuni.amis.utils.token.*;import cz.cuni.amis.utils.*;
 		/**
 		 * Representation of the GameBots2004 command DLGEND.
 		 *
 		 * 
		Ends a batch of DialogItem commands, showing the dialog on screen.
	
         */
 	public class DialogEnd 
		extends CommandMessage
	        {
	        	
		        
    	/** Example how the message looks like - used during parser tests. */
    	public static final String PROTOTYPE =
    		" {Player text}  {Id text} ";
    
		/**
		 * Creates new instance of command DialogEnd.
		 * 
		Ends a batch of DialogItem commands, showing the dialog on screen.
	
		 * Corresponding GameBots message for this command is
		 * DLGEND.
		 *
		 * 
		 *    @param Player Name of the player on who's HUD should be the dialog shown.
		 *    @param Id Dialog identification. This should be an unique positive integer.
		 */
		public DialogEnd(
			String Player,  String Id
		) {
			
				this.Player = Player;
            
				this.Id = Id;
            
		}

		
			/**
			 * Creates new instance of command DialogEnd.
			 * 
		Ends a batch of DialogItem commands, showing the dialog on screen.
	
			 * Corresponding GameBots message for this command is
			 * DLGEND.
			 * <p></p>
			 * WARNING: this is empty-command constructor, you have to use setters to fill it up with data that should be sent to GameBots2004!
		     */
		    public DialogEnd() {
		    }
			
		
		/**
		 * Cloning constructor.
		 *
		 * @param original
		 */
		public DialogEnd(DialogEnd original) {
		   
		        this.Player = original.Player;
		   
		        this.Id = original.Id;
		   
		}
    
	        /**
	        Name of the player on who's HUD should be the dialog shown. 
	        */
	        protected
	         String Player =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * Name of the player on who's HUD should be the dialog shown. 
         */
        public String getPlayer()
 	
	        {
	            return
	        	 Player;
	        }
	        
	        
	        
 		
 		/**
         * Name of the player on who's HUD should be the dialog shown. 
         */
        public DialogEnd 
        setPlayer(String Player)
 	
			{
				this.Player = Player;
				return this;
			}
		
	        /**
	        Dialog identification. This should be an unique positive integer. 
	        */
	        protected
	         String Id =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * Dialog identification. This should be an unique positive integer. 
         */
        public String getId()
 	
	        {
	            return
	        	 Id;
	        }
	        
	        
	        
 		
 		/**
         * Dialog identification. This should be an unique positive integer. 
         */
        public DialogEnd 
        setId(String Id)
 	
			{
				this.Id = Id;
				return this;
			}
		
 	    public String toString() {
            return toMessage();
        }
 	
 		public String toHtmlString() {
			return super.toString() + "[<br/>" +
            	
            	"<b>Player</b> = " +
            	String.valueOf(getPlayer()
 	) +
            	" <br/> " +
            	
            	"<b>Id</b> = " +
            	String.valueOf(getId()
 	) +
            	" <br/> " +
            	 
            	"<br/>]"
            ;
		}
 	
		public String toMessage() {
     		StringBuffer buf = new StringBuffer();
     		buf.append("DLGEND");
     		
						if (Player != null) {
							buf.append(" {Player " + Player + "}");
						}
					
						if (Id != null) {
							buf.append(" {Id " + Id + "}");
						}
					
   			return buf.toString();
   		}
 	
 		// --- Extra Java from XML BEGIN (extra/code/java)
        	
		// --- Extra Java from XML END (extra/code/java)
 	
	        }
    	