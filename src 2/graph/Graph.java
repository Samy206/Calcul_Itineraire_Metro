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


public class Graph implements GraphAbility{

	//les differentes variables d'un graphe
	private Hashtable <Integer,Node>nodes;
	private Hashtable <String,Edge> edges;
	private boolean graphDirected;



	//Implementation du constructeur
	public Graph(boolean graphDirected){
		nodes  = new Hashtable <Integer,Node>();
		edges = new  Hashtable <String,Edge>();
		setGraphDirected(graphDirected);
	}
	
    public Node addNode(Node n){
		if(isNode(n.getNum())||isNode(n))
			return null;
		addNode(n.getNum(),n);

		return n;
    }
	protected void addNode(int i,Node n){
		nodes.put(i,n);
	}

	//ajout d'un edge
	public Edge addEdge(Edge e) {
		if(!isEdge(e))
			return addEdge(e.getFrom(),e.getTo(),e.getValue());
		return null;
	}
	public Edge addEdge(Node from,Node to,double value) {
		if(isNode(from)&&isNode(to))
			return addEdge(from.getNum(),to.getNum(),value);
		return null;
	}
	public  Edge addEdge(int from,int to,double value){
		String s="("+from+","+to+")";
		String u="("+to+","+from+")";
		if(!(isNode(from)&&isNode(to)))
			return null;
		if(isEdge(s)||(isEdge(u)&&getGraphDirected()==false))
			return null;
		Node tmpNF=getNode(from);
		Node tmpNT=getNode(to);
		Edge e=new Edge(tmpNF,tmpNT,value,getGraphDirected());
		addEdge(s,e);
		tmpNF.addNext(to);
		tmpNT.addPrec(from);
		return e;
	}
	protected void addEdge(String s,Edge e){
		edges.put(s,e);
	}
		
	//Implementation des methodes get
    public int getNbNodes() { return nodes.size(); }
    public int getNbEdges() { return edges.size();}
	public int getMaxNbEdges(){ 
		int tmp=getNbNodes()*(getNbNodes()-1);
		if(getGraphDirected()==true)
			return tmp+getNbNodes();
		else
			return tmp/2+getNbNodes();
	}
			
    public Node getNode(int s){return nodes.get(s);}
	public Edge getEdge(String s){	
		int []t=new int[2];
		Edge tmp=edges.get(s);
		if(getGraphDirected()==false&&tmp==null){
			t=edgeFromTo(s);
			s="("+t[1]+","+t[0]+")";
			tmp=edges.get(s);
		}
		return tmp;
	}
	
	public boolean getGraphDirected(){return graphDirected;}
	

	//Implementation des methodes set
	private void setGraphDirected(boolean graphDirected){
		this.graphDirected=graphDirected;
	}
	public void removeEdge(int from,int to){
		String s="("+from+","+to+")";
		String u="("+to+","+from+")";
		edges.remove(s);
		getNode(from).removeNext(to);
		getNode(to).removePrec(from);
		if(getGraphDirected()==false){
			edges.remove(u);
			getNode(to).removeNext(from);
			getNode(from).removeNext(to);
		}
	}
	public void removeEdge(Node from,Node to){
		if(isNode(from)&&isNode(to))
			removeEdge(from.getNum(),to.getNum());
	}
	public void removeEdge(Edge e){
		removeEdge(e.getFrom(),e.getTo());
	}
	
	public void removeNode(Node n){
		if(isNode(n))
			removeNode(n.getNum());
	}
	public void removeNode(int i){
		int tmp;
		String k;
		if(!isNode(i))
			return ;
		Iterator<Integer> e1=getNode(i).next();
		while(e1.hasNext()){
			tmp=e1.next();
			k="("+i+","+tmp+")";
			edges.remove(k);
			getNode(tmp).removePrec(i);
		}
		e1=getNode(i).prec();
		while(e1.hasNext()){
			tmp=e1.next();
			k="("+tmp+","+i+")";
			edges.remove(k);
			getNode(tmp).removeNext(i);
		}
		nodes.remove(i);
	}

	
	public Enumeration<Node> getNodes(){
		return nodes.elements();
	}
	public Enumeration<Edge> getEdges(){
		return edges.elements();
	}
		
