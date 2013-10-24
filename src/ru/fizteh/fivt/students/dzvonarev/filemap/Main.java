package ru.fizteh.fivt.students.dzvonarev.filemap;

<<<<<<< HEAD
import ru.fizteh.fivt.students.dzvonarev.shell.CommandInterface;
import ru.fizteh.fivt.students.dzvonarev.shell.Shell;

import java.io.IOException;
import java.util.Vector;

class Main {

    public static Vector<CommandInterface> getCommandObjects() {
        Vector<CommandInterface> arr = new Vector<CommandInterface>();
        Put put = new Put();
        Get get = new Get();
        Remove remove = new Remove();
        Exit exit = new Exit();
        arr.add(put);
        arr.add(get);
        arr.add(remove);
        arr.add(exit);
        return arr;
    }

    public static Vector<String> getCommandNames() {
        Vector<String> arr = new Vector<String>();
        arr.add("put");
        arr.add("get");
        arr.add("remove");
        arr.add("exit");
        return arr;
    }

    public static void main(String[] arr) {
        Vector<CommandInterface> fileMapCommands = getCommandObjects();
        Vector<String> commandName = getCommandNames();
        Shell shell = new Shell(commandName, fileMapCommands);
        try {
            DoCommand.readFileMap("db.dat");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        if (arr.length == 0) {
            shell.interactiveMode();
        }
        if (arr.length != 0) {
            shell.packageMode(arr);
=======
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
>>>>>>> 38f61f7ba6a6e3f4171e3e9c8ce606b57c1e3154
        }
    }

}
