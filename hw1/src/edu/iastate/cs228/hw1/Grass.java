package edu.iastate.cs228.hw1;

/**
 *  
 * @author Noah Roberts
 *
 */

/**
 * Grass remains if more than rabbits in the neighborhood; otherwise, it is eaten. 
 *
 */
public class Grass extends Living 
{
	public Grass (Plain p, int r, int c) 
	{
		plain = p;
		row = r;
		column = c;
	}
	
	public State who()
	{
		// TODO  
		return State.GRASS; 
	}
	
	/**
	 * Grass can be eaten out by too many rabbits. Rabbits may also multiply fast enough to take over Grass.
	 */
	public Living next(Plain pNew)
	{
		Living newLiving;
		int[] population = new int[5];
		census(population);
		
		if(population[RABBIT] >= 3 * population[GRASS])
		{
			newLiving = new Empty(plain, row, column);
		}
		
		else if(population[RABBIT] >= 3)
		{
			newLiving = new Rabbit(plain, row, column, 0);
		}
		
		else
		{
			newLiving = new Grass(plain, row, column);
		}
		
		
		plain.getGrid()[row][column] = newLiving;
		pNew = plain;
		
		return newLiving;
	}
}
