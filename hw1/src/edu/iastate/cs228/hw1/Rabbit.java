package edu.iastate.cs228.hw1;

/**
 *  
 * @author Noah Roberts
 *
 */

/*
 * A rabbit eats grass and lives no more than three years.
 */
public class Rabbit extends Animal 
{	
	/**
	 * Creates a Rabbit object.
	 * @param p: plain  
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Rabbit (Plain p, int r, int c, int a) 
	{
		plain = p;
		row = r;
		column = c;
		age = a;
	}
		
	// Rabbit occupies the square.
	public State who()
	{
		return State.RABBIT; 
	}
	
	/**
	 * A rabbit dies of old age or hunger. It may also be eaten by a badger or a fox.  
	 * @param pNew     plain of the next cycle 
	 * @return Living  new life form occupying the same square
	 */
	public Living next(Plain pNew)
	{
		Living newLiving;
		int[] population = new int[5];
		census(population);
		
		if(age >= RABBIT_MAX_AGE)
		{
			newLiving = new Empty(plain, row, column);
		}
		
		else if(population[GRASS] <= 0)
		{
			newLiving = new Empty(plain, row, column);
		}
		
		else if(population[BADGER] + population[FOX] >= population[RABBIT]
	         || population[FOX] > population[BADGER])
		{
			newLiving = new Fox(plain, row, column, 0);	
		}
		
		else
		{
			newLiving = new Rabbit(plain, row, column, age + 1);
		}
		
		plain.getGrid()[row][column] = newLiving;
		pNew = plain;
		
		return newLiving;
	}
	
}
