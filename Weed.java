public class Weed extends Actor
{
	// defaults constructor - sets the actor type which is a property of the base class
    public Weed()
    {
        actorType = "Weed";
    }
    
    @Override
    public void act(Field theField, Simulator simulator) 
    {
    	// calls the GrowWeed method which accept theField, location and simulator as parameters
    	GrowWeed(theField, location, simulator);
    	
    	// calls the base class method
        DoPlantAction();
    }
    
    // method used to grow weeds, uses the field, current location and simulator objects as parameters
    public void GrowWeed(Field theField, Location currentLocation, Simulator simulator)
    {
    	// find a free adjacent location using the current location
    	Location newLocation=theField.freeAdjacentLocation(currentLocation);
    	
    	//If the new location is different to the current location and not null
        if(newLocation!=null && newLocation!=location)
        {
        	// create a new weed instance
        	Weed weed = new Weed();
        	
        	// assigns values to the weed's properties
        	weed.actorType = "Weed";
        	weed.SetAgeToOne();
        	weed.SetActorStatus(true);
        	weed.setLocation(newLocation);
        	
        	// places the weed on the field
        	theField.place(weed, newLocation);
        	
        	// adds the weed to the new actor list and NOT the current actor list
        	// otherwise this allows every new weed to grow in this current step which means they take over the field
        	// by placing the new weed in the new actor list means that the new weed's act method will be deffered to the next step
        	simulator.AddToNewActorList(weed);
        	
        	// prints out the location of the new weed
        	System.out.println("Weed grew onto the new location " + newLocation);
        }
    }
}
