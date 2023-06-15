package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardHandler implements Handler {

  private BoardList list = new BoardList();
  private Prompt prompt;
  private String title;

  public BoardHandler(Prompt prompt, String title) {
    this.prompt = prompt;
    this.title = title;
  }

  public void execute() {
    printMenu();

    while (true) {
      String boardNo = prompt.inputString("%s> ", this.title);
      if (boardNo.equals("0")) {
        return;
      } else if (boardNo.equals("menu")) {
        printMenu();
      } else if (boardNo.equals("1")) {
        this.inputBoard();
      } else if (boardNo.equals("2")) {
        this.printBoards();
      } else if (boardNo.equals("3")) {
        this.viewBoard();
      } else if (boardNo.equals("4")) {
        this.updateBoard();
      } else if (boardNo.equals("5")) {
        this.deleteBoard();
      } else {
        System.out.println("게시판 번호가 옳지 않습니다!");
      }
    }
  }

  private static void printMenu() {
    System.out.println("1. 등록");
    System.out.println("2. 목록");
    System.out.println("3. 조회");
    System.out.println("4. 변경");
    System.out.println("5. 삭제");
    System.out.println("0. 메인");
  }

  // 인스턴스 멤버(필드나 메서드)를 사용하는 경우 인스턴스 메서드로 정의해야 한다.
  private void inputBoard() {

    Board board = new Board();
    board.setTitle(this.prompt.inputString("제목? "));
    board.setContent(this.prompt.inputString("내용? "));
    board.setWriter(this.prompt.inputString("작성자? "));
    board.setPassword(this.prompt.inputString("암호? "));

    if (!this.list.add(board)) {
      System.out.println("입력 실패입니다.");
    }
  }

  private void printBoards() {

    System.out.println("---------------------------------------");
    System.out.println("번호, 제목, 작성자, 조회수, 등록일");
    System.out.println("---------------------------------------");

    Board[] arr = this.list.list();
    for (Board board : arr) {
      System.out.printf("%d, %s, %s, %d, %tY-%5$tm-%5$td\n", board.getNo(), board.getTitle(),
          board.getWriter(), board.getViewCount(), board.getCreatedDate());
    }
  }



  private void viewBoard() {
    int boardNo = this.prompt.inputInt("번호? ");

    Board board = this.list.get(boardNo);

    if (board == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("작성자: %s\n", board.getWriter());
    System.out.printf("조회수: %s\n", board.getViewCount());
    System.out.printf("등록일: %tY-%1$tm-%1$td\n", board.getCreatedDate());
  }


  private void updateBoard() {
    int boardNo = this.prompt.inputInt("번호? ");

    Board board = this.list.get(boardNo);
    if (board == null) {
      System.out.println("해당 번호의 게시물이 없습니다!");
      return;
    }

    if (!this.prompt.inputString("비밀번호? ").equals(board.getPassword())) {
      System.out.println("암호가 일치하지 않습니다");
      return;
    }

    board.setTitle(this.prompt.inputString("제목(%s)? ", board.getTitle()));
    board.setContent(this.prompt.inputString("내용(%s)? ", board.getContent()));
  }


  // private void updateBoard() {
  //
  // String boardNo = this.prompt.inputString("번호? ");
  // for (int i = 0; i < this.length; i++) {
  // Board board = this.boards[i];
  // if (board.getNo() == Integer.parseInt(boardNo)) {
  // if (!this.prompt.inputString("비밀번호? ").equals(board.getPassword())) {
  // System.out.println("암호가 일치하지 않습니다");
  // return;
  // }
  // board.setTitle(this.prompt.inputString("제목(%s)? ", board.getTitle()));
  // board.setContent(this.prompt.inputString("내용(%s)? ", board.getContent()));
  // System.out.println("수정되었습니다.");
  // return;
  //
  // }
  // }
  // System.out.println("해당 번호의 게시글이 없습니다!");
  // }

  private void deleteBoard() {
    int boardNo = this.prompt.inputInt("번호? ");

    Board board = this.list.get(boardNo);

    if (!this.prompt.inputString("비밀번호? ").equals(board.getPassword())) {
      System.out.println("암호가 일치하지 않습니다");
      return;
    }


    if (!this.list.delete(boardNo)) {
      System.out.println("해당 번호의 게시글이 없습니다!");
    }
  }

  // private void deleteBoard() {
  //
  // int deletedIndex = indexOf(this.prompt.inputInt("번호? "));
  // if (deletedIndex == -1) {
  // System.out.println("해당 번호의 게시글이 없습니다!");
  // return;
  // }
  //
  // for (int i = deletedIndex; i < this.length - 1; i++) {
  // this.boards[i] = this.boards[i + 1];
  // }
  // this.boards[--this.length] = null;
  //
  //
  // }



}
