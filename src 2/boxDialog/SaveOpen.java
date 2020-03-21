//
//  Dialog.java
//  Programme
//
//  Created by Stéphane jeandeaux on 01/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package  programme.boxDialog;
import java.io.*;
import java.awt.*;

public class SaveOpen {

	static public String openFile(String title,MyFilter filter)
	{
		Frame p=new Frame();
		FileDialog dial = new FileDialog(p,title,FileDialog.LOAD);
		dial.setFilenameFilter((FilenameFilter)filter);
		dial.setVisible(true);
		if(dial.getFile()==null)
			return null;
		return dial.getDirectory()+"/"+dial.getFile();
		
	}

	static public String saveFile(String title,MyFilter filter)
	{
		Frame p=new Frame();
		FileDialog dial = new FileDialog(p,title,FileDialog.SAVE);
		dial.setFilenameFilter((FilenameFilter)filter);
		dial.setVisible(true);
		if(dial.getFile()==null)
			return null;
		return dial.getDirectory()+"/"+dial.getFile()+".grph";
	}


}
