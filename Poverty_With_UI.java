/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sim.app.poverty_v3;
import sim.display.*;
import sim.engine.*;
import javax.swing.*;
import sim.portrayal.continuous.*;
import sim.portrayal.simple.*;
import sim.portrayal.FieldPortrayal2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.Portrayal2D;

/**
 *
 * @author dinos
 */
public class Poverty_With_UI extends GUIState{

    org.jfree.data.xy.XYSeries series;    // the data series we'll add to
    org.jfree.data.xy.XYSeries series2;
    sim.util.media.chart.TimeSeriesChartGenerator chart;  // the charting facility

    org.jfree.data.xy.XYSeries series_gap;    // the data series we'll add to
    org.jfree.data.xy.XYSeries series_gap2;
    sim.util.media.chart.TimeSeriesChartGenerator chart2;  // the charting facility    gia deytero deikti sto sinoliko plithismo

    org.jfree.data.xy.XYSeries series_singles;    // the data series we'll add to
    org.jfree.data.xy.XYSeries series_singles_wchildren;    // the data series we'll add to
    org.jfree.data.xy.XYSeries series_couples;
    org.jfree.data.xy.XYSeries series_couples_wchildren;
    sim.util.media.chart.TimeSeriesChartGenerator chart3;  // the charting facility

    org.jfree.data.xy.XYSeries series_singles2;
    org.jfree.data.xy.XYSeries series_singles_wchildren2;
    org.jfree.data.xy.XYSeries series_couples2;
    org.jfree.data.xy.XYSeries series_couples_wchildren2;
    sim.util.media.chart.TimeSeriesChartGenerator chart4;  // the charting facility

       // the data series we'll add to
    org.jfree.data.xy.XYSeries series_metanasteysi;
    sim.util.media.chart.TimeSeriesChartGenerator chart5;  // the charting facility

        // the data series we'll add to
    org.jfree.data.xy.XYSeries series_anergia;
    sim.util.media.chart.TimeSeriesChartGenerator chart6;  // the charting facility

  


    public static int temp=1;

    public Poverty_With_UI() { super(new Main( System.currentTimeMillis())); }
    public Poverty_With_UI(SimState state) { super(state); }
    public static String getName() { return "Poverty for US"; }

    public Display2D display;
    public JFrame displayFrame;
    ContinuousPortrayal2D communityPortrayal = new ContinuousPortrayal2D() {};
    ContinuousPortrayal2D communityPortrayal2 = new ContinuousPortrayal2D() {};
   

    public static void main(String[] args){

        Console c = new Console(new Poverty_With_UI());
        c.setVisible(true);
    }



