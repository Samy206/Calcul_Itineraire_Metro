//
//  Progamme.java
//  stage
//
//  Created by Stéphane jeandeaux on 23/05/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package programme.generator;

import java.util.Enumeration;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;

import programme.genere.GenereBipartite;
import programme.genere.GenereGraph;
import programme.genere.GenereTree;

import programme.editor.FrameEditor;

import programme.boxDialog.SaveOpen;
import programme.boxDialog.MyFilter;
import programme.boxDialog.MessPanel;
import programme.boxDialog.MessDialog;
import programme.boxDialog.SmallDialog;

import programme.graph.Node;
import programme.graph.Edge;
import programme.graph.Graph;
import programme.graph.GraphAbility;

import programme.panelProgress.PanelProgress;
import programme.panelProgress.ProgressAbility;

public class PanelGenerator extends Panel implements ActionListener,ItemListener,ProgressAbility {
	
	private GraphAbility g=null;
	
	private Panel pDirected,pType;
	private MessPanel pNorth;
	/*Liste de nodes*/
	private List lNodes;
	int []tabNodes;
	/*Liste de nodes*/
	private List lEdges;
	private String []tabEdges;
	//les labels du bas
	private Label labelN,labelE,labelD,labelT;
	private TextField tNbN,tNbE;
	private CheckboxGroup directed;
	private Checkbox[] trueFalse;
	private CheckboxGroup type;
	private static final int NB_TYPE=4;
	private Checkbox[] diffType;
	
	/*Les boutons*/
	private Button bGenerer,bOpen,bSave,bSee;
	
	
	
	private boolean done=false;
	public boolean isDone(){return done;}
	private PanelProgress pProgress;

	
	public PanelGenerator(){
	
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(500,400));
		
		//Le panneau d'en haut qui indique ce que l'on a généré
		pNorth=new MessPanel("Vous n'avez rien généré encore");
		toAdd(this,pNorth,0,0,5,1,500,30,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
		
		//Les deux listes du milieu 
		//Liste des nodes
		lNodes=new List(10,false);
		lNodes.addActionListener(this);
		toAdd(this,new Label("Nodes"),0,1,1,1,200,10,GridBagConstraints.NONE,GridBagConstraints.CENTER);
		toAdd(this,lNodes,0,2,1,1,200,50,GridBagConstraints.VERTICAL,GridBagConstraints.CENTER);
		//Liste des edges
		lEdges=new List(10,false);
		lEdges.addActionListener(this);
		toAdd(this,new Label("Edges"),1,1,3,1,0,10,GridBagConstraints.NONE,GridBagConstraints.CENTER);
		toAdd(this,lEdges,1,2,2,1,400,50,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
		
		//Le label et la zone de saisie pour le nombre de nodes
		labelN=new Label("Nombre de nodes :",Label.LEFT);
		toAdd(this,labelN,0,3,1,1,200,10,GridBagConstraints.NONE,GridBagConstraints.CENTER);
		tNbN=new TextField(0+"",5);
		toAdd(this,tNbN,1,3,1,1,200,10,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST);
		
		//Le label et la zone de saisie pour le nombre de edges
		labelE=new Label("Nombre de edges :",Label.LEFT);
		toAdd(this,labelE,0,4,1,1,200,10,GridBagConstraints.NONE,GridBagConstraints.CENTER);
		tNbE=new TextField(0+"",5);
		toAdd(this,tNbE,1,4,1,1,200,10,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST);
		
		//le label et la zone pour savoir si le graphe est directed ou undirected
		labelD=new Label("Directed graph     :",Label.LEFT);
		toAdd(this,labelD,0,5,1,1,200,10,GridBagConstraints.NONE,GridBagConstraints.CENTER);
		pDirected=new Panel();
		directed = new CheckboxGroup();
		trueFalse = new Checkbox[2];
		pDirected.add(trueFalse[0] = new Checkbox ("True", directed, true));
		pDirected.add(trueFalse[1] = new Checkbox ("False", directed, false));
		for (int i=0; i<trueFalse.length; i++)
			trueFalse[i].addItemListener(this);
		toAdd(this,pDirected,1,5,1,1,200,10,GridBagConstraints.NONE,GridBagConstraints.WEST);
		
		//le label et la zone pour savoir si le type de graphe
		labelT=new Label("Type de graphe     :",Label.LEFT);
		toAdd(this,labelT,0,6,1,1,200,10,GridBagConstraints.NONE,GridBagConstraints.NORTH);
		pType=new Panel();
		type = new CheckboxGroup();
		diffType = new Checkbox[NB_TYPE];
		pType.setLayout(new GridLayout(2,2));
		pType.add(diffType[0] = new Checkbox ("Simple Graph", type, true));
		pType.add(diffType[1] = new Checkbox ("Loop Graph", type, false));
		pType.add(diffType[2] = new Checkbox ("Bipartite", type, false));
		pType.add(diffType[3] = new Checkbox ("Tree", type, false));
		for (int i=0; i<diffType.length; i++)
			diffType[i].addItemListener(this);
		toAdd(this,pType,1,6,1,1,200,10,GridBagConstraints.SOUTH,GridBagConstraints.WEST);
		
		//les boutons
		bGenerer=new Button("Generer ");
		bGenerer.setBounds(94, 29, 70, 23);
		bGenerer.addActionListener(this);
		toAdd(this,bGenerer,2,3,1,1,200,10,GridBagConstraints.NONE,GridBagConstraints.CENTER);
		
		bSee=new Button("Afficher");
		bSee.setBounds(94, 29, 70, 23);
		bSee.addActionListener(this);
		toAdd(this,bSee,2,4,1,1,200,10,GridBagConstraints.NONE,GridBagConstraints.CENTER);
		
		bOpen=new Button(" Ouvrir ");
		bOpen.setBounds(94, 29, 70, 23);
		bOpen.addActionListener(this);
		toAdd(this,bOpen,2,5,1,1,200,10,GridBagConstraints.NONE,GridBagConstraints.CENTER);
		
		bSave=new Button(" Sauver ");
		bSave.setBounds(94, 29, 70, 23);
		bSave.addActionListener(this);
		toAdd(this,bSave,2,6,1,1,200,10,GridBagConstraints.NONE,GridBagConstraints.NORTH);
		
		
	
		toAdd(this,new Panel(),4,0,1,6,20,300,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
	}
	private void toAdd(Container cn,Component cm,int gridx,int gridy,int gridwidth,int gridheight,int weightx,int weighty,int fill,int anchor){
		GridBagConstraints contraints=new GridBagConstraints();
		contraints.gridx=gridx;
		contraints.gridy=gridy;
		contraints.gridwidth=gridwidth;
		contraints.gridheight=gridheight;
		contraints.weightx=weightx;
		contraints.weighty=weighty;
		contraints.fill=fill;
		contraints.anchor=anchor;
		cn.add(cm,contraints);
	}
	public void actionPerformed(ActionEvent e)
	{
		int index;
		String name;
		Node tmpN;
		Edge tmpE;
		SmallDialog s;
		FrameEditor  p;
		
		//double click sur une node de la liste
		if(e.getSource()==lNodes){
			index=lNodes.getSelectedIndex();
			tmpN=g.getNode(tabNodes[index]);
			s=new SmallDialog("Nom de la node","Entrer le nom de la node:",tmpN.getLabel());
			if(s.okOrNo==true)
				tmpN.setLabel(s.nameNode);
			remplirListe(g);
			
		}
		//double click sur une edge de la liste
		if(e.getSource()==lEdges){
			index=lEdges.getSelectedIndex();
			tmpE=g.getEdge(tabEdges[index]);
			s=new SmallDialog("Valeur de la edge","Entrer la valeur de la node:",tmpE.getValue());
			if(s.okOrNo==true)
				tmpE.setValue(s.valueEdge);
			lEdges.replaceItem(tmpE+"",index);
			
		}
		
		//bouton generer
		if(e.getSource()==bGenerer){
			done=false;
			pProgress=new PanelProgress("Vous etes entrain de generer un graphe",this);
			actionGenerer();
			done=true;
		}
			
		//permet d'ouvrir un fichier.grph
		if(e.getSource()==bOpen){
			name=SaveOpen.openFile("Ouvrir un graphe",new MyFilter(new String[]{"grph"},"GRPH graph"));
			if(name!=null){
				GraphAbility tmp=Graph.loadGraph(name);
				if(tmp!=null){
					setGraph(tmp);
					remplirListe(getGraph());
				}
			}
		}
		
		//permet de sauver un graphe
		if(e.getSource()==bSave){
			name=SaveOpen.saveFile("Enregistrer le graphe",new MyFilter(new String[]{"grph"},"GRPH graph"));
			if(name!=null)
				getGraph().saveGraph(name);
		}
		
		//permet d'afficher le graphe
		if(e.getSource()==bSee){
			if(g==null)
				return;
			p=new  FrameEditor(g,this);
		}
		
	}
	public void actionGenerer(){
		int nbn=0,nbe=0;
		String s="";
		boolean direct;
	
			try{
				nbn=Integer.valueOf(tNbN.getText());
				}
			catch(Exception ex){labelN.setForeground(Color.red);}
			try{
				nbe=Integer.valueOf(tNbE.getText());
				}
			catch(Exception ex){labelE.setForeground(Color.red);return; }
			labelN.setForeground(Color.black);
			labelE.setForeground(Color.black);
			try{
			if(directed.getSelectedCheckbox().getLabel()=="True")
				direct=true;
			else
				direct=false;
			if(type.getSelectedCheckbox().getLabel()=="Simple Graph")
				g=GenereGraph.GenerateGraph(nbn,nbe,direct,false);
			else
				if(type.getSelectedCheckbox().getLabel()=="Loop Graph")
					g=GenereGraph.GenerateGraph(nbn,nbe,direct,true);
				else
					if(type.getSelectedCheckbox().getLabel()=="Bipartite")
						g=GenereBipartite.GenerateBipartite(nbn,nbe,direct);
					else
						g=GenereTree.GenerateTree(nbn);
				}
			
			catch(Exception ex){System.out.println(ex);labelE.setForeground(Color.red);labelN.setForeground(Color.red);return; }
			remplirListe(g);			
	}
	public void remplirListe(GraphAbility g)
	{
		String s="";
		int nbe;
		lNodes.removeAll();
		lEdges.removeAll();
		Node tmpN;
		tabNodes=new int[g.getNbNodes()];
		tabEdges=new String[g.getNbEdges()];
		Enumeration<Node> e1=g.getNodes();
		int i=0;
		while(e1.hasMoreElements()){
			tmpN=e1.nextElement();
			lNodes.add(""+tmpN);
			tabNodes[i]=tmpN.getNum();
			i++;
		}
		i=0;
		Edge tmpE;
		Enumeration<Edge> e2=g.getEdges();
		while(e2.hasMoreElements()){
			tmpE=e2.nextElement();
			lEdges.add(""+tmpE);
			tabEdges[i]="("+tmpE.getFrom().getNum()+","+tmpE.getTo().getNum()+")";
			i++;
		}

			
		nbe=g.getNbEdges();
		if(g.getGraphDirected()==false)
			s="undirected";
		else
			s="directed";
		pNorth.setMessage("Vous avez genere un "+s+" graph à "+g.getNbNodes()+" nodes et "+nbe+" edges");
		pNorth.repaint();
	}
	public GraphAbility getGraph(){return g;}
	public void setGraph(GraphAbility gr){g=gr;}

	public void itemStateChanged(ItemEvent e)  {}

	public Node nodeOfIndex(int i){
		return g.getNode(tabNodes[i]);
	}
	public List getLNodes(){
		return lNodes;
	}

}
