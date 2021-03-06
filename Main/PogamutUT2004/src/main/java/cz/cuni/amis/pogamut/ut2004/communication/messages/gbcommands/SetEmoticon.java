
	 	/**
         IMPORTANT !!!

         DO NOT EDIT THIS FILE. IT IS GENERATED FROM approriate xml file in xmlresources/gbcommands BY
         THE JavaClassesGenerator.xslt. MODIFY THAT FILE INSTEAD OF THIS ONE.
         
         Use Ant task process-gb-messages after that to generate .java files again.
         
         IMPORTANT END !!!
        */
 	package cz.cuni.amis.pogamut.ut2004.communication.messages.gbcommands;import java.util.*;import javax.vecmath.*;import cz.cuni.amis.pogamut.base.communication.messages.*;import cz.cuni.amis.pogamut.base.communication.worldview.*;import cz.cuni.amis.pogamut.base.communication.worldview.event.*;import cz.cuni.amis.pogamut.base.communication.worldview.object.*;import cz.cuni.amis.pogamut.multi.communication.worldview.object.*;import cz.cuni.amis.pogamut.base.communication.translator.event.*;import cz.cuni.amis.pogamut.multi.communication.translator.event.*;import cz.cuni.amis.pogamut.base3d.worldview.object.*;import cz.cuni.amis.pogamut.base3d.worldview.object.event.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.*;import cz.cuni.amis.pogamut.ut2004.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004.communication.translator.itemdescriptor.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.ItemType.Category;import cz.cuni.amis.pogamut.unreal.communication.messages.UnrealId;import cz.cuni.amis.utils.exception.*;import cz.cuni.amis.pogamut.base.communication.translator.event.IWorldObjectUpdateResult.Result;import cz.cuni.amis.utils.SafeEquals;import cz.cuni.amis.pogamut.base.agent.*;import cz.cuni.amis.pogamut.multi.agent.*;import cz.cuni.amis.pogamut.multi.communication.worldview.property.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.property.*;import cz.cuni.amis.utils.token.*;import cz.cuni.amis.utils.*;
 		/**
 		 * Representation of the GameBots2004 command SETEMOT.
 		 *
 		 * 
		Sets the emoticon for current bot.
	
         */
 	public class SetEmoticon 
		extends CommandMessage
	        {
	        	
		        
    	/** Example how the message looks like - used during parser tests. */
    	public static final String PROTOTYPE =
    		" {Center text}  {CenterSize 0}  {Left text}  {LeftSize 0}  {Right text}  {RightSize 0}  {BubbleType text}  {BubbleSize 0}  {Time 0} ";
    
		/**
		 * Creates new instance of command SetEmoticon.
		 * 
		Sets the emoticon for current bot.
	
		 * Corresponding GameBots message for this command is
		 * SETEMOT.
		 *
		 * 
		 *    @param Center 
            Sets the center emoticon.
        
		 *    @param CenterSize 
            Sets the center emoticon size. 0 will disable the emoticon.
        
		 *    @param Left 
            Sets the left emoticon.
        
		 *    @param LeftSize 
            Sets the left emoticon size. 0 will disable the emoticon.
        
		 *    @param Right 
            Sets the right emoticon.
        
		 *    @param RightSize 
            Sets the right emoticon size. 0 will disable the emoticon.
        
		 *    @param BubbleType 
            Sets the bubble type behind the emoticons.
        
		 *    @param BubbleSize 
            Sets the bubble size behind the emoticons. 0 for no bubble.
        
		 *    @param Time 
            Fade out of the emoticons in seconds.
        
		 */
		public SetEmoticon(
			String Center,  Integer CenterSize,  String Left,  Integer LeftSize,  String Right,  Integer RightSize,  String BubbleType,  Integer BubbleSize,  Double Time
		) {
			
				this.Center = Center;
            
				this.CenterSize = CenterSize;
            
				this.Left = Left;
            
				this.LeftSize = LeftSize;
            
				this.Right = Right;
            
				this.RightSize = RightSize;
            
				this.BubbleType = BubbleType;
            
				this.BubbleSize = BubbleSize;
            
				this.Time = Time;
            
		}

		
			/**
			 * Creates new instance of command SetEmoticon.
			 * 
		Sets the emoticon for current bot.
	
			 * Corresponding GameBots message for this command is
			 * SETEMOT.
			 * <p></p>
			 * WARNING: this is empty-command constructor, you have to use setters to fill it up with data that should be sent to GameBots2004!
		     */
		    public SetEmoticon() {
		    }
			
		
		/**
		 * Cloning constructor.
		 *
		 * @param original
		 */
		public SetEmoticon(SetEmoticon original) {
		   
		        this.Center = original.Center;
		   
		        this.CenterSize = original.CenterSize;
		   
		        this.Left = original.Left;
		   
		        this.LeftSize = original.LeftSize;
		   
		        this.Right = original.Right;
		   
		        this.RightSize = original.RightSize;
		   
		        this.BubbleType = original.BubbleType;
		   
		        this.BubbleSize = original.BubbleSize;
		   
		        this.Time = original.Time;
		   
		}
    
	        /**
	        
            Sets the center emoticon.
         
	        */
	        protected
	         String Center =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
            Sets the center emoticon.
         
         */
        public String getCenter()
 	
	        {
	            return
	        	 Center;
	        }
	        
	        
	        
 		
 		/**
         * 
            Sets the center emoticon.
         
         */
        public SetEmoticon 
        setCenter(String Center)
 	
			{
				this.Center = Center;
				return this;
			}
		
	        /**
	        
            Sets the center emoticon size. 0 will disable the emoticon.
         
	        */
	        protected
	         Integer CenterSize =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
            Sets the center emoticon size. 0 will disable the emoticon.
         
         */
        public Integer getCenterSize()
 	
	        {
	            return
	        	 CenterSize;
	        }
	        
	        
	        
 		
 		/**
         * 
            Sets the center emoticon size. 0 will disable the emoticon.
         
         */
        public SetEmoticon 
        setCenterSize(Integer CenterSize)
 	
			{
				this.CenterSize = CenterSize;
				return this;
			}
		
	        /**
	        
            Sets the left emoticon.
         
	        */
	        protected
	         String Left =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
            Sets the left emoticon.
         
         */
        public String getLeft()
 	
	        {
	            return
	        	 Left;
	        }
	        
	        
	        
 		
 		/**
         * 
            Sets the left emoticon.
         
         */
        public SetEmoticon 
        setLeft(String Left)
 	
			{
				this.Left = Left;
				return this;
			}
		
	        /**
	        
            Sets the left emoticon size. 0 will disable the emoticon.
         
	        */
	        protected
	         Integer LeftSize =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
            Sets the left emoticon size. 0 will disable the emoticon.
         
         */
        public Integer getLeftSize()
 	
	        {
	            return
	        	 LeftSize;
	        }
	        
	        
	        
 		
 		/**
         * 
            Sets the left emoticon size. 0 will disable the emoticon.
         
         */
        public SetEmoticon 
        setLeftSize(Integer LeftSize)
 	
			{
				this.LeftSize = LeftSize;
				return this;
			}
		
	        /**
	        
            Sets the right emoticon.
         
	        */
	        protected
	         String Right =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
            Sets the right emoticon.
         
         */
        public String getRight()
 	
	        {
	            return
	        	 Right;
	        }
	        
	        
	        
 		
 		/**
         * 
            Sets the right emoticon.
         
         */
        public SetEmoticon 
        setRight(String Right)
 	
			{
				this.Right = Right;
				return this;
			}
		
	        /**
	        
            Sets the right emoticon size. 0 will disable the emoticon.
         
	        */
	        protected
	         Integer RightSize =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
            Sets the right emoticon size. 0 will disable the emoticon.
         
         */
        public Integer getRightSize()
 	
	        {
	            return
	        	 RightSize;
	        }
	        
	        
	        
 		
 		/**
         * 
            Sets the right emoticon size. 0 will disable the emoticon.
         
         */
        public SetEmoticon 
        setRightSize(Integer RightSize)
 	
			{
				this.RightSize = RightSize;
				return this;
			}
		
	        /**
	        
            Sets the bubble type behind the emoticons.
         
	        */
	        protected
	         String BubbleType =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
            Sets the bubble type behind the emoticons.
         
         */
        public String getBubbleType()
 	
	        {
	            return
	        	 BubbleType;
	        }
	        
	        
	        
 		
 		/**
         * 
            Sets the bubble type behind the emoticons.
         
         */
        public SetEmoticon 
        setBubbleType(String BubbleType)
 	
			{
				this.BubbleType = BubbleType;
				return this;
			}
		
	        /**
	        
            Sets the bubble size behind the emoticons. 0 for no bubble.
         
	        */
	        protected
	         Integer BubbleSize =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
            Sets the bubble size behind the emoticons. 0 for no bubble.
         
         */
        public Integer getBubbleSize()
 	
	        {
	            return
	        	 BubbleSize;
	        }
	        
	        
	        
 		
 		/**
         * 
            Sets the bubble size behind the emoticons. 0 for no bubble.
         
         */
        public SetEmoticon 
        setBubbleSize(Integer BubbleSize)
 	
			{
				this.BubbleSize = BubbleSize;
				return this;
			}
		
	        /**
	        
            Fade out of the emoticons in seconds.
         
	        */
	        protected
	         Double Time =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
            Fade out of the emoticons in seconds.
         
         */
        public Double getTime()
 	
	        {
	            return
	        	 Time;
	        }
	        
	        
	        
 		
 		/**
         * 
            Fade out of the emoticons in seconds.
         
         */
        public SetEmoticon 
        setTime(Double Time)
 	
			{
				this.Time = Time;
				return this;
			}
		
 	    public String toString() {
            return toMessage();
        }
 	
 		public String toHtmlString() {
			return super.toString() + "[<br/>" +
            	
            	"<b>Center</b> = " +
            	String.valueOf(getCenter()
 	) +
            	" <br/> " +
            	
            	"<b>CenterSize</b> = " +
            	String.valueOf(getCenterSize()
 	) +
            	" <br/> " +
            	
            	"<b>Left</b> = " +
            	String.valueOf(getLeft()
 	) +
            	" <br/> " +
            	
            	"<b>LeftSize</b> = " +
            	String.valueOf(getLeftSize()
 	) +
            	" <br/> " +
            	
            	"<b>Right</b> = " +
            	String.valueOf(getRight()
 	) +
            	" <br/> " +
            	
            	"<b>RightSize</b> = " +
            	String.valueOf(getRightSize()
 	) +
            	" <br/> " +
            	
            	"<b>BubbleType</b> = " +
            	String.valueOf(getBubbleType()
 	) +
            	" <br/> " +
            	
            	"<b>BubbleSize</b> = " +
            	String.valueOf(getBubbleSize()
 	) +
            	" <br/> " +
            	
            	"<b>Time</b> = " +
            	String.valueOf(getTime()
 	) +
            	" <br/> " +
            	 
            	"<br/>]"
            ;
		}
 	
		public String toMessage() {
     		StringBuffer buf = new StringBuffer();
     		buf.append("SETEMOT");
     		
						if (Center != null) {
							buf.append(" {Center " + Center + "}");
						}
					
						if (CenterSize != null) {
							buf.append(" {CenterSize " + CenterSize + "}");
						}
					
						if (Left != null) {
							buf.append(" {Left " + Left + "}");
						}
					
						if (LeftSize != null) {
							buf.append(" {LeftSize " + LeftSize + "}");
						}
					
						if (Right != null) {
							buf.append(" {Right " + Right + "}");
						}
					
						if (RightSize != null) {
							buf.append(" {RightSize " + RightSize + "}");
						}
					
						if (BubbleType != null) {
							buf.append(" {BubbleType " + BubbleType + "}");
						}
					
						if (BubbleSize != null) {
							buf.append(" {BubbleSize " + BubbleSize + "}");
						}
					
						if (Time != null) {
							buf.append(" {Time " + Time + "}");
						}
					
   			return buf.toString();
   		}
 	
 		// --- Extra Java from XML BEGIN (extra/code/java)
        	
		// --- Extra Java from XML END (extra/code/java)
 	
	        }
    	