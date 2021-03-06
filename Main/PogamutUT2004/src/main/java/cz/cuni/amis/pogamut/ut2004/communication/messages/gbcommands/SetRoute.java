
	 	/**
         IMPORTANT !!!

         DO NOT EDIT THIS FILE. IT IS GENERATED FROM approriate xml file in xmlresources/gbcommands BY
         THE JavaClassesGenerator.xslt. MODIFY THAT FILE INSTEAD OF THIS ONE.
         
         Use Ant task process-gb-messages after that to generate .java files again.
         
         IMPORTANT END !!!
        */
 	package cz.cuni.amis.pogamut.ut2004.communication.messages.gbcommands;import java.util.*;import javax.vecmath.*;import cz.cuni.amis.pogamut.base.communication.messages.*;import cz.cuni.amis.pogamut.base.communication.worldview.*;import cz.cuni.amis.pogamut.base.communication.worldview.event.*;import cz.cuni.amis.pogamut.base.communication.worldview.object.*;import cz.cuni.amis.pogamut.multi.communication.worldview.object.*;import cz.cuni.amis.pogamut.base.communication.translator.event.*;import cz.cuni.amis.pogamut.multi.communication.translator.event.*;import cz.cuni.amis.pogamut.base3d.worldview.object.*;import cz.cuni.amis.pogamut.base3d.worldview.object.event.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.*;import cz.cuni.amis.pogamut.ut2004.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.objects.*;import cz.cuni.amis.pogamut.ut2004.communication.translator.itemdescriptor.*;import cz.cuni.amis.pogamut.ut2004.communication.messages.ItemType.Category;import cz.cuni.amis.pogamut.unreal.communication.messages.UnrealId;import cz.cuni.amis.utils.exception.*;import cz.cuni.amis.pogamut.base.communication.translator.event.IWorldObjectUpdateResult.Result;import cz.cuni.amis.utils.SafeEquals;import cz.cuni.amis.pogamut.base.agent.*;import cz.cuni.amis.pogamut.multi.agent.*;import cz.cuni.amis.pogamut.multi.communication.worldview.property.*;import cz.cuni.amis.pogamut.ut2004multi.communication.worldview.property.*;import cz.cuni.amis.utils.token.*;import cz.cuni.amis.utils.*;
 		/**
 		 * Representation of the GameBots2004 command SETROUTE.
 		 *
 		 * 
		Set whether you want to draw blue lines between supported locations in the game. You need
		to enable this in the HUD by pressing ALT + R. (Works in UnrealRuntime2 for now). The lines
		are drawn between neighbouring points. First is point 0.
	
         */
 	public class SetRoute 
		extends CommandMessage
	        {
	        	
		        
    	/** Example how the message looks like - used during parser tests. */
    	public static final String PROTOTYPE =
    		" {Erase False}  {P0 0,0,0}  {P1 0,0,0}  {P2 0,0,0}  {P3 0,0,0}  {P4 0,0,0}  {P5 0,0,0}  {P6 0,0,0}  {P7 0,0,0}  {P8 0,0,0}  {P9 0,0,0}  {P10 0,0,0}  {P11 0,0,0}  {P12 0,0,0}  {P13 0,0,0}  {P14 0,0,0}  {P15 0,0,0}  {P16 0,0,0}  {P17 0,0,0}  {P18 0,0,0}  {P19 0,0,0}  {P20 0,0,0}  {P21 0,0,0}  {P22 0,0,0}  {P23 0,0,0}  {P24 0,0,0}  {P25 0,0,0}  {P26 0,0,0}  {P27 0,0,0}  {P28 0,0,0}  {P29 0,0,0}  {P30 0,0,0}  {P31 0,0,0} ";
    
		/**
		 * Creates new instance of command SetRoute.
		 * 
		Set whether you want to draw blue lines between supported locations in the game. You need
		to enable this in the HUD by pressing ALT + R. (Works in UnrealRuntime2 for now). The lines
		are drawn between neighbouring points. First is point 0.
	
		 * Corresponding GameBots message for this command is
		 * SETROUTE.
		 *
		 * 
		 *    @param Erase 
			If true will erase previously set points.
		
		 *    @param P0 
			Location point.
		
		 *    @param P1 
			Location point.
		
		 *    @param P2 
			Location point.
		
		 *    @param P3 
			Location point.
		
		 *    @param P4 
			Location point.
		
		 *    @param P5 
			Location point.
		
		 *    @param P6 
			Location point.
		
		 *    @param P7 
			Location point.
		
		 *    @param P8 
			Location point.
		
		 *    @param P9 
			Location point.
		
		 *    @param P10 
			Location point.
		
		 *    @param P11 
			Location point.
		
		 *    @param P12 
			Location point.
		
		 *    @param P13 
			Location point.
		
		 *    @param P14 
			Location point.
		
		 *    @param P15 
			Location point.
		
		 *    @param P16 
			Location point.
		
		 *    @param P17 
			Location point.
		
		 *    @param P18 
			Location point.
		
		 *    @param P19 
			Location point.
		
		 *    @param P20 
			Location point.
		
		 *    @param P21 
			Location point.
		
		 *    @param P22 
			Location point.
		
		 *    @param P23 
			Location point.
		
		 *    @param P24 
			Location point.
		
		 *    @param P25 
			Location point.
		
		 *    @param P26 
			Location point.
		
		 *    @param P27 
			Location point.
		
		 *    @param P28 
			Location point.
		
		 *    @param P29 
			Location point.
		
		 *    @param P30 
			Location point.
		
		 *    @param P31 
			Location point.
		
		 */
		public SetRoute(
			Boolean Erase,  Location P0,  Location P1,  Location P2,  Location P3,  Location P4,  Location P5,  Location P6,  Location P7,  Location P8,  Location P9,  Location P10,  Location P11,  Location P12,  Location P13,  Location P14,  Location P15,  Location P16,  Location P17,  Location P18,  Location P19,  Location P20,  Location P21,  Location P22,  Location P23,  Location P24,  Location P25,  Location P26,  Location P27,  Location P28,  Location P29,  Location P30,  Location P31
		) {
			
				this.Erase = Erase;
            
				this.P0 = P0;
            
				this.P1 = P1;
            
				this.P2 = P2;
            
				this.P3 = P3;
            
				this.P4 = P4;
            
				this.P5 = P5;
            
				this.P6 = P6;
            
				this.P7 = P7;
            
				this.P8 = P8;
            
				this.P9 = P9;
            
				this.P10 = P10;
            
				this.P11 = P11;
            
				this.P12 = P12;
            
				this.P13 = P13;
            
				this.P14 = P14;
            
				this.P15 = P15;
            
				this.P16 = P16;
            
				this.P17 = P17;
            
				this.P18 = P18;
            
				this.P19 = P19;
            
				this.P20 = P20;
            
				this.P21 = P21;
            
				this.P22 = P22;
            
				this.P23 = P23;
            
				this.P24 = P24;
            
				this.P25 = P25;
            
				this.P26 = P26;
            
				this.P27 = P27;
            
				this.P28 = P28;
            
				this.P29 = P29;
            
				this.P30 = P30;
            
				this.P31 = P31;
            
		}

		
			/**
			 * Creates new instance of command SetRoute.
			 * 
		Set whether you want to draw blue lines between supported locations in the game. You need
		to enable this in the HUD by pressing ALT + R. (Works in UnrealRuntime2 for now). The lines
		are drawn between neighbouring points. First is point 0.
	
			 * Corresponding GameBots message for this command is
			 * SETROUTE.
			 * <p></p>
			 * WARNING: this is empty-command constructor, you have to use setters to fill it up with data that should be sent to GameBots2004!
		     */
		    public SetRoute() {
		    }
			
		
		/**
		 * Cloning constructor.
		 *
		 * @param original
		 */
		public SetRoute(SetRoute original) {
		   
		        this.Erase = original.Erase;
		   
		        this.P0 = original.P0;
		   
		        this.P1 = original.P1;
		   
		        this.P2 = original.P2;
		   
		        this.P3 = original.P3;
		   
		        this.P4 = original.P4;
		   
		        this.P5 = original.P5;
		   
		        this.P6 = original.P6;
		   
		        this.P7 = original.P7;
		   
		        this.P8 = original.P8;
		   
		        this.P9 = original.P9;
		   
		        this.P10 = original.P10;
		   
		        this.P11 = original.P11;
		   
		        this.P12 = original.P12;
		   
		        this.P13 = original.P13;
		   
		        this.P14 = original.P14;
		   
		        this.P15 = original.P15;
		   
		        this.P16 = original.P16;
		   
		        this.P17 = original.P17;
		   
		        this.P18 = original.P18;
		   
		        this.P19 = original.P19;
		   
		        this.P20 = original.P20;
		   
		        this.P21 = original.P21;
		   
		        this.P22 = original.P22;
		   
		        this.P23 = original.P23;
		   
		        this.P24 = original.P24;
		   
		        this.P25 = original.P25;
		   
		        this.P26 = original.P26;
		   
		        this.P27 = original.P27;
		   
		        this.P28 = original.P28;
		   
		        this.P29 = original.P29;
		   
		        this.P30 = original.P30;
		   
		        this.P31 = original.P31;
		   
		}
    
	        /**
	        
			If true will erase previously set points.
		 
	        */
	        protected
	         Boolean Erase =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			If true will erase previously set points.
		 
         */
        public Boolean isErase()
 	
	        {
	            return
	        	 Erase;
	        }
	        
	        
	        
 		
 		/**
         * 
			If true will erase previously set points.
		 
         */
        public SetRoute 
        setErase(Boolean Erase)
 	
			{
				this.Erase = Erase;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P0 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP0()
 	
	        {
	            return
	        	 P0;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP0(Location P0)
 	
			{
				this.P0 = P0;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P1 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP1()
 	
	        {
	            return
	        	 P1;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP1(Location P1)
 	
			{
				this.P1 = P1;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P2 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP2()
 	
	        {
	            return
	        	 P2;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP2(Location P2)
 	
			{
				this.P2 = P2;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P3 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP3()
 	
	        {
	            return
	        	 P3;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP3(Location P3)
 	
			{
				this.P3 = P3;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P4 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP4()
 	
	        {
	            return
	        	 P4;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP4(Location P4)
 	
			{
				this.P4 = P4;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P5 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP5()
 	
	        {
	            return
	        	 P5;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP5(Location P5)
 	
			{
				this.P5 = P5;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P6 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP6()
 	
	        {
	            return
	        	 P6;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP6(Location P6)
 	
			{
				this.P6 = P6;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P7 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP7()
 	
	        {
	            return
	        	 P7;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP7(Location P7)
 	
			{
				this.P7 = P7;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P8 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP8()
 	
	        {
	            return
	        	 P8;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP8(Location P8)
 	
			{
				this.P8 = P8;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P9 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP9()
 	
	        {
	            return
	        	 P9;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP9(Location P9)
 	
			{
				this.P9 = P9;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P10 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP10()
 	
	        {
	            return
	        	 P10;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP10(Location P10)
 	
			{
				this.P10 = P10;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P11 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP11()
 	
	        {
	            return
	        	 P11;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP11(Location P11)
 	
			{
				this.P11 = P11;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P12 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP12()
 	
	        {
	            return
	        	 P12;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP12(Location P12)
 	
			{
				this.P12 = P12;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P13 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP13()
 	
	        {
	            return
	        	 P13;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP13(Location P13)
 	
			{
				this.P13 = P13;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P14 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP14()
 	
	        {
	            return
	        	 P14;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP14(Location P14)
 	
			{
				this.P14 = P14;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P15 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP15()
 	
	        {
	            return
	        	 P15;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP15(Location P15)
 	
			{
				this.P15 = P15;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P16 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP16()
 	
	        {
	            return
	        	 P16;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP16(Location P16)
 	
			{
				this.P16 = P16;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P17 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP17()
 	
	        {
	            return
	        	 P17;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP17(Location P17)
 	
			{
				this.P17 = P17;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P18 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP18()
 	
	        {
	            return
	        	 P18;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP18(Location P18)
 	
			{
				this.P18 = P18;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P19 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP19()
 	
	        {
	            return
	        	 P19;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP19(Location P19)
 	
			{
				this.P19 = P19;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P20 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP20()
 	
	        {
	            return
	        	 P20;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP20(Location P20)
 	
			{
				this.P20 = P20;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P21 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP21()
 	
	        {
	            return
	        	 P21;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP21(Location P21)
 	
			{
				this.P21 = P21;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P22 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP22()
 	
	        {
	            return
	        	 P22;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP22(Location P22)
 	
			{
				this.P22 = P22;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P23 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP23()
 	
	        {
	            return
	        	 P23;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP23(Location P23)
 	
			{
				this.P23 = P23;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P24 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP24()
 	
	        {
	            return
	        	 P24;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP24(Location P24)
 	
			{
				this.P24 = P24;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P25 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP25()
 	
	        {
	            return
	        	 P25;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP25(Location P25)
 	
			{
				this.P25 = P25;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P26 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP26()
 	
	        {
	            return
	        	 P26;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP26(Location P26)
 	
			{
				this.P26 = P26;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P27 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP27()
 	
	        {
	            return
	        	 P27;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP27(Location P27)
 	
			{
				this.P27 = P27;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P28 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP28()
 	
	        {
	            return
	        	 P28;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP28(Location P28)
 	
			{
				this.P28 = P28;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P29 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP29()
 	
	        {
	            return
	        	 P29;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP29(Location P29)
 	
			{
				this.P29 = P29;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P30 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP30()
 	
	        {
	            return
	        	 P30;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP30(Location P30)
 	
			{
				this.P30 = P30;
				return this;
			}
		
	        /**
	        
			Location point.
		 
	        */
	        protected
	         Location P31 =
	       	
	        		null
	        	;
	
	        
	        
 		/**
         * 
			Location point.
		 
         */
        public Location getP31()
 	
	        {
	            return
	        	 P31;
	        }
	        
	        
	        
 		
 		/**
         * 
			Location point.
		 
         */
        public SetRoute 
        setP31(Location P31)
 	
			{
				this.P31 = P31;
				return this;
			}
		
 	    public String toString() {
            return toMessage();
        }
 	
 		public String toHtmlString() {
			return super.toString() + "[<br/>" +
            	
            	"<b>Erase</b> = " +
            	String.valueOf(isErase()
 	) +
            	" <br/> " +
            	
            	"<b>P0</b> = " +
            	String.valueOf(getP0()
 	) +
            	" <br/> " +
            	
            	"<b>P1</b> = " +
            	String.valueOf(getP1()
 	) +
            	" <br/> " +
            	
            	"<b>P2</b> = " +
            	String.valueOf(getP2()
 	) +
            	" <br/> " +
            	
            	"<b>P3</b> = " +
            	String.valueOf(getP3()
 	) +
            	" <br/> " +
            	
            	"<b>P4</b> = " +
            	String.valueOf(getP4()
 	) +
            	" <br/> " +
            	
            	"<b>P5</b> = " +
            	String.valueOf(getP5()
 	) +
            	" <br/> " +
            	
            	"<b>P6</b> = " +
            	String.valueOf(getP6()
 	) +
            	" <br/> " +
            	
            	"<b>P7</b> = " +
            	String.valueOf(getP7()
 	) +
            	" <br/> " +
            	
            	"<b>P8</b> = " +
            	String.valueOf(getP8()
 	) +
            	" <br/> " +
            	
            	"<b>P9</b> = " +
            	String.valueOf(getP9()
 	) +
            	" <br/> " +
            	
            	"<b>P10</b> = " +
            	String.valueOf(getP10()
 	) +
            	" <br/> " +
            	
            	"<b>P11</b> = " +
            	String.valueOf(getP11()
 	) +
            	" <br/> " +
            	
            	"<b>P12</b> = " +
            	String.valueOf(getP12()
 	) +
            	" <br/> " +
            	
            	"<b>P13</b> = " +
            	String.valueOf(getP13()
 	) +
            	" <br/> " +
            	
            	"<b>P14</b> = " +
            	String.valueOf(getP14()
 	) +
            	" <br/> " +
            	
            	"<b>P15</b> = " +
            	String.valueOf(getP15()
 	) +
            	" <br/> " +
            	
            	"<b>P16</b> = " +
            	String.valueOf(getP16()
 	) +
            	" <br/> " +
            	
            	"<b>P17</b> = " +
            	String.valueOf(getP17()
 	) +
            	" <br/> " +
            	
            	"<b>P18</b> = " +
            	String.valueOf(getP18()
 	) +
            	" <br/> " +
            	
            	"<b>P19</b> = " +
            	String.valueOf(getP19()
 	) +
            	" <br/> " +
            	
            	"<b>P20</b> = " +
            	String.valueOf(getP20()
 	) +
            	" <br/> " +
            	
            	"<b>P21</b> = " +
            	String.valueOf(getP21()
 	) +
            	" <br/> " +
            	
            	"<b>P22</b> = " +
            	String.valueOf(getP22()
 	) +
            	" <br/> " +
            	
            	"<b>P23</b> = " +
            	String.valueOf(getP23()
 	) +
            	" <br/> " +
            	
            	"<b>P24</b> = " +
            	String.valueOf(getP24()
 	) +
            	" <br/> " +
            	
            	"<b>P25</b> = " +
            	String.valueOf(getP25()
 	) +
            	" <br/> " +
            	
            	"<b>P26</b> = " +
            	String.valueOf(getP26()
 	) +
            	" <br/> " +
            	
            	"<b>P27</b> = " +
            	String.valueOf(getP27()
 	) +
            	" <br/> " +
            	
            	"<b>P28</b> = " +
            	String.valueOf(getP28()
 	) +
            	" <br/> " +
            	
            	"<b>P29</b> = " +
            	String.valueOf(getP29()
 	) +
            	" <br/> " +
            	
            	"<b>P30</b> = " +
            	String.valueOf(getP30()
 	) +
            	" <br/> " +
            	
            	"<b>P31</b> = " +
            	String.valueOf(getP31()
 	) +
            	" <br/> " +
            	 
            	"<br/>]"
            ;
		}
 	
		public String toMessage() {
     		StringBuffer buf = new StringBuffer();
     		buf.append("SETROUTE");
     		
						if (Erase != null) {
							buf.append(" {Erase " + Erase + "}");
						}
					
					    if (P0 != null) {
					        buf.append(" {P0 " +
					            P0.getX() + "," +
					            P0.getY() + "," +
					            P0.getZ() + "}");
					    }
					
					    if (P1 != null) {
					        buf.append(" {P1 " +
					            P1.getX() + "," +
					            P1.getY() + "," +
					            P1.getZ() + "}");
					    }
					
					    if (P2 != null) {
					        buf.append(" {P2 " +
					            P2.getX() + "," +
					            P2.getY() + "," +
					            P2.getZ() + "}");
					    }
					
					    if (P3 != null) {
					        buf.append(" {P3 " +
					            P3.getX() + "," +
					            P3.getY() + "," +
					            P3.getZ() + "}");
					    }
					
					    if (P4 != null) {
					        buf.append(" {P4 " +
					            P4.getX() + "," +
					            P4.getY() + "," +
					            P4.getZ() + "}");
					    }
					
					    if (P5 != null) {
					        buf.append(" {P5 " +
					            P5.getX() + "," +
					            P5.getY() + "," +
					            P5.getZ() + "}");
					    }
					
					    if (P6 != null) {
					        buf.append(" {P6 " +
					            P6.getX() + "," +
					            P6.getY() + "," +
					            P6.getZ() + "}");
					    }
					
					    if (P7 != null) {
					        buf.append(" {P7 " +
					            P7.getX() + "," +
					            P7.getY() + "," +
					            P7.getZ() + "}");
					    }
					
					    if (P8 != null) {
					        buf.append(" {P8 " +
					            P8.getX() + "," +
					            P8.getY() + "," +
					            P8.getZ() + "}");
					    }
					
					    if (P9 != null) {
					        buf.append(" {P9 " +
					            P9.getX() + "," +
					            P9.getY() + "," +
					            P9.getZ() + "}");
					    }
					
					    if (P10 != null) {
					        buf.append(" {P10 " +
					            P10.getX() + "," +
					            P10.getY() + "," +
					            P10.getZ() + "}");
					    }
					
					    if (P11 != null) {
					        buf.append(" {P11 " +
					            P11.getX() + "," +
					            P11.getY() + "," +
					            P11.getZ() + "}");
					    }
					
					    if (P12 != null) {
					        buf.append(" {P12 " +
					            P12.getX() + "," +
					            P12.getY() + "," +
					            P12.getZ() + "}");
					    }
					
					    if (P13 != null) {
					        buf.append(" {P13 " +
					            P13.getX() + "," +
					            P13.getY() + "," +
					            P13.getZ() + "}");
					    }
					
					    if (P14 != null) {
					        buf.append(" {P14 " +
					            P14.getX() + "," +
					            P14.getY() + "," +
					            P14.getZ() + "}");
					    }
					
					    if (P15 != null) {
					        buf.append(" {P15 " +
					            P15.getX() + "," +
					            P15.getY() + "," +
					            P15.getZ() + "}");
					    }
					
					    if (P16 != null) {
					        buf.append(" {P16 " +
					            P16.getX() + "," +
					            P16.getY() + "," +
					            P16.getZ() + "}");
					    }
					
					    if (P17 != null) {
					        buf.append(" {P17 " +
					            P17.getX() + "," +
					            P17.getY() + "," +
					            P17.getZ() + "}");
					    }
					
					    if (P18 != null) {
					        buf.append(" {P18 " +
					            P18.getX() + "," +
					            P18.getY() + "," +
					            P18.getZ() + "}");
					    }
					
					    if (P19 != null) {
					        buf.append(" {P19 " +
					            P19.getX() + "," +
					            P19.getY() + "," +
					            P19.getZ() + "}");
					    }
					
					    if (P20 != null) {
					        buf.append(" {P20 " +
					            P20.getX() + "," +
					            P20.getY() + "," +
					            P20.getZ() + "}");
					    }
					
					    if (P21 != null) {
					        buf.append(" {P21 " +
					            P21.getX() + "," +
					            P21.getY() + "," +
					            P21.getZ() + "}");
					    }
					
					    if (P22 != null) {
					        buf.append(" {P22 " +
					            P22.getX() + "," +
					            P22.getY() + "," +
					            P22.getZ() + "}");
					    }
					
					    if (P23 != null) {
					        buf.append(" {P23 " +
					            P23.getX() + "," +
					            P23.getY() + "," +
					            P23.getZ() + "}");
					    }
					
					    if (P24 != null) {
					        buf.append(" {P24 " +
					            P24.getX() + "," +
					            P24.getY() + "," +
					            P24.getZ() + "}");
					    }
					
					    if (P25 != null) {
					        buf.append(" {P25 " +
					            P25.getX() + "," +
					            P25.getY() + "," +
					            P25.getZ() + "}");
					    }
					
					    if (P26 != null) {
					        buf.append(" {P26 " +
					            P26.getX() + "," +
					            P26.getY() + "," +
					            P26.getZ() + "}");
					    }
					
					    if (P27 != null) {
					        buf.append(" {P27 " +
					            P27.getX() + "," +
					            P27.getY() + "," +
					            P27.getZ() + "}");
					    }
					
					    if (P28 != null) {
					        buf.append(" {P28 " +
					            P28.getX() + "," +
					            P28.getY() + "," +
					            P28.getZ() + "}");
					    }
					
					    if (P29 != null) {
					        buf.append(" {P29 " +
					            P29.getX() + "," +
					            P29.getY() + "," +
					            P29.getZ() + "}");
					    }
					
					    if (P30 != null) {
					        buf.append(" {P30 " +
					            P30.getX() + "," +
					            P30.getY() + "," +
					            P30.getZ() + "}");
					    }
					
					    if (P31 != null) {
					        buf.append(" {P31 " +
					            P31.getX() + "," +
					            P31.getY() + "," +
					            P31.getZ() + "}");
					    }
					
   			return buf.toString();
   		}
 	
 		// --- Extra Java from XML BEGIN (extra/code/java)
        	
      		public SetRoute setRoute(List<? extends ILocated> route) {
      			if (route.size() == 0) return this;
      			setP0(route.get(0).getLocation());
      			if (route.size() == 1) return this;
      			setP1(route.get(1).getLocation());
      			if (route.size() == 2) return this;
      			setP2(route.get(2).getLocation());
      			if (route.size() == 3) return this;
      			setP3(route.get(3).getLocation());
      			if (route.size() == 4) return this;
      			setP4(route.get(4).getLocation());
      			if (route.size() == 5) return this;
      			setP5(route.get(5).getLocation());
      			if (route.size() == 6) return this;
      			setP6(route.get(6).getLocation());
      			if (route.size() == 7) return this;
      			setP7(route.get(7).getLocation());
      			if (route.size() == 8) return this;
      			setP8(route.get(8).getLocation());
      			if (route.size() == 9) return this;
      			setP9(route.get(9).getLocation());
      			if (route.size() == 10) return this;
      			setP10(route.get(10).getLocation());
      			if (route.size() == 11) return this;
      			setP11(route.get(11).getLocation());
      			if (route.size() == 12) return this;
      			setP12(route.get(12).getLocation());
      			if (route.size() == 13) return this;
      			setP13(route.get(13).getLocation());
      			if (route.size() == 14) return this;
      			setP14(route.get(14).getLocation());
      			if (route.size() == 15) return this;
      			setP15(route.get(15).getLocation());
      			if (route.size() == 16) return this;
      			setP16(route.get(16).getLocation());
      			if (route.size() == 17) return this;
      			setP17(route.get(17).getLocation());
      			if (route.size() == 18) return this;
      			setP18(route.get(18).getLocation());
      			if (route.size() == 19) return this;
      			setP19(route.get(19).getLocation());
      			if (route.size() == 20) return this;
      			setP20(route.get(20).getLocation());
      			if (route.size() == 21) return this;
      			setP21(route.get(21).getLocation());
      			if (route.size() == 22) return this;
      			setP22(route.get(22).getLocation());
      			if (route.size() == 23) return this;
      			setP23(route.get(23).getLocation());
      			if (route.size() == 24) return this;
      			setP24(route.get(24).getLocation());
      			if (route.size() == 25) return this;
      			setP25(route.get(25).getLocation());
      			if (route.size() == 26) return this;
      			setP26(route.get(26).getLocation());
      			if (route.size() == 27) return this;
      			setP27(route.get(27).getLocation());
      			if (route.size() == 28) return this;
      			setP28(route.get(28).getLocation());
      			if (route.size() == 29) return this;
      			setP29(route.get(29).getLocation());
      			if (route.size() == 30) return this;
      			setP30(route.get(30).getLocation());
      			if (route.size() == 31) return this;
      			setP31(route.get(31).getLocation());
      			return this;
      		}
      	
		// --- Extra Java from XML END (extra/code/java)
 	
	        }
    	