package org;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcCur {
	static ConcurrentHashMap<Integer, Integer> cc = new ConcurrentHashMap<>();
	
	public static void main(String[] args) {
		
			   
			  
		
		/*double d1 = 1.0;//Double.NaN;
		double d2 = 1;//Double.NaN;
		System.out.println("Test "+(d1==d2));
		
	//Map <Integer, Integer> cc = new HashMap<>();
		cc.put(1, 11);
		cc.put(2, 22);
		cc.put(3, 33);
//		cc.entrySet().stream().forEachOrdered(e->System.out.println(e.getKey()+" "+e.getValue()));
		
		//for(int i=0; i<cc.size(); i++)
		Set en = cc.entrySet();
		Iterator it = en.iterator() ;
		while (it.hasNext())
		{
			cc.remove(2);
			//System.out.println(it.next());
			//cc.put(4, 44);
		}
		*/
		try(FileOutputStream fi = new FileOutputStream(new File("C:\\Users\\Sudesh_Mane\\Downloads\\flatten12.jpeg")); ) {
			fi.close();
			fi.close();
			System.out.print("sadasdsa");
		}catch(Exception e) {
			System.out.print(e);
		}finally {
			
		}
		List<String> list = new ArrayList<>();
		list.add("Nikhil");list.add("Nitin");list.add("Abcd");list.add("Pqr");list.add("Xyz");
		
		list.stream().filter(e-> {System.out.println("In Predicate "+e); return e.startsWith("N"); })	
		.forEach(e->System.out.println("For Each "+e));
		

		//filter() - intermediate method
		//forEach () - terminal
		
	}
	

}
