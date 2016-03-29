/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sim.app.poverty_v3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import sim.engine.SimState;
import sim.engine.Steppable;

/**
 *
 * @author dinos
 */
public class Deserialization implements Steppable,java.io.Serializable{

    Economic_Structure est = null;
    Natural_Environment nen = null;
    Social_Norms sno = null;
    Demographic_Characteristics dch = null;
    Institutions inst = null;

    public void step(SimState state) {

    }

    void Deserialization() {

        //if(state.schedule.getSteps()==0){
            
            try{
                FileInputStream fileIn1 =new FileInputStream("serial_es1.ser");
                ObjectInputStream in1 = new ObjectInputStream(fileIn1);
                est = (Economic_Structure) in1.readObject();
                in1.close();
                fileIn1.close();
            }
            catch(IOException i){
                i.printStackTrace();
                return;
            }
            catch(ClassNotFoundException c){
                System.out.println(".Economic_Structure class not found");
                c.printStackTrace();
                return;
            }

            System.out.println("yeah"+est.buisness.size());

            //na kano oles tis klaseis kai na dino tis palies times stis kainourgies!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

            try{
                FileInputStream fileIn2 =new FileInputStream("serial_ne1.ser");
                ObjectInputStream in2 = new ObjectInputStream(fileIn2);
                nen = (Natural_Environment) in2.readObject();
                in2.close();
                fileIn2.close();
            }
            catch(IOException i){
                i.printStackTrace();
                return;
            }
            catch(ClassNotFoundException c){
                System.out.println(".ne class not found");
                c.printStackTrace();
                return;
            }

            System.out.println("yeah2"+nen.natural_resources);

            try{
                FileInputStream fileIn3 =new FileInputStream("serial_sn1.ser");
                ObjectInputStream in3 = new ObjectInputStream(fileIn3);
                sno = (Social_Norms) in3.readObject();
                in3.close();
                fileIn3.close();
            }
            catch(IOException i){
                i.printStackTrace();
                return;
            }
            catch(ClassNotFoundException c){
                System.out.println(".sn class not found");
                c.printStackTrace();
                return;
            }

            try{
                FileInputStream fileIn4 =new FileInputStream("serial_dc1.ser");
                ObjectInputStream in4 = new ObjectInputStream(fileIn4);
                dch = (Demographic_Characteristics) in4.readObject();
                in4.close();
                fileIn4.close();
            }
            catch(IOException i){
                i.printStackTrace();
                return;
            }
            catch(ClassNotFoundException c){
                System.out.println(".dc class not found");
                c.printStackTrace();
                return;
            }

            try{
                FileInputStream fileIn5 =new FileInputStream("serial_ins1.ser");
                ObjectInputStream in5 = new ObjectInputStream(fileIn5);
                inst = (Institutions) in5.readObject();
                in5.close();
                fileIn5.close();
            }
            catch(IOException i){
                i.printStackTrace();
                return;
            }
            catch(ClassNotFoundException c){
                System.out.println(".ins class not found");
                c.printStackTrace();
                return;
            }

       // }
    }

}
