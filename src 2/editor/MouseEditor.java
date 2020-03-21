//
//  MouseEditor.java
//  Programme
//
//  Created by Stéphane jeandeaux on 01/06/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
package programme.editor;
import java.lang.Math;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import programme.boxDialog.SmallDialog;
import programme.graph.GraphAbility;
import programme.graph.Edge;
import programme.graph.Node;

public class MouseEditor implements MouseListener,MouseMotionListener {

	private PanelEditor parent;
	public int tmpX,tmpY;
	public Node nodeMove=null;
	public Node nodeBeg=null;
	public Node from=null,to=null;
	private GraphAbility getGraph(){return parent.getGraph();}
	public MouseEditor(PanelEditor parent){this.parent=parent;}
	public void setNodeBeg(Node n){ this.nodeBeg=n;}
	public void mouseMoved(MouseEvent e) {}
	private int x,y;
	
    public void mouseClicked(MouseEvent e) {
		int nbNode=getGraph().getNbNodes();
		Node tmp1;
		Edge tmp2;
		SmallDialog s;
		if(e.getClickCount()==2&&downKey(e,false,false,false)){
			tmp1 = nearMove(e.getX(), e.getY());
			if(tmp1==null){
				tmp2= nearEdge(e.getX(), e.getY());
				if(tmp2!=null){
					s=new SmallDialog("Valeur de la edge","Entrer la valeur de l'edge:",tmp2.getValue());
					if(s.okOrNo==true)
						tmp2.setValue(s.valueEdge);
				}
				else{
					s=new SmallDialog("Nom de la node","Entrer le nom de la node:",nbNode+"");
					if(s.okOrNo==true){
						tmp1=getGraph().addNode(new Node(s.nameNode));
						tmp1.setXY(e.getX(),e.getY());
					}
					
				}
			}
			else{
				s=new SmallDialog("Nom de la node","Entrer le nom de la node:",tmp1.getLabel());
				if(s.okOrNo==true)
					tmp1.setLabel(s.nameNode);
					
			}

				
			parent.repaint();
			return;
		}
		if(e.getClickCount()==2&&downKey(e,true,false,false)){
			tmp1 = nearMove(e.getX(), e.getY());
			if(tmp1!=null)
				getGraph().removeNode(tmp1);
			else{
				tmp2= nearEdge(e.getX(), e.getY());
				if(tmp2!=null)
					getGraph().removeEdge(tmp2.getFrom(),tmp2.getTo());
				else
					return;
			}
			parent.repaint();
			return;
		}
		if(e.getClickCount()==2&&downKey(e,true,true,false)){
			nodeBeg = nearMove(e.getX(), e.getY());
			parent.repaint();
			return;
		}
	
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}       
	public void	mouseDragged(MouseEvent e){
		if(e.getY()<=0)
			return;
		if (nodeMove != null&&downKey(e,false,false,false)) {
            nodeMove.setXY(e.getX(), e.getY());
            parent.repaint();
			return;
        }
		if(from!=null&&downKey(e,false,true,false)){
			tmpX=e.getX();
			tmpY= e.getY();
			parent.repaint();
		}
		if(downKey(e,false,false,true)){
			Enumeration<Node> en=getGraph().getNodes();
			Node tmp;
			while(en.hasMoreElements()){
				tmp=en.nextElement();
				tmp.setXY(tmp.getX()-(x-e.getX())/28,tmp.getY()-(y-e.getY())/28);
			}
			parent.repaint();
		}
		
		
	}
	 public void mouseReleased(MouseEvent e) {
		 
           if(from!=null&&downKey(e,false,true,false)){
				to=nearMove(e.getX(), e.getY());
				if(to!=null){
					Edge tmp=getGraph().addEdge(from,to,0);
					SmallDialog s;
					if(tmp!=null){
						s=new SmallDialog("Valeur de la edge","Entrer la valeur de la node:",tmp.getValue());
						if(s.okOrNo==true)
							tmp.setValue(s.valueEdge);
						else
							getGraph().removeEdge(from,to);
					}
					to=null;
				}
				from=null;
				parent.repaint();
			}
			nodeMove = null;
			parent.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			
    }
	
	 public void mousePressed(MouseEvent e) {
		
		if(downKey(e,false,true,false))
			from=nearMove(e.getX(), e.getY());
		else
			if(downKey(e,false,false,true)){
				parent.setCursor(new Cursor(Cursor.HAND_CURSOR));
				x=e.getX();
				y=e.getY();
			}
			
			else
				nodeMove = nearMove(e.getX(), e.getY());
	}
	
		
	public Edge nearEdge(int x,int y){
		int x0,x1,y0,y1,px,py;
		Enumeration<Edge> e=getGraph().getEdges();
		Edge tmp;
		while(e.hasMoreElements()){
				tmp=e.nextElement();
				x0=tmp.getFrom().getX();
				y0=tmp.getFrom().getY();
				if(tmp.getFrom()!=tmp.getTo()){
					x1=tmp.getTo().getX();
					y1=tmp.getTo().getY();
					px = (int) (x0+2*x1)/3;
					py = (int) (y0+2*y1)/3;
				}
				else{
					px=x0+10;
					py=y0+20;
				}
			if(lenght(x,y,px,py)<20)
				return tmp;
		}
		return null;
	}
	
	public Node nearMove(int x,int y){
		Enumeration<Node> e=getGraph().getNodes();
		Node tmp;
		while(e.hasMoreElements()){
			tmp=e.nextElement();
			if(lenght(x,y,tmp.getX(),tmp.getY())<20)
				return tmp;
		}
		return null;
	}

	public int lenght(int x0,int y0,int x1,int y1){
	double tmp=(x0-x1)*(x0-x1)+(y0-y1)*(y0-y1);
	tmp=Math.sqrt(tmp);
	return (int)tmp;
	}
	private boolean downKey(MouseEvent e,boolean ctrl,boolean alt,boolean shift){
		if(ctrl==true)
			ctrl=e.isControlDown();
		else
			ctrl=!e.isControlDown();
		if(alt==true)
			alt=e.isAltDown();
		else
			alt=!e.isAltDown();
		if(shift==true)
			shift=e.isShiftDown();
		else
			shift=!e.isShiftDown();
		return ctrl&&alt&&shift;
	}
	public Node getNodeBeg(){
		return nodeBeg;
	}
	
}
