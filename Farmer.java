public class Farmer extends Actor 
{
	  //Location location = new Location(0,0);

    Farmer()
    {
        actorType = "Farmer";
    }

    @Override
    public void act(Field theField) 
    {
       // TODO Auto-generated method stub
       // System.out.println("FARMER ACT");

        // MOVEMENT
        Location newLocation=theField.freeAdjacentLocation(location);
        
        if(newLocation!=null && newLocation!=location){//If the new location is different to the current location.
        	theField.clearLocation(location);//Remove the farmer from the old location.
        	theField.place(this, newLocation);//Place it on the new location
        	System.out.println("Farmer moved from " + location + " to " + newLocation);
        	location=newLocation;
        }
        
        /*
        System.out.println(theField.freeAdjacentLocation(location));
        theField.freeAdjacentLocation(location);
        System.out.println(theField.freeAdjacentLocation(location));
        theField.adjacentLocations(location);
        */
        
    }
}
