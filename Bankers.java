import java.util.*;
public class Bankers 
{
	public static void main(String[] args) 
	{
		algo a=new algo();
		a.accept();
		a.calc();
		a.display();
		a.calc2();
		a.SafeSeqDisplay();
	}
}	
	
class algo
{
	//global variables
	int alloc[][]=new int[10][10];
	int max[][]=new int[10][10];
	int need[][]=new int[10][10];
	int resources[]=new int[10];
	int r;	//column
	int p;	//row
	int safeseq[]=new int[10];
	int executed[]=new int[10];
	int index;
	
	//default constructor
	algo()
	{
		r=p=index=0;
		for(int i=0; i<10; i++)
		{
			resources[i]=0;
			safeseq[i]=0;
			executed[i]=0;
			
			for(int j=0; j<10; j++)
			{
				alloc[i][j]=0;
				max[i][j]=0;
				need[i][j]=0;
			}
		}
	}
	
	Scanner s=new Scanner(System.in);
	
	//accepting all details from user
	void accept()
	{
		System.out.println("Enter number of resources");
		r=s.nextInt();
		
		System.out.println("Enter maximum number of available resources for each");
		for(int i=0; i<r; i++)
		{
			System.out.println("R"+(i+1)+"=");
			resources[i]=s.nextInt();
		}
		
		System.out.println("Enter number of processes");
		p=s.nextInt();
		
		//accepting max matrix
		System.out.println("Enter maximum number of resources needed by each process");
		for(int i=0; i<p; i++)
		{
			System.out.println("For process P"+i);
			for(int j=0; j<r; j++)
			{
				System.out.println("Resource "+(j+1));
				max[i][j]=s.nextInt();
				//System.out.println(max[i][j]);
			}
		}
		
		//accepting alloc matrix
		System.out.println("Enter allocated number of resources for each process");
		for(int i=0; i<p; i++)
		{
			System.out.println("For process P"+i);
			for(int j=0; j<r; j++)
			{
				System.out.println("Resource "+(j+1));
				alloc[i][j]=s.nextInt();
			}
		}
	}
	
	//calculating need matrix
	void calc()
	{
		for(int i=0; i<p; i++)
		{
			for(int j=0; j<r; j++)
			{
				need[i][j]=max[i][j]-alloc[i][j];
				//System.out.println(need[i][j]);
			}
		}
	}
	
	//display 2-D matrix
	void display()
	{
		System.out.println("\tMax\tAlloc\tNeed");
		for(int i=0; i<p; i++)
		{
			System.out.print("P"+i+"\t");
			//max matrix
			for(int j=0; j<r; j++)
			{
				System.out.print(max[i][j]+" ");
			}
			System.out.print("\t");
			
			//allocated matrix
			for(int j=0; j<r; j++)
			{
				System.out.print(alloc[i][j]+" ");
			}
			System.out.print("\t");
			
			//need matrix
			for(int j=0; j<r; j++)
			{
				System.out.print(need[i][j]+" ");
			}
			
			System.out.println();
		}
	}
	
	void calc2()
	{
		int sum[]=new int[r];
		for(int i=0; i<r; i++)
		{
			sum[i]=0;
		}
		
		int exec=0;
		//calculate sum of allocated matrix
		for(int i=0; i<r; i++)
		{
			for(int j=0; j<p; j++)
			{
				sum[i]=sum[i]+alloc[j][i];
				
			}
			//System.out.print(sum[i]);
		}
		
		//remaining resources calculation
		int remaining[]=new int[r];
		for(int i=0; i<r; i++)
		{
			remaining[i]=resources[i]-sum[i];
			//System.out.print(remaining[i]);
		}
		
		int flag1=0;
		int flag2=0;
		int i=0;
		//now to check for which process would the remaining resources suffice
		while(exec<p)
		{
			flag1=flag2=0;
			for(int j=0; j<r; j++)
			{
				if(need[i][j]>remaining[j])
				{
					flag1=1;
					break;
				}
			}
			
			if(flag1==1)
			{
				//System.out.println("Not satisfied for P"+i);
				i++;
			}
			
			else	//process found
			{
				//System.out.println("Satisfied for P"+i);
				if(executed[i]!=1)
				{
					safeseq[index]=i;
					executed[i]=1;
					exec++;
					flag2=1;
					
					//remaining resources incremented
					for(int j=0; j<r; j++)
					{
						remaining[j]+=alloc[i][j];
						//System.out.print(remaining[j]);
					}
					i=0;
					index++;
				}
				else
					i++;
			}
			
		}
		if(flag2==0)
			System.out.println("Not satisfied for any process");
	}
	
	//display safe sequence
	void SafeSeqDisplay()
	{
		System.out.println("Safe sequence is: ");
		for(int i=0; i<index; i++)
		{
			System.out.print("P"+safeseq[i]+" ");
		}	
	}
}

/*Output
 * Enter number of resources
3
Enter maximum number of available resources for each
R1=
10
R2=
5
R3=
7
Enter number of processes
5
Enter maximum number of resources needed by each process
For process P0
Resource 1
7
Resource 2
5
Resource 3
3
For process P1
Resource 1
3
Resource 2
2
Resource 3
2
For process P2
Resource 1
9
Resource 2
0
Resource 3
2
For process P3
Resource 1
2
Resource 2
2
Resource 3
2
For process P4
Resource 1
4
Resource 2
3
Resource 3
3
Enter allocated number of resources for each process
For process P0
Resource 1
0
Resource 2
1
Resource 3
0
For process P1
Resource 1
2
Resource 2
0
Resource 3
0
For process P2
Resource 1
3
Resource 2
0
Resource 3
2
For process P3
Resource 1
2
Resource 2
1
Resource 3
1
For process P4
Resource 1
0
Resource 2
0
Resource 3
2
	Max		Alloc	Need
P0	7 5 3 	0 1 0 	7 4 3 
P1	3 2 2 	2 0 0 	1 2 2 
P2	9 0 2 	3 0 2 	6 0 0 
P3	2 2 2 	2 1 1 	0 1 1 
P4	4 3 3 	0 0 2 	4 3 1 
Safe sequence is: 
P1 P3 P0 P2 P4 */