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
public class Generator {

  
  

    public Vector<Vector<Buisness>> buisness = new Vector();//[A(2),B(4),C(1)....]
    public Vector<Vector<Integer>> environment_char = new Vector();

    public Vector<Integer> pososta_plithismou = new Vector();
    public Vector<Integer> pososta_skills = new Vector();

    public Integer population=0;
        
    public Integer men=0;
    public Integer children=0;

    public Integer officials_behavior=0;// 0-10, 0 asximi- 10 aristi

    public Double immigration=0.0; // diathesi metansteysis tis koinotitas
    public Double education=0.0; // diathesi ekpaideysis tis koinotitas
    public Double quality_of_life=0.0;
    public Integer minorities=0;//
    public Vector buisness_norms = new Vector();//1h thesi buisness A, 2h thesi B etc.... 1 allowed, 0 not allowed
    
    public Integer accesibility=0;// 0-10
    public Integer techdevelop=0;// 0-10
    public Integer hostile=0;
    public Integer natural_resources=1;

    //voithitikes
    int temp1=0;

    public void economic_structure(){
        Set_Economic_Structure s_ES = new Set_Economic_Structure();
        s_ES.Set_Economic_Structure();
        
    }

    public void demographic_characteristics(){
        Set_Demographic_Characteristics s_DCh = new Set_Demographic_Characteristics();
        s_DCh.Set_Demographic_Characteristics();
        
    }

    public void natural_environment(){
        Set_Natural_Environment s_NE = new Set_Natural_Environment();
        s_NE.Set_Natural_Environment();
    }

    public void institutions(){
        Set_Institutions s_In = new Set_Institutions();
        s_In.Set_Institutions();
    }

    public void social_norms(){
        Set_Social_Norms s_SN = new Set_Social_Norms();
        s_SN.Set_Social_Norms();
      //  System.out.println("----asdasd----"+immigration+"Social_Norms.immigration=generator.immigration");
    }

    class Set_Demographic_Characteristics {

       // public int population=0;
        // pososta %
       // public int men=0;
        Integer women=0;
        Integer old=0;
        Integer single_women=0;
        Integer single_men=0;
        //Integer children=0;
        Integer immigrants=0;
        Integer single_women_with_children=0;
        Integer single_men_with_children=0;
        Integer unemployment=0;
        Integer temp=0;
        Integer temp2=0;
        public void Set_Demographic_Characteristics(){
            int random=1;
            if(random==1){
                Random generator = new Random();
                population=5000;//generator.nextInt(5000)+100;//100-5000
                men= generator.nextInt(55 - 45 + 1)+45;//45-55%
                women=100-men;
                old= generator.nextInt(20 - 5 + 1)+5;//5-20% ton men-women
                children=generator.nextInt(25 - 20 + 1)+20;//18-25% ton men-women
                single_men=generator.nextInt(25 - 5 + 1)+5;//5-25% ton men
                //single_women = single men arithmitika +/- tis ipolipes
                immigrants=generator.nextInt(6-2+1)+2;//2-6% tou sinolikou plithismou
                single_women_with_children=generator.nextInt(6)+1;//1-6% ton single women
                single_men_with_children=generator.nextInt(3)+1;//1-3% ton single men
                unemployment=generator.nextInt(20)+1;
               
                temp=(int) (population * 0.04);//0-4 ilikia
                pososta_plithismou.add(temp);

                temp=(int) (population * 0.05);//5-9
                pososta_plithismou.add(temp);

                temp=(int) (population * 0.05);//10-14
                pososta_plithismou.add(temp);

                temp=(int) (population * 0.07);//15-19
                pososta_plithismou.add(temp);

                temp=(int) (population * 0.08);//20-24
                pososta_plithismou.add(temp);

                temp=(int) (population * 0.08);//25-29
                pososta_plithismou.add(temp);

                temp=(int) (population * 0.09);//30-34
                pososta_plithismou.add(temp);

                temp=(int) (population * 0.08);//35-39
                pososta_plithismou.add(temp);

                temp=(int) (population * 0.07);//40-44
                pososta_plithismou.add(temp);

                temp=(int) (population * 0.07);//45-49
                pososta_plithismou.add(temp);

                temp=(int) (population * 0.06);//50-54
                pososta_plithismou.add(temp);

                temp=(int) (population * 0.05);//55-59
                pososta_plithismou.add(temp);

                temp=(int) (population * 0.06);//60-64
                pososta_plithismou.add(temp);

                temp=(int) (population * 0.06);//65-69
                pososta_plithismou.add(temp);

                temp=(int) (population * 0.05);//70-74
                pososta_plithismou.add(temp);

                temp=(int) (population * 0.03);//75-79
                pososta_plithismou.add(temp);

                temp=(int) (population * 0.02);//80-84
                pososta_plithismou.add(temp);

                temp=(int) (population * 0.01);//84+
                pososta_plithismou.add(temp);


                temp=(int) (population * 0.35);
                pososta_skills.add(temp);

                temp=(int) (population * 0.12);
                pososta_skills.add(temp);

                temp=(int) (population * 0.11);
                pososta_skills.add(temp);

                temp=(int) (population * 0.11);
                pososta_skills.add(temp);

                temp=(int) (population * 0.05);
                pososta_skills.add(temp);

                temp=(int) (population * 0.05);
                pososta_skills.add(temp);

                temp=(int) (population * 0.05);
                pososta_skills.add(temp);

                temp=(int) (population * 0.05);
                pososta_skills.add(temp);

                temp=(int) (population * 0.01);
                pososta_skills.add(temp);

                temp=(int) (population * 0.01);
                pososta_skills.add(temp);
            }
        }
    }

