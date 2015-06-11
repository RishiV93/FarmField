import java.util.ArrayList;

public class BeanPlant extends Actor 
{
    Location location = new Location(0,0);

    @Override
    public void act(Field theField, Simulator simulator) 
    {
        // TODO Auto-generated method stub
        //System.out.println("BEAN PLANT ACT");
        
        DoPlantAction();
    }
    
    @Override
    public void act(Field theField, ArrayList<Actor> actors) 
    {
    	//MoveFarmer(theField);
    	
    	//DealWithWeeds(theField);
    	
    	//PlantBeanplant(theField);        
    }

    public BeanPlant()
    {
        actorType = "BeanPlant";
    }
}
