//
//  MyFilter.java
//  stage
//
//  Created by Stéphane jeandeaux on 25/05/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package  programme.boxDialog;
import java.io.File;
import java.io.FilenameFilter;

public class MyFilter implements FilenameFilter {
String []lesSuffixes;
    String  laDescription;
 
   public MyFilter(String []lesSuffixes, String laDescription){
        this.lesSuffixes = lesSuffixes;
        this.laDescription = laDescription;
   }
 
   boolean appartient( String suffixe ){
      for( int i = 0; i<lesSuffixes.length; ++i){
          if(suffixe.equals(lesSuffixes[i])) return true;
      }
      return false;
   }
 
   public boolean accept(File dir,String name) {
     String suffixe = null;

     int i = name.lastIndexOf('.');
    if (i > 0 &&  i < name.length() - 1) {
         suffixe = name.substring(i+1).toLowerCase();
     }
     return suffixe != null && appartient(suffixe);
   }
 
   // la description du filtre
   public String getDescription() {
       return laDescription;
   }

}
