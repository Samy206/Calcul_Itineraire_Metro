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
import java.util.*;


public class MethodMatricielle {

	public Double[][] v;
	public int[][] p;
	public int nbNodes=0;
	int []nums;
	
	
	public GraphAbility g=null;
	
	
	public MethodMatricielle(GraphAbility gr)
	{
			this.g=gr;
			if(g==null){
				v=null;
				return;
			}
			nbNodes=g.getNbNodes();
			if(nbNodes==0){
				v=null;
				return;
			}
			nums=numNodes();
			v=matValuation();
			p=matWay();
			for(int k=0;k<nbNodes;k++)
				for(int i=0;i<nbNodes;i++)
					for(int j=0;j<nbNodes;j++)
						if(v[i][k]+v[k][j]<v[i][j]){
							v[i][j]=v[i][k]+v[k][j];
							p[i][j]=p[k][j];
						}
							

	}
		
	private Double[][] matValuation(){
		Double[][]t=new Double[nbNodes][nbNodes];
		Edge tmp;
		String str;
		for(int i=0;i<nbNodes;i++)
			for(int j=0;j<nbNodes;j++){
				str="("+nums[i]+","+nums[j]+")";
				if((tmp=g.getEdge(str))!=null)
					t[i][j]=tmp.getValue();
				else
					t[i][j]=Double.MAX_VALUE;
			}
		return t;
	}
		
	private int[][] matWay(){
		int[][]t=new int[nbNodes][nbNodes];
		for(int i=0;i<nbNodes;i++)
			for(int j=0;j<nbNodes;j++)
					t[i][j]=i;
		return t;
	}
		
	private int[] numNodes(){
		Enumeration<Integer> n=g.getNumNodes();
		int []tmp=new int[nbNodes];
		int i=0;
		while(n.hasMoreElements())
			tmp[i++]=n.nextElement();
		return tmp;
	}
	
	public String toString(){
		String str="Matrice des longueurs\n";
		for(int i=0;i<nbNodes;i++){
			str+=g.getNode(nums[i])+"|";
			for(int j=0;j<nbNodes;j++)
				if(v[i][j]==Double.MAX_VALUE)
					str+="   "+"inf"+"   |";
				else
					str+="   "+v[i][j]+"   |";
			str+="\n";
		}
		str+="\n\n Matrice des chemins optimaux \n";
		for(int i=0;i<nbNodes;i++){
			str+=g.getNode(nums[i])+"|";
			for(int j=0;j<nbNodes;j++)
					str+="   "+g.getNode(nums[p[i][j]])+"   |";
			str+="\n";
		}
		return str;
	}
		
}
