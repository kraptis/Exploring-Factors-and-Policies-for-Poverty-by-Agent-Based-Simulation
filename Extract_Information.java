/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sim.app.poverty_v3;

import java.util.Vector;
import sim.engine.SimState;
import sim.engine.Steppable;

/**
 *
 * @author dinos
 */
public class Extract_Information implements Steppable{

    public static Vector<Double> apotelesmata_d1_sxetiki = new Vector();
    public static Vector<Double> apotelesmata_d1_apoliti = new Vector();

    public static Vector<Double> apotelesmata_d2_sxetiki = new Vector();
    public static Vector<Double> apotelesmata_d2_apoliti = new Vector();

    public static Vector<Double> apotelesmata_d1_sxetiki_single = new Vector();
    public static Vector<Double> apotelesmata_d1_sxetiki_single_with_children = new Vector();
    public static Vector<Double> apotelesmata_d1_sxetiki_couples = new Vector();
    public static Vector<Double> apotelesmata_d1_sxetiki_couples_with_children = new Vector();
    public static Vector<Double> apotelesmata_d1_apoliti_single = new Vector();
    public static Vector<Double> apotelesmata_d1_apoliti_single_with_children = new Vector();
    public static Vector<Double> apotelesmata_d1_apoliti_couples = new Vector();
    public static Vector<Double> apotelesmata_d1_apoliti_couples_with_children = new Vector();

    public static Vector<Double> apotelesmata_d2_sxetiki_single = new Vector();
    public static Vector<Double> apotelesmata_d2_sxetiki_single_with_children = new Vector();
    public static Vector<Double> apotelesmata_d2_sxetiki_couples = new Vector();
    public static Vector<Double> apotelesmata_d2_sxetiki_couples_with_children = new Vector();
    public static Vector<Double> apotelesmata_d2_apoliti_single = new Vector();
    public static Vector<Double> apotelesmata_d2_apoliti_single_with_children = new Vector();
    public static Vector<Double> apotelesmata_d2_apoliti_couples = new Vector();
    public static Vector<Double> apotelesmata_d2_apoliti_couples_with_children = new Vector();

    public static Vector<Double> apotelesmata_metanasteysis = new Vector();
    public static int count_immigration=0;
    public static Vector<Double> apotelesmata_anergeias = new Vector();

    public static Vector<Double> apotelesmata_polidiastatou = new Vector();

    private double temp1=0.0;
    private double temp2=0.0;
    private double temp3=0.0;
    private double temp3h=0.0;
    private double temp4=0.0;
    private double temp4h=0.0;
    private double temp5=0.0;
    private double temp5_2=0.0;
    private double temp5h=0.0;
    private double temp5a=0.0;
    private double temp5s=0.0;
    private double temp6=0.0;
    private double temp6_2=0.0;
    private double temp6h=0.0;
    private double temp6a=0.0;
    private double temp6s=0.0;
    private double temp7=0.0;
    private double temp7_2=0.0;
    private double temp7h=0.0;
    private double temp7a=0.0;
    private double temp7s=0.0;
    private double temp8=0.0;
    private double temp8_2=0.0;
    private double temp8h=0.0;
    private double temp8a=0.0;
    private double temp8s=0.0;
    private double temp9=0.0;
    private double temp9_2=0.0;
    private double temp9h=0.0;
    private double temp9a=0.0;
    private double temp9s=0.0;
    private double temp10=0.0;
    private double temp10_2=0.0;
    private double temp10h=0.0;
    private double temp10a=0.0;
    private double temp10s=0.0;
    private double temp11=0.0;
    private double temp11_2=0.0;
    private double temp11h=0.0;
    private double temp11a=0.0;
    private double temp11s=0.0;
    private double temp12=0.0;
    private double temp12_2=0.0;
    private double temp12h=0.0;
    private double temp12a=0.0;
    private double temp12s=0.0;

    private double temp_anergoi=0.0;
    private double temp_immigrants=0.0;

    private int count1=0;
    private int count2=0;
    private int count3=0;
    private int count4=0;
    private int count5=0;
    private int count6=0;
    private int count7=0;
    private int count8=0;
    private int count9=0;
    private int count10=0;
    private int count11=0;
    private int count12=0;
    private int count13=0;
    private int count14=0;
    private int count15=0;

