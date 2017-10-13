
	 	/**
         IMPORTANT !!!

         DO NOT EDIT THIS FILE. IT IS GENERATED FROM approriate xml file in xmlresources/gbcommands BY
         THE JavaClassesGenerator.xslt. MODIFY THAT FILE INSTEAD OF THIS ONE.
         
         Use Ant task process-gb-messages after that to generate .java files again.
         
         IMPORTANT END !!!
        */
 	package cz.cuni.amis.pogamut.ut2004.communication.messages.gbcommands;import java.util.*;import javax.vecmath.*;import cz.cuni.amis.pogamut.base.communication.messages.*;import cz.cuni.amis.pogamut.base.communication.worldview.*;import cz.cuni.amis.pogamut.base.communication.worldview.event.*;import cz.cuni.amis.pogamut.base.communication.worldview.object.*;import cz.cuni.amis.pogamut.multi.communication.worldview.object.*;import cz.cuni.amis.pogamut.base.communication.translator.event.*;import cz.cuni.amis.pogamut.multi.communication.translator.event.*;import cz.cuni.amis.pogamut.base3d.worldview.object.*;import cz.cuni.amis.pogamut.base3d.worldview.object.event.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.*;import cz.cuni.amis.pogamut.ut2004.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004.communication.translator.itemdescriptor.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.ItemType.Category;import cz.cuni.amis.pogamut.unreal.communication.messages.UnrealId;import cz.cuni.amis.utils.exception.*;import cz.cuni.amis.pogamut.base.communication.translator.event.IWorldObjectUpdateResult.Result;import cz.cuni.amis.utils.SafeEquals;import cz.cuni.amis.pogamut.base.agent.*;import cz.cuni.amis.pogamut.multi.agent.*;import cz.cuni.amis.pogamut.multi.communication.worldview.property.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.property.*;import cz.cuni.amis.utils.token.*;import cz.cuni.amis.utils.*;
 		/**
 		 * Representation of the GameBots2004 command CHANGEWEAPON.
 		 *
 		 * Switch the bots weapon to another.
         */
 	public class ChangeWeapon 
		extends CommandMessage
	        {
	        	
		        
    	/** Example how the message looks like - used during parser tests. */
    	public static final String PROTOTYPE =
    		" {Id text} ";
    
		/**
		 * Creates new instance of command ChangeWeapon.
		 * Switch the bots weapon to another.
		 * Corresponding GameBots message for this command is
		 * CHANGEWEAPON.
		 *
		 * 
		 *    @param Id 
			Unique Id of weapon to switch to. If you just send "Best" as
			Id, the server will pick your best weapon that still has
			ammo for you. Obtain Unique Id's from AIN messages. Be
			carefull, the weapon Id in bots inventory is different from
			the Ids of weapons that are lying in the map!
		
		 */
		public ChangeWeapon(
			String Id
		) {
			
				this.Id = Id;
            
		}

		
			/**
			 * Creates new instance of command ChangeWeapon.
			 * Switch the bots weapon to another.
			 * Corresponding GameBots message for this command is
			 * CHANGEWEAPON.
			 * <p></p>
			 * WARNING: this is empty-command constructor, you have to use setters to fill it up with data that should be sent to GameBots2004!
		     */
		    public ChangeWeapon() {
		    }
			
		
		/**
		 * Cloning constructor.
		 *
		 * @param original
		 */
		public ChangeWeapon(ChangeWeapon original) {
		   
		        this.Id = original.Id;
		   
		}
    
	        /**
	        
			Unique Id of weapon to switch to. If you just send "Best" as
			Id, the server will pick your best weapon that still has
			ammo for you. Obtain Unique Id's from AIN messages. Be
			carefull, the weapon Id in bots inventory is different from
			the Ids of weapons that are lying in the map!
		 
	        */
	        protected
	         String Id =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Unique Id of weapon to switch to. If you just send "Best" as
			Id, the server will pick your best weapon that still has
			ammo for you. Obtain Unique Id's from AIN messages. Be
			carefull, the weapon Id in bots inventory is different from
			the Ids of weapons that are lying in the map!
		 
         */
        public String getId()
 	
	        {
	            return
	        	 Id;
	        }
	        
	        
	        
 		
 		/**
         * 
			Unique Id of weapon to switch to. If you just send "Best" as
			Id, the server will pick your best weapon that still has
			ammo for you. Obtain Unique Id's from AIN messages. Be
			carefull, the weapon Id in bots inventory is different from
			the Ids of weapons that are lying in the map!
		 
         */
        public ChangeWeapon 
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
            	
            	"<b>Id</b> = " +
            	String.valueOf(getId()
 	) +
            	" <br/> " +
            	 
            	"<br/>]"
            ;
		}
 	
		public String toMessage() {
     		StringBuffer buf = new StringBuffer();
     		buf.append("CHANGEWEAPON");
     		
						if (Id != null) {
							buf.append(" {Id " + Id + "}");
						}
					
   			return buf.toString();
   		}
 	
 		// --- Extra Java from XML BEGIN (extra/code/java)
        	
		// --- Extra Java from XML END (extra/code/java)
 	
	        }
    	