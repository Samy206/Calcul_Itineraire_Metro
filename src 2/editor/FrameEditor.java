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

import programme.menuBar.MenuBarProg;
import programme.menuBar.MenuBarAbility;

import programme.generator.PanelGenerator;

import programme.boxDialog.SaveOpen;
import programme.boxDialog.MyFilter;
import programme.boxDialog.MessDialog;
import programme.boxDialog.TextDialog;

import programme.algo.Connexe;
import programme.algo.Dijkstra;
import programme.algo.MethodMatricielle;

import programme.graph.Graph;
import programme.graph.GraphAbility;

public class FrameEditor extends Frame implements WindowListener,ActionListener,MenuBarAbility{
		
		private Container parent;
		private PanelEditor fils=null;
		private MenuBarProg bar=null;
		private AideEditor help=null;
		private MenuItem iReset;
		
		public FrameEditor(GraphAbility g,Container c){
			super("Editeur Graphe");
			parent=c;
			setLayout(new BorderLayout());
			help=new AideEditor();
			fils=new PanelEditor(g,this);
			bar=new MenuBarProg(this);
			iReset=new MenuItem("Reset");
			setMenuBar(bar);
			getBar().getMAlgo().add(iReset);
			iReset.addActionListener(this);
			add(fils,BorderLayout.CENTER);
			add(help,BorderLayout.NORTH);
			addWindowListener(this);
			pack();
			setVisible(true);
		}

		public FrameEditor(){
			this(new Graph(true),null);
			
		}
		public MenuBarProg getBar(){
			return bar;
		}
		
		public void actionPerformed(ActionEvent e){

		
		
		if(e.getSource()==iReset){
			fils.setNodeBeg(null);
			getBar().getIDijkstra().setState(false);
			getBar().getIConnexe().setState(false);
			getBar().getIKruskal().setState(false);
			fils.repaint();
		}
			


	}
	public void actionQuit(){
		windowClosing(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
	}
	
	public void actionOpen(){
		String name;
		GraphAbility tmp;
		name=SaveOpen.openFile("Ouvrir un graphe",new MyFilter(new String[]{"grph"},"GRPH graph"));
		if(name!=null){
			tmp=Graph.loadGraph(name);
			if(tmp!=null){
				fils.setGraph(tmp);
				fils.repaint();
			}
		}
	}
	
	public void actionSave(){
		String name;
		name=SaveOpen.saveFile("Enregistrer le graphe",new MyFilter(new String[]{"grph"},"GRPH graph"));
		if(name!=null)
			fils.getGraph().saveGraph(name);
	}
	
	public void actionDijkstra(){
		MessDialog m;
		if(fils.getGraph()!=null){
			if(fils.getNodeBeg()==null)
				m= new MessDialog("Attention","Choisir une node de depart : ctrl+alt + double-click sur la node");
			else{
				getBar().getIDijkstra().setState(true);
				fils.repaint();
			
			}
		}
	}
	public void actionMetMat(){
		MessDialog m;
		TextDialog t;
		if(fils.getGraph()!=null)
			t=new TextDialog("Resultat",new MethodMatricielle(fils.getGraph())+"");
		else
			m=new MessDialog("Attention","Il faut generer un graphe");
	}
	
	public void actionConnexe(){
		MessDialog m;
		if(fils.getGraph()!=null){
				getBar().getIConnexe().setState(true);
				fils.repaint();
		}
		else
			m=new MessDialog("Attention","Il faut un graphe");
	}
	public void actionKruskal(){
		MessDialog m;
		if(fils.getGraph()!=null){
				getBar().getIKruskal().setState(true);
				fils.repaint();
		}
		else
			m=new MessDialog("Attention","Il faut un graphe");
	}




	public void	windowActivated(WindowEvent e) {}
	public void	windowClosed(WindowEvent e) {}
	public void	windowDeactivated(WindowEvent e){} 
	public void	windowDeiconified(WindowEvent e) {}
	public void	windowIconified(WindowEvent e) {}
    public void	windowOpened(WindowEvent e) {}
	
	public void	windowClosing(WindowEvent e) {
		if(parent instanceof PanelGenerator){
			((PanelGenerator)parent).setGraph(fils.getGraph());
			if(fils.getGraph()!=null)
				((PanelGenerator)parent).remplirListe(fils.getGraph());
			dispose();
		}
		else
			System.exit(0);
	
	}
	
	
				
			
}
