public abstract class Actor extends ModelConstants
{
    private int age;
    private boolean alive = true;
    public String actorType;
    public static final int plantMaximumAge = 150;
  
    // (row, column)
    private Location location;

    // the various types of actors within the system.
    public  enum  ActorType
    {
        BeanPlant,
        Weed,
        Farmer
    }

    public abstract void act(Field theField);

    public abstract void setLocation(Location location);
  
    public abstract Location getLocation();
  
    // returns bool true if the actor is a type of plant, else false.
    public boolean IsPlant()
    {
        if(actorType == "BeanPlant" || actorType == "Weed")
        {
            return true;
        }
        
        return false;
    }
    
    // updates the alive indicator for weeds and beanplant to flase if they are determined to be dead.
    public void SetActorAliveStatus()
    {
        if(IsPlant())
        {
            alive = false;  
        }
    }
    
    // increments the age of an actor (beanplant and weeds only) which will be called upon the use of the Act method in derived classes.
    public void IncreaseAge()
    {
        this.age = (this.age + 1);
    }
    
    // returns the act of the actor.
    public int GetAge()
    {
        return this.age;
    }
    
    // to be called within the derived Beanplant and weed classes to do their action which occurs per step.
    public void DoPlantAction()
    {
        if (IsPlant()) 
        {
            IncreaseAge();
        
            if (GetAge() >= ModelConstants.MAXIMUM_AGE) 
            {
                SetActorAliveStatus();
            
                // remove from JFrame
            }    
        }        
    }
}
