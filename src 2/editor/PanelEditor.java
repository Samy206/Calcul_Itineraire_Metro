//
//  PanelGraph.java
//  stage
//
//  Created by Stéphane jeandeaux on 26/05/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package programme.editor;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

import programme.graph.GraphAbility;
import programme.graph.Node;
import programme.graph.Edge;

import programme.algo.Connexe;
import programme.algo.Dijkstra;
import programme.algo.Kruskal;

public class PanelEditor extends Panel{

		private FrameEditor parent=null;
		private GraphAbility gr=null;
		private MouseEditor mEditor;
		
		private Dijkstra dijkstra=null;
		private Connexe connexe=null;
		private Kruskal kruskal=null;
		
	
		
		public PanelEditor(GraphAbility g,FrameEditor p){
			setGraph(g);
			parent=p;
			setPreferredSize(new Dimension(500,500));
			mEditor=new MouseEditor(this);
			addMouseListener(mEditor);
			addMouseMotionListener(mEditor);
			
		}
		public void paint(Graphics g){
			Color tmp=null;
			super.paint(g);
			if(getGraph()!=null){
				gr.paint(g,20);
				if(parent.getBar().getIDijkstra().getState()){
					setDijkstra(new Dijkstra(getGraph(),getNodeBeg()));
					getDijkstra().paint(g,20);
				}
				if(parent.getBar().getIConnexe().getState()==true){
					setConnexe(new Connexe(getGraph()));
					getConnexe().paint(g,20);
				}
				if(parent.getBar().getIKruskal().getState()==true){
					setKruskal(new Kruskal(getGraph()));
					getKruskal().paint(g,20);
				}
				
			}
			if(mEditor.from!=null&&mEditor.to==null)
				g.drawLine(mEditor.from.getX(),mEditor.from.getY(),mEditor.tmpX,mEditor.tmpY);
			if(mEditor.getNodeBeg()!=null){
				tmp=getNodeBeg().getColor();
				getNodeBeg().setColor(Color.GREEN);
				getNodeBeg().paint(g,20);
				getNodeBeg().setColor(tmp);
			}
	}
	
	
	public GraphAbility getGraph(){return gr;}
	public void setGraph(GraphAbility g){gr=g;}
	public Node getNodeBeg(){return mEditor.getNodeBeg();}
	public void setNodeBeg(Node n){ mEditor.setNodeBeg(n);}
	
	public void  setDijkstra(Dijkstra d){this.dijkstra=d;}
	public Dijkstra  getDijkstra(){return dijkstra;}
	
	public void  setConnexe(Connexe d){this.connexe=d;}
	public Connexe  getConnexe(){return connexe;}
	
	public void  setKruskal(Kruskal d){this.kruskal=d;}
	public Kruskal  getKruskal(){return kruskal;}
	
	
}
