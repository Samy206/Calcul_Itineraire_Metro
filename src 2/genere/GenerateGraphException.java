//
//  GenereGraph.java
//  stage
//
//  Created by Stéphane jeandeaux on 23/05/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package programme.genere;

 class GenerateGraphException extends Exception{
 private int num;
	public GenerateGraphException(int n){num=n;}
	public String toString(){
		if(num==0) return "bad nbNodes";
		if(num==1) return "bad nbEdges";
		if(num==2) return "bad nbNodes and nbEdges";
		return "";
	}
}
 