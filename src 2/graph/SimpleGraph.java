//
//  Graph.java
//  stage
//
//  Created by Stéphane jeandeaux on 22/05/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package programme.graph;
import java.util.*;
import java.io.*;
import java.awt.*;


public class SimpleGraph extends Graph implements GraphAbility{


	
	public SimpleGraph(boolean graphDirected){
			super(graphDirected);
	}
	
		
	public Edge addEdge(int from,int to,double value){
		if(from==to)
			return null;
		return super.addEdge(from,to,value);
	}
	public int getMaxNbEdges(){ 
		int tmp=getNbNodes()*(getNbNodes()-1);
		if(getGraphDirected()==true)
			return tmp;
		else
			return tmp/2;
	}
}