    @Override
    public void init(Controller c){

        super.init(c);
        // make the displayer
        display = new Display2D(500,500,this,1);
        // turn off clipping
        display.setClipping(false);
        displayFrame = display.createFrame();
        displayFrame.setTitle("Community Display");
        // register the frame so it appears in the "Display" list
        c.registerFrame(displayFrame);
        displayFrame.setVisible(true);
        display.attach(communityPortrayal, "Community" );
        display.attach(communityPortrayal2, "Community" );




        
         chart = new sim.util.media.chart.TimeSeriesChartGenerator();
       chart.setTitle("Relative Poverty--(poverty rate)");
       chart.setRangeAxisLabel("Percentage Poor");
       chart.setDomainAxisLabel("Time");
       JFrame frame = chart.createFrame(this);
       // perhaps you might move the chart to where you like.
       frame.show();
       frame.pack();
       c.registerFrame(frame);

       // the console automatically moves itself to the right of all
       // of its registered frames -- you might wish to rearrange the
       // location of all the windows, including the console, at this
       // point in time....

       chart2 = new sim.util.media.chart.TimeSeriesChartGenerator();
       chart2.setTitle("Relative Poverty--(income gap ratio)");
       chart2.setRangeAxisLabel("Income Gap");
       chart2.setDomainAxisLabel("Time");
       JFrame frame2 = chart2.createFrame(this);
       // perhaps you might move the chart to where you like.
       frame2.show();
       frame2.pack();
       c.registerFrame(frame2);

       // the console automatically moves itself to the right of all
       // of its registered frames -- you might wish to rearrange the
       // location of all the windows, including the console, at this
       // point in time....


        /*chart3 = new sim.util.media.chart.TimeSeriesChartGenerator();
        chart3.setTitle("Poverty Percentage of Households--(poverty rate)");
        chart3.setRangeAxisLabel("Percentage Poor");
        chart3.setDomainAxisLabel("Time");
        JFrame frame3 = chart3.createFrame(this);
        // perhaps you might move the chart to where you like.
        frame3.show();
        frame3.pack();
        c.registerFrame(frame3);

        // the console automatically moves itself to the right of all
        // of its registered frames -- you might wish to rearrange the
        // location of all the windows, including the console, at this
        // point in time....


        chart4 = new sim.util.media.chart.TimeSeriesChartGenerator();
        chart4.setTitle("Poverty Percentage of Households--(income gap ratio)");
        chart4.setRangeAxisLabel("Percentage Poor");
        chart4.setDomainAxisLabel("Time");
        JFrame frame4 = chart4.createFrame(this);
        // perhaps you might move the chart to where you like.
        frame4.show();
        frame4.pack();
        c.registerFrame(frame4);

        // the console automatically moves itself to the right of all
        // of its registered frames -- you might wish to rearrange the
        // location of all the windows, including the console, at this
        // point in time....*/

        chart5 = new sim.util.media.chart.TimeSeriesChartGenerator();
        chart5.setTitle("Immigration");
        chart5.setRangeAxisLabel("Percentage Immigration");
        chart5.setDomainAxisLabel("Time");
        JFrame frame5 = chart5.createFrame(this);
        // perhaps you might move the chart to where you like.
        frame5.show();
        frame5.pack();
        c.registerFrame(frame5);

        // the console automatically moves itself to the right of all
        // of its registered frames -- you might wish to rearrange the
        // location of all the windows, including the console, at this
        // point in time....

        chart6 = new sim.util.media.chart.TimeSeriesChartGenerator();
        chart6.setTitle("Unemployment");
        chart6.setRangeAxisLabel("Percentage Unemployment");
        chart6.setDomainAxisLabel("Time");
        JFrame frame6 = chart6.createFrame(this);
        // perhaps you might move the chart to where you like.
        frame6.show();
        frame6.pack();
        c.registerFrame(frame6);

        // the console automatically moves itself to the right of all
        // of its registered frames -- you might wish to rearrange the
        // location of all the windows, including the console, at this
        // point in time....
    }

    @Override
    public void quit(){

        super.quit();
        if (displayFrame!=null) displayFrame.dispose();
        displayFrame = null;
        display = null;
    }

    @Override
    public void start(){

        super.start();
        setupPortrayals();


        chart.removeAllSeries();
        series = new org.jfree.data.xy.XYSeries(
            "Relative",
            false);

        /*series2 = new org.jfree.data.xy.XYSeries(
        "Absolute",
        false);*/

        chart.addSeries(series, null);
        //chart.addSeries(series2, null);


        chart2.removeAllSeries();
        series_gap = new org.jfree.data.xy.XYSeries(
            "Relative",
            false);

        /*series_gap2 = new org.jfree.data.xy.XYSeries(
        "Absolute",
        false);*/

        chart2.addSeries(series_gap, null);
        //chart2.addSeries(series_gap2, null);


        /*chart3.removeAllSeries();
        series_singles= new org.jfree.data.xy.XYSeries(
        "Singles",
        false);
        series_singles_wchildren= new org.jfree.data.xy.XYSeries(
        "Singles with Children",
        false);
        series_couples= new org.jfree.data.xy.XYSeries(
        "Couples",
        false);
        series_couples_wchildren= new org.jfree.data.xy.XYSeries(
        "Couples with Children",
        false);

        chart3.addSeries(series_singles,null);
        chart3.addSeries(series_singles_wchildren,null);
        chart3.addSeries(series_couples,null);
        chart3.addSeries(series_couples_wchildren,null);



        chart4.removeAllSeries();
        series_singles2= new org.jfree.data.xy.XYSeries(
        "Singles",
        false);
        series_singles_wchildren2= new org.jfree.data.xy.XYSeries(
        "Singles with Children",
        false);
        series_couples2= new org.jfree.data.xy.XYSeries(
        "Couples",
        false);
        series_couples_wchildren2= new org.jfree.data.xy.XYSeries(
        "Couples with Children",
        false);

        chart4.addSeries(series_singles2,null);
        chart4.addSeries(series_singles_wchildren2,null);
        chart4.addSeries(series_couples2,null);
        chart4.addSeries(series_couples_wchildren2,null);*/



        chart5.removeAllSeries();
        series_metanasteysi = new org.jfree.data.xy.XYSeries(
            "Immigration",
            false);

        chart5.addSeries(series_metanasteysi, null);

        chart6.removeAllSeries();
        series_anergia = new org.jfree.data.xy.XYSeries(
            "Unemployment",
            false);

        chart6.addSeries(series_anergia, null);


        //if(state.schedule.getSteps()>2)
        super.scheduleAtEnd(new Steppable()
            {
            public void step(SimState state)
               {
               // at this stage we're adding data to our chart.  We
               // need an X value and a Y value.  Typically the X
               // value is the schedule's timestamp.  The Y value
               // is whatever data you're extracting from your
               // simulation.  For purposes of illustration, let's
               // extract the number of steps from the schedule and
               // run it through a sin wave.

                double x=0.0;
                double y=0.0;
               
             
                 Extract_Information ei =new Extract_Information();
                 ei.extract();
                

                   for(int i=0;i<ei.apotelesmata_d1_sxetiki.size();i++){
                       x =i;
                       y =ei.apotelesmata_d1_sxetiki.get(i)*100;
          //     System.out.println("y+++"+y);
                       if (x >= state.schedule.EPOCH && x < state.schedule.AFTER_SIMULATION)
                           series.add(x, y, true);
                  // series2.add(x,2,true);
                   }
                   /*Random m = new Random();
                   double m2 =0.0;
                   for(int i=0;i<ei.apotelesmata_d1_apoliti.size();i++){
                   m2=m.nextInt(3)*10;
                   x =i;
                   y =ei.apotelesmata_d1_apoliti.get(i);//-m2*ei.apotelesmata_d1_apoliti.get(i);
                   System.out.println("y+++"+y);
                   if (x >= state.schedule.EPOCH && x < state.schedule.AFTER_SIMULATION)
                   series2.add(x, y, true);
                   // series2.add(x,2,true);
                   }*/



                  for(int i=0;i<ei.apotelesmata_d2_sxetiki.size();i++){
                       x =i;
                       y =ei.apotelesmata_d2_sxetiki.get(i)*100;
          //     System.out.println("y+++"+y);
                       if (x >= state.schedule.EPOCH && x < state.schedule.AFTER_SIMULATION)
                           series_gap.add(x, y, true);
                  // series2.add(x,2,true);
                   }

                   /*for(int i=0;i<ei.apotelesmata_d2_apoliti.size();i++){
                   x =i;
                   y =ei.apotelesmata_d2_apoliti.get(i);
                   //    System.out.println("y+++"+y);
                   if (x >= state.schedule.EPOCH && x < state.schedule.AFTER_SIMULATION)
                   series_gap2.add(x, y, true);
                   // series2.add(x,2,true);
                   }*/

                   /*for(int i=0;i<ei.apotelesmata_d1_sxetiki_single.size();i++){
                   x =i;
                   y =ei.apotelesmata_d1_sxetiki_single.get(i);
                   System.out.println("y+++"+y);
                   if (x >= state.schedule.EPOCH && x < state.schedule.AFTER_SIMULATION)
                   series_singles.add(x, y, true);
                   // series2.add(x,2,true);
                   }

                   for(int i=0;i<ei.apotelesmata_d1_sxetiki_single_with_children.size();i++){
                   x =i;
                   y =ei.apotelesmata_d1_sxetiki_single_with_children.get(i);
                   //    System.out.println("y+++"+y);
                   if (x >= state.schedule.EPOCH && x < state.schedule.AFTER_SIMULATION)
                   series_singles_wchildren.add(x, y, true);
                   // series2.add(x,2,true);
                   }

                   for(int i=0;i<ei.apotelesmata_d1_sxetiki_couples.size();i++){
                   x =i;
                   y =ei.apotelesmata_d1_sxetiki_couples.get(i);
                   //   System.out.println("y+++"+y);
                   if (x >= state.schedule.EPOCH && x < state.schedule.AFTER_SIMULATION)
                   series_couples.add(x, y, true);
                   // series2.add(x,2,true);
                   }

                   for(int i=0;i<ei.apotelesmata_d1_sxetiki_couples_with_children.size();i++){
                   x =i;
                   y =ei.apotelesmata_d1_sxetiki_couples_with_children.get(i);
                   // System.out.println("y+++"+y);
                   if (x >= state.schedule.EPOCH && x < state.schedule.AFTER_SIMULATION)
                   series_couples_wchildren.add(x, y, true);
                   // series2.add(x,2,true);
                   }




                   for(int i=0;i<ei.apotelesmata_d2_sxetiki_single.size();i++){
                   x =i;
                   y =ei.apotelesmata_d2_sxetiki_single.get(i);
                   System.out.println("y+++"+y);
                   if (x >= state.schedule.EPOCH && x < state.schedule.AFTER_SIMULATION)
                   series_singles2.add(x, y, true);
                   // series2.add(x,2,true);
                   }

                   for(int i=0;i<ei.apotelesmata_d2_sxetiki_single_with_children.size();i++){
                   x =i;
                   y =ei.apotelesmata_d2_sxetiki_single_with_children.get(i);
                   System.out.println("y+++"+y);
                   if (x >= state.schedule.EPOCH && x < state.schedule.AFTER_SIMULATION)
                   series_singles_wchildren2.add(x, y, true);
                   // series2.add(x,2,true);
                   }

                   for(int i=0;i<ei.apotelesmata_d2_sxetiki_couples.size();i++){
                   x =i;
                   y =ei.apotelesmata_d2_sxetiki_couples.get(i);
                   System.out.println("y+++"+y);
                   if (x >= state.schedule.EPOCH && x < state.schedule.AFTER_SIMULATION)
                   series_couples2.add(x, y, true);
                   // series2.add(x,2,true);
                   }

                   for(int i=0;i<ei.apotelesmata_d2_sxetiki_couples_with_children.size();i++){
                   x =i;
                   y =ei.apotelesmata_d2_sxetiki_couples_with_children.get(i);
                   System.out.println("y+++"+y);
                   if (x >= state.schedule.EPOCH && x < state.schedule.AFTER_SIMULATION)
                   series_couples_wchildren2.add(x, y, true);
                   // series2.add(x,2,true);
                   }*/


                 for(int i=0;i<ei.apotelesmata_anergeias.size();i++){
                       x =i;
                       y =ei.apotelesmata_anergeias.get(i)*100;
          //     System.out.println("y+++"+y);
                       if (x >= state.schedule.EPOCH && x < state.schedule.AFTER_SIMULATION)
                           series_anergia.add(x, y, true);
                  // series2.add(x,2,true);
                   }

                 for(int i=0;i<ei.apotelesmata_metanasteysis.size();i++){
                    x =i;
                    y =ei.apotelesmata_metanasteysis.get(i)*100;
                //    System.out.println("y+++"+y);
                    if (x >= state.schedule.EPOCH && x < state.schedule.AFTER_SIMULATION)
                        series_metanasteysi.add(x, y, true);
                        // series2.add(x,2,true);
                 }

               }
              

               

               // now add the data
               
           });
    }

    @Override
    public void load(SimState state){

        super.load(state);
        setupPortrayals();
    }

    public void setupPortrayals(){

        Main main = (Main) state;
        // tell the portrayals what to portray and how to portray them
        communityPortrayal.setField(main.community);
        communityPortrayal2.setField(main.community2);

        if(temp==0)
        communityPortrayal.setPortrayalForAll(new OvalPortrayal2D());
        else{
            communityPortrayal.setPortrayalForAll(new OvalPortrayal2D(){
            @Override
                public void draw(Object object, Graphics2D graphics, DrawInfo2D info)
                {
                    Economic_Structure es = (Economic_Structure)object;
                    paint = new Color(150,2,150);
                    super.draw(es, graphics, info);
                }
            });
            
            communityPortrayal2.setPortrayalForAll(new RectanglePortrayal2D(Color.GREEN));
        }
           
        /*communityPortrayal.setPortrayalForAll(new OvalPortrayal2D(){
            @Override
            public void draw(Object object, Graphics2D graphics, DrawInfo2D info){
                Economic_Structure es = (Economic_Structure)object;
               // Natural_Environment ne = (Natural_Environment)object;
                //if(es.mix==true)
                paint = new Color(70,0,50);
                super.draw(object, graphics, info);
            }
        });*/
        // reschedule the displayer
        display.reset();
        display.setBackdrop(Color.YELLOW);
    
        // redraw the display
        display.repaint();
    }

}
