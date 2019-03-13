package assignment10;
import java.util.*;
import java.lang.Runnable;

public class assignment10 
{
	public static void main(String args[])
	{
		MyClass c1=new MyClass("Thread1", "Ping");
		MyClass c2=new MyClass("Thread2", "Pong");
		Thread t1=new Thread(c1);
		Thread t2=new Thread(c2);
		t1.start();
		t2.start();
	}
}

class MyClass implements Runnable
{
	private String name, msg;
	
	//parameterized constructor
	public MyClass( String n, String m)
	{
		name=n;
		msg=m;
	}
	
	public void run()
	{
		for(int i=0; i<5; i++)
		{
			System.out.println(name + "\tsays\t" + msg);
		}
	
		//for exceptions
		try
		{
			Thread.sleep(5000);
		}
		catch(InterruptedException ie) {};
		
		System.out.println(name + ": Finished Execution");
	}
	
}

