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


public class Bipartite extends SimpleGraph implements Serializable, GraphAbility{

	public static final Color DEFAULT_COLOR_ONE=Color.RED;
	public static final Color DEFAULT_COLOR_TWO=Color.GREEN;

	
	public Bipartite(boolean graphDirected){
			super(graphDirected);
	}
	
	
		
	public Edge addEdge(Node from,Node to,double value){
		int sizeLFrom=from.nbNeighbors();
		int sizeLTo=to.nbNeighbors();
		
		if(sizeLFrom==0&&sizeLTo==0){
			from.setColor(DEFAULT_COLOR_ONE);
			to.setColor(DEFAULT_COLOR_TWO);
		}
		if(sizeLFrom==0&&sizeLTo!=0){
			if(to.getColor()==DEFAULT_COLOR_ONE)
				from.setColor(DEFAULT_COLOR_TWO);
			else
				from.setColor(DEFAULT_COLOR_ONE);
		}
		if(sizeLFrom!=0&&sizeLTo==0){
			if(from.getColor()==DEFAULT_COLOR_ONE)
				to.setColor(DEFAULT_COLOR_TWO);
			else
				to.setColor(DEFAULT_COLOR_ONE);
		}
		if(from.getColor()==to.getColor())
			return null;
		return super.addEdge(from,to,value);
		
		
	}
	
}
