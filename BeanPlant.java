public class BeanPlant extends Actor 
{
	Location location = new Location(0,0);

	@Override
	public void act(Field theField) {
		// TODO Auto-generated method stub
		System.out.println("BEAN PLANT ACT");
	}
	
	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return location;
	}
	
	@Override
	public void setLocation(Location sL) {
		// TODO Auto-generated method stub
		this.location = sL;
	}
	
	public BeanPlant()
	{
		actorType = "BeanPlant";
	}
}
