package designPattern.templeteMethod;

/* 출처: http://jdm.kr/blog/116
 * 템플릿 메소드 패턴은 "알고리즘의 뼈대"를 맞추는 것에 있다.
 * 즉, 전체적인 레이아웃을 통일 시키지만 상속받은 클래스로 하여금 어느정도 유연성을 주도록하는 디자인 패턴
 *
 * 추상 메소드(abstrcat method)와 훅 메소드(hook method)를 적절히 사용해서 전체적인 알고리즘의 뼈대를 유지하되
 * 유연하게 기능을 변경할 수 있도록 하고자 할 때 사용하면 유용
 * */
public class TemplateMain {
    public static void main(String[] args) {
        // 전사는 1) 체력을 단련한다. 2) 검을 닦는다. 3) 갑옷을 입는다.
        Warrior warrior = new Warrior();
        warrior.readyToBattle();

        // 궁수는 1) 체력을 단련한다. 2) 활을 손질한다. 3) 화살을 준비한다.
        Archer archer = new Archer();
        archer.readyToBattle();

        // 마법사는 1) 체력을 단련한다. 2) 지팡이를 준비한다. 3) 로브를 입는다.
        Wizard wizard = new Wizard();
        wizard.readyToBattle();
    }
}
