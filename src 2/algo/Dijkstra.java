//
//  Dijkstra.java
//  Programme
//
//  Created by Stéphane jeandeaux on 04/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package programme.algo;
import  programme.graph.GraphAbility;
import  programme.graph.Edge;
import  programme.graph.Node;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;


public class Dijkstra {

	public Hashtable<Integer,Integer> prec;
	public Hashtable<Integer,Double> s;
	
	public GraphAbility g=null;
	public Node depart=null;
	
	public Dijkstra(GraphAbility gr,Node n)
	{
		int deb=n.getNum();
	
		prec=new Hashtable<Integer,Integer>();
		s=new Hashtable<Integer,Double>();
		
		Hashtable<Integer,Double> t=new Hashtable<Integer,Double>();
		
		
		String str;
		Edge tmp;
		int i,k;
		setGraph(gr);
		depart=getGraph().getNode(deb);
		if(!g.isNode(deb)){
			prec=null;
			s=null;
			return;
		}
		
	
		s.put(deb,0.0);
		prec.put(deb,deb);
		Enumeration<Integer> eN=getGraph().getNumNodes();
		while(eN.hasMoreElements()){
			i=eN.nextElement();
			str="("+deb+","+i+")";
			if((tmp=g.getEdge(str))==null){
				t.put(i,Double.MAX_VALUE);
				prec.put(i,-1);
			}
			else{
				t.put(i,tmp.getValue());
				prec.put(i,deb);
			}
		}
		
		while(!t.isEmpty()){
			i=getMin(t);
			s.put(i,t.get(i));
			if(t.get(i)==Double.MAX_VALUE)
				return;
			t.remove(i);
			Enumeration <Integer>e=t.keys();
			while(e.hasMoreElements()){
				k=e.nextElement();
				str="("+i+","+k+")";
					if((tmp=g.getEdge(str))!=null){
						if(s.get(i)+tmp.getValue()<t.get(k)){
							t.put(k,s.get(i)+tmp.getValue());
							prec.put(k,i);
						}
					}
			
			}
			
		}

	}
	private  int getMin(Hashtable<Integer,Double> t){
		Enumeration<Integer> e1=t.keys();
		int min=e1.nextElement();
		int i;
		while(e1.hasMoreElements()){
			i=e1.nextElement();
			if(t.get(i)<t.get(min))
				min=i;
		}
		return min;
	}
				
	public String toString(){
		String str="";
		String valeur="";
		int num;
		Node n;
		Enumeration<Integer> e=s.keys();
		str+="La node de depart est "+depart+"\n";
		while(e.hasMoreElements()){
			num=e.nextElement();
			if(num!=depart.getNum()){
				if(s.get(num)!=Double.MAX_VALUE)
					str+="Le plus court chemin pour aller a la node "+getGraph().getNode(num)+" a pour valeur : "+s.get(num)+"\n";
			}
		}
		return str;
	}
	public void paint(Graphics gr,int rayon){
		Enumeration<Integer> e=prec.keys();
		String str;
		Edge tmpE;
		Color tmpC;
		int f,t;
		while(e.hasMoreElements()){
			t=e.nextElement();
			f=prec.get(t);
			str="("+f+","+t+")";
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


}
