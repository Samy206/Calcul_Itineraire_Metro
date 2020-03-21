//
//  PanelGraph.java
//  stage
//
//  Created by Stéphane jeandeaux on 26/05/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package programme.boxDialog;

import java.awt.*;
import java.util.*;
import java.awt.event.*;



public class TextDialog extends Dialog{
		private Button bOk;
		public TextDialog(String title,String mess){
			super(new Frame(),title,true);
			setLayout(new BorderLayout());
			setPreferredSize(new Dimension(500,500));
			
			TextArea t=new TextArea(100,80);
			t.setEditable(false);
			t.setText(mess);
			add(t,BorderLayout.CENTER);
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
