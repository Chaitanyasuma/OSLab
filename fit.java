package fit;
import java.util.*;

public class fit 
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		
		//for memory
		System.out.println("Enter number of memory blocks");
		int m=sc.nextInt();
		int memory[]=new int[m];
		int occupied[]=new int[m];		//to keep track of which memory blocks are occupied
		
		//accepting size of memory blocks
		for(int i=0; i<m; i++)
		{
			System.out.println("Enter size of memory block in kB");
			memory[i]=sc.nextInt();
		}
		
		//for processes
		System.out.println("Enter number of processes");
		int n=sc.nextInt();
		int process[]=new int[n];
		int allocated[]=new int[n];		//to check if process has been allocated or not
		
		//accepting sizes of processes
		for(int i=0; i<n; i++)
		{
			System.out.println("Enter size of process in kB");
			process[i]=sc.nextInt();
		}
		
		int choice=0;
		do
		{
			System.out.println("\nMenu-\n1-First Fit\n2-Best Fit\n3-Worst Fit\n4-Exit");
			choice=sc.nextInt();
			
			switch(choice)
			{
				case 1: init(m, n, occupied, allocated);
						firstfit(m, memory, n, process, occupied, allocated);
						check(allocated, n, process);
				break;
				
				case 2: init(m, n, occupied, allocated);
						bestfit(m, memory, n, process, occupied, allocated);
						check(allocated, n, process);
				break;
				
				case 3: init(m, n, occupied, allocated);
						worstfit(m, memory, n, process, occupied, allocated);
						check(allocated, n, process);
				break;
		
			}
			
		}while(choice!=4);
	}
	
	//to initialize both occupied and allocated arrays with zero before every algorithm
	static void init(int m, int n, int occupied[], int allocated[])
	{
		for(int i=0; i<n; i++)
		{
			allocated[i]=0;
		}
		
		for(int i=0; i<m; i++)
		{
			occupied[i]=0;
		}
	}
	
	static void firstfit(int m, int memory[], int n, int process[], int occupied[], int allocated[])
	{
		//first fit algorithm-first big enough block
		for(int i=0; i<n; i++)			//for every process
		{
			for(int j=0; j<m; j++)		//check every memory block
			{
				if(process[i]<=memory[j] && occupied[j]!=1)
				{
					System.out.println(process[i]+"kB is allocated "+memory[j]+"kB");
					occupied[j]=1;
					allocated[i]=1;
					break;
				}
			}
		}
	}
	
	static void check(int allocated[], int n, int process[])
	{
		//to check which block isn't allocated
		for(int i=0; i<n; i++)
		{
			if(allocated[i]!=1)
			{
				System.out.println(process[i]+"kB has not been allocated");
			}
		}
	}
	
	static void bestfit(int m, int memory[], int n, int process[], int occupied[], int allocated[])
	{
		//sort memory in ascending order
		for(int i=0; i<m-1; i++)
		{
			for(int j=0; j<m-i-1; j++)
			{
				if(memory[j]>memory[j+1])
				{
					int temp=memory[j];
					memory[j]=memory[j+1];
					memory[j+1]=temp;
				}
			}
		}
		
		//best fit algorithm-smallest large enough block
		for(int i=0; i<n; i++)			//for every process
		{
			for(int j=0; j<m; j++)		//check every memory block
			{
				if(process[i]<=memory[j] && occupied[j]!=1)
				{
					System.out.println(process[i]+"kB is allocated "+memory[j]+"kB");
					occupied[j]=1;
					allocated[i]=1;
					break;
				}
			}
		}
	}
	
	static void worstfit(int m, int memory[], int n, int process[], int occupied[], int allocated[])
	{
		//sort memory in descending order
		for(int i=0; i<m-1; i++)
		{
			for(int j=0; j<m-i-1; j++)
			{
				if(memory[j]<memory[j+1])
				{
					int temp=memory[j];
					memory[j]=memory[j+1];
					memory[j+1]=temp;
				}
			}
		}
		
		//worst fit algorithm-largest large enough block
		for(int i=0; i<n; i++)			//for every process
		{
			for(int j=0; j<m; j++)		//check every memory block
			{
				if(process[i]<=memory[j] && occupied[j]!=1)
				{
					System.out.println(process[i]+"kB is allocated "+memory[j]+"kB");
					occupied[j]=1;
					allocated[i]=1;
					break;
				}
			}
		}
	}

}

/*Enter number of memory blocks
5
Enter size of memory block in kB
100
Enter size of memory block in kB
500
Enter size of memory block in kB
200
Enter size of memory block in kB
600
Enter size of memory block in kB
300
Enter number of processes
4
Enter size of process in kB
212
Enter size of process in kB
417
Enter size of process in kB
112
Enter size of process in kB
426

Menu-
1-First Fit
2-Best Fit
3-Worst Fit
4-Exit
1
212kB is allocated 500kB
417kB is allocated 600kB
112kB is allocated 200kB
426kB has not been allocated

Menu-
1-First Fit
2-Best Fit
3-Worst Fit
4-Exit
2
212kB is allocated 300kB
417kB is allocated 500kB
112kB is allocated 200kB
426kB is allocated 600kB

Menu-
1-First Fit
2-Best Fit
3-Worst Fit
4-Exit
3
212kB is allocated 600kB
417kB is allocated 500kB
112kB is allocated 300kB
426kB has not been allocated

Menu-
1-First Fit
2-Best Fit
3-Worst Fit
4-Exit*/
