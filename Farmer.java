import java.util.ArrayList;

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
        
        //if(newLocation!=null && newLocation!=location && theField.getObjectAt(newLocation).actorType.toString() == "Weed")
        //{
       // 	theField.clearLocation(newLocation);//Remove the weed from its location.
        	//System.out.println("Farmer cleared weed from at " + newLocation);
        //}
        //else 
        
        if(newLocation!=null && newLocation!=location){//If the new location is different to the current location.
        theField.clearLocation(location);//Remove the farmer from the old location.
        theField.place(this, newLocation);//Place it on the new location
        
        System.out.println("Farmer moved from " + location + " to " + newLocation);
        location=newLocation;
        
        
        
        PlantOneBeanPlant(theField, location, simulator);
        
        }
    }
    
    @Override
    public void act(Field theField, ArrayList<Actor> actors) 
    {
    	//MoveFarmer(theField);
    	
    	//DealWithWeeds(theField);
    	
    	//PlantOneBeanPlant(theField, location, simulator);        
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
