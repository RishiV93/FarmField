import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// the Simulator class - contains the main application entry point
public class Simulator 
{	
	// private variables
	private int steps = 25;
	private int step = 1;
	private List<Actor> actorList = new ArrayList<Actor>();
	private List<Actor> deadActorList = new ArrayList<Actor>();
	private Field field;
	private List<Actor> tempNewActorList = new ArrayList<Actor>();
	
	// default application entry point is always the main method
	public static void main(String[] args) 
	{	
		// instantiate an instance of the simulator class.
		Simulator simulator = new Simulator();

		// call the simulator's RunSimulator method to render a JFrame of the specified size, populate and simulate
		simulator.RunSimulator(50, 50);
	}
	
	// default constructor for the Simulator class.  
	public Simulator()
	{            

	}

	// adds an actor to the actor list
	public void AddActorToList(Actor actor)
	{
		this.actorList.add(actor);
	}
	
	// adds an actor to the dead actor list
	public void AddToDeadActorFromList(Actor actor)
	{
		this.deadActorList.add(actor);		
	}

	// main run simulator method which holds creation of JFrame and excution of the simulation
	public void RunSimulator(int depth, int width)
	{
		// creates a field with the provided depth and width e.g. 50*50
		field = new Field(depth,width);

		if (depth < 0 || depth > ModelConstants.MAX_WIDTH_DEPTH)
		{
			depth = ModelConstants.DEFAULT_DEPTH;
			field.setDepth(depth);
		}

		if (width < 0 || width > ModelConstants.MAX_WIDTH_DEPTH)
		{
			width = ModelConstants.DEFAULT_WIDTH;
			field.setWidth(width);
		}

		// outputs a graphical view of the field
		SimulatorView view = new SimulatorView(depth,width);	

		// sets the colour to be used by each farmer instance
		view.setColor(Farmer.class, Color.black);

		// sets the colour to be used by each weed instance
		view.setColor(Weed.class, Color.blue);

		// sets the colour to be used by each bean plant instance
		view.setColor(BeanPlant.class, Color.green);

		// calls the 'populate' method, passing the field and a random integer (as a seed) for the parameters
		populate(field, new Random().nextInt());

		// a loop which holds all simulation
		// will only be broken once the maximum number of steps has been reached
		while(true)
		{
			// updates the step number on the JFrame
			view.showStatus(step, field);
			
			// if the current step exceeds the maximum number of steps then it will break the While loop
			// which ends the simulation
			if(step >= steps)	
			{
				break;
			}
				
			// used for diagnostics - outputs the current step number to the console
			System.out.println("Now running step number " + step);
			
			// calls the simulate method
			simulate();
		}
		
		// used for diagnostics - output to the console when all steps have completed
		System.out.println("Application ended after " + steps + " steps.");
	}

	// method which is used to simulate all movement on the field
	public void simulate()
	{
		// calls the simulate one step method - passing in the current step number
		simulateOneStep(this.step);
	}

	// simulates a single step - accepts an integer of the current step number
	public void simulateOneStep(int step)
	{	
		try {
			// have a slight delay so that the user can noticably view the changes
			Thread.sleep(1000);
			
			// cycles through each actor using a for loop
			for (int x = 0 ; x < actorList.size() ; x++ )
			{
				// gets the next actor is the actorList array list collection111
				Actor actor = actorList.get(x);

				// calls each actors .act method - each type of actor has it's own implementation
				actor.act(field, this);
			}

			// add all the newly created actors once every actor has actor in this step
			actorList.addAll(tempNewActorList);
			
			// removes all dead actors from the actors list so that they are not included in the next step
			actorList.removeAll(deadActorList);
			
		} 
		catch (InterruptedException e) {
			// print out thread.sleep exception stack trace
			e.printStackTrace();
		}		
		
		// increment the step count once this step has completed
		this.step++;
	}

	// populates the field with initial set of actors (farmers, weeds and bean plants) using a random generation process
	// so that the number of each type and the populated fields are different everytime
	public void populate(Field field, int seed)			
	{
		// empties all field cells
		field.clear();

		int depth = field.getDepth();
		int width = field.getWidth();

		final int SIZE = 50;

		RandomGenerator.initialiseWithSeed(seed);
		Random rand = RandomGenerator.getRandom();

		// Goes through each row
		for ( int x = 0 ; x < width ; x++ ) 				
		{			
			// Goes through each column
			for ( int y = 0 ; y < depth ; y++ ) 			
			{
				double[] a = new double[SIZE];

				for (int i = 0; i < SIZE; i++)
				{
					a[i] = rand.nextDouble();
				}

				double random = rand.nextDouble();

				if (random < Farmer.FARMER_CP)
				{
					Farmer farmer = new Farmer();
					field.place(farmer, x, y);
					farmer.actorType = "Farmer";
					Location location = new Location(x,y);
					farmer.setLocation(location);
					actorList.add(farmer);
				}

				random = rand.nextDouble();

				if(random < BeanPlant.BEANPLANT_CP)
				{
					BeanPlant beanPlant = new BeanPlant();
					field.place(beanPlant, x, y);
					beanPlant.actorType = "Bean Plant";
					Location location = new Location(x,y);
					beanPlant.setLocation(location);
					actorList.add(beanPlant);
				}

				random = rand.nextDouble();

				if(random < Weed.WEED_CP)
				{
					Weed weed = new Weed();
					field.place(weed, x, y);
					weed.actorType = "Weed";
					Location location = new Location(x,y);
					weed.setLocation(location);
					actorList.add(weed);
				}
			}
		}
	}
	
	// adds an actor to the temp/new actor list - does not return anything as it has a void return type
	public void AddToNewActorList(Actor actor)
	{
		// calls the locally created tempNewActorList's standard Add method and passes in the new actor as a parameter.
		tempNewActorList.add(actor);
	}
	
	// returns a List<Actor> object for all temp/new actors
	public List<Actor> GetTempActorsList()
	{
		return tempNewActorList;
	}
	
}
