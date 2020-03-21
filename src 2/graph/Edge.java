//
//  Edge.java
//  stage
//
//  Created by StŽphane jeandeaux on 22/05/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package programme.graph;
import java.io.*;
import java.awt.*;

public class Edge implements Comparable,Cloneable,Serializable {
	//Variable des arcs
	private Color color;
	public static final Color DEFAULT_COLOR=Color.BLACK;
	private Node from,to;			//origine et extremite de l'arc
	private double value;			//valeur de l'arc
	private boolean edgeDirected=true;
	
	//Implementation du construction
	public Edge(Node from, Node to,double value,boolean edgeDirected)
	{
		setFrom(from);
		setTo(to);
		setValue(value);
		setEdgeDirected(edgeDirected);
		setColor(DEFAULT_COLOR);
	}
	
	public Edge(Node from,Node to,boolean edgeDirected)
	{
		this(from,to,0,edgeDirected);
	}
	
	//Implementation des methodes get
	public Color getColor(){return color;}
	public Node getFrom(){return from;}
	public Node getTo(){return to;}
	public double getValue(){return value;}
	public boolean getEdgeDirected(){return edgeDirected;}
	//Implementation des methodes set
	public void setFrom(Node from){this.from=from;}
	public void setTo(Node to){this.to=to;}
	public void setValue(double value){this.value=value;}
	public void setEdgeDirected(boolean edgeDirected){this.edgeDirected=edgeDirected;}
	public void setColor(Color color){this.color=color;}

	//Implementation des methodes de comparaison et d'egalite
	public int compareTo(Object object){
		return compareTo((Edge)object);
	}
		
	private int compareTo(Edge object){
		double result=(this.getValue() - object.getValue());
		return((int)result);
	}	
		
	public  boolean equals(Object object){
		if (object instanceof Edge){
			return equals((Edge)object);
		}
		return false;
	}
		
	private boolean equals(Edge object){
		return getValue()==(object.getValue());
	}
	
	//Implementation des methodes d'affichage
	public String toString(){
		String result="";
		result+=getFrom();
		if(getEdgeDirected()==true)
			result+="-->";
		else
			result+="---";
		result+=getTo();
		result+=" with value "+getValue();
		return result;
	}
	private void seeEdge(Graphics g,int x0,int y0,int x1,int y1){
		float u = x1-x0;
		float v = y1-y0;
	    float l = (float) Math.sqrt(u*u+v*v);
	    u /= l;
	    v /= l;
		int au=6;
		int av=6;
		int [] x = new int[3];
		int [] y = new int[3];
		x[0] = (int) (x0+2*x1)/3;
		y[0] = (int) (y0+2*y1)/3;
		x[1] = (int) (x[0]-au*u+av*v);
		y[1] = (int) (y[0]-au*v-av*u);
		x[2] = (int) (x[0]-au*u-av*v);
		y[2] = (int)  (y[0]-au*v+av*u);
		if(getEdgeDirected()==true){
			Color tmp=g.getColor();
			g.setColor(getColor());
			g.fillPolygon(x,y,3);
			g.setColor(tmp);
		}
		g.drawString(""+getValue(),x[1]+1,y[1]+1);
	}
	public void paint(Graphics g,int r){
			Node f=getFrom();
			Node t=getTo();
			int x0=f.getX();
			int	y0=f.getY();
			int	x1=t.getX();
			int	y1=t.getY();
			double u,v,l;
			if(f==t){
				g.drawOval(x0,y0,10,20);
				g.drawString(""+getValue(),x0+10,y0+20);
			}
			else{
			    u = x1-x0;
				v = y1-y0;
			    l=(double) Math.sqrt(u*u+v*v);
				u=(u*r)/(l*2);
				v=(v*r)/(l*2);
				Color tmp=g.getColor();
				if(getEdgeDirected()==false)
						g.setColor(getColor());
			
				g.drawLine((int)(x0+u),(int)(y0+v),(int)(x1-u),(int)(y1-v));
				g.setColor(tmp);
				

				
				}
			seeEdge(g,x0,y0,x1,y1);
			
	}
	public Edge clone(){
		return new Edge(getFrom().clone(),getTo().clone(),getValue(),getEdgeDirected());
		}


	
}
