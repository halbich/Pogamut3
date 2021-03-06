
	 	/**
         IMPORTANT !!!

         DO NOT EDIT THIS FILE. IT IS GENERATED FROM approriate xml file in xmlresources/gbcommands BY
         THE JavaClassesGenerator.xslt. MODIFY THAT FILE INSTEAD OF THIS ONE.
         
         Use Ant task process-gb-messages after that to generate .java files again.
         
         IMPORTANT END !!!
        */
 	package cz.cuni.amis.pogamut.ut2004.communication.messages.gbcommands;import java.util.*;import javax.vecmath.*;import cz.cuni.amis.pogamut.base.communication.messages.*;import cz.cuni.amis.pogamut.base.communication.worldview.*;import cz.cuni.amis.pogamut.base.communication.worldview.event.*;import cz.cuni.amis.pogamut.base.communication.worldview.object.*;import cz.cuni.amis.pogamut.multi.communication.worldview.object.*;import cz.cuni.amis.pogamut.base.communication.translator.event.*;import cz.cuni.amis.pogamut.multi.communication.translator.event.*;import cz.cuni.amis.pogamut.base3d.worldview.object.*;import cz.cuni.amis.pogamut.base3d.worldview.object.event.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.*;import cz.cuni.amis.pogamut.ut2004.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004.communication.translator.itemdescriptor.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.ItemType.Category;import cz.cuni.amis.pogamut.unreal.communication.messages.UnrealId;import cz.cuni.amis.utils.exception.*;import cz.cuni.amis.pogamut.base.communication.translator.event.IWorldObjectUpdateResult.Result;import cz.cuni.amis.utils.SafeEquals;import cz.cuni.amis.pogamut.base.agent.*;import cz.cuni.amis.pogamut.multi.agent.*;import cz.cuni.amis.pogamut.multi.communication.worldview.property.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.property.*;import cz.cuni.amis.utils.token.*;import cz.cuni.amis.utils.*;
 		/**
 		 * Representation of the GameBots2004 command SETSKIN.
 		 *
 		 * 
		Sets the bot current skin through Skin attribute (e.g. SETSKIN
		{Skin "HumanMaleA.MercMaleA"}). Find all packages and skins
		through unrealEd (Actor browser, search in UT2004/Animations
		folder). Supported bot skins are Aliens (Aliens.), Bots (Bot.),
		human males (HumanMaleA.), human females (HumanFemaleA. ),
		juggernauts (Jugg.). Skaarj skins are not supported at the time
		being.
		In UnrealRuntime we use different attributes to set up textures of 
		the bots.
	
         */
 	public class SetSkin 
		extends CommandMessage
	        {
	        	
		        
    	/** Example how the message looks like - used during parser tests. */
    	public static final String PROTOTYPE =
    		" {Skin text}  {URHair text}  {URClothes text}  {URSkin text} ";
    
		/**
		 * Creates new instance of command SetSkin.
		 * 
		Sets the bot current skin through Skin attribute (e.g. SETSKIN
		{Skin "HumanMaleA.MercMaleA"}). Find all packages and skins
		through unrealEd (Actor browser, search in UT2004/Animations
		folder). Supported bot skins are Aliens (Aliens.), Bots (Bot.),
		human males (HumanMaleA.), human females (HumanFemaleA. ),
		juggernauts (Jugg.). Skaarj skins are not supported at the time
		being.
		In UnrealRuntime we use different attributes to set up textures of 
		the bots.
	
		 * Corresponding GameBots message for this command is
		 * SETSKIN.
		 *
		 * 
		 *    @param Skin Holds the desired skin.
		 *    @param URHair A number representing hair. String type is desired here. Parsed only in UnrealRuntime.
		 *    @param URClothes A number representing clothes. String type is desired here. Parsed only in UnrealRuntime.
		 *    @param URSkin A number representing skin. String type is desired here. Parsed only in UnrealRuntime.
		 */
		public SetSkin(
			String Skin,  String URHair,  String URClothes,  String URSkin
		) {
			
				this.Skin = Skin;
            
				this.URHair = URHair;
            
				this.URClothes = URClothes;
            
				this.URSkin = URSkin;
            
		}

		
			/**
			 * Creates new instance of command SetSkin.
			 * 
		Sets the bot current skin through Skin attribute (e.g. SETSKIN
		{Skin "HumanMaleA.MercMaleA"}). Find all packages and skins
		through unrealEd (Actor browser, search in UT2004/Animations
		folder). Supported bot skins are Aliens (Aliens.), Bots (Bot.),
		human males (HumanMaleA.), human females (HumanFemaleA. ),
		juggernauts (Jugg.). Skaarj skins are not supported at the time
		being.
		In UnrealRuntime we use different attributes to set up textures of 
		the bots.
	
			 * Corresponding GameBots message for this command is
			 * SETSKIN.
			 * <p></p>
			 * WARNING: this is empty-command constructor, you have to use setters to fill it up with data that should be sent to GameBots2004!
		     */
		    public SetSkin() {
		    }
			
		
		/**
		 * Cloning constructor.
		 *
		 * @param original
		 */
		public SetSkin(SetSkin original) {
		   
		        this.Skin = original.Skin;
		   
		        this.URHair = original.URHair;
		   
		        this.URClothes = original.URClothes;
		   
		        this.URSkin = original.URSkin;
		   
		}
    
	        /**
	        Holds the desired skin. 
	        */
	        protected
	         String Skin =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * Holds the desired skin. 
         */
        public String getSkin()
 	
	        {
	            return
	        	 Skin;
	        }
	        
	        
	        
 		
 		/**
         * Holds the desired skin. 
         */
        public SetSkin 
        setSkin(String Skin)
 	
			{
				this.Skin = Skin;
				return this;
			}
		
	        /**
	        A number representing hair. String type is desired here. Parsed only in UnrealRuntime. 
	        */
	        protected
	         String URHair =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * A number representing hair. String type is desired here. Parsed only in UnrealRuntime. 
         */
        public String getURHair()
 	
	        {
	            return
	        	 URHair;
	        }
	        
	        
	        
 		
 		/**
         * A number representing hair. String type is desired here. Parsed only in UnrealRuntime. 
         */
        public SetSkin 
        setURHair(String URHair)
 	
			{
				this.URHair = URHair;
				return this;
			}
		
	        /**
	        A number representing clothes. String type is desired here. Parsed only in UnrealRuntime. 
	        */
	        protected
	         String URClothes =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * A number representing clothes. String type is desired here. Parsed only in UnrealRuntime. 
         */
        public String getURClothes()
 	
	        {
	            return
	        	 URClothes;
	        }
	        
	        
	        
 		
 		/**
         * A number representing clothes. String type is desired here. Parsed only in UnrealRuntime. 
         */
        public SetSkin 
        setURClothes(String URClothes)
 	
			{
				this.URClothes = URClothes;
				return this;
			}
		
	        /**
	        A number representing skin. String type is desired here. Parsed only in UnrealRuntime. 
	        */
	        protected
	         String URSkin =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * A number representing skin. String type is desired here. Parsed only in UnrealRuntime. 
         */
        public String getURSkin()
 	
	        {
	            return
	        	 URSkin;
	        }
	        
	        
	        
 		
 		/**
         * A number representing skin. String type is desired here. Parsed only in UnrealRuntime. 
         */
        public SetSkin 
        setURSkin(String URSkin)
 	
			{
				this.URSkin = URSkin;
				return this;
			}
		
 	    public String toString() {
            return toMessage();
        }
 	
 		public String toHtmlString() {
			return super.toString() + "[<br/>" +
            	
            	"<b>Skin</b> = " +
            	String.valueOf(getSkin()
 	) +
            	" <br/> " +
            	
            	"<b>URHair</b> = " +
            	String.valueOf(getURHair()
 	) +
            	" <br/> " +
            	
            	"<b>URClothes</b> = " +
            	String.valueOf(getURClothes()
 	) +
            	" <br/> " +
            	
            	"<b>URSkin</b> = " +
            	String.valueOf(getURSkin()
 	) +
            	" <br/> " +
            	 
            	"<br/>]"
            ;
		}
 	
		public String toMessage() {
     		StringBuffer buf = new StringBuffer();
     		buf.append("SETSKIN");
     		
						if (Skin != null) {
							buf.append(" {Skin " + Skin + "}");
						}
					
						if (URHair != null) {
							buf.append(" {URHair " + URHair + "}");
						}
					
						if (URClothes != null) {
							buf.append(" {URClothes " + URClothes + "}");
						}
					
						if (URSkin != null) {
							buf.append(" {URSkin " + URSkin + "}");
						}
					
   			return buf.toString();
   		}
 	
 		// --- Extra Java from XML BEGIN (extra/code/java)
        	
		// --- Extra Java from XML END (extra/code/java)
 	
	        }
    	