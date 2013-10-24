package ru.fizteh.fivt.students.dzvonarev.filemap;

<<<<<<< HEAD
import ru.fizteh.fivt.students.dzvonarev.shell.CommandInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class Remove implements CommandInterface {

    public void execute(Vector<String> args) throws IOException {
        String str = args.elementAt(0);
        int spaceIndex = str.indexOf(' ', 0);
        while (str.indexOf(' ', spaceIndex + 1) == spaceIndex + 1) {
            ++spaceIndex;
        }
=======
import java.io.IOException;
import java.util.HashMap;

public class Remove {

    public static void removeItem(String str, int spaceIndex) throws IOException {
>>>>>>> 38f61f7ba6a6e3f4171e3e9c8ce606b57c1e3154
        if (str.indexOf(' ', spaceIndex + 1) != -1) {
            throw new IOException("remove: wrong input");
        }
        String key = str.substring(spaceIndex + 1, str.length());
        HashMap<String, String> fileMap = DoCommand.getFileMap();
        String value = fileMap.remove(key);
        if (value == null) {
            System.out.println("not found");
        } else {
            System.out.println("removed");
        }
<<<<<<< HEAD
=======
        DoCommand.updateFile("db.dat", fileMap);
>>>>>>> 38f61f7ba6a6e3f4171e3e9c8ce606b57c1e3154
    }

}
