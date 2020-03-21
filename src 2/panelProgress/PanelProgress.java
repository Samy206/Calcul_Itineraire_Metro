//
//  PanelGraph.java
//  stage
//
//  Created by Stéphane jeandeaux on 26/05/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//

package programme.panelProgress;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class PanelProgress extends Dialog{

		public final static int ONE_SECOND = 1000;
		private ProgressAbility parent;
		private JProgressBar progressBar;
		private Timer timer;
		public PanelProgress(String title,ProgressAbility pa){
			super(new Frame(),title,true);
			setLayout(new BorderLayout());
			parent=pa;
			setPreferredSize(new Dimension(500,500));
			progressBar = new JProgressBar(0, 100);
			progressBar.setValue(0);
			progressBar.setStringPainted(true);
			add(progressBar);
			timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
					Double i=progressBar.getValue()+Math.random()*100; 
					progressBar.setValue(i.intValue());
					if (parent.isDone()==true) {
                    Toolkit.getDefaultToolkit().beep();
                    timer.stop();
					dispose();
                }
            }
        });
			 
			timer.start();
			addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent evt){dispose();}});
			pack();
			setVisible(true);
			setModal(false);
		}
}
