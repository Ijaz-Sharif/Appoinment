package com.example.sharif.appointment;

/**
 * Created by SHARIF on 8/13/2018.
 */

 public class Patient {
     String dr_name,p_name,Cnic,place,Day;
     public Patient(String a,String b,String c,String d,String e){
         this.p_name=a;
         this.Cnic=b;
         this.place=c;
         this.dr_name=d;
         this.Day=e;
     }
     public String getP_name(){
         return p_name;
     }
     public String getCnic(){
         return Cnic;
     }
     public String getPlace(){
         return place;
     }
     public String getDr_name(){
         return dr_name;
     }
     public String getDay(){
         return Day;
     }
}
