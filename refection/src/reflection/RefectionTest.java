package reflection;
import java.util.*;
import java.lang.reflect.*;

public class RefectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name;
		if(args.length>0)
			name=args[0];
		else
		{
			Scanner in =new Scanner(System.in);
			System.out.println("Enter class name (e.g.java.util.Date):");
			name=in.next();
		}
		try
		{
			Class cl=Class.forName(name);
			Class supercl=cl.getSuperclass();
			String modifiers=Modifier.toString(cl.getModifiers());
			if(modifiers.length()>0)
				System.out.println("extends"+supercl.getName());
			System.out.println("\n{\n");
			printConstructors(cl);
			System.out.println();
			printMethods(cl);
			System.out.println();
			printFields(cl);
			System.out.println("}");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		System.exit(0);
	}

	public static void printFields(Class cl) {
		// TODO Auto-generated method stub
		Field[] fields=cl.getDeclaredFields();
		for(Field f:fields)
		{
			Class type=f.getType();
			String name=f.getName();
			System.out.println("  ");
			String modifiers=Modifier.toString(f.getModifiers());
			if(modifiers.length()>0)
				System.out.println(modifiers+" ");
			System.out.println(type.getName()+" "+name+";");
		}
	}

	public static void printMethods(Class cl) {
		// TODO Auto-generated method stub
		Method[]methods=cl.getDeclaredMethods();
		for(Method m:methods)
		{
			Class retType=m.getReturnType();
			String name=m.getName();
			
			System.out.println("  ");
			String modifiers=Modifier.toString(m.getModifiers());
			if(modifiers.length()>0)
				System.out.print(modifiers+" ");
			System.out.print(retType.getName()+" "+name+"(");
			
			Class[]paramTypes=m.getParameterTypes();
			for(int j=0;j<paramTypes.length;j++)
			{
				if(j>0)
					System.out.println(", ");
				System.out.println(paramTypes[j].getName());
			}
			System.out.println(");");
			
		}
	}

	public static void printConstructors(Class cl) {
		// TODO Auto-generated method stub
		Constructor[] constructors=cl.getDeclaredConstructors();
		for(Constructor c:constructors)
		{
			String name=c.getName();
			System.out.println(" ");
			String modifiers=Modifier.toString(c.getModifiers());
			if(modifiers.length()>0)
				System.out.println(modifiers+" ");
			System.out.println(name+"(");
			
			Class[]paramTypes=c.getParameterTypes();
			for(int j=0;j<paramTypes.length;j++)
			{
				if(j>0)
					System.out.println(", ");
				System.out.println(paramTypes[j].getName());
			}
		}
	}
	

}
