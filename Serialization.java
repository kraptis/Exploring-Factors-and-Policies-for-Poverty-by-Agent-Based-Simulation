/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sim.app.poverty_v3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import sim.engine.SimState;
import sim.engine.Steppable;

/**
 *
 * @author dinos
 */
public class Serialization implements Steppable,java.io.Serializable{

    public void step(SimState state) {

    }

     void Serialization() {

      //  if(state.schedule.getSteps()==2){
        
            Economic_Structure es = new Economic_Structure();
            Natural_Environment ne = new Natural_Environment();
            Social_Norms sn = new Social_Norms();
            Demographic_Characteristics dc = new Demographic_Characteristics();
            Institutions ins = new Institutions();
            Economic_Structure est = null;

            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            //na prostheso oles tis klaseis

            System.out.println("serial es.buis.size"+es.buisness.size());

            try{
            FileOutputStream fileOut1 =new FileOutputStream("serial_es1.ser");
            ObjectOutputStream out1 = new ObjectOutputStream(fileOut1);
            out1.writeObject(es);
            out1.close();
            fileOut1.close();
            }
            catch(IOException i){
            i.printStackTrace();
            }






            try{
                FileOutputStream fileOut2 =new FileOutputStream("serial_ne1.ser");
                ObjectOutputStream out2 = new ObjectOutputStream(fileOut2);
                out2.writeObject(ne);
                out2.close();
                fileOut2.close();
            }
            catch(IOException i){
                i.printStackTrace();
            }



            try{
                FileOutputStream fileOut3 =new FileOutputStream("serial_sn1.ser");
                ObjectOutputStream out3 = new ObjectOutputStream(fileOut3);
                out3.writeObject(sn);
                out3.close();
                fileOut3.close();
            }
            catch(IOException i){
                i.printStackTrace();
            }

            try{
                FileOutputStream fileOut4 =new FileOutputStream("serial_dc1.ser");
                ObjectOutputStream out4 = new ObjectOutputStream(fileOut4);
                out4.writeObject(dc);
                out4.close();
                fileOut4.close();
            }
            catch(IOException i){
                i.printStackTrace();
            }

            try{
                FileOutputStream fileOut5 =new FileOutputStream("serial_ins1.ser");
                ObjectOutputStream out5 = new ObjectOutputStream(fileOut5);
                out5.writeObject(ins);
                out5.close();
                fileOut5.close();
            }
            catch(IOException i){
                i.printStackTrace();
            }
          
      //  }

    }

}
