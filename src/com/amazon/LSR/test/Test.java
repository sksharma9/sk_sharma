package com.amazon.LSR.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
	
	
	
	public static void main(String[] args) {
		
		Date d1= new Date(2020-1900, 05, 12, 13, 30);
		
		Date d2= new Date(2020-1900, 05, 14, 9, 15);
		
		Calendar c= Calendar.getInstance();
		
		System.out.println(d1.compareTo(d2));
		
		System.out.println(d1.getTime());
		
		Timestamp timestamp1 = new Timestamp(d1.getTime());
		
		System.out.println(c);
		
		c.set(2020+1900,5,12,13,30);
		
		
		Calendar c2= Calendar.getInstance();
		
		
		c2.set(2020+1900,5,15,9,15);
		
		
		System.out.println(c+" "+c2);
		
		System.out.println(c.getTimeInMillis()-c2.getTimeInMillis());
		
		
		/*
		 * d1.get;
		 * 
		 * // Locker l= new Locker();
		 * 
		 * 
		 * String s1="abc"; String s2="bcl";
		 * 
		 * s1.com
		 * 
		 * RepositoryUtil.getSession().createQuery("select  l from Locker l",Locker.
		 * class).stream().filter(l->l.getLockerId()==2) .map(Locker::getActivationDate)
		 * .forEach(System.out::println);
		 */

		
		List<Testing> l= new ArrayList<Testing>();
		
		l.add(new Testing(4,7));
		
		l.add(new Testing(2,9));
		l.add(new Testing(1,5));
		l.add(new Testing(3,5));
		l.add(new Testing(8,11));
		
		
		l.stream().sorted((t1,t2)-> (t2.a-t2.b)-(t1.a-t1.b)).forEach(Testing::display);;
		
	
       long diff= d2.getTime()-d1.getTime();
		
		double diffHours = (double)diff / (60 * 60 * 1000);
		
		System.out.println(diffHours);
		
		Double A[]=new Double []{0.45,1.2,1.6,1.9,2.5,2.8,4.3,5.2,5.3,5.8,6.3,6.7,7.4,7.9};
		
		List<Double> list= Arrays.asList(A);
		
		Collections.sort(list);
		
		Map<Double,List<Double>> map= new HashMap<>();
		
		Double slot1= list.get(0)+(Double)(list.get(list.size()-1)-list.get(0))/4;
		
		Double slot2= slot1+(Double)(list.get(list.size()-1)-slot1)/2;
		
		//System.out.println(slot1+" "+slot2);
		
		map.put(slot1, new ArrayList<Double>());
		
		map.put(slot2, new ArrayList<Double>());
		
		map.put(list.get(list.size()-1), new ArrayList<Double>());
		
}
	
	public static void dist(List<Double> list,Map<Double,List<Double>> map,Double [] slots)
	{
		
		
		for(int i=0;i<list.size();i++)
		{
			Double key=minDist(slots, list.get(i));
			map.get(key).add(list.get(i));
		}
		
		int i=0;
		for(Map.Entry<Double,List<Double>> entry:map.entrySet() )
		{
			
			slots[i]=entry.getValue().stream().mapToDouble(d->d).average();
					
		}
		
		
		
	}
	
	public static Double minDist(Double [] slots,Double d)
	{
		
		Double slot = null;
		double min=Double.MAX_VALUE;
		for(int i=0;i<slots.length;i++)
		{
			if(min>Math.abs(slots[i]-d));
			{
				slot=slots[i];
				min=Math.abs(slots[i]-d);
			}
		}
		
		return slot;
	}

}



class Testing {
	
	int a;
	int b;
	
	public Testing(int a,int b) {
		// TODO Auto-generated constructor stub
		this.a=a;
		this.b=b;
	}
	public void display()
	{
		System.out.println(a+" "+b);
	}
}