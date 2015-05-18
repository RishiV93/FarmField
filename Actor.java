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

  // updates the alive indicator for weeds and beanplant
  public void UpdateActorAliveStatus(boolean newStatus)
  {
    if(actorType == "BeanPlant" || actorType == "Weed")
    {
      alive = newStatus;  
    }
  }
}
