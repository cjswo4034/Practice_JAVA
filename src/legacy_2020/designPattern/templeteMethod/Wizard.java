package designPattern.templeteMethod;

public class Wizard extends Person {
    @Override
    protected void prepareArmor() {
        System.out.println("로브를 입는다.");
    }

    @Override
    protected void prepareWeapon() {
        System.out.println("지팡이를 준비한다.");
    }
}
