package ru.fizteh.fivt.students.dzvonarev.filemap;

<<<<<<< HEAD
import ru.fizteh.fivt.students.dzvonarev.shell.CommandInterface;

import java.io.IOException;
import java.util.Vector;

public class Exit implements CommandInterface {

    public void execute(Vector<String> args) {
        try {
            DoCommand.updateFile("db.dat");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.exit(0);
=======
public class Exit {

    public static void exitFileMap(int status) {
        System.exit(status);
>>>>>>> 38f61f7ba6a6e3f4171e3e9c8ce606b57c1e3154
    }

}
