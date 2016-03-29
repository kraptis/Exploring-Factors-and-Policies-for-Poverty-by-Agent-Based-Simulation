/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sim.app.poverty_v3;

import java.util.Random;
import java.util.Vector;

/**
 *
 * @author dinos
 */
public class Buisness {

    //Generator gen = new Generator();
    Random generator = new Random();

    //---katastasi perivallontos pou xreiazontai gia na doulepsoun----
    //1 tous endiaferei, 0 den tous endiaferei
    public Integer climate=0;
    public Integer natural_resources=0;
    public Double isolation=0.0;
    public Integer minorities=0;
    public Integer social_norms=0;
    public Integer institutions=0;//0-10 simperifora ton institouton
    //----------------------------------------------------------------

    //-------xaraktiristika tis kathe etairias----------------------
    Integer skills_levels=10;
    Vector<Vector<Integer>> characteristics = new Vector();
    
    //voithitikes
    double t;
    Vector<Integer> temp = new Vector();
    
    public void Buisness(int positions2){
        Integer skills=0;

        climate=generator.nextInt(11);
        natural_resources=generator.nextInt(11);
        isolation=generator.nextDouble()*10;
        minorities=generator.nextInt(11);
        institutions=generator.nextInt(11);


//!!!!!!!!!!!! prepei na eisago ton mikrotero kai ton megalitero mistho oste na ipologizete simfona me ta skills
        Integer min_wage=1900;
        Integer max_wage=2000;

       // int positions=gen.s_DCh.population-gen.s_DCh.unemployment;
        //int positions=0;//!!!!!!!!!!!!!!!!!!!!!!!!thelei allagi
        //Vector temp = new Vector();//prosoxi me temp mporei na min allazei tis times!!!!!!!!!!!!!!!
        //temp[skills, wage, hours, positions]
        Integer positions=positions2;
       // System.out.println(positions+"----positions--2-2-2-22-");
        for(int i=1;i<=skills_levels;i++){
            skills=i;//1-10
            temp.add(skills);
            temp.add(min_wage-((max_wage-min_wage)/9)+skills*((max_wage-min_wage)/9));
            if(skills<9)
                temp.add(8);
            else temp.add(10);
            if(skills==1 && positions!=0){
                t=0.3*positions;
                temp.add((int)t);
            }
            else if(skills==2&& positions!=0){
                t=0.2*positions;
                temp.add((int)t);
            }
            else if(skills==3&& positions!=0){
                t=0.2*positions;
                temp.add((int)t);
            }
            else if(skills==4&& positions!=0){
                t=0.15*positions;
                temp.add((int)t);
            }
            else if(skills==9 || skills==10 && positions!=0){
                t=0.1*positions;
                temp.add((int)t);
            }
            else{
                t=0.05*positions;
                temp.add((int)t);
            }
            //System.out.println("--------------------"+ temp.get(i) +"----------------------");
            characteristics.add((Vector<Integer>) temp.clone());
        //    System.out.println(temp.get(3)+"----positions--2-2-2-22-");
            temp.clear();
            //temp.clone();
        }
    }
     
     
}
