package bitcamp.test.step13;

public class Score {

  String name;
  int kor;
  int eng;
  int math;
  int sum;
  float aver;


  // 생성자: 인스턴스를 생성한 직후 호출하는 메소드
  Score(String name, int kor, int eng, int math) {
    this.name = name;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
    this.compute();
  }

  void compute() {
    this.sum = this.kor + this.eng+ this.math;
    this.aver = this.sum / 3f;
  }
  
}
