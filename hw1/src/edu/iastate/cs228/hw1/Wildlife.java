package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 *  
 * @author Noah Roberts
 *
 */

/**
 * 
 * The Wildlife class performs a simulation of a grid plain with
 * squares inhabited by badgers, foxes, rabbits, grass, or none. 
 *
 */
public class Wildlife 
{
	/**
	 * Update the new plain from the old plain in one cycle. 
	 * @param pOld  old plain
	 * @param pNew  new plain 
	 */
	public static void updatePlain(Plain pOld, Plain pNew)
	{
		for(int row = 0; row < pOld.getWidth(); row++)
		{
			for(int col = 0; col < pOld.getWidth(); col++)
			{
				pOld.getGrid()[row][col].next(pNew);
			}
		}
		
		// For every life form (i.e., a Living object) in the grid pOld, generate  
		// a Living object in the grid pNew at the corresponding location such that 
		// the former life form changes into the latter life form. 
		// 
		// Employ the method next() of the Living class. 
	}
	
	/**
	 * Repeatedly generates plains either randomly or from reading files. 
	 * Over each plain, carries out an input number of cycles of evolution. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{	
		Scanner input = new Scanner(System.in);
		Plain even = null;   				
		Plain odd = null;             
		
		boolean correctInput = false;
		int trialNum = 1;
		int plainType = 0;
		int gridWidth = 0;
		int numCycles = 0;
		String fileName = null;
		
		System.out.println("Simulation of Wildlife of the Plain");
		System.out.println("Keys: 1 (random plain), 2 (file input), 3 (exit)");
	
		do
		{
			System.out.print("Trial " + trialNum + ": ");
		
			plainType = input.nextInt();
			
			if(plainType == 1 || plainType == 2 || plainType == 3)
			{
				correctInput = true;
			}
			
			if(correctInput)
			{
				if(plainType == 1)
				{
					do
					{
						System.out.print("Grid Width: ");
						gridWidth = input.nextInt();
					} 
					while(gridWidth <= 0);				
				}
					
				else if(plainType == 2)
				{
					System.out.print("File Name: ");
					fileName = input.next();
				}
				
				do
				{
					System.out.print("# of Cycles: ");
					numCycles = input.nextInt();
				}
				while(numCycles < 0);
				
				
				if(numCycles % 2 == 0)
				{
					if(plainType == 1)
					{
						even = new Plain(gridWidth);
					}
					
					else
					{
						even = new Plain(fileName);
					}
					
					odd = new Plain(gridWidth);
					
					System.out.println("\nInitial Plain:\n" + even.toString());
				}
				
				else
				{
					if(plainType == 1)
					{
						odd = new Plain(gridWidth);
					}
					
					else
					{
						
						odd = new Plain(fileName);
					}
					
					even = new Plain(gridWidth);
					
					System.out.println("\nInitial Plain:\n" + odd.toString());
				}
				
				int cyclesLeft = numCycles;
				
				for(int i = 0; i < numCycles; i++)
				{
					if(cyclesLeft % 2 == 0)
					{
						updatePlain(even, odd);
					}
					
					else
					{
						updatePlain(odd, even);
					}
					
					
					
					cyclesLeft--;
				}
			    
				if(numCycles % 2 == 0)
				{
					System.out.println("Final Plain: \n" + even.toString());
				}
				
				else
				{
					System.out.println("Final Plain: \n" + odd.toString());
				}
				
				
			    trialNum++;
			}
			
		} 
		while(plainType != 3);
		

		// Generate wildlife simulations repeatedly like shown in the 
		// sample run in the project description. 
		// 
		// 1. Enter 1 to generate a random plain, 2 to read a plain from an input
		//    file, and 3 to end the simulation. (An input file always ends with 
		//    the suffix .txt.)
		// 
		// 2. Print out standard messages as given in the project description. 
		// 
		// 3. For convenience, you may define two plains even and odd as below. 
		//    In an even numbered cycle (starting at zero), generate the plain 
		//    odd from the plain even; in an odd numbered cycle, generate even 
		//    from odd. 
		
		
		
		// 4. Print out initial and final plains only.  No intermediate plains should
		//    appear in the standard output.  (When debugging your program, you can 
		//    print intermediate plains.)
		// 
		// 5. You may save some randomly generated plains as your own test cases. 
		// 
		// 6. It is not necessary to handle file input & output exceptions for this 
		//    project. Assume data in an input file to be correctly formated. 
		
		
		
	 // odd cycle 3   start with odd -> do cycle into  even, 2 left -> do cycle into odd, 1 left -> do cycle into even, 0 left: 
	//  odd cycle 1 start with odd -> do cycle into even, 0 left
		
	}
}
