

package programme.graph;
import java.io.*;
import java.awt.*;
import java.util.*;
public class Node implements Serializable{
	//Variables du noeud
	private String label;
	private final int num;
	private static int NBNODE=0;
	private Color color;
	public static final Color DEFAULT_COLOR=Color.BLUE;
	private int x,y;
	
	private TreeSet<Integer> lNext=null;
	private TreeSet<Integer> lPrec=null;
	
	//Implementation du construction
	public Node(String label,int x,int y){	
		this(label,x,y,DEFAULT_COLOR);
	}
	
	public Node(String label){	
		this(label,0,0,DEFAULT_COLOR);
	}
	public Node(String label,Color color){	
		this(label,0,0,color);
	}
	
	public Node(String label,int x,int y,Color color){	
		setXY(x,y);
		setLabel(label);
		setColor(color);
		lNext=new TreeSet<Integer>();
		lPrec=new TreeSet<Integer>();
		this.num=NBNODE++;
	}
	
	//Implementation des methodes get
	public String getLabel(){
		return label;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Color getColor(){
		return color;
	}
	public int getNum(){
		return num;
	}
	//Implementation des methodes set
	public void setLabel(String label){
		this.label=label;
	}
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	public void setXY(int x,int y){
		setX(x);
		setY(y);
	}
	public void setColor(Color color){
		this.color=color;
	}
	public void addNext(int n){
		lNext.add(n);
	}
	public void addPrec(int p){
		lPrec.add(p);
	}
	public void removeAllPrec(){
		lPrec.clear();
	}
	public void removeAllNext(){
		lNext.clear();
	}
	public void removeNext(int n){
		lNext.remove(n);
	}
	public void removePrec(int p){
		lPrec.remove(p);
	}
	public boolean isPrec(int p){
		return lPrec.contains(p);
	}
	public boolean isNext(int n){
		return lNext.contains(n);
	}
	public Iterator<Integer> next(){
		return lNext.iterator();
	}
	public Iterator<Integer> prec(){
		return lPrec.iterator();
	}
	public Iterator<Integer> neighbors(){
		TreeSet tmp= listNeighbors();
		return tmp.iterator();
	}
	public int nbNext(){
		return lNext.size();
	}
	public int nbPrec(){
		return lPrec.size();
	}
	
	public int nbNeighbors(){
		return listNeighbors().size();
	}
	private TreeSet<Integer> listNeighbors(){
		TreeSet<Integer> tmp=new TreeSet(lNext);
		tmp.addAll(lPrec);
		return tmp;
	}
	
	//Implementation de l'affichage
	public String toString(){
		String result;
		result=getLabel();
		return result;
	}
	public void paint(Graphics g,int r){
		Color tmp=g.getColor();
		g.setColor(getColor());
		g.fillOval((int)getX()-r/2,(int)getY()-r/2,r,r);
		g.drawString(getLabel(),(int)getX()-r/2,(int)getY()-r/2);
		g.setColor(tmp);
	}
	public Node clone(){
		int i=NBNODE;
		NBNODE=getNum();
		Node tmp=new Node(getLabel(),getX(),getY(),getColor());
		tmp.lNext=(TreeSet<Integer> )(this.lNext.clone());
		tmp.lPrec=(TreeSet<Integer> )(this.lPrec.clone());
		NBNODE=i+NBNODE;
		return tmp;
	}
	
	
}

