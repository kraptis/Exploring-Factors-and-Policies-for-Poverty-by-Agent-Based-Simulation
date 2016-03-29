/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sim.app.poverty_v3;

import sim.engine.SimState;
import sim.engine.Steppable;

/**
 *
 * @author dinos
 */
public class Natural_Environment implements Steppable,java.io.Serializable{

    

    public static Double isolation=0.0;
    public static Integer natural_resources=0;

    public static Integer accesibility =0;
    public static Integer techdevelop =0;

    public static Integer clima=0;
    //natural_resources n_u = new natural_resources();
    //isolation iso = new isolation();
    //climate cli = new climate();
    

    public void natural_environment(){
        natural_resources n_u = new natural_resources();
        n_u.natural_resources();

        isolation iso = new isolation();
        iso.isolation();

        climate cli = new climate();
        cli.climate();

    }

    public void step(SimState state) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    class natural_resources{
        //int yes;
        Generator generator = new Generator();
        public void natural_resources(){
            generator.natural_environment();
            natural_resources = generator.natural_resources;
        }
    }

    class isolation{
        Generator generator = new Generator();
        boolean state = false;
        public void isolation(){
            generator.natural_environment();
            accesibility =generator.accesibility;
            System.out.println("------Accesibility: "+accesibility);
            techdevelop = generator.techdevelop;
            System.out.println("------Tech. develop. : "+techdevelop);
            //double geoiso =0.0;
            isolation = (0.8 * accesibility + 0.2 * techdevelop);
          //  if(geoiso > 5)
          //      state = true;
         //   else state = false;
        }

    }

    class climate{
        //int hostile;
        Generator generator = new Generator();
        public void climate(){
            generator.natural_environment();
            clima = generator.hostile;
        }
        
    }

}
