package ru.fizteh.fivt.students.dzvonarev.filemap;

import java.io.IOException;
import java.util.Scanner;

class Main {

    public static String mergeAll(String[] arr) {
        StringBuilder s = new StringBuilder();
        for (String anArr : arr) {
            s.append(anArr);
            s.append(" ");
        }
        return s.toString();
    }

    public static boolean isEmpty(String str) {
        str = str.trim();
        return str.isEmpty();
    }

    public static void interactiveMode() {
        invite();
        Scanner sc = new Scanner(System.in);
        String input = "";
        if (sc.hasNextLine()) {
            input = sc.nextLine();
        } else {
            Exit.exitFileMap(0);
        }
        while (!input.equals("exit")) {
            String[] s = input.split("\\s*;\\s*");
            for (String value : s) {
                if (isEmpty(value)) {
                    continue;
                }
                try {
                    DoCommand.run(value);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            invite();
            if (sc.hasNextLine()) {
                input = sc.nextLine();
            } else {
                Exit.exitFileMap(0);
            }
        }
    }

    public static void packageMode(String[] arr) {
        String expression = mergeAll(arr);
        String[] s = expression.split("\\s*;\\s*");
        for (String value : s) {
            if (value.equals("exit")) {
                Exit.exitFileMap(0);
            }
            if (isEmpty(value)) {
                continue;
            }
            try {
                DoCommand.run(value);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                Exit.exitFileMap(1);
            }
        }
    }

    public static void invite() {
        System.out.print("$ ");
    }

    public static void main(String[] arr) {
        if (arr.length == 0) {
            interactiveMode();
        }
        if (arr.length != 0) {
            packageMode(arr);
        }
    }

}
