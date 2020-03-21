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
import programme.graph.Tree;
import programme.graph.Edge;
import programme.graph.Node;


public class GenereTree
{
	
	public static GraphAbility  GenerateTree(int nbNodes) 
	throws GenerateGraphException
	{
		Random r1=new Random();
		int v1;
		int tmp;
		Node tmpN;
		Node tmpP;
		int tNumNodes[]=new int[nbNodes];
		int [][]fils=new int[nbNodes][nbNodes];
		int []nbFils=new int[nbNodes];
		GraphAbility g=new Tree(true);
		
		for(int i=0;i<nbNodes;i++){
			tmpN=g.addNode(new Node(""+i));
			tNumNodes[i]=tmpN.getNum();
			nbFils[i]=0;
		}
		tmpN=g.getNode(tNumNodes[0]);
		tmpN.setLabel(tNumNodes[0]+" racine");
		tmpN.setXY(10,250);
		for(int i=1;i<nbNodes;i++){
			v1=r1.nextInt(i);
			g.addEdge(tNumNodes[v1],tNumNodes[i],0);
			tmpN=g.getNode(tNumNodes[i]);
			tmpN.setLabel(tNumNodes[i]+" fils de "+tNumNodes[v1]);
			fils[v1][i]=1;
			nbFils[v1]++;
		}
		for(int i=0;i<nbNodes;i++){
			if(nbFils[i]!=0){
				tmpP=g.getNode(tNumNodes[i]);
				for(int j=i;j<nbNodes;j++){
					if(fils[i][j]==1){
						tmpN=g.getNode(tNumNodes[j]);
						if(nbFils[i]%2==0)
							tmp=tmpP.getY()+30*nbFils[i];
						else
							tmp=tmpP.getY()-30*nbFils[i];
						tmpN.setXY(tmpP.getX()+50,tmp);
						nbFils[i]--;
					}
				}
			}
		}
		
			
			
		return g;
	}
		
		
}
 