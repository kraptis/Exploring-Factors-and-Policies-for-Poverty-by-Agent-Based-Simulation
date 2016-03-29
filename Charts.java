/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sim.app.poverty_v3;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PlotState;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RefineryUtilities;
import sim.engine.SimState;
import sim.engine.Steppable;

/**
 *
 * @author dinos
 */
public class Charts implements Steppable{

    double temp=0.0;

    public void step(SimState state){

        if(state.schedule.getSteps()==7){
            // create a dataset...
            DefaultCategoryDataset data = new DefaultCategoryDataset();
             //final CategoryDataset dataset = createDataset();
             //final JFreeChart chart = createChart(dataset);
            //final ChartPanel chartPanel = new ChartPanel(chart);
            //chartPanel.setPreferredSize(new Dimension(500, 270));
           // setContentPane(chartPanel);
           // Plot plot = new Plot()

            
          //XYDataset dat = new XYDataset();
           //PlotOrientation p = new PlotOrientation();
           for(int i=0;i<Extract_Information.apotelesmata_d1_sxetiki.size();i++){
               temp=Extract_Information.apotelesmata_d1_sxetiki.get(i)*100;
            data.addValue(temp,"s" ,"s");
            data.addValue(i,"ou","ou");
            System.out.println("diagramata"+Extract_Information.apotelesmata_d1_sxetiki.get(i));
           }
            // create a chart...
            JFreeChart chart = ChartFactory.createLineChart("r", "k", "l",  data, PlotOrientation.VERTICAL, true, true, false);

            
            chart.setBackgroundPaint(Color.WHITE);
         

            final CategoryPlot plot = (CategoryPlot) chart.getPlot();
            plot.setBackgroundPaint(Color.darkGray);
            plot.setRangeGridlinePaint(Color.YELLOW);

            

            final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            rangeAxis.setAutoRangeIncludesZero(true);

             final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
//        renderer.setDrawShapes(true);

        renderer.setSeriesStroke(
            0, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {10.0f, 6.0f}, 0.0f
            )
        );
        renderer.setSeriesStroke(
            1, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {6.0f, 6.0f}, 0.0f
            )
        );
        renderer.setSeriesStroke(
            2, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {2.0f, 6.0f}, 0.0f
            )
        );

          



            // create and display a frame...
            ChartFrame frame = new ChartFrame("First", chart);

            frame.pack();
            frame.show();
            RefineryUtilities.centerFrameOnScreen(frame);
            frame.setVisible(true);
        }
    }

}
