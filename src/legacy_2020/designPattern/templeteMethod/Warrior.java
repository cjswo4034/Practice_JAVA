package designPattern.templeteMethod;

public class Warrior extends Person{
    @Override
    protected void prepareWeapon() {
        System.out.println("검을 닦는다.");
    }

    @Override
    protected void prepareArmor() {
        System.out.println("갑옥을 입는다.");
    }
}