	//Implementation des methodes pour l'affichage
    public String toString() {
		Enumeration<Node> e1=getNodes();
		Enumeration<Edge> e2=getEdges();
		Node tmpN;
		Edge tmpE;
		String s = getNbNodes()+" nodes\n";
		while(e1.hasMoreElements()){
			tmpN=e1.nextElement();
			s+=tmpN+"\n";
		}
		s+="\n";
		s += getNbEdges()+" edges\n";
		while(e2.hasMoreElements()){
			tmpE=e2.nextElement();
			s+=tmpE+"\n";
		}
		return s+"\n";
    }
	
	//Sauver le graphe en fichier

	public void saveGraph(String s)
	{
	try{
		ObjectOutputStream o=new ObjectOutputStream(new FileOutputStream(s));
		o.writeObject(this);
		o.close();
		}
	catch(Exception e){System.out.println("Error File : "+e.getMessage());}
	
	}
	
	public static GraphAbility loadGraph(String s)
	{
	Graph tmp=null;
	try{
		ObjectInputStream i=new ObjectInputStream(new FileInputStream(s));
		tmp = (Graph) i.readObject();
		i.close();
		return (GraphAbility)tmp;
		
	}
	catch(Exception e){
		System.out.println("Error File : "+e.getMessage());
		return null;
	}
	
	}
	public Graph clone(){
		Graph tmp=new Graph(getGraphDirected());
		tmp.nodes=cloneNodes();
		tmp.edges=cloneEdges(tmp.nodes);
		tmp.setGraphDirected(getGraphDirected());
		return tmp;
	}
	public void paint(Graphics gr,int r){
		Enumeration<Node> e1=getNodes();
		Enumeration<Edge> e2=getEdges();
		Node tmpN;
		Edge tmpE;
		while(e1.hasMoreElements()){
			tmpN=e1.nextElement();
			tmpN.paint(gr,r);
		}
		while(e2.hasMoreElements()){
			tmpE=e2.nextElement();
			tmpE.paint(gr,r);
		}
	}	
	
	public Hashtable <Integer,Node> cloneNodes(){
		Hashtable <Integer,Node>tmp=new Hashtable <Integer,Node>();
		Enumeration<Integer> k=getNumNodes();
		int i;
		while(k.hasMoreElements()){
			i=k.nextElement();
			tmp.put(i,getNode(i).clone());
		}
		return tmp;
	}
	public Hashtable <String,Edge> cloneEdges(Hashtable <Integer,Node> n){
		Hashtable <String,Edge>tmp=new Hashtable <String,Edge>();
		Enumeration<String> k=getNameEdges();
		String s;
		Edge tmpE;
		int f,t;
		while(k.hasMoreElements()){
			s=k.nextElement();
			tmpE=getEdge(s);
			f=tmpE.getFrom().getNum();
			t=tmpE.getTo().getNum();
			tmp.put(s,new Edge(n.get(f),n.get(t),tmpE.getValue(),tmpE.getEdgeDirected()));
		}
		return tmp;
	}
	public boolean isEdge(String s){
		return edges.containsKey(s);
	}
	public boolean isEdge(Edge e){
		return edges.contains(e);
	}
	public boolean isNode(int s){
		return nodes.containsKey(s);
	}
	public boolean isNode(Node s){
		return nodes.contains(s);
	}
	public Enumeration<Integer> getNumNodes(){
		return nodes.keys();
	}
	public Enumeration<String> getNameEdges(){
		return edges.keys();
	}
	
	public int[] edgeFromTo(String s){
		int []t=new int[2];
		int indexV=s.indexOf(",");
		t[0]=Integer.parseInt(s.substring(1,indexV));
		t[1]=Integer.parseInt(s.substring(indexV+1,s.length()-1));
		return t;
	}
		

		
}
