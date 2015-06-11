import java.util.Iterator;

public class Farmer extends Actor 
{
	  //Location location = new Location(0,0);
 
	// defaults constructor - sets the actor type which is a property of the base class
    public Farmer()
    {
        actorType = "Farmer";
    }

    @Override
    public void act(Field theField, Simulator simulator) 
    {
    	// finds a free adjacent location
        Location newLocation=theField.freeAdjacentLocation(location);
        
      //If the new location is different to the current location.
        if(newLocation!=null && newLocation!=location)
        {
        	//Remove the farmer from the old location.
        	theField.clearLocation(location);
        	
        	//Place it on the new location
        	theField.place(this, newLocation);
        
        	// prints an output of the farmers movement
        	System.out.println("Farmer moved from " + location + " to " + newLocation);
        	
        	// sets the location of the farmer to the newly identified location
        	location=newLocation;
        
        	// perform action of planting bean plants
        	PlantOneBeanPlant(theField, location, simulator);
        
        	// perform action of clearing weeds.
        	pestControl(theField, location, simulator);
        }
    }
        
    // method used to clear weeds from the field - accepts the field, current location and the simulator as parameters
    public void pestControl(Field theField, Location currentLocation, Simulator simulator)
    {
    	// finds and stores adjacent locations into a local variable named adjacentLocations
    	Iterator<Location> adjacentLocations = theField.adjacentLocations(currentLocation);

    	// a while loop, will iterate through each of the adjacent locations - will end loop once they have all been dealt with
    	while (adjacentLocations.hasNext())
    	{
    		// gets the next location
    		Location nextLocation = adjacentLocations.next();
    		
    		if (nextLocation != null && theField.getObjectAt(nextLocation) != null && theField.getObjectAt(nextLocation).actorType.toString() == "Weed")
    		{
    			// gets the object at the location - at this point we already know its a type of weed
    			Actor actor = theField.getObjectAt(nextLocation);
    			
    			Location newLocation = nextLocation;
    			
    			// clear the weed from the field
    			theField.clearLocation(newLocation);
    			
    			// adds the weed to the dead actors lists and it will be removed from memory at the end of the current step
    			simulator.AddToDeadActorFromList(actor);
    			
    			// prints out the location which the farmer has cleared a weed from
    			System.out.println("Farmer cleared weed at " + newLocation);
    		}
    	}
    }
    
    // method used to plant a new bean plant
    public void PlantOneBeanPlant(Field theField, Location currentLocation, Simulator simulator)
    {
    	// finds an adjacent locations - not multiple as any farmer can only plant one beant plant in any given step
    	Location newLocation=theField.freeAdjacentLocation(currentLocation);
    	
    	//If the new location is different to the current location
        if(newLocation!=null && newLocation!=location)
        {
        	// create new instance of a bean plant actor
        	BeanPlant beanPlant = new BeanPlant();
        	
        	// set the values of the new bean plant actor.
        	beanPlant.actorType = "BeanPlant";
        	beanPlant.SetAgeToOne();
        	beanPlant.SetActorStatus(true);
        	beanPlant.setLocation(newLocation);
        	
        	//Place a bean plant on the new location
        	theField.place(beanPlant, newLocation);
        	
        	// adds the new bean plant to the new actor list 
        	simulator.AddToNewActorList(beanPlant);
        	
        	// prints out information of the location of the new bean plant.
        	System.out.println("Farmer planted a new BeanPlant on " + newLocation);
        }
    }
}
