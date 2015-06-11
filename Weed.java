import java.util.ArrayList;

public class Weed extends Actor
{
    Location location = new Location(0,0);
    Simulator simulator = new Simulator();
    
    public Weed()
    {
        actorType = "Weed";
    }
    
    @Override
    public void act(Field theField, Simulator simulator) 
    {

    	GrowWeed(theField, simulator);
    	
        DoPlantAction();
    }
    
    @Override
    public void act(Field theField, ArrayList<Actor> actors) 
    {
    	//MoveWeed(theField);
    	
    	//DealWithWeeds(theField);
    	
    	//PlantBeanplant(theField);        
    }
    
    public void GrowWeed(Field theField, Simulator simulator)
    {
    	Location newLocation=theField.freeAdjacentLocation(location);
    	
        if(newLocation!=null && newLocation!=location){//If the new location is different to the current location.
        
        	//Grow onto the new location
        	
        	Weed weed = new Weed();
        	
        	weed.actorType = "Weed";
        	weed.SetAgeToOne();
        	weed.SetActorStatus(true);
        	weed.setLocation(newLocation);
        	
        	theField.place(weed, newLocation);
        	simulator.AddActorToList(weed);
        	
        	System.out.println("Weed grew onto the new location " + newLocation);
        }
    }
}
