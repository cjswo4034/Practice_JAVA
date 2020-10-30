package designPattern.templeteMethod;

abstract class Person {
    void readyToBattle() {
        startBodyTraining();
        prepareWeapon();
        prepareArmor();
        if (isUsingPrepareOther()) {
           prepareOther();
        }
    }

    // 체력 단련 -> 전사 궁수 마법사 모두 공통으로 사용하기 때문에 추상메소드 x
    final void startBodyTraining() {
        System.out.println("체력을 단련합니다.");
    }

    // 방어구 착용
    protected abstract void prepareWeapon();

    // 무기 손질
    protected abstract void prepareArmor();

    // 만약 다른 무언가를 사용하려면 true로 바꿔야 한다.
    // 이 메소드는 후킹 용도로 사용한다.
    // 상속받은 클래스에서 선택적으로 오버라이드할 수 있음
    boolean isUsingPrepareOther() {
        return false;
    }

    // 다른 무언가가 필요하면 사용한다.
    // isUsingPrepareOther 값에 의해 오버라이드 해서 사용한다.
    void prepareOther() {}
}