    private int count2p=0;

    private double an =0.0;

    public void extract(){
        apotelesmata_d1_sxetiki=(Vector<Double>) apotelesmata_d1_sxetiki.clone();
        apotelesmata_d1_apoliti=(Vector<Double>) apotelesmata_d1_apoliti.clone();
        apotelesmata_d2_sxetiki=(Vector<Double>) apotelesmata_d2_sxetiki.clone();
        apotelesmata_d2_apoliti=(Vector<Double>) apotelesmata_d2_apoliti.clone();

        apotelesmata_d1_sxetiki_single=(Vector<Double>) apotelesmata_d1_sxetiki_single.clone();//singles
        apotelesmata_d1_apoliti_single=(Vector<Double>) apotelesmata_d1_apoliti_single.clone();
        apotelesmata_d2_sxetiki_single=(Vector<Double>) apotelesmata_d2_sxetiki_single.clone();
        apotelesmata_d2_apoliti_single=(Vector<Double>) apotelesmata_d2_apoliti_single.clone();

        apotelesmata_d1_sxetiki_single_with_children=(Vector<Double>) apotelesmata_d1_sxetiki_single_with_children.clone();//singles with children
        apotelesmata_d1_apoliti_single_with_children=(Vector<Double>) apotelesmata_d1_apoliti_single_with_children.clone();
        apotelesmata_d2_sxetiki_single_with_children=(Vector<Double>) apotelesmata_d2_sxetiki_single_with_children.clone();
        apotelesmata_d2_apoliti_single_with_children=(Vector<Double>) apotelesmata_d2_apoliti_single_with_children.clone();

        apotelesmata_d1_sxetiki_couples=(Vector<Double>) apotelesmata_d1_sxetiki_couples.clone();//couples without
        apotelesmata_d1_apoliti_couples=(Vector<Double>) apotelesmata_d1_apoliti_couples.clone();
        apotelesmata_d2_sxetiki_couples=(Vector<Double>) apotelesmata_d2_sxetiki_couples.clone();
        apotelesmata_d2_apoliti_couples=(Vector<Double>) apotelesmata_d2_apoliti_couples.clone();

        apotelesmata_d1_sxetiki_couples_with_children=(Vector<Double>) apotelesmata_d1_sxetiki_couples_with_children.clone();//couples withchildren
        apotelesmata_d1_apoliti_couples_with_children=(Vector<Double>) apotelesmata_d1_apoliti_couples_with_children.clone();
        apotelesmata_d2_sxetiki_couples_with_children=(Vector<Double>) apotelesmata_d2_sxetiki_couples_with_children.clone();
        apotelesmata_d2_apoliti_couples_with_children=(Vector<Double>) apotelesmata_d2_apoliti_couples_with_children.clone();

        apotelesmata_anergeias=(Vector<Double>) apotelesmata_anergeias.clone();
        apotelesmata_metanasteysis=(Vector<Double>) apotelesmata_metanasteysis.clone();
    }

