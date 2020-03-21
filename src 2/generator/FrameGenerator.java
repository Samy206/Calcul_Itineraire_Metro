
package programme.generator;

import java.awt.*;
import java.awt.event.*;

import programme.graph.Graph;
import programme.graph.GraphAbility;

import programme.algo.Connexe;
import programme.algo.Dijkstra;
import programme.algo.MethodMatricielle;
import programme.algo.Kruskal;

import programme.menuBar.MenuBarProg;
import programme.menuBar.MenuBarAbility;

import programme.editor.FrameEditor;

import programme.boxDialog.SaveOpen;
import programme.boxDialog.MyFilter;
import programme.boxDialog.TextDialog;
import programme.boxDialog.MessDialog;

public class FrameGenerator extends Frame implements MenuBarAbility{

	private PanelGenerator fils;
	private MenuBarProg bar;
	
	
	public FrameGenerator(){
		super("Generer Graphe");
		fils=new PanelGenerator();
		add(fils);
		bar=new MenuBarProg(this);
		setMenuBar(bar);
		addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent evt){System.exit(0);}});
		pack();
		setVisible(true);
	}
		
	public void actionSave(){
		String name;
		name=SaveOpen.saveFile("Enregistrer le graphe",new MyFilter(new String[]{"grph"},"GRPH graph"));
		if(name!=null)
			fils.getGraph().saveGraph(name);
	}
	public void actionQuit(){
		System.exit(0);
	}
	public void actionOpen(){
		String name;
		GraphAbility tmp;
		name=SaveOpen.openFile("Ouvrir un graphe",new MyFilter(new String[]{"grph"},"GRPH graph"));
		if(name!=null){
			tmp=Graph.loadGraph(name);
			if(tmp!=null){
				fils.setGraph(tmp);
				fils.remplirListe(fils.getGraph());
			}
		}
	}
	public void actionDijkstra(){
			MessDialog m;
			TextDialog t;
			int ind=fils.getLNodes().getSelectedIndex();
			if(ind==-1)
				m=new MessDialog("Attention","Selectionner une node dans la liste");
			else
				t=new TextDialog("Resultat",new Dijkstra(fils.getGraph(),fils.nodeOfIndex(ind))+"");

	}
	public void actionMetMat(){
		MessDialog m;
		TextDialog t;
		if(fils.getGraph()!=null)
			t=new TextDialog("Resultat",new MethodMatricielle(fils.getGraph())+"");
		else
			m=new MessDialog("Attention","Il faut generer un graphe");
	}
	public void actionKruskal(){
		MessDialog m;
		TextDialog t;
		if(fils.getGraph()!=null)
			t=new TextDialog("Resultat",new Kruskal(fils.getGraph())+"");
		else
			m=new MessDialog("Attention","Il faut generer un graphe");
	}

	public void actionConnexe(){
		MessDialog m;
		TextDialog t;
		if(fils.getGraph()!=null)
			t=new TextDialog("Resultat",new Connexe(fils.getGraph())+"");
		else
			m=new MessDialog("Attention","Il faut generer un graphe");
	}
	
		
}