    class Set_Natural_Environment {

        
        public void Set_Natural_Environment(){
            int random=1;
            if(random==1){
                Random generator = new Random();
                accesibility = generator.nextInt(6)+5;
                techdevelop = generator.nextInt(6)+5;
                hostile = generator.nextInt(6)+5;
                natural_resources = generator.nextInt(6)+5;
            }
        }
    }

    class Set_Economic_Structure {
        Integer typesofbuisness=0;
        public Integer unemployment=0;//??????????tin pernei tin timi?
       // public Vector<Vector<Buisness>> buisness = new Vector();//[A(2),B(4),C(1)....]
        Vector<Integer> numberofbuisness = new Vector<Integer>();
        //Buisness buis = new Buisness(2000);//(s_DCh.population-s_DCh.unemployment);
        Vector<Buisness> tempVector = new Vector();
        Random generator = new Random();


              
        
        public void Set_Economic_Structure(){
            Integer population10=1800;
            int random=1;
            if(random==1){
               

               typesofbuisness = generator.nextInt(21)+10;
               temp1=typesofbuisness;
              // System.out.println("--------------------"+ temp1 +"----------------------");
               Integer i1=0;
               Integer temp3=0;
               Integer ran1=0;
               Integer ran2=0;
               Integer a=0;
               Vector<Integer> temp100 = new Vector();

               Integer temp_climate=0;
               Integer temp_natural_resources=0;
               double temp_isolation=0.0;
               Integer temp_minorities=0;
               Integer temp_institutions=0;
               
               
               for(i1=0; i1<typesofbuisness; i1++){

                   Random r10 = new Random();
                   temp_climate=r10.nextInt(8);
                   temp_natural_resources=r10.nextInt(8);
                   temp_isolation=r10.nextDouble()*10;
                   temp_minorities=r10.nextInt(8);
                   temp_institutions=r10.nextInt(11);

                   temp100.add(temp_climate);
                   temp100.add(temp_natural_resources);
                   temp100.add((int)temp_isolation);
                   temp100.add(temp_minorities);
                   temp100.add(temp_institutions);

                   environment_char.add((Vector<Integer>) temp100.clone());
                   temp100.clear();

                   temp3=generator.nextInt(1)+1;
                   //System.out.println("--------------------"+ temp3 +"----------------------");
                   numberofbuisness.add(temp3);
                   //System.out.println("--------------------"+ numberofbuisness.get(i1) +"----------------------");
                   
               }
               //numberofbuisness[2,4,1.....]
                
              for(int j=0; j<typesofbuisness; j++){
                 
                   for(int i=0; i<numberofbuisness.get(j); i++){
                       ran1=(generator.nextInt(41)-20);
                       a=(int) (((population10 + ran1) / typesofbuisness) + 0.3 * ((population10 + ran1) / typesofbuisness));
                       //a=population10+ran1;
                      // System.out.println(a+"---a---a");
                       Buisness buis = new Buisness();
                       buis.Buisness(a);

                      tempVector.add(buis);
                  }
                   buisness.add((Vector<Buisness>) tempVector.clone());
                   tempVector.clear();

               }
    
    
               /*
                *
                */
            }
        }
    }

    class Set_Institutions {
        
        public void Set_Institutions(){
            int random=1;
            if(random==1){
                Random generator = new Random();
                officials_behavior=generator.nextInt(10)+1;
            }
        }
    }

    class Set_Social_Norms {
       
        public void Set_Social_Norms(){
            int random=1;
            if(random==1){
               Random generator = new Random();
               immigration = generator.nextDouble()*1000;
               education = generator.nextDouble()*1000;
                quality_of_life= generator.nextDouble()*1000;
               minorities = generator.nextInt(11);
               for(int i=0; i<temp1; i++)
                   buisness_norms.add(generator.nextInt(6)+5);
            }
        }
    }

}
