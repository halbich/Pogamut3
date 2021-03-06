
	 	/**
         IMPORTANT !!!

         DO NOT EDIT THIS FILE. IT IS GENERATED FROM approriate xml file in xmlresources/gbcommands BY
         THE JavaClassesGenerator.xslt. MODIFY THAT FILE INSTEAD OF THIS ONE.
         
         Use Ant task process-gb-messages after that to generate .java files again.
         
         IMPORTANT END !!!
        */
 	package cz.cuni.amis.pogamut.ut2004.communication.messages.gbcommands;import java.util.*;import javax.vecmath.*;import cz.cuni.amis.pogamut.base.communication.messages.*;import cz.cuni.amis.pogamut.base.communication.worldview.*;import cz.cuni.amis.pogamut.base.communication.worldview.event.*;import cz.cuni.amis.pogamut.base.communication.worldview.object.*;import cz.cuni.amis.pogamut.multi.communication.worldview.object.*;import cz.cuni.amis.pogamut.base.communication.translator.event.*;import cz.cuni.amis.pogamut.multi.communication.translator.event.*;import cz.cuni.amis.pogamut.base3d.worldview.object.*;import cz.cuni.amis.pogamut.base3d.worldview.object.event.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.*;import cz.cuni.amis.pogamut.ut2004.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004.communication.translator.itemdescriptor.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.ItemType.Category;import cz.cuni.amis.pogamut.unreal.communication.messages.UnrealId;import cz.cuni.amis.utils.exception.*;import cz.cuni.amis.pogamut.base.communication.translator.event.IWorldObjectUpdateResult.Result;import cz.cuni.amis.utils.SafeEquals;import cz.cuni.amis.pogamut.base.agent.*;import cz.cuni.amis.pogamut.multi.agent.*;import cz.cuni.amis.pogamut.multi.communication.worldview.property.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.property.*;import cz.cuni.amis.utils.token.*;import cz.cuni.amis.utils.*;
 		/**
 		 * Representation of the GameBots2004 command INIT.
 		 *
 		 * 
		Message you'll send at the beginning of the communication to
		create a bot in the game. You must send this message before you
		can have a character to play in the game.
	
         */
 	public class Initialize 
		extends CommandMessage
	        {
	        	
		        
    	/** Example how the message looks like - used during parser tests. */
    	public static final String PROTOTYPE =
    		" {Name text}  {Team 0}  {ManualSpawn False}  {AutoTrace False}  {Location 0,0,0}  {Rotation 0,0,0}  {Skin text}  {DesiredSkill 0}  {ShouldLeadTarget False}  {AutoPickupOff False}  {NewSelfBatchProtocol False}  {Jmx text}  {ClassName text} ";
    
		/**
		 * Creates new instance of command Initialize.
		 * 
		Message you'll send at the beginning of the communication to
		create a bot in the game. You must send this message before you
		can have a character to play in the game.
	
		 * Corresponding GameBots message for this command is
		 * INIT.
		 *
		 * 
		 *    @param Name Desired name.
		 *    @param Team 
			Preferred team. If illegal or no team provided, one will be
			provided for you. Normally a team game has team 0 and team
			1. In BotDeathMatch, team is meaningless.
		
		 *    @param ManualSpawn 
			Sets if the bot wil have tol be respawned after death
			manually by RESPAWN command. If false, the bot will respawn
			automatically.
		
		 *    @param AutoTrace 
			Enables/disables auto ray tracing feature.
		
		 *    @param Location 
			Specify start location, if unspecified, then random.
		
		 *    @param Rotation 
			Specify start rotation, if unspecified, then random.
		
		 *    @param Skin 
			Sets the bot current skin (e.g. "HumanMaleA.MercMaleA").
			Find all packages and skins through unrealEd (Actor browser,
			search in UT2004/Animations folder). Supported bot skins are
			Aliens (Aliens.), Bots (Bot.), human males (HumanMaleA.),
			human females (HumanFemaleA. ), juggernauts (Jugg.). Skaarj
			skins are not supported at the time being.
		
		 *    @param DesiredSkill 
			Can range from 0 to 7. This sets the bot accuracy. 1 lowest,
			7 highest. Shouldn't have any other effect.
		
		 *    @param ShouldLeadTarget 
			When firing slow projectiles (missiles...), if the engine
			will try to count the impact point for the bot or not (when
			shooting at moving targets).
		
		 *    @param AutoPickupOff 
			It enables/disables automatic pickup of the bot. If true the items can be picked up through PICK command.
		
		 *    @param NewSelfBatchProtocol 
			If set to true new synchronous batch procotol will be used. Sync. batches will be sent
			more regularly containing SELF message only (based on SelfUpdateTime value). Visibility
			information will be still sent in synchronous batches, but not always, but each VisionTime.
			VisionTime now needs to be multiple of SelfUpdateTime and will be set to nearest multiple if 
			set improperly. BEG and END message will contain new attribute VisUpdate telling if visibility
			update is sent in this batch or not.
		
		 *    @param Jmx 
			Sets the jmx adress we can use to debug the bot from Java (if we are using Pogamut).
		
		 *    @param ClassName 
			Sets the Unreal Tournament class of the bot that should be used. By default leave it blank. In UnrealRuntime2 it may be used to spawn bots of different class,
			see CharacterType class in project PogamutEmohawk project.
		
		 */
		public Initialize(
			String Name,  Integer Team,  Boolean ManualSpawn,  Boolean AutoTrace,  Location Location,  Rotation Rotation,  String Skin,  Integer DesiredSkill,  Boolean ShouldLeadTarget,  Boolean AutoPickupOff,  Boolean NewSelfBatchProtocol,  String Jmx,  String ClassName
		) {
			
				this.Name = Name;
            
				this.Team = Team;
            
				this.ManualSpawn = ManualSpawn;
            
				this.AutoTrace = AutoTrace;
            
				this.Location = Location;
            
				this.Rotation = Rotation;
            
				this.Skin = Skin;
            
				this.DesiredSkill = DesiredSkill;
            
				this.ShouldLeadTarget = ShouldLeadTarget;
            
				this.AutoPickupOff = AutoPickupOff;
            
				this.NewSelfBatchProtocol = NewSelfBatchProtocol;
            
				this.Jmx = Jmx;
            
				this.ClassName = ClassName;
            
		}

		
			/**
			 * Creates new instance of command Initialize.
			 * 
		Message you'll send at the beginning of the communication to
		create a bot in the game. You must send this message before you
		can have a character to play in the game.
	
			 * Corresponding GameBots message for this command is
			 * INIT.
			 * <p></p>
			 * WARNING: this is empty-command constructor, you have to use setters to fill it up with data that should be sent to GameBots2004!
		     */
		    public Initialize() {
		    }
			
		
		/**
		 * Cloning constructor.
		 *
		 * @param original
		 */
		public Initialize(Initialize original) {
		   
		        this.Name = original.Name;
		   
		        this.Team = original.Team;
		   
		        this.ManualSpawn = original.ManualSpawn;
		   
		        this.AutoTrace = original.AutoTrace;
		   
		        this.Location = original.Location;
		   
		        this.Rotation = original.Rotation;
		   
		        this.Skin = original.Skin;
		   
		        this.DesiredSkill = original.DesiredSkill;
		   
		        this.ShouldLeadTarget = original.ShouldLeadTarget;
		   
		        this.AutoPickupOff = original.AutoPickupOff;
		   
		        this.NewSelfBatchProtocol = original.NewSelfBatchProtocol;
		   
		        this.Jmx = original.Jmx;
		   
		        this.ClassName = original.ClassName;
		   
		}
    
	        /**
	        Desired name. 
	        */
	        protected
	         String Name =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * Desired name. 
         */
        public String getName()
 	
	        {
	            return
	        	 Name;
	        }
	        
	        
	        
 		
 		/**
         * Desired name. 
         */
        public Initialize 
        setName(String Name)
 	
			{
				this.Name = Name;
				return this;
			}
		
	        /**
	        
			Preferred team. If illegal or no team provided, one will be
			provided for you. Normally a team game has team 0 and team
			1. In BotDeathMatch, team is meaningless.
		 
	        */
	        protected
	         Integer Team =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Preferred team. If illegal or no team provided, one will be
			provided for you. Normally a team game has team 0 and team
			1. In BotDeathMatch, team is meaningless.
		 
         */
        public Integer getTeam()
 	
	        {
	            return
	        	 Team;
	        }
	        
	        
	        
 		
 		/**
         * 
			Preferred team. If illegal or no team provided, one will be
			provided for you. Normally a team game has team 0 and team
			1. In BotDeathMatch, team is meaningless.
		 
         */
        public Initialize 
        setTeam(Integer Team)
 	
			{
				this.Team = Team;
				return this;
			}
		
	        /**
	        
			Sets if the bot wil have tol be respawned after death
			manually by RESPAWN command. If false, the bot will respawn
			automatically.
		 
	        */
	        protected
	         Boolean ManualSpawn =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Sets if the bot wil have tol be respawned after death
			manually by RESPAWN command. If false, the bot will respawn
			automatically.
		 
         */
        public Boolean isManualSpawn()
 	
	        {
	            return
	        	 ManualSpawn;
	        }
	        
	        
	        
 		
 		/**
         * 
			Sets if the bot wil have tol be respawned after death
			manually by RESPAWN command. If false, the bot will respawn
			automatically.
		 
         */
        public Initialize 
        setManualSpawn(Boolean ManualSpawn)
 	
			{
				this.ManualSpawn = ManualSpawn;
				return this;
			}
		
	        /**
	        
			Enables/disables auto ray tracing feature.
		 
	        */
	        protected
	         Boolean AutoTrace =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Enables/disables auto ray tracing feature.
		 
         */
        public Boolean isAutoTrace()
 	
	        {
	            return
	        	 AutoTrace;
	        }
	        
	        
	        
 		
 		/**
         * 
			Enables/disables auto ray tracing feature.
		 
         */
        public Initialize 
        setAutoTrace(Boolean AutoTrace)
 	
			{
				this.AutoTrace = AutoTrace;
				return this;
			}
		
	        /**
	        
			Specify start location, if unspecified, then random.
		 
	        */
	        protected
	         Location Location =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Specify start location, if unspecified, then random.
		 
         */
        public Location getLocation()
 	
	        {
	            return
	        	 Location;
	        }
	        
	        
	        
 		
 		/**
         * 
			Specify start location, if unspecified, then random.
		 
         */
        public Initialize 
        setLocation(Location Location)
 	
			{
				this.Location = Location;
				return this;
			}
		
	        /**
	        
			Specify start rotation, if unspecified, then random.
		 
	        */
	        protected
	         Rotation Rotation =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Specify start rotation, if unspecified, then random.
		 
         */
        public Rotation getRotation()
 	
	        {
	            return
	        	 Rotation;
	        }
	        
	        
	        
 		
 		/**
         * 
			Specify start rotation, if unspecified, then random.
		 
         */
        public Initialize 
        setRotation(Rotation Rotation)
 	
			{
				this.Rotation = Rotation;
				return this;
			}
		
	        /**
	        
			Sets the bot current skin (e.g. "HumanMaleA.MercMaleA").
			Find all packages and skins through unrealEd (Actor browser,
			search in UT2004/Animations folder). Supported bot skins are
			Aliens (Aliens.), Bots (Bot.), human males (HumanMaleA.),
			human females (HumanFemaleA. ), juggernauts (Jugg.). Skaarj
			skins are not supported at the time being.
		 
	        */
	        protected
	         String Skin =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Sets the bot current skin (e.g. "HumanMaleA.MercMaleA").
			Find all packages and skins through unrealEd (Actor browser,
			search in UT2004/Animations folder). Supported bot skins are
			Aliens (Aliens.), Bots (Bot.), human males (HumanMaleA.),
			human females (HumanFemaleA. ), juggernauts (Jugg.). Skaarj
			skins are not supported at the time being.
		 
         */
        public String getSkin()
 	
	        {
	            return
	        	 Skin;
	        }
	        
	        
	        
 		
 		/**
         * 
			Sets the bot current skin (e.g. "HumanMaleA.MercMaleA").
			Find all packages and skins through unrealEd (Actor browser,
			search in UT2004/Animations folder). Supported bot skins are
			Aliens (Aliens.), Bots (Bot.), human males (HumanMaleA.),
			human females (HumanFemaleA. ), juggernauts (Jugg.). Skaarj
			skins are not supported at the time being.
		 
         */
        public Initialize 
        setSkin(String Skin)
 	
			{
				this.Skin = Skin;
				return this;
			}
		
	        /**
	        
			Can range from 0 to 7. This sets the bot accuracy. 1 lowest,
			7 highest. Shouldn't have any other effect.
		 
	        */
	        protected
	         Integer DesiredSkill =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Can range from 0 to 7. This sets the bot accuracy. 1 lowest,
			7 highest. Shouldn't have any other effect.
		 
         */
        public Integer getDesiredSkill()
 	
	        {
	            return
	        	 DesiredSkill;
	        }
	        
	        
	        
 		
 		/**
         * 
			Can range from 0 to 7. This sets the bot accuracy. 1 lowest,
			7 highest. Shouldn't have any other effect.
		 
         */
        public Initialize 
        setDesiredSkill(Integer DesiredSkill)
 	
			{
				this.DesiredSkill = DesiredSkill;
				return this;
			}
		
	        /**
	        
			When firing slow projectiles (missiles...), if the engine
			will try to count the impact point for the bot or not (when
			shooting at moving targets).
		 
	        */
	        protected
	         Boolean ShouldLeadTarget =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			When firing slow projectiles (missiles...), if the engine
			will try to count the impact point for the bot or not (when
			shooting at moving targets).
		 
         */
        public Boolean isShouldLeadTarget()
 	
	        {
	            return
	        	 ShouldLeadTarget;
	        }
	        
	        
	        
 		
 		/**
         * 
			When firing slow projectiles (missiles...), if the engine
			will try to count the impact point for the bot or not (when
			shooting at moving targets).
		 
         */
        public Initialize 
        setShouldLeadTarget(Boolean ShouldLeadTarget)
 	
			{
				this.ShouldLeadTarget = ShouldLeadTarget;
				return this;
			}
		
	        /**
	        
			It enables/disables automatic pickup of the bot. If true the items can be picked up through PICK command.
		 
	        */
	        protected
	         Boolean AutoPickupOff =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			It enables/disables automatic pickup of the bot. If true the items can be picked up through PICK command.
		 
         */
        public Boolean isAutoPickupOff()
 	
	        {
	            return
	        	 AutoPickupOff;
	        }
	        
	        
	        
 		
 		/**
         * 
			It enables/disables automatic pickup of the bot. If true the items can be picked up through PICK command.
		 
         */
        public Initialize 
        setAutoPickupOff(Boolean AutoPickupOff)
 	
			{
				this.AutoPickupOff = AutoPickupOff;
				return this;
			}
		
	        /**
	        
			If set to true new synchronous batch procotol will be used. Sync. batches will be sent
			more regularly containing SELF message only (based on SelfUpdateTime value). Visibility
			information will be still sent in synchronous batches, but not always, but each VisionTime.
			VisionTime now needs to be multiple of SelfUpdateTime and will be set to nearest multiple if 
			set improperly. BEG and END message will contain new attribute VisUpdate telling if visibility
			update is sent in this batch or not.
		 
	        */
	        protected
	         Boolean NewSelfBatchProtocol =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			If set to true new synchronous batch procotol will be used. Sync. batches will be sent
			more regularly containing SELF message only (based on SelfUpdateTime value). Visibility
			information will be still sent in synchronous batches, but not always, but each VisionTime.
			VisionTime now needs to be multiple of SelfUpdateTime and will be set to nearest multiple if 
			set improperly. BEG and END message will contain new attribute VisUpdate telling if visibility
			update is sent in this batch or not.
		 
         */
        public Boolean isNewSelfBatchProtocol()
 	
	        {
	            return
	        	 NewSelfBatchProtocol;
	        }
	        
	        
	        
 		
 		/**
         * 
			If set to true new synchronous batch procotol will be used. Sync. batches will be sent
			more regularly containing SELF message only (based on SelfUpdateTime value). Visibility
			information will be still sent in synchronous batches, but not always, but each VisionTime.
			VisionTime now needs to be multiple of SelfUpdateTime and will be set to nearest multiple if 
			set improperly. BEG and END message will contain new attribute VisUpdate telling if visibility
			update is sent in this batch or not.
		 
         */
        public Initialize 
        setNewSelfBatchProtocol(Boolean NewSelfBatchProtocol)
 	
			{
				this.NewSelfBatchProtocol = NewSelfBatchProtocol;
				return this;
			}
		
	        /**
	        
			Sets the jmx adress we can use to debug the bot from Java (if we are using Pogamut).
		 
	        */
	        protected
	         String Jmx =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Sets the jmx adress we can use to debug the bot from Java (if we are using Pogamut).
		 
         */
        public String getJmx()
 	
	        {
	            return
	        	 Jmx;
	        }
	        
	        
	        
 		
 		/**
         * 
			Sets the jmx adress we can use to debug the bot from Java (if we are using Pogamut).
		 
         */
        public Initialize 
        setJmx(String Jmx)
 	
			{
				this.Jmx = Jmx;
				return this;
			}
		
	        /**
	        
			Sets the Unreal Tournament class of the bot that should be used. By default leave it blank. In UnrealRuntime2 it may be used to spawn bots of different class,
			see CharacterType class in project PogamutEmohawk project.
		 
	        */
	        protected
	         String ClassName =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Sets the Unreal Tournament class of the bot that should be used. By default leave it blank. In UnrealRuntime2 it may be used to spawn bots of different class,
			see CharacterType class in project PogamutEmohawk project.
		 
         */
        public String getClassName()
 	
	        {
	            return
	        	 ClassName;
	        }
	        
	        
	        
 		
 		/**
         * 
			Sets the Unreal Tournament class of the bot that should be used. By default leave it blank. In UnrealRuntime2 it may be used to spawn bots of different class,
			see CharacterType class in project PogamutEmohawk project.
		 
         */
        public Initialize 
        setClassName(String ClassName)
 	
			{
				this.ClassName = ClassName;
				return this;
			}
		
 	    public String toString() {
            return toMessage();
        }
 	
 		public String toHtmlString() {
			return super.toString() + "[<br/>" +
            	
            	"<b>Name</b> = " +
            	String.valueOf(getName()
 	) +
            	" <br/> " +
            	
            	"<b>Team</b> = " +
            	String.valueOf(getTeam()
 	) +
            	" <br/> " +
            	
            	"<b>ManualSpawn</b> = " +
            	String.valueOf(isManualSpawn()
 	) +
            	" <br/> " +
            	
            	"<b>AutoTrace</b> = " +
            	String.valueOf(isAutoTrace()
 	) +
            	" <br/> " +
            	
            	"<b>Location</b> = " +
            	String.valueOf(getLocation()
 	) +
            	" <br/> " +
            	
            	"<b>Rotation</b> = " +
            	String.valueOf(getRotation()
 	) +
            	" <br/> " +
            	
            	"<b>Skin</b> = " +
            	String.valueOf(getSkin()
 	) +
            	" <br/> " +
            	
            	"<b>DesiredSkill</b> = " +
            	String.valueOf(getDesiredSkill()
 	) +
            	" <br/> " +
            	
            	"<b>ShouldLeadTarget</b> = " +
            	String.valueOf(isShouldLeadTarget()
 	) +
            	" <br/> " +
            	
            	"<b>AutoPickupOff</b> = " +
            	String.valueOf(isAutoPickupOff()
 	) +
            	" <br/> " +
            	
            	"<b>NewSelfBatchProtocol</b> = " +
            	String.valueOf(isNewSelfBatchProtocol()
 	) +
            	" <br/> " +
            	
            	"<b>Jmx</b> = " +
            	String.valueOf(getJmx()
 	) +
            	" <br/> " +
            	
            	"<b>ClassName</b> = " +
            	String.valueOf(getClassName()
 	) +
            	" <br/> " +
            	 
            	"<br/>]"
            ;
		}
 	
		public String toMessage() {
     		StringBuffer buf = new StringBuffer();
     		buf.append("INIT");
     		
						if (Name != null) {
							buf.append(" {Name " + Name + "}");
						}
					
						if (Team != null) {
							buf.append(" {Team " + Team + "}");
						}
					
						if (ManualSpawn != null) {
							buf.append(" {ManualSpawn " + ManualSpawn + "}");
						}
					
						if (AutoTrace != null) {
							buf.append(" {AutoTrace " + AutoTrace + "}");
						}
					
					    if (Location != null) {
					        buf.append(" {Location " +
					            Location.getX() + "," +
					            Location.getY() + "," +
					            Location.getZ() + "}");
					    }
					
					    if (Rotation != null) {
					        buf.append(" {Rotation " +
					            Rotation.getPitch() + "," +
					            Rotation.getYaw() + "," +
					            Rotation.getRoll() + "}");
					    }
					
						if (Skin != null) {
							buf.append(" {Skin " + Skin + "}");
						}
					
						if (DesiredSkill != null) {
							buf.append(" {DesiredSkill " + DesiredSkill + "}");
						}
					
						if (ShouldLeadTarget != null) {
							buf.append(" {ShouldLeadTarget " + ShouldLeadTarget + "}");
						}
					
						if (AutoPickupOff != null) {
							buf.append(" {AutoPickupOff " + AutoPickupOff + "}");
						}
					
						if (NewSelfBatchProtocol != null) {
							buf.append(" {NewSelfBatchProtocol " + NewSelfBatchProtocol + "}");
						}
					
						if (Jmx != null) {
							buf.append(" {Jmx " + Jmx + "}");
						}
					
						if (ClassName != null) {
							buf.append(" {ClassName " + ClassName + "}");
						}
					
   			return buf.toString();
   		}
 	
 		// --- Extra Java from XML BEGIN (extra/code/java)
        	
		// --- Extra Java from XML END (extra/code/java)
 	
	        }
    	