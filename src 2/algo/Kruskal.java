//
//  Dijkstra.java
//  Programme
//
//  Created by Stéphane jeandeaux on 04/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package programme.algo;
import  programme.graph.GraphAbility;
import  programme.graph.Tree;
import  programme.graph.Edge;
import  programme.graph.Node;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;


public class Kruskal {

	public Vector<String> edges;

	
	public GraphAbility g=null;
	public Tree tree=null;
	
	public Kruskal(GraphAbility gr)
	{
		LinkedList<Edge> treeEdges=new LinkedList<Edge>();
		Enumeration<Edge> e1=gr.getEdges();
		Node n;
		while(e1.hasMoreElements())
			treeEdges.add(e1.nextElement());
		Collections.sort(treeEdges);
		System.out.println(treeEdges);
		edges=new Vector<String>();
		setGraph(gr);
		Edge tmp1,tmp2;
		tree=new Tree(getGraph().getGraphDirected());
		Enumeration<Node> e2=getGraph().getNodes();
		while(e2.hasMoreElements()){
			 n=e2.nextElement().clone();
			 n.removeAllPrec();
			 n.removeAllNext();
			 tree.addNode(n);
		}
		while(!treeEdges.isEmpty()){
			tmp1=treeEdges.getFirst();
			tmp2=tree.addEdge(tree.getNode(tmp1.getFrom().getNum()),tree.getNode(tmp1.getTo().getNum()),tmp1.getValue());
			if(tmp2!=null)
				edges.add("("+tmp2.getFrom().getNum()+","+tmp2.getTo().getNum()+")");
			System.out.println(treeEdges.getFirst()+" "+tmp2);
			treeEdges.removeFirst();
		}
		
	}
	
	public void paint(Graphics gr,int rayon){
		System.out.println(this);
		Enumeration<String> e=edges.elements();
		String str;
		Edge tmpE;
		Color tmpC;
		while(e.hasMoreElements()){
			str=e.nextElement();
			tmpE=getGraph().getEdge(str);
			if(tmpE!=null){
				tmpC=tmpE.getColor();
				tmpE.setColor(Color.GREEN);
				tmpE.paint(gr,rayon);
				tmpE.setColor(tmpC);
			}
		}
	}
	
	public GraphAbility getGraph(){
		return g;
	}
	public void setGraph(GraphAbility g){
		this.g=g;
	}
	
	public String toString(){
		return edges+"";
	}
					
	
}

