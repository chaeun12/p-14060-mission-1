package com.back;

import java.sql.ClientInfoStatus;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("== 명언 앱 ==");

        Scanner scanner = new Scanner(System.in);

        int lastId = 0;

        WiseSaying[] wiseSayings = new WiseSaying[100];

        int wiseSayingsLastIndex = -1;

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("목록")) {

                boolean hasAnyActiveQuotes = false;
                for (int i = 0; i <= wiseSayingsLastIndex; i++) { // wiseSayingsLastIndex까지 탐색
                    if (wiseSayings[i] != null) {
                        hasAnyActiveQuotes = true;
                        break;
                    }
                }

                if (!hasAnyActiveQuotes) { // 유효한 명언이 하나도 없는 경우
                    System.out.println("등록된 명언이 없습니다.");
                    continue;
                }
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");

                for (int i = wiseSayingsLastIndex; i >= 0; i--) {
                    WiseSaying wiseSaying = wiseSayings[i];
                    if (wiseSaying != null) {
                        System.out.printf("%d / %s / %s\n", wiseSaying.id, wiseSaying.author, wiseSaying.content);
                    }
                }
            } else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String wiseSayingContent = scanner.nextLine().trim();
                System.out.print("작가 : ");
                String wiseSayingAuthor = scanner.nextLine().trim();

                int id = ++lastId;

                WiseSaying wiseSaying = new WiseSaying();
                wiseSaying.id = id;
                wiseSaying.content = wiseSayingContent;
                wiseSaying.author = wiseSayingAuthor;

                wiseSayings[++wiseSayingsLastIndex] = wiseSaying;

                System.out.println("%d번 명언이 등록되었습니다.".formatted(id));

            } else if (cmd.startsWith("삭제?id=")) {
                String idStr = cmd.substring("삭제?id=".length());
                int idToDelete = Integer.parseInt(idStr);

                if (idToDelete > 0 && idToDelete <= lastId && wiseSayings[idToDelete - 1] != null) {
                    wiseSayings[idToDelete - 1] = null;
                    System.out.println(idToDelete + "번 명언이 삭제되었습니다.");
                } else {
                    System.out.println(idToDelete + "번 명언은 존재하지 않습니다.");
                }

            } else if (cmd.startsWith("수정?id=")) {

                String idStr = cmd.substring("수정?id=".length());
                int idToModify = Integer.parseInt(idStr);

                if (idToModify > 0 && idToModify <= lastId && wiseSayings[idToModify - 1] != null) {
                    WiseSaying original = wiseSayings[idToModify - 1];

                    System.out.println("명언(기존) : " + original.content);
                    System.out.print("명언 : ");
                    String newContent = scanner.nextLine().trim();

                    System.out.println("작가(기존) : " + original.author); // 직접 필드 접근
                    System.out.print("작가 : ");
                    String newAuthor = scanner.nextLine().trim();

                    original.content = newContent;
                    original.author = newAuthor;

                    System.out.println(idToModify + "번 명언이 수정되었습니다.");
                } else {
                    System.out.println(idToModify + "번 명언은 존재하지 않습니다.");
                }

            }

        }
        scanner.close();

    }

}