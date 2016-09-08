package notebook;

import java.util.ArrayList;
class Value{
	private int i;
	public void set(int i){
		this.i=i;
	}
	public int get(){return i;}
}
public class Notebook {
	private ArrayList<String> notes=new ArrayList<String>();
	public void add(String s){
		notes.add(s);
	}
	public void add(String s,int location){
		notes.add(location,s);
	}
	public int getSize(){
		return notes.size();
	}
	public String getNote(int index){
		return notes.get(index);
	}
	public void removeNote(int index){
	    notes.remove(index);
	}
	public String[] list(){
		
		 String[]a=new String[notes.size()];
//		 for(int i=0;i<notes.size();i++){
//			 a[i]=notes.get(i);
//		 }
		 notes.toArray(a);
		  
		 return a;
	}
	
	
	public static void main(String[] args) {
		Value[]a=new Value[10];
		for(int i=0;i<a.length;i++){
			a[i]=new Value();
			a[i].set(i);
		}
		for(Value v:a){
			System.out.println(v.get());
			v.set(0);
		}
		for(Value v:a){
			System.out.println(v.get());
			v.set(0);
		}
}
}

		// TODO Auto-generated method stub
//		Notebook nb=new Notebook();	
//		String[]a=new String[2];
//		a[0]="first";
//		a[1]="second";
//		nb.add("bystc");
//		nb.add("jzystc");
//		nb.add("third",1);
//		System.out.println(nb.getSize());
//		System.out.println(nb.getNote(0));
//		System.out.println(nb.getNote(1));
//		nb.removeNote(1);
//		String[]a=nb.list();
//		for(String s:a){
//			System.out.println(s);
//		}
	

