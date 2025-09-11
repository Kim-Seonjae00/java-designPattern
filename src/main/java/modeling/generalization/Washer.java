package modeling.generalization;

public class Washer extends HomeAppliancies{
   @Override
    public void turnOn(){
        System.out.println("Washer is turned on");
    }
    @Override
    public void turnOff(){
        System.out.println("Washer is turned off");
    }
}
