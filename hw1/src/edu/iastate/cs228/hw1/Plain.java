package edu.iastate.cs228.hw1;

/**
 *  
 * @author Noah Roberts
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;

/**
 * 
 * The plain is represented as a square grid of size width x width.
 *
 */
public class Plain 
{
	private int width; // grid size: width X width

	private Living[][] grid;

	/**
	 * Default constructor reads from a file
	 */
	public Plain(String inputFileName) throws FileNotFoundException 
	{

			File inputFile = new File(inputFileName);

			Scanner fileReader = new Scanner(inputFile);

			String line = fileReader.nextLine();
			Scanner widthReader = new Scanner(line);
			width = 0;
			

			// Determines the width of the grid from the first line
			while (widthReader.hasNext()) 
			{
				widthReader.next();
				width++;
			}

			grid = new Living[width][width];

			for (int row = 0; row < width; row++) 
			{
				Scanner lineReader = new Scanner(line);

				for (int col = 0; col < width; col++) 
				{
					String livingTypeString = lineReader.next();
					char livingType = livingTypeString.charAt(0);
					int livingTypeAge = 0;

					// Checks if the tag is an Animal, if so then it gets the 2nd char
					if (livingType == 'B' || livingType == 'F' || livingType == 'R') 
					{
						livingTypeAge = Integer.parseInt(livingTypeString.substring(1, 2));
					}

					switch (livingType) 
					{
					case 'B':
						grid[col][row] = new Badger(this, row, col, livingTypeAge);
						break;

					case 'E':
						grid[col][row] = new Empty(this, row, col);
						break;

					case 'F':
						grid[col][row] = new Fox(this, row, col, livingTypeAge);
						break;

					case 'G':
						grid[col][row] = new Grass(this, row, col);
						break;

					case 'R':
						grid[col][row] = new Rabbit(this, row, col, livingTypeAge);
						break;
					}
				}
				
				if(fileReader.hasNextLine())
				{
					line = fileReader.nextLine();
				}
				
			}

			fileReader.close();
	}

	/**
	 * Constructor that builds a w x w grid without initializing it.
	 * 
	 * @param width the grid
	 */
	public Plain(int w) 
	{
		width = w;
		grid = new Living[width][width];
		randomInit();
	}

	/**
	 * Gets width of the Plain.
	 * 
	 * @return width
	 */
	public int getWidth() 
	{
		return width;
	}
	
	public Living[][] getGrid()
	{
		return grid;
	}
	
	
	/**
	 * Initialize the plain by randomly assigning to every square of the grid one of
	 * BADGER, FOX, RABBIT, GRASS, or EMPTY.
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit() 
	{
		Random generator = new Random();

		for (int row = 0; row < width; row++) 
		{
			for (int col = 0; col < width; col++) 
			{
				int randNum = generator.nextInt(5);

				switch (randNum) 
				{
				case 0:
					grid[col][row] = new Badger(this, row, col, 0);
					break;

				case 1:
					grid[col][row] = new Empty(this, row, col);
					break;

				case 2:
					grid[col][row] = new Fox(this, row, col, 0);
					break;

				case 3:
					grid[col][row] = new Grass(this, row, col);
					break;

				case 4:
					grid[col][row] = new Rabbit(this, row, col, 0);
					break;
				}
			}
		}
	}

	/**
	 * Output the plain grid. For each square, output the first letter of the living
	 * form occupying the square. If the living form is an animal, then output the
	 * age of the animal followed by a blank space; otherwise, output two blanks.
	 */
	public String toString() 
	{
		String stringOutput = "";
		State state;
		for (int row = 0; row < width; row++) 
		{
			for (int col = 0; col < width; col++) 
			{
				state = grid[col][row].who();

				switch (state) 
				{
				case BADGER:
					stringOutput += "B" + ((Animal) grid[col][row]).myAge() + " ";
					break;
				case EMPTY:
					stringOutput += "E" + "  ";
					break;
				case FOX:
					stringOutput += "F" + ((Animal) grid[col][row]).myAge() + " ";
					break;
				case GRASS:
					stringOutput += "G" + "  ";
					break;
				case RABBIT:
					stringOutput += "R" + ((Animal) grid[col][row]).myAge() + " ";
					break;
				}
			}
			stringOutput += "\n";
		}

		return stringOutput;
	}

	/**
	 * Write the plain grid to an output file. Also useful for saving a randomly
	 * generated plain for debugging purpose.
	 * 
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException 
	{
			PrintWriter writer = new PrintWriter(outputFileName);
		
			for (int row = 0; row < width; row++) 
			{
				for (int col = 0; col < width; col++) 
				{
					State state = grid[row][col].who();
					int animalAge = 0;
					if(state == State.BADGER || state == State.FOX || state == State.RABBIT)
					{
						animalAge = ((Animal) grid[row][col]).myAge();
					}
					
					switch (state) 
					{
						case BADGER:
							writer.print("B" + animalAge + " ");
							break;
						case EMPTY:
							writer.print("E  ");
							break;
						case FOX:
							writer.print("F" + animalAge + " ");
							break;
						case GRASS:
							writer.print("G  ");
							break;
						case RABBIT:
							writer.print("R" + animalAge + " ");
							break;
					}
					
				}
				
				writer.println();
			}	
	}
}