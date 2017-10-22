
	 	/**
         IMPORTANT !!!

         DO NOT EDIT THIS FILE. IT IS GENERATED FROM approriate xml file in xmlresources/gbcommands BY
         THE JavaClassesGenerator.xslt. MODIFY THAT FILE INSTEAD OF THIS ONE.
         
         Use Ant task process-gb-messages after that to generate .java files again.
         
         IMPORTANT END !!!
        */
 	package cz.cuni.amis.pogamut.ut2004.communication.messages.gbcommands;import java.util.*;import javax.vecmath.*;import cz.cuni.amis.pogamut.base.communication.messages.*;import cz.cuni.amis.pogamut.base.communication.worldview.*;import cz.cuni.amis.pogamut.base.communication.worldview.event.*;import cz.cuni.amis.pogamut.base.communication.worldview.object.*;import cz.cuni.amis.pogamut.multi.communication.worldview.object.*;import cz.cuni.amis.pogamut.base.communication.translator.event.*;import cz.cuni.amis.pogamut.multi.communication.translator.event.*;import cz.cuni.amis.pogamut.base3d.worldview.object.*;import cz.cuni.amis.pogamut.base3d.worldview.object.event.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.*;import cz.cuni.amis.pogamut.ut2004.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004.communication.translator.itemdescriptor.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.ItemType.Category;import cz.cuni.amis.pogamut.unreal.communication.messages.UnrealId;import cz.cuni.amis.utils.exception.*;import cz.cuni.amis.pogamut.base.communication.translator.event.IWorldObjectUpdateResult.Result;import cz.cuni.amis.utils.SafeEquals;import cz.cuni.amis.pogamut.base.agent.*;import cz.cuni.amis.pogamut.multi.agent.*;import cz.cuni.amis.pogamut.multi.communication.worldview.property.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.property.*;import cz.cuni.amis.utils.token.*;import cz.cuni.amis.utils.*;import java.awt.Color;
 		/**
 		 * Representation of the GameBots2004 command SHTXT.
 		 *
 		 * 
		Shows for specified amount of time or removes a text from players HUD.
	
         */
 	public class ShowText 
		extends CommandMessage
	        {
	        	
		        
    	/** Example how the message looks like - used during parser tests. */
    	public static final String PROTOTYPE =
    		" {Player text}  {Text text}  {TextColor null}  {Time 0}  {Show False} ";
    
		/**
		 * Creates new instance of command ShowText.
		 * 
		Shows for specified amount of time or removes a text from players HUD.
	
		 * Corresponding GameBots message for this command is
		 * SHTXT.
		 *
		 * 
		 *    @param Player Name of the player on who's HUD should be the text shown.
		 *    @param Text The text that should be shown.
		 *    @param TextColor The color of text.
		 *    @param Time Number of seconds for which should be the text shown. The default is until manual removal.
		 *    @param Show False if the message should be removed, true if it should be shown.
		 */
		public ShowText(
			String Player,  String Text,  Color TextColor,  Double Time,  Boolean Show
		) {
			
				this.Player = Player;
            
				this.Text = Text;
            
				this.TextColor = TextColor;
            
				this.Time = Time;
            
				this.Show = Show;
            
		}

		
			/**
			 * Creates new instance of command ShowText.
			 * 
		Shows for specified amount of time or removes a text from players HUD.
	
			 * Corresponding GameBots message for this command is
			 * SHTXT.
			 * <p></p>
			 * WARNING: this is empty-command constructor, you have to use setters to fill it up with data that should be sent to GameBots2004!
		     */
		    public ShowText() {
		    }
			
		
		/**
		 * Cloning constructor.
		 *
		 * @param original
		 */
		public ShowText(ShowText original) {
		   
		        this.Player = original.Player;
		   
		        this.Text = original.Text;
		   
		        this.TextColor = original.TextColor;
		   
		        this.Time = original.Time;
		   
		        this.Show = original.Show;
		   
		}
    
	        /**
	        Name of the player on who's HUD should be the text shown. 
	        */
	        protected
	         String Player =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * Name of the player on who's HUD should be the text shown. 
         */
        public String getPlayer()
 	
	        {
	            return
	        	 Player;
	        }
	        
	        
	        
 		
 		/**
         * Name of the player on who's HUD should be the text shown. 
         */
        public ShowText 
        setPlayer(String Player)
 	
			{
				this.Player = Player;
				return this;
			}
		
	        /**
	        The text that should be shown. 
	        */
	        protected
	         String Text =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * The text that should be shown. 
         */
        public String getText()
 	
	        {
	            return
	        	 Text;
	        }
	        
	        
	        
 		
 		/**
         * The text that should be shown. 
         */
        public ShowText 
        setText(String Text)
 	
			{
				this.Text = Text;
				return this;
			}
		
	        /**
	        The color of text. 
	        */
	        protected
	         Color TextColor =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * The color of text. 
         */
        public Color getTextColor()
 	
	        {
	            return
	        	 TextColor;
	        }
	        
	        
	        
 		
 		/**
         * The color of text. 
         */
        public ShowText 
        setTextColor(Color TextColor)
 	
			{
				this.TextColor = TextColor;
				return this;
			}
		
	        /**
	        Number of seconds for which should be the text shown. The default is until manual removal. 
	        */
	        protected
	         Double Time =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * Number of seconds for which should be the text shown. The default is until manual removal. 
         */
        public Double getTime()
 	
	        {
	            return
	        	 Time;
	        }
	        
	        
	        
 		
 		/**
         * Number of seconds for which should be the text shown. The default is until manual removal. 
         */
        public ShowText 
        setTime(Double Time)
 	
			{
				this.Time = Time;
				return this;
			}
		
	        /**
	        False if the message should be removed, true if it should be shown. 
	        */
	        protected
	         Boolean Show =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * False if the message should be removed, true if it should be shown. 
         */
        public Boolean isShow()
 	
	        {
	            return
	        	 Show;
	        }
	        
	        
	        
 		
 		/**
         * False if the message should be removed, true if it should be shown. 
         */
        public ShowText 
        setShow(Boolean Show)
 	
			{
				this.Show = Show;
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
            	
            	"<b>Text</b> = " +
            	String.valueOf(getText()
 	) +
            	" <br/> " +
            	
            	"<b>TextColor</b> = " +
            	String.valueOf(getTextColor()
 	) +
            	" <br/> " +
            	
            	"<b>Time</b> = " +
            	String.valueOf(getTime()
 	) +
            	" <br/> " +
            	
            	"<b>Show</b> = " +
            	String.valueOf(isShow()
 	) +
            	" <br/> " +
            	 
            	"<br/>]"
            ;
		}
 	
		public String toMessage() {
     		StringBuffer buf = new StringBuffer();
     		buf.append("SHTXT");
     		
						if (Player != null) {
							buf.append(" {Player " + Player + "}");
						}
					
						if (Text != null) {
							buf.append(" {Text " + Text + "}");
						}
					
					    if (TextColor != null) {
					        buf.append(" {TextColor " +
					            TextColor.getRed() + "," +
					            TextColor.getGreen() + "," +
					            TextColor.getBlue() + "," +
					            TextColor.getAlpha() + "}");
					    }
					
						if (Time != null) {
							buf.append(" {Time " + Time + "}");
						}
					
						if (Show != null) {
							buf.append(" {Show " + Show + "}");
						}
					
   			return buf.toString();
   		}
 	
 		// --- Extra Java from XML BEGIN (extra/code/java)
        	
				        
			      
		// --- Extra Java from XML END (extra/code/java)
 	
	        }
    	