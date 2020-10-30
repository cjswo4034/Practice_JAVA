package designPattern.templeteMethod;

public class Archer extends Person{
    @Override
    protected void prepareWeapon() {
        System.out.println("활을 손질한다.");
    }

    @Override
    protected void prepareArmor() {
    }

    @Override
    boolean isUsingPrepareOther() {
        return true;
    }

    @Override
    void prepareOther() {
        System.out.println("화살을 준비한다.");
    }
}
