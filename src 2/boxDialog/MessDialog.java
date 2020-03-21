//
//  MessDialog.java
//  Programme
//
//  Created by Stéphane jeandeaux on 07/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package programme.boxDialog;
import java.awt.*;
import java.awt.event.*;
public class MessDialog extends Dialog {
	
	private Button bOk;
	
	public MessDialog(String title,String Message){
		super(new Frame(),title,true);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(500,100));
		MessPanel m=new MessPanel(Message);
		add(m,BorderLayout.CENTER);
		bOk=new Button("Ok");
		Panel p=new Panel();
		p.setLayout(new FlowLayout(FlowLayout.CENTER));
		p.add(bOk);
		add(p,BorderLayout.SOUTH);
		addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent evt){dispose();}});
		bOk.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent event) { dispose();}});
		pack();
		setVisible(true);
				
	}
	
}

