package modeling.generalization;

public class DishWasher extends HomeAppliancies{
    @Override
    public void turnOn() {
        System.out.println("Dish Washer is turned on");
    }

    @Override
    public void turnOff() {
        System.out.println("Dish Washer is turned off");
    }
}
