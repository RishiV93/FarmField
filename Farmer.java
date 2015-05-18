public class Farmer extends Actor 
{
	  Location location = new Location(0,0);
	
	  Farmer()
	  {
		  actorType = "Farmer";
	  }
	  
	  public void setLocation(Location sL)
	  {
		  this.location = sL;
	  }
	  
	  public Location getLocation()
	  {
		  return location;
	  }
	  
	  @Override
	public void act(Field theField) {
		// TODO Auto-generated method stub
		System.out.println("FARMER ACT");
		
		// MOVEMENT
		theField.freeAdjacentLocation(location);
		System.out.println(theField.freeAdjacentLocation(location));
		theField.adjacentLocations(location);
	}
}
