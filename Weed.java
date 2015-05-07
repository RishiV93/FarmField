public class Weed extends Actor
{
  // the age at which the Weed can begin spreading.
  private static final int weedSpreadingAge = 20;
  
  Location location =  new Location();
  
  public Weed()
  {
    actor.actorType = ActorType.Weed.toString();
  }
  
  public void grow (Field newField, Actor weed)
  {
    if (weed.getAge() >= weedSpreadingAge && weed.getAge() < plantMaximumAge)
    {
      Location newLoc = newField.freeAdjacentLocation(location);
      
      // only transfer if possible
      if (newLoc == null)
      {
        setLocation(newLoc);
        newField.place(this, newLoc);
      }
      else
      {
        weed.setAlive(false);
      }
    }
  }
}
