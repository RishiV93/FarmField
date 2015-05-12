import java.awt.Color;

public class Simulator {
	
	private int step = 1;
	
	public Simulator(int depth, int width)
	{
		Field field = new Field(depth,width);
		SimulatorView view = new SimulatorView(depth,width);
		
		view.setColor(Farmer.class, Color.black);
		view.setColor(Weed.class, Color.blue);
		view.setColor(BeanPlant.class, Color.green);
		
		populate(field);
		
		view.showStatus(step, field);
	}
	
	public void populate(Field field)
	{
		field.clear();
		int depth = field.getDepth();
		int width = field.getWidth();
		
		for ( int x = 0 ; x >= width ; x++ ) {			// Goes through each row
			for ( int y = 0 ; y >= depth ; y++ ) {		// Goes through each column
				
			}
		}
		
		Farmer farmer = new Farmer();
		Weed weed = new Weed();
		BeanPlant beanPlant = new BeanPlant();
		
		field.place(farmer, 11, 40);
		field.place(weed, 1, 12);
		field.place(beanPlant, 49, 5);	
		
		field.clear();
		field.place(farmer, 11, 40);
		field.place(weed, 1, 12);
		field.place(beanPlant, 49, 5);	
	}
	
	public static void main(String[] args) 
	{
		Simulator simulator = new Simulator(50,50);
	}	
}