    public void step(SimState state) {


        if(state.schedule.getSteps()<3)
            count_immigration=0;
        if(state.schedule.getSteps()>2){
         //   System.out.println("--------------------");
            System.out.println(SetParameters.poverty_treshold+"-------threshold-------------");
            System.out.println(SetParameters.absolute_poverty_treshold+"----absolute---threshold-------------");
            this.an=SetParameters.absolute_poverty_treshold;
            for(int i=0;i<Social_Norms.agents.size();i++){
              //  System.out.println(Social_Norms.agents.get(i).totalincome+"-----totalincome-------------count--"+Social_Norms.agents.get(i).id+"---age--"+Social_Norms.agents.get(i).age);
              // System.out.println(Social_Norms.agents.get(i).wage+"-----wage-------------count--"+Social_Norms.agents.get(i).id);
             //  System.out.println(an+"----an");
                if(Social_Norms.agents.get(i).totalincome<SetParameters.poverty_treshold && Social_Norms.agents.get(i).age>0){
                    this.temp3h=this.temp3h+(SetParameters.poverty_treshold-Social_Norms.agents.get(i).totalincome);

                    this.count1++;
                }

                if(Social_Norms.agents.get(i).totalincome<an && Social_Norms.agents.get(i).age>0 ){
                    temp4h=temp4h+(an-Social_Norms.agents.get(i).totalincome);
                     //System.out.println("poutseeeeessssssss");
                    this.count3++;
                }

                //anergoi
                if(Social_Norms.agents.get(i).wage==0 && Social_Norms.agents.get(i).age>71 && Social_Norms.agents.get(i).age<260){
                   // System.out.println("poutseeeeessssssss");
                    this.count2++;
                }
                if(Social_Norms.agents.get(i).age>71 && Social_Norms.agents.get(i).age<260){
                    this.count2p++;
                }

  //sxetiki gia omades
                if(Social_Norms.agents.get(i).totalincome<SetParameters.poverty_treshold && Social_Norms.agents.get(i).age>0 && Social_Norms.agents.get(i).children.size()==0 && Social_Norms.agents.get(i).partner.size()==0){
                    //singles
                    this.temp5h=this.temp5h+(SetParameters.poverty_treshold-Social_Norms.agents.get(i).totalincome);
                    this.count4++;
                }
                if(Social_Norms.agents.get(i).totalincome<SetParameters.poverty_treshold && Social_Norms.agents.get(i).age>0 && Social_Norms.agents.get(i).children.size()!=0 && Social_Norms.agents.get(i).partner.size()==0){
                    //singles with children
                    this.temp6h=this.temp6h+(SetParameters.poverty_treshold-Social_Norms.agents.get(i).totalincome);
                    this.count5++;
                }
                if(Social_Norms.agents.get(i).totalincome<SetParameters.poverty_treshold && Social_Norms.agents.get(i).age>0 && Social_Norms.agents.get(i).children.size()==0 && Social_Norms.agents.get(i).partner.size()!=0){
                    //couples without children
                    this.temp7h=this.temp7h+(SetParameters.poverty_treshold-Social_Norms.agents.get(i).totalincome);
                    this.count6++;
                }
                if(Social_Norms.agents.get(i).totalincome<SetParameters.poverty_treshold && Social_Norms.agents.get(i).age>0 && Social_Norms.agents.get(i).children.size()!=0 && Social_Norms.agents.get(i).partner.size()!=0){
                    //couples with children
                    this.temp8h=this.temp8h+(SetParameters.poverty_treshold-Social_Norms.agents.get(i).totalincome);
                    this.count7++;
                }

//apoliti gia omades
                if(Social_Norms.agents.get(i).totalincome<SetParameters.absolute_poverty_treshold && Social_Norms.agents.get(i).age>0 && Social_Norms.agents.get(i).children.size()==0 && Social_Norms.agents.get(i).partner.size()==0){
                    //singles
                    this.temp9h=this.temp9h+(SetParameters.absolute_poverty_treshold -Social_Norms.agents.get(i).totalincome);
                    this.count12++;
                }
                if(Social_Norms.agents.get(i).totalincome<SetParameters.absolute_poverty_treshold  && Social_Norms.agents.get(i).age>0 && Social_Norms.agents.get(i).children.size()!=0 && Social_Norms.agents.get(i).partner.size()==0){
                    //singles with children
                    this.temp10h=this.temp10h+(SetParameters.absolute_poverty_treshold -Social_Norms.agents.get(i).totalincome);
                    this.count13++;
                }
                if(Social_Norms.agents.get(i).totalincome<SetParameters.absolute_poverty_treshold  && Social_Norms.agents.get(i).age>0 && Social_Norms.agents.get(i).children.size()==0 && Social_Norms.agents.get(i).partner.size()!=0){
                    //couples without children
                    this.temp11h=this.temp11h+(SetParameters.absolute_poverty_treshold -Social_Norms.agents.get(i).totalincome);
                    this.count14++;
                }
                if(Social_Norms.agents.get(i).totalincome<SetParameters.absolute_poverty_treshold  && Social_Norms.agents.get(i).age>0 && Social_Norms.agents.get(i).children.size()!=0 && Social_Norms.agents.get(i).partner.size()!=0){
                    //couples with children
                    this.temp12h=this.temp12h+(SetParameters.absolute_poverty_treshold -Social_Norms.agents.get(i).totalincome);
                    this.count15++;
                }


//plithismos omadon
                if(Social_Norms.agents.get(i).age>0 && Social_Norms.agents.get(i).children.size()==0 && Social_Norms.agents.get(i).partner.size()==0){
                    //singles
                  
                    this.count8++;
                }
                if(Social_Norms.agents.get(i).age>0 && Social_Norms.agents.get(i).children.size()!=0 && Social_Norms.agents.get(i).partner.size()==0){
                    //singles with children
                    this.count9++;
                }
                if(Social_Norms.agents.get(i).age>0 && Social_Norms.agents.get(i).children.size()==0 && Social_Norms.agents.get(i).partner.size()!=0){
                    //couples without children
                    this.count10++;
                }
               if(Social_Norms.agents.get(i).age>0 && Social_Norms.agents.get(i).children.size()!=0 && Social_Norms.agents.get(i).partner.size()!=0){
                    //couples with children
                    this.count11++;
                }
            }
            //System.out.println(count1+"------------------count1--");
            System.out.println(Social_Norms.agents.size()+"------------------agents--");
            System.out.println(Social_Norms.boys_agents.size()+"------------------boys--");
            System.out.println(Social_Norms.girls_agents.size()+"----------------girloys--");
            System.out.println(Social_Norms.families.size()+"------------------familiess--");
            System.out.println(Social_Norms.couples.size()+"------------------couples--");
            System.out.println(Social_Norms.men_agents.size()+"------------------men--");
            System.out.println(Social_Norms.women_agents.size()+"------------------women--");
            this.temp1=((double)this.count1/Social_Norms.agents.size());//diktis 1 sxeitiki gia olous
            this.temp2=((double)this.count3/Social_Norms.agents.size());//diktis 1 apoliti  gia olous
            System.out.println("temp2"+this.count3);
            System.out.println("temp2"+this.temp2);
            this.temp3=this.temp3h/(this.count1*SetParameters.poverty_treshold);//diktis 2 sxetiki gia olous
            this.temp4=this.temp4h/(this.count3*SetParameters.absolute_poverty_treshold);//diktis 2 apoliti gia olous
            
           

            if(this.count9>0 && this.count10>0 && this.count11>0 && this.count8>0){

            this.temp5s=(double)(this.count4/this.count8);//diktis 1 sxetiki gia single
            this.temp5a=this.count12/this.count8;//diktis 1 apoliti gia single
            this.temp5=this.temp5h/(this.count4*SetParameters.poverty_treshold);
            this.temp9=this.temp9h/(this.count12*SetParameters.absolute_poverty_treshold);

            this.temp6s=(double)(this.count5/this.count9);//diktis 1 sxetiki gia singles with children
            this.temp6a=this.count13/this.count9;//diktis 1 apoliti
            this.temp6=this.temp6h/(this.count5*SetParameters.poverty_treshold);
            this.temp10=this.temp10h/(this.count13*SetParameters.absolute_poverty_treshold);
            

            this.temp7s=(double)(this.count6/this.count10);//diktis 1 sxetiki gia couples without
            this.temp7a=this.count14/this.count10;//diktis 1 apoliti
            this.temp7=this.temp7h/(this.count6*SetParameters.poverty_treshold);
            this.temp11=this.temp11h/(this.count14*SetParameters.absolute_poverty_treshold);

            this.temp8s=(double)(this.count7/this.count11);//diktis 1 sxetiki gia couples with children
            this.temp8a=this.count15/this.count11;//diktis 1 apoliti gia
            this.temp8=this.temp8h/(this.count7*SetParameters.poverty_treshold);
            this.temp12=this.temp12h/(this.count15*SetParameters.absolute_poverty_treshold);
            }
            if(count2p>0){
            this.temp_anergoi=(double)(this.count2)/this.count2p;
            
            }
            else this.temp_anergoi=0.0;

            this.temp_immigrants=(double)count_immigration/Social_Norms.agents.size();


         //   System.out.println(temp1+"temp1");
            System.out.println(count2+"count2"+count2p);
            System.out.println(temp_immigrants+"count2"+Social_Norms.agents.size());
            apotelesmata_d1_sxetiki.add(this.temp1);//oloi
            apotelesmata_d1_apoliti.add(this.temp2);
            apotelesmata_d2_sxetiki.add(this.temp3);
            apotelesmata_d2_apoliti.add(this.temp4);

            apotelesmata_d1_sxetiki_single.add(this.temp5s);//singles
            apotelesmata_d1_apoliti_single.add(this.temp5a);
            apotelesmata_d2_sxetiki_single.add(this.temp5);
            apotelesmata_d2_apoliti_single.add(this.temp9);

            apotelesmata_d1_sxetiki_single_with_children.add(this.temp6s);//singles with children
            apotelesmata_d1_apoliti_single_with_children.add(this.temp6a);
            apotelesmata_d2_sxetiki_single_with_children.add(this.temp6);
            apotelesmata_d2_apoliti_single_with_children.add(this.temp10);
            
            apotelesmata_d1_sxetiki_couples.add(this.temp7s);//couples without
            apotelesmata_d1_apoliti_couples.add(this.temp7a);
            apotelesmata_d2_sxetiki_couples.add(this.temp7);
            apotelesmata_d2_apoliti_couples.add(this.temp11);

            apotelesmata_d1_sxetiki_couples_with_children.add(this.temp8s);//couples withchildren
            apotelesmata_d1_apoliti_couples_with_children.add(this.temp8a);
            apotelesmata_d2_sxetiki_couples_with_children.add(this.temp8);
            apotelesmata_d2_apoliti_couples_with_children.add(this.temp12);

            apotelesmata_anergeias.add(this.temp_anergoi);
            apotelesmata_metanasteysis.add(this.temp_immigrants);



            this.temp1=0;
            this.temp2=0;
            this.temp3=0;
            this.temp3h=0;
            this.temp4=0;
            this.temp4h=0;
            this.count3=0;
            this.count4=0;
            this.count1=0;
            this.count2=0;
          this. temp1=0.0;
    this.temp2=0.0;
    this.temp3=0.0;
    this.temp3h=0.0;
    this.temp4=0.0;
    this.temp4h=0.0;
    this.temp5=0.0;
    this.temp5_2=0.0;
   this.temp5h=0.0;
   this.temp5a=0.0;
   this.temp5s=0.0;
    this.temp6=0.0;
     this.temp6_2=0.0;
     this.temp6h=0.0;
     this.temp6a=0.0;
     this.temp6s=0.0;
     this.temp7=0.0;
     this.temp7_2=0.0;
     this.temp7h=0.0;
     this.temp7a=0.0;
     this.temp7s=0.0;
     this.temp8=0.0;
     this.temp8_2=0.0;
     this.temp8h=0.0;
     this.temp8a=0.0;
     this.temp8s=0.0;
     this.temp9=0.0;
     this.temp9_2=0.0;
     this.temp9h=0.0;
     this.temp9a=0.0;
     this.temp9s=0.0;
     this.temp10=0.0;
     this.temp10_2=0.0;
     this.temp10h=0.0;
     this.temp10a=0.0;
     this.temp10s=0.0;
     this.temp11=0.0;
     this.temp11_2=0.0;
     this.temp11h=0.0;
     this.temp11a=0.0;
     this.temp11s=0.0;
     this.temp12=0.0;
     this.temp12_2=0.0;
     this.temp12h=0.0;
     this.temp12a=0.0;
     this.temp12s=0.0;

     this.temp_anergoi=0.0;
     this.temp_immigrants=0.0;

    this.count1=0;
    this.count2=0;
     this.count3=0;
     this.count4=0;
    this.count5=0;
    this.count6=0;
    this.count7=0;
    this.count8=0;
    this.count9=0;
    this.count10=0;
    this.count11=0;
    this.count12=0;
    this.count13=0;
    this.count14=0;
    this.count15=0;

   this.count2p=0;
   count_immigration=0;
        }
    }

}
