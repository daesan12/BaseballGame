package com.example.numplay;


import java.util.Random;
import java.util.Scanner;

public class BaseballGame {

    private int[] answer;
    Scanner sc = new Scanner(System.in);
    private GameRecordManager recordManager = new GameRecordManager();
    int attempts = 0;
    int dfficulty;

    public BaseballGame(GameRecordManager recordManager) {
        this.recordManager = recordManager; // 기록 관리 객체 주입
        generateAnswer(); // 정답 생성
    }

    public BaseballGame() {

    }

    public void BaseballGamePlay(GameRecordManager recordManager) {
        System.out.println("<난이도 설정(자리수)입력해주세요>");
        dfficulty = sc.nextInt();

        System.out.println("<" + dfficulty + "자리 난이도 설정 되었습니다.>");
        //정답 만들기
        generateAnswer();
        //게임시작
        play();
    }

    //정답만들기
    private void generateAnswer() {
        answer = new int[dfficulty];
        Random rand = new Random();
        //중복없는 세 자리 숫자 생성
        for (int i = 0; i < answer.length; i++) {
            answer[i] = (int) rand.nextInt(9) + 1;
            //중복체크
            for (int j = 0; j < i; j++) {
                //값이 같으면 i--로 for문 한 번 더 반복
                if (answer[i] == answer[j]) {
                    i--;
                    break;
                }
            }
        }
        //정답 출력확인코드
        System.out.print("정답: ");

        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
    }


    public void play() {
        System.out.println("-------------------------");


        System.out.println("<게임을 시작합니다!>");
        //입력시도횟수
        String input;
        int[] Correct = new int[dfficulty];

        while (true) {
            System.out.println("<정답을 입력해주세요!>\n-------------------------");
            //유저에게 입력값을 받음
            input = sc.next();

            // 입력값 검증, validateInput메서드 실행시 입력값이 올바르지못할시 false, 올바를시 true반환
            //올바른 true가 반환되면 if문 실행 안 함
            if (validateInput(input)) {
                System.out.println("<잘못된 입력입니다. 다시 입력해주세요.>");
                continue;
            }
            //스트라이크 갯수
            int strikes = countStrike(input);
            //볼 갯수
            int balls = countBall(input);

            if (strikes == dfficulty) {
                System.out.println("<정답입니다! " + (++attempts) + "번 만에 맞췄습니다.>");
                recordManager.addRecord(attempts);
                break; // 정답을 맞추면 루프 종료
            } else {
                System.out.println("스트라이크: " + strikes + ", 볼: " + balls);
                attempts++; // 시도 횟수 증가
            }
        }
        System.out.println("<메인메뉴로 이동합니다.>\n-------------------------");
    }

    //입력 유효성 검사
    public boolean validateInput(String input) {
        //자리수 확인
        String regExp = "\\d{" + dfficulty + "}";
        if (input.matches(regExp)) {
            //자리수가 맞지않으면 false반환
            return false;
        }
        //  입력값을 비교하기 위해 문자배열로 변환
        char[] digitsArray = input.toCharArray();

        // 중복값확인
        boolean hasDuplicate = false;
        for (int i = 0; i < digitsArray.length; i++) {
            for (int j = i + 1; j < digitsArray.length; j++) {
                if (digitsArray[i] == digitsArray[j]) {
                    hasDuplicate = true;
                    break; // 중복값 발견시 브레이크
                }
            }
            if (hasDuplicate) break;//중복발견시 포문 종료
        }
        return !hasDuplicate;//중복이 없으면 최종 true 반환
    }

    //스트라이크 갯수확인
    int countStrike(String input) {
        int strikes = 0;
        for (int i = 0; i < dfficulty; i++) {
            int userDigit = Character.getNumericValue(input.charAt(i));
            if (userDigit == answer[i]) {
                strikes++; // 자리와 숫자가 모두 일치
            }
        }

        return strikes;
    }

    //볼 갯수확인
    private int countBall(String input) {
        int balls = 0;
        for (int i = 0; i < dfficulty; i++) {
            int userDigit = Character.getNumericValue(input.charAt(i));
            if (userDigit != answer[i]) { // 자리 틀림
                for (int j = 0; j < dfficulty; j++) {
                    if (userDigit == answer[j]) {
                        balls++; // 숫자는 맞지만 자리 틀림
                        break;
                    }
                }
            }
        }
        return balls;
    }

    public void attempts() {
        System.out.println(attempts + "번");
    }
}