
public class Weed extends Actor
{
    //Location location = new Location(0,0);
    
	// defaults constructor - sets the actor type which is a property of the base class
    public Weed()
    {
        actorType = "Weed";
    }
    
    @Override
    public void act(Field theField, Simulator simulator) 
    {

    	GrowWeed(theField, location, simulator);
    	
        DoPlantAction();
    }
    
    public void GrowWeed(Field theField, Location currentLocation, Simulator simulator)
    {
    	Location newLocation=theField.freeAdjacentLocation(currentLocation);
    	
        if(newLocation!=null && newLocation!=location){//If the new location is different to the current location.
        
        	//Place a weed on the new location
        	
        	Weed weed = new Weed();
        	
        	weed.actorType = "Weed";
        	weed.SetAgeToOne();
        	weed.SetActorStatus(true);
        	weed.setLocation(newLocation);
        	
        	theField.place(weed, newLocation);
        	simulator.AddToNewActorList(weed);
        	
        	System.out.println("Weed grew onto the new location " + newLocation);
        }
    }
}
