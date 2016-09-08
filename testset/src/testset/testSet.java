package testset;
import java.util.ArrayList;
import java.util.HashSet;

public class testSet {

	public static void main(String[] args){
		
		tables tb=new tables();
		 tb.addCj(new Cj("140201","计算机",7));
		 tb.addCj(new Cj("140201","php",80));
		 tb.addCj(new Cj("140202","计算机",77));
		 tb.addCj(new Cj("140201","c语言",65));
		 tb.addCj(new Cj("140203","计算机",90));
		 ArrayList<Cj> a=new ArrayList<Cj>();
		a= tb.getCjByCourse("计算机");
		System.out.println("dddddfddddd");
		for(Cj cj:a){
		 System.out.println(cj.getCourse()+" :"+cj.getxh()+": "+cj.getCj());
		}
	}
}

class Cj{
	private String Xh;
	private int score;
	private String course;
	public Cj(String xh,String course,int score){
		this.Xh=xh;
		this.score=score;
		this.course=course;
	}	
	public String getxh()
	{return Xh;
		
	}
	public int getCj(){
		return score;
		
	}
	public String getCourse(){
		return course;
	}
}

class tables{
	private HashSet<Cj> s=new HashSet<Cj>();
	public void addCj(Cj cj ){
		s.add(cj);
	}
	
	public ArrayList<Cj> getCjByCourse(String Course){
		
		ArrayList<Cj> list=new ArrayList<Cj>();
		String ss="";
		for(Cj cj:s){
			ss=cj.getCourse();
			if(ss.equals(Course)){
				list.add(cj);
			}
		}
		return list;
	}
}