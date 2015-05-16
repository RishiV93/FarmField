import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulator {
	
	private int step = 1;
	
	public Simulator(int depth, int width)
	{
		Field field = new Field(depth,width);	// creates a field with the provided depth and width e.g. 50*50
		
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
		
		SimulatorView view = new SimulatorView(depth,width);	// outputs a graphical view of the field
		
		view.setColor(Farmer.class, Color.black);				// sets the colour to be used by each farmer instance
		view.setColor(Weed.class, Color.blue);					// sets the colour to be used by each weed instance
		view.setColor(BeanPlant.class, Color.green);			// sets the colour to be used by each bean plant instance

		List<Actor> actorList = new ArrayList<Actor>();
		List<Farmer> farmerList = new ArrayList<Farmer>();
		List<BeanPlant> beanPlantList = new ArrayList<BeanPlant>();
		
		populate(field, new Random().nextInt());		// calls 'populate', providing the field and a random integer as a seed
		
		view.showStatus(step, field);					// shows an "updated version" of the field, having now been populated
		
	}
	
	public void simulate(int noOfSteps)
	{
		
	}
	
	public void simulateOneStep(int step)
	{
		
	}
	
	public void populate(Field field, int seed)
	{
		field.clear();									// empties all field cells
		
		int depth = field.getDepth();
		int width = field.getWidth();
		

		final int SIZE = 20;

		RandomGenerator.initialiseWithSeed(seed);
		Random rand = RandomGenerator.getRandom();
		
		for ( int x = 0 ; x < width ; x++ ) 				// Goes through each row
		{			
			for ( int y = 0 ; y < depth ; y++ ) 			// Goes through each column
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
					farmerList.add(farmer);
				}
				
				random = rand.nextDouble();
				
				if(random < BeanPlant.BEANPLANT_CP)
				{
					field.place(new BeanPlant(), x,y);
				}
				
				random = rand.nextDouble();
			
				
				if(random < Weed.WEED_CP)
				{
					field.place(new Weed(), x, y);
				}
			}
		}
	}

	
	public static void main(String[] args) 
	{	
		Simulator simulator = new Simulator(50,50);
	}	
}
