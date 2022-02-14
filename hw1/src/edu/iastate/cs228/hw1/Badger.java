package edu.iastate.cs228.hw1;

/**
 *  
 * @author Noah Roberts
 *
 */

/**
 * A badger eats a rabbit and competes against a fox. 
 */
public class Badger extends Animal
{
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Badger (Plain p, int r, int c, int a) 
	{
		plain = p;
		row = r;
		column = c;
		age = a;
	}
	
	/**
	 * A badger occupies the square. 	 
	 */
	public State who()
	{
		return State.BADGER; 
	}
	
	/**
	 * A badger dies of old age or hunger, or from isolation and attack by a group of foxes. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		Living newLiving;
		int[] population = new int[5];
		census(population);
		
		if(age >= BADGER_MAX_AGE)
		{
			newLiving = new Empty(plain, row, column);
		}
		
		else if(population[BADGER] == 1 && population[FOX] > 1)
		{
			newLiving = new Fox(plain, row, column, 0);
		}
		
		else if(population[BADGER] + population[FOX] > population[RABBIT])
		{
			newLiving = new Empty(plain, row, column);		
		}
		
		else
		{
			newLiving = new Badger(plain, row, column, age + 1);
		}
		
		plain.getGrid()[row][column] = newLiving;
		pNew = plain;
		
		return newLiving;
	}
}
