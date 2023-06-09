package bitcamp.test.step17;

import bitcamp.test.step17.vo.Score;

// 1) 낱개의 변수 사용
// 2) 낱개의 변수 재사용
// 3) 배열 사용
// 4) 클래스를 이용하여 데이터 타입 정의 
// 5) 출력 기능을 별도의 메소드로 분리(중첩클래스; 스태틱 클래스) 
// 6) 합계 및 평균을 계산하는 기능을 메서드로 분리 
// 7) GRASP 패턴 : Information Expert(정보를 갖고 있는 클래스가 그 정보를 다룬다.)
// 8) 인스턴스 메서드 도입
// 9) 객체 생성이 복잡한 경우 메서드로 분리하는 것이 낫다. (디자인패턴; )
// 10) GRASP 패턴 : Information Expert
// 11) 생성자 도입 : 인스턴스 변수를 보다 쉽게 초기화시키기 
// 12) 클래스를 유지보수 하기 쉽게 별도 소스 파일로 분리
// 12) 변수의 접근을 제어하기 : private/public, getter/setter
// 13) 클래스를 유지보수 하기 쉽게 패키지로 분류: import, pulic
// 14) 외부접근 차단과 값 꺼내기 : private, getter          
// 15) 프로그래밍의 일관성을 위해 보통 다른 필드에 대해서도 getter을 만들고 사용한다.
// 16) 필드의 직접 접근을 막고 setter가 정의하는 이유
// 17) 필드의 직접 접근을 막기: 인스턴스 변수에 무효한 값이 저장되지 않게 하기 위해
//       => getter 정의 : 값을 꺼낼때 사용
//         => setter 정의 : 값을 변경할 떄 사용. 단 유효한 값을 저장하도록 통제한다. 

public class App {

  public static void main(String[] args) {

    final int MAX_SIZE = 10;
    Score[] scores = new Score[MAX_SIZE];
    int length = 0;

    // new Score (String, int, int, int);
    // => Score 설계도에 따라 인스턴스를 생성하라.
    // => 생성한 후 String, int, int, int 파라미터 값을 받는 생성자를 호출하라.
    // => 이렇게 초기화시킨 인스턴스의 주소를 리턴하라. 

    scores[length++] = new Score("홍길동", 100, 800, 100);
    scores[length++] = new Score("임꺽정", 90, 90, 90);
    scores[length++] = new Score("유관순", 80, 80, 80);

    scores[0].setKor(70); // setter를 통해서는 값 변경 가능. 단 유효한 값만 가능
    //scores[0].compute(); // 호출하는 것을 잃어버릴 수 있기 때문에 setter에서 호출한다. 

    //합계와 평균 계산이 끝난 후에 국어 점수를 변경한다면
    // => 국영수 점수와 합계, 평균 점수가 일치하지 않는 문제가 발생한다.
    // 데이터에 결함이 발생한다.
    // 국영수 점수를 변경한 후에 compute()를 호출하면 되지 않을까?
    // => 만약 개발자가 compute() 호출되는 것을 잊어 먹는다면 아무 소용이 없다. 
    // 만약 유효하지 않은 국영수 점수를 입력한다면?
    // 흠.... 이건 도저히 막을 길이 없다.
    // scores[0].kor = 7000; // 접근 불가!
    // scores[0].compute(); // 호출하지 않으면 아무 소용이 없다.



    // //이렇게 개발자가 인스턴스 필드에 접근하는 것을 막을 수 없다. 
    // scores[0].sum = 600;
 
    for (int i = 0; i < length; i++) {
      printScore(scores[i]);
    }
  }

  static void printScore(Score s) {
    System.out.printf("%s: 국어 = %d, 영어=%d, 수학=%d, 합계=%d, 평균=%.1f\n", 
        s.getName(), s.getKor(), s.getEng(), s.getMath(), s.getSum(), s.getAver());
  }
}
