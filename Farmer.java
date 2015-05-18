public class Farmer extends Actor 
{
	  Location location = new Location(0,0);

	  public void farmerAct(Field theField)
	  {
		  System.out.println(location);
	  }
	
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
	}
}
