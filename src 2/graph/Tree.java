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


public class Tree extends SimpleGraph implements Serializable, GraphAbility{

	
	public Tree(boolean directed){
		super(directed);

	}
		
	public Edge addEdge(Node from,Node to,double value){
		Edge tmpE= super.addEdge(from,to,value);
		if(!acyclic()){
			removeEdge(tmpE);
			tmpE=null;
		}
		return tmpE;
		
		
	}
	//0 pas visite
	//1 entrain d'etre visiste
	//2 fin de la visite
	Hashtable<Integer,Integer> t;
	private boolean acyclic(){
		init();
		int i;
		Enumeration<Integer> e=getNumNodes();
		while(e.hasMoreElements()){
			i=e.nextElement();
			if(t.get(i)==0&&cyclic(i,Integer.MAX_VALUE))
				return false;
		}
		return true;
	}
	private void init(){
		Enumeration<Integer> e=getNumNodes();
		t=new Hashtable<Integer,Integer>();
		int i;
		while(e.hasMoreElements()){
			i=e.nextElement();
			t.put(i,0);
		}
	}
	private boolean cyclic(int node,int pere){
		t.put(node,1);
		Iterator<Integer> i=getNode(node).neighbors();
		int j;
		while(i.hasNext()){
			j=i.next();
			if((isEdge("("+j+","+node+")")&&isEdge("("+node+","+j+")")))
				return true;
			if(t.get(j)==1&&pere!=j)
					return true;
			if(t.get(j)==0&&cyclic(j,node))
				return true;
		}
		t.put(node,2);
		return false;
	}
		
		
		
	public int getMaxNbEdges(){ 
		return getNbNodes()-1;

	}
}
