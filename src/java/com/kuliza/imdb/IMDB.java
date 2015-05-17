package com.kuliza.imdb;

import java.io.*;
import java.net.*;
import java.util.*;

public class IMDB{
 
 public static void main(String []str){
   
  URL url = null;
  Scanner sc = null;
  String apiurl="http://www.imdb.com/chart/top?ref_=nv_sr_1";
  String moviename=null;  
  String dataurl=null;
  String retdata=null;
  InputStream is = null;
  DataInputStream dis = null;
  
  

  try{

   dataurl=apiurl;
      
   System.out.println("Getting data from service");
   System.out.println("########################################");
   
   url = new URL(dataurl);   
   
   is = url.openStream();
   dis  = new DataInputStream(is);
   
   String details[];
   //Reading data from url
   while((retdata = dis.readLine())!=null){
    //Indicates that movie does not exist in IMDB databse
    if(retdata.equals("error|Film not found")){
     System.out.println("No such movie found");
     break;
    }
   
    System.out.print(retdata);
    System.out.println();
       
   }  
      
  }  
  catch(Exception e){
   System.out.println(e);
  }
  finally{
   try{
   
    if(dis!=null){
     dis.close();
    }
    
    if(is!=null){
     is.close();
    }
    
    if(sc!=null){
     sc.close();
    }
   }
   catch(Exception e2){
    ;
   }
  }
   
     
 }

}

