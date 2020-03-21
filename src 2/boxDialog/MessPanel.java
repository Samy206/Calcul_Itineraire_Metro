//
//  PNorth.java
//  Programme
//
//  Created by Stéphane jeandeaux on 30/05/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package programme.boxDialog;
import java.awt.*;

public class MessPanel extends Panel{
	private String message;
	
	public MessPanel(String m){
		setMessage(m);
	}
	
	public void paint(Graphics g){
		Font f=new Font("TimesRoman",Font.BOLD,14);
		g.setFont(f);
		int w = getSize().width;
		int h = getSize().height;
		g.drawString(getMessage(),(w-g.getFontMetrics().stringWidth(getMessage()))/2, 8*h/10);
	}
	
	public void setMessage(String m){message=m;}
	public String getMessage(){return message;}
}

