//
//  MenuBarProg.java
//  Programme
//
//  Created by Stéphane jeandeaux on 31/05/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package programme.menuBar;
import java.awt.*;
import java.awt.event.*;


public class MenuBarProg extends MenuBar implements ActionListener,ItemListener{

	private Menu mFile,mAlgo;
	private MenuItem iSave,iQuit,iOpen;
	private CheckboxMenuItem iDijkstra,iMetMat,iConnexe,iKruskal;
	private MenuBarAbility parent=null;
	
	public MenuBarProg(MenuBarAbility parent){
		super();
		setParent(parent);
		mFile= new Menu("Fichier");
		add(mFile);
		iOpen=new MenuItem("Ouvrir...");
		mFile.add(iOpen);
		iSave=new MenuItem("Enregistrer sous ...");
		mFile.add(iSave);
		mFile.addSeparator();
		iQuit=new MenuItem("Quitter");
		mFile.add(iQuit);
		
		mAlgo= new Menu("Algorithme");
		add(mAlgo);
		
		iDijkstra=new CheckboxMenuItem("Dijkstra");
		mAlgo.add(iDijkstra);
		
		iKruskal=new CheckboxMenuItem("Kruskal");
		mAlgo.add(iKruskal);
		
		iMetMat=new CheckboxMenuItem("Methode Matricielle");
		mAlgo.add(iMetMat);
		
		iConnexe=new CheckboxMenuItem("Composante connexe");
		mAlgo.add(iConnexe);
		
		
		
		getIQuit().addActionListener(this);
		getIOpen().addActionListener(this);
		getISave().addActionListener(this);
		getIDijkstra().addItemListener(this);
		getIKruskal().addItemListener(this);
		getIMetMat().addItemListener(this);
		getIConnexe().addItemListener(this);

		
	}
	
	public MenuBarAbility getParentHere(){
		return parent;
	}
	public void setParent(MenuBarAbility parent){
		this.parent=parent;
	}
	
	public Menu getMFile(){return mFile;}
	public MenuItem getISave(){return iSave;}
	public MenuItem getIQuit(){return iQuit;}
	public MenuItem getIOpen(){return iOpen;}
	
	public Menu getMAlgo(){return mAlgo;}
	public CheckboxMenuItem getIDijkstra(){return iDijkstra;}
	public CheckboxMenuItem getIKruskal(){return iKruskal;}
	public CheckboxMenuItem getIMetMat(){return iMetMat;}
	public CheckboxMenuItem getIConnexe(){return iConnexe;}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==getISave())
			getParentHere().actionSave();
		if(e.getSource()==getIQuit())
			getParentHere().actionQuit();
		if(e.getSource()==getIOpen())
			getParentHere().actionOpen();
	}
	public void itemStateChanged(ItemEvent ie)
	{
		Object source=ie.getItemSelectable();
		int chstate=ie.getStateChange();
		if((source==getIDijkstra()) && (getIDijkstra().getState()==true)){
			getIDijkstra().setState(false);
			getParentHere().actionDijkstra();
			return;
		}
		if((source==getIKruskal()) && (getIKruskal().getState()==true)){
			getIKruskal().setState(false);
			getParentHere().actionKruskal();
			return;
		}
		if((source==getIConnexe()) && (getIConnexe().getState()==true)){
			getIConnexe().setState(false);
			getParentHere().actionConnexe();
			return;
		}
		if((source==getIMetMat()) && (getIMetMat().getState()==true)){
			getIMetMat().setState(false);
			getParentHere().actionMetMat();
			return;
		}
	}


}

	