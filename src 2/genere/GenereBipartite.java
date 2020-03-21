//
//  GenereGraph.java
//  stage
//
//  Created by Stéphane jeandeaux on 23/05/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package programme.genere;
import java.awt.*;
import java.util.*;
import java.lang.Math; 
import programme.graph.GraphAbility;
import programme.graph.Bipartite;
import programme.graph.Edge;
import programme.graph.Node;


public class GenereBipartite
{
	public static  int radius=200,center=250;
	public static GraphAbility GenerateBipartite(int nbNodes,int nbEdges,boolean directed)
	throws GenerateGraphException
	{
		int nbColor=2;
		Color []color=new Color[nbColor];
		color[0]=Bipartite.DEFAULT_COLOR_ONE;
		color[1]=Bipartite.DEFAULT_COLOR_TWO;
		GraphAbility g=new Bipartite(directed);
		Random r1=new Random();
		Random r2=new Random();
		Random r3=new Random();
		Random c=new Random();
		String s;
		Node tmpN;
		Edge e;
		int co;
		int v1,v2,j;
		int i;
		int maxEdges;
		double alpha = 2*Math.PI/nbNodes;

		if(nbNodes<=0)
			throw new GenerateGraphException(0);
		if(nbEdges<0)
			throw new GenerateGraphException(1);
		if(nbEdges<nbNodes-1)
			throw new GenerateGraphException(1);
		if(nbNodes<=0&&nbEdges<0)
			throw new GenerateGraphException(2);
		Vector <Node>[]groups=new Vector[nbColor];
		for(i=0;i<nbColor;i++)
			groups[i]=new Vector<Node>();
		if(nbEdges==nbNodes-1||nbNodes<4){
				tmpN=g.addNode(new Node(0+"",(int)(Math.cos(alpha * 0)*radius+center),(int)(Math.sin(alpha * 0)*radius+center),color[0]));
				groups[0].addElement(tmpN);
				for(i=1;i<nbNodes;i++){
					tmpN=g.addNode(new Node(i+"",(int)(Math.cos(alpha * i)*radius+center),(int)(Math.sin(alpha * i)*radius+center),color[nbColor-1]));
					groups[nbColor-1].addElement(tmpN);
				}
		}
		else{
			for(i=0;i<nbNodes/2;i++){
				tmpN=g.addNode(new Node(i+"",(int)(Math.cos(alpha * i)*radius+center),(int)(Math.sin(alpha * i)*radius+center),color[0]));
				groups[0].addElement(tmpN);
			}
			for(;i<nbNodes;i++){
				tmpN=g.addNode(new Node(i+"",(int)(Math.cos(alpha * i)*radius+center),(int)(Math.sin(alpha * i)*radius+center),color[1]));
				groups[1].addElement(tmpN);
			}
		}
				
		maxEdges=groups[0].size()*groups[1].size();
		if(g.getGraphDirected()!=false)
			maxEdges=maxEdges*2;
		if(nbEdges>maxEdges)
			nbEdges=maxEdges;
		for(i=0;i<groups[0].size()&&i<nbEdges;i++){
			v1=r1.nextInt(groups[1].size());
			g.addEdge(groups[0].elementAt(i).getNum(),groups[1].elementAt(v1).getNum(),0);
		}
		int tmp=i;
		for(i=0;i<groups[1].size()&&tmp<nbEdges;i++){
			for(j=0;j<groups[0].size()&&g.isEdge("("+groups[0].elementAt(j)+","+groups[1].elementAt(i)+")");j++);
			if(j<groups[0].size()){
				g.addEdge(groups[1].elementAt(i).getNum(),groups[0].elementAt(j).getNum(),0);
				tmp++;
			}
		}
		return g;
	}
	
	
	private int nbNext(GraphAbility g,int num){
		int nb=0;
		int []t;
		Enumeration<String> s=g.getNameEdges();
		while(s.hasMoreElements()){
			t=g.edgeFromTo(s.nextElement());
			if(t[0]==num)
				nb++;
		}
		return nb;
	}
	
	

}
  