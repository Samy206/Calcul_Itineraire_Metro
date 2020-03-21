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


public class Connexe {

	private Hashtable<Integer,Integer> num;
	private Hashtable<Integer,Integer> ncomp;
	private int k,l;
	private GraphAbility g=null;
	
	
	public Connexe(GraphAbility gr){
		int element;
		setGraph(gr);
		num=new Hashtable<Integer,Integer>();
		ncomp=new Hashtable<Integer,Integer>();
		k=1;l=0;
		Enumeration<Integer> e=getGraph().getNumNodes();
		while(e.hasMoreElements()){
			element=e.nextElement();
			num.put(element,0);
			ncomp.put(element,0);
		}
		e=getGraph().getNumNodes();
		while(e.hasMoreElements()){
			element=e.nextElement();
			if(num.get(element)==0){
				num.put(element,k);
				l++;
				ncomp.put(element,l);
					exploreCC(element);
			}
		}
	}
	
	public void exploreCC(int i){
		Enumeration<String> e=getGraph().getNameEdges();
		String str;
		int []t;
		int j;
		while(e.hasMoreElements()){
			str=e.nextElement();
			t=getGraph().edgeFromTo(str);
			if((t[0]==i&&num.get(t[1])==0)||(t[1]==i&&num.get(t[0])==0)){
				if(t[0]==i)
					j=t[1];
				else
					j=t[0];
				k++;
				num.put(j,k);
				ncomp.put(j,l);
				exploreCC(j);
			}
		}
	}
	
	public String toString(){
		String str="";
		String fin="";
		int i;
		int ind;
		Enumeration<Integer> e;
		if(l>1)
			fin="s";
		str="Il y a "+l+" composante"+fin+" connexe"+fin+"\n";
		i=l;
		while(i>0){
			e=ncomp.keys();
			str+="{ ";
			while(e.hasMoreElements()){
				ind=e.nextElement();
				if(ncomp.get(ind)==i)
					str+=getGraph().getNode(ind)+" ";
			}
			str+="}\n";
			i--;
		}
		return str;
	}
	
	public void paint(Graphics gr,int rayon){
		Enumeration<Integer> e1=ncomp.keys();
		Node tmpN;
		Color tmpC;
		int ind;
		while(e1.hasMoreElements()){
			ind=e1.nextElement();
			tmpN=g.getNode(ind);
			if(tmpN!=null){
				tmpC=tmpN.getColor();
				ind=ncomp.get(tmpN.getNum());
				tmpN.setColor(getCol(ind));
				tmpN.paint(gr,rayon);
				tmpN.setColor(tmpC);
			}
		}
	}

	
	private Color getCol(int i){
		Color []t={Color.BLUE,Color.CYAN,Color.RED,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.YELLOW};
		return t[i%7];
	}
		
					
		
	
	
	


	
	private void setGraph(GraphAbility g){
		this.g=g;
	}
	private GraphAbility getGraph(){
		return g;
	}
}
