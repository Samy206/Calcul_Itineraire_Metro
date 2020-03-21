//
//  SmallDialog.java
//  Programme
//
//  Created by Stéphane jeandeaux on 03/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package  programme.boxDialog;
import java.awt.*;
import java.awt.event.*;


public class SmallDialog extends Dialog{
	
	private Button set, annuler;
	private TextField text;
	public boolean okOrNo=false;
	public String nameNode;
	public double valueEdge;
	public Color color;
	
	SmallDialog(String title,String label){
		super(new Frame(),title,true);
		setLayout(new GridLayout(2,1));
		Panel p1=new Panel();
		Panel p2=new Panel();
		addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent evt){dispose();}});
		set=new Button("set");
		annuler=new Button("annuler");
		annuler.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent event) { dispose();}});
		text=new TextField(15);
		p1.add(new Label(label));
		p1.add(text);
		p2.add(set);
		p2.add(annuler);
		add(p1);
		add(p2);
		
	}
	public SmallDialog(String title,String label,String node){
		this(title,label);
		this.nameNode=node;
		text.setText(nameNode);
		set.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent event) {nameNode=text.getText();
															okOrNo=true;dispose();}});
		pack();
		setVisible(true);
	}
	public SmallDialog(String title,String label,double edge){
		this(title,label);
		this.valueEdge=edge;
		text.setText(valueEdge+"");
		set.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent event){
																	valueEdge=0;
																try{
																	valueEdge=Double.parseDouble(text.getText());
																	}
																catch(Exception e){dispose();}
													
																okOrNo=true;
																dispose();}});
		pack();
		setVisible(true);
	}
	  
}
