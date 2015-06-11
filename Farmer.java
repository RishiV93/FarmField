import java.util.ArrayList;
import java.util.Iterator;

public class Farmer extends Actor 
{
	  //Location location = new Location(0,0);
 
	
    Farmer()
    {
        actorType = "Farmer";
    }

    @Override
    public void act(Field theField, Simulator simulator) 
    {
        Location newLocation=theField.freeAdjacentLocation(location);
        
        if(newLocation!=null && newLocation!=location){//If the new location is different to the current location.
        theField.clearLocation(location);//Remove the farmer from the old location.
        theField.place(this, newLocation);//Place it on the new location
        
        System.out.println("Farmer moved from " + location + " to " + newLocation);
        location=newLocation;
        
        // perform action of planting bean plants
        PlantOneBeanPlant(theField, location, simulator);
        
        // perform action of clearing weeds.
        pestControl(theField, location, simulator);
        }
    }
        
    public void pestControl(Field theField, Location currentLocation, Simulator simulator)
    {
    
    Iterator<Location> adjacentLocations = theField.adjacentLocations(currentLocation);

    while (adjacentLocations.hasNext())
    {
    	Location nextLocation = adjacentLocations.next();
    		if (nextLocation != null && theField.getObjectAt(nextLocation) != null && theField.getObjectAt(nextLocation).actorType.toString() == "Weed")
    		{
    			Location newLocation = nextLocation;
    			theField.clearLocation(newLocation);    			
    			System.out.println("Farmer cleared weed at " + newLocation);
    		}
    	}
    }
    
    public void PlantOneBeanPlant(Field theField, Location currentLocation, Simulator simulator)
    {
    	Location newLocation=theField.freeAdjacentLocation(currentLocation);
    	
        if(newLocation!=null && newLocation!=location){//If the new location is different to the current location.
        
        	//Place a bean plant on the new location
        	
        	BeanPlant beanPlant = new BeanPlant();
        	
        	beanPlant.actorType = "BeanPlant";
        	beanPlant.SetAgeToOne();
        	beanPlant.SetActorStatus(true);
        	beanPlant.setLocation(newLocation);
        	
        	theField.place(beanPlant, newLocation);
        	simulator.AddActorToList(beanPlant);
        	
        	System.out.println("Farmer planted a new BeanPlant on " + newLocation);
        }
    }
}
