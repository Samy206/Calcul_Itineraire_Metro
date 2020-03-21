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



public class AideEditor extends Panel implements ItemListener

{

		private static final int NB_CHOICE=8; 
		private String []iChoice;
		private String []iAide;
		private Choice c;
		private TextArea t;
		
		public AideEditor(){
			setLayout(new FlowLayout ());
			initialisation();  
			c = new Choice();  
			c.addItemListener(this);
			t=new TextArea(2,40);
			t.setEditable(false);
			for(int i=0;i<NB_CHOICE;i++)
				c.addItem(iChoice[i]); 
			c.select(iChoice[0]);  
			t.setText(iAide[0]);
			add(c);  
			add(t);
			
		}
		private void initialisation(){
			iChoice=new String[NB_CHOICE];
			iChoice[0]="Creer d'un node";
			iChoice[1]="Renommer une node";
			iChoice[2]="Supprimer une node";
			iChoice[3]="Creer d'une edge";
			iChoice[4]="Modifier valeur edge";
			iChoice[5]="Supprimer une edge";
			iChoice[6]="choisir node depart";
			iChoice[7]="Deplacer le graphe";
			iAide=new String[NB_CHOICE];
			iAide[0]="double click gauche avec la souris";
			iAide[1]="double click gauche avec la souris \n"+"sur la node a renommer";
			iAide[2]="ctrl+double clik gauche avec la souris \n"+"sur la node a supprimer";
			iAide[3]="Alt+la node d'origine jusqu'a l'extremite";
			iAide[4]="double click gauche avec la souris \n"+"sur la valeur de l'edge a modifier";
			iAide[5]="ctrl+double clik gauche avec la souris \n"+"sur l'edge a supprimer";
			iAide[6]="ctrl+Alt+double click gauche avec la souris \n"+"sur la node de depart";
			iAide[7]="shift+click gauche et deplacement";
		}
		public void itemStateChanged(ItemEvent e){
			if(e.getSource()==c){
				int i=c.getSelectedIndex();
				t.setText(iAide[i]);
			}
		}



}
