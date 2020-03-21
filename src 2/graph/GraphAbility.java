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

public interface GraphAbility extends Serializable,Cloneable{
	/**
	*@param la node a ajoute
	*@return la node créée
	*/
	public Node addNode(Node n);
	
	/**
	*@param la node from et la node To et la valeur de l'edge
	*@return la edge créée
	*/
	public Edge addEdge(Edge e);
	
	public Edge addEdge(Node from,Node to,double value);
	/**
	*@param la node from et la node To et la valeur de l'edge
	*@return la edge créée
	*/
	public Edge addEdge(int from,int to,double value);
	/**
	*@return le nombre de nodes
	*/
    public int getNbNodes() ;
	/**
	*@return le nombre de edges
	*/
    public int getNbEdges() ;
	/**
	*@return le nombre de maximum edges
	*/
    public int getMaxNbEdges();
	/**
	*@param l'indice de la node a retourne
	*@return la node ou null
	*/
    public Node getNode(int s);
	/**
	*@param le nom de l'edge ex :(from,to)
	*@return l'edge ou null
	*/
	public Edge getEdge(String s);
	/**
	*@return true si orienté false sinon
	*/
	public boolean getGraphDirected();
	/**
	*@param true : oriente ; false :non-oriente
	*/
	public boolean isEdge(String s);
	public boolean isNode(int s);
	
	public void removeEdge(int from,int to);
	public void removeEdge(Node from,Node to);
	
	public void removeNode(Node n);
	public void removeNode(int i);
	
	public Enumeration<Node> getNodes();
	public Enumeration<Edge> getEdges();
	
	public Enumeration<Integer> getNumNodes();
	public Enumeration<String> getNameEdges();
	
	public int[] edgeFromTo(String s);
	
	public Graph clone();
	public void paint(Graphics gr,int r);	
	public void saveGraph(String s);
	public String toString() ;
}
