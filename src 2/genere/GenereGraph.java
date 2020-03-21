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
import programme.graph.SimpleGraph;
import programme.graph.Graph;
import programme.graph.Edge;
import programme.graph.Node;


public class GenereGraph
{
	public static  int radius=200,center=250;
	
	public static GraphAbility  GenerateGraph(int nbNodes,int nbEdges,boolean directed,boolean loop) 
	throws GenerateGraphException
	{
		GraphAbility g;
		if(loop==true)
			g=new Graph(directed);
		else
			g=new SimpleGraph(directed);
			
		Random r1=new Random();
		Random r2=new Random();
		Node tmpN;
		int v1,v2;
		int tNumNodes[];
		int tmp;
		double alpha = 2*Math.PI/nbNodes;
		
		if(nbNodes<0)
			throw new GenerateGraphException(0);
		if(nbEdges<0)
			throw new GenerateGraphException(1);
		if(nbNodes<=0&&nbEdges<0)
			throw new GenerateGraphException(2);
		tNumNodes=new int[nbNodes];
		
		for(int i=0;i<nbNodes;i++){
			tmpN=g.addNode(new Node(""+i));
			tmpN.setXY((int)(Math.cos(alpha * i)*radius+center),(int)(Math.sin(alpha * i)*radius+center));
			tNumNodes[i]=tmpN.getNum();
		}
		
		tmp=g.getMaxNbEdges();
		if(nbEdges>tmp)
			nbEdges=tmp;
		tmp=tmp/2;
	
		if(nbEdges<tmp)
			while(g.getNbEdges()<nbEdges)
			{				
				v1=r1.nextInt(nbNodes);				
				v2=r2.nextInt(nbNodes);
				g.addEdge(tNumNodes[v1],tNumNodes[v2],0);
			}
		else
		{
			for(int i=0;i<g.getNbNodes();i++)
				if(g.getGraphDirected()==false)
				{
					for(int j=i;j<g.getNbNodes();j++)
						g.addEdge(tNumNodes[i],tNumNodes[j],0);
				}
				else
				{
					for(int j=0;j<g.getNbNodes();j++)
						g.addEdge(tNumNodes[i],tNumNodes[j],0);
				}

			while(g.getNbEdges()!=nbEdges)
			{				
				v1=r1.nextInt(nbNodes);				
				v2=r2.nextInt(nbNodes);
				g.removeEdge(tNumNodes[v1],tNumNodes[v2]);

			}
				
		}
		return g;
		
	}
		

}
 