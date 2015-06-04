public class BeanPlant extends Actor 
{
    Location location = new Location(0,0);

    @Override
    public void act(Field theField) 
    {
        // TODO Auto-generated method stub
        //System.out.println("BEAN PLANT ACT");
        
        DoPlantAction();
    }

    public BeanPlant()
    {
        actorType = "BeanPlant";
    }
}
