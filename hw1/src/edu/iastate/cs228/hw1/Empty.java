package edu.iastate.cs228.hw1;

/**
 *  
 * @author Noah Roberts
 *
 */

/** 
 * Empty squares are competed by various forms of life.
 */
public class Empty extends Living 
{
	public Empty (Plain p, int r, int c) 
	{
		plain = p;
		row = r;
		column = c;
	}
	
	public State who()
	{
		// TODO 
		return State.EMPTY; 
	}
	
	/**
	 * An empty square will be occupied by a neighboring Badger, Fox, Rabbit, or Grass, or remain empty. 
	 * @param pNew     plain of the next life cycle.
	 * @return Living  life form in the next cycle.   
	 */
	public Living next(Plain pNew)
	{
		Living newLiving;
		int[] population = new int[5];
		census(population);
		
		if(population[RABBIT] > 1)
		{
			newLiving = new Rabbit(plain, row, column, 0);
		}
		
		else if(population[FOX] > 1)
		{
			newLiving = new Fox(plain, row, column, 0);
		}
		
		else if(population[BADGER] > 1)
		{
			newLiving = new Badger(plain, row, column, 0);
		}
		
		else if(population[GRASS] > 1)
		{
			newLiving = new Grass(plain, row, column);
		}
		
		else
		{
			newLiving = new Empty(plain, row, column);
		}
		
		plain.getGrid()[row][column] = newLiving;
		pNew = plain;
		
		return newLiving;
	}
}
