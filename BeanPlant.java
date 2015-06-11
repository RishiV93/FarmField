public class BeanPlant extends Actor 
{
	// sets the default location of this actor - will be overwritten later on
    Location location = new Location(0,0);
 
    @Override
    public void act(Field theField, Simulator simulator) 
    { 
    	// calls the base class method
        DoPlantAction();
    }

	// defaults constructor - sets the actor type which is a property of the base class
    public BeanPlant()
    {
        actorType = "BeanPlant";
    }
}
