package modeling.generalization;

public class Freezer extends HomeAppliancies{
    @Override
    public void turnOn(){
        System.out.println("Freezer is turned on");
    }

    @Override
    public void turnOff(){
        System.out.println("Freezer is turned off");
    }
}
