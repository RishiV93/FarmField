public class Weed extends Actor
{
    Location location = new Location(0,0);

    @Override
    public void act(Field theField) 
    {
        // TODO Auto-generated method stub
        System.out.println("WEED ACT");
        
        DoPlantAction();
    }

    public Weed()
    {
        actorType = "Weed";
    }
}
