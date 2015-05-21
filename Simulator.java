import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ModelConstants;

public class Simulator 
{	
    private int steps = 500;
    private int step = 1;
    private List<Actor> actorList = new ArrayList<Actor>();
    private Field field;

    // default constructor for the Simulator class.  
    public Simulator()
    {            

    }


    public void CreateSimulatorView(int depth, int width, int noOfSteps)
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

        if (noOfSteps < 0 || noOfSteps > ModelConstants.MAX_NO_STEPS)
        {
            steps = ModelConstants.DEFAULT_NO_STEPS;
        }

        // outputs a graphical view of the field
        SimulatorView view = new SimulatorView(depth,width);	

        // sets the colour to be used by each farmer instance
        view.setColor(Farmer.class, Color.black);

        // sets the colour to be used by each weed instance
        view.setColor(Weed.class, Color.blue);

        // sets the colour to be used by each bean plant instance
        view.setColor(BeanPlant.class, Color.green);

        // calls 'populate', providing the field and a random integer as a seed
        populate(field, new Random().nextInt());

        simulate(this.step);

        // shows an "updated version" of the field, having now been populated
        view.showStatus(step, field);
    }

    public void simulate(int noOfSteps)
    {
        simulateOneStep(this.step);
        this.step++;
    }

    public void simulateOneStep(int step)
    {	
        for (int x = 1 ; x < actorList.size() ; x++ )
        {
            Actor actor = actorList.get(this.step);

            actor.act(field);

            System.out.println(actor.actorType);
            System.out.println(actor.getLocation());

            this.step++;
        }
    }

    // populates the field with initial actors
    public void populate(Field field, int seed)			
    {
        // empties all field cells
        field.clear();

        int depth = field.getDepth();
        int width = field.getWidth();

        final int SIZE = 50;

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

    // default application entry point is always the main method
    public static void main(String[] args) 
    {	
        // instantiate an instance of the simulator class.
        Simulator simulator = new Simulator();

        // call the simulator's CreateSimulatorView method to render a JFrame of the specified size.
        simulator.CreateSimulatorView(50, 50, 30);
    }
}
