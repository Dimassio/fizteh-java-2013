package ru.fizteh.fivt.students.dzvonarev.filemap;

<<<<<<< HEAD
import ru.fizteh.fivt.students.dzvonarev.shell.CommandInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class Put implements CommandInterface {

    public void execute(Vector<String> args) throws IOException {
        String str = args.elementAt(0);
        int spaceIndex = str.indexOf(' ', 0);
        while (str.indexOf(' ', spaceIndex + 1) == spaceIndex + 1) {
            ++spaceIndex;
        }
        int newSpaceIndex = str.indexOf(' ', spaceIndex + 1);
        if (newSpaceIndex == -1) {
            throw new IOException("put: wrong parameters");
        }
        int index = newSpaceIndex;
        String key = str.substring(spaceIndex + 1, index);
        while (str.indexOf(' ', newSpaceIndex + 1) == newSpaceIndex + 1) {
            ++newSpaceIndex;
        }
=======
import java.io.IOException;
import java.util.HashMap;

public class Put {

    public static void putItem(String str, int spaceIndex) throws IOException {
        int newSpaceIndex = str.indexOf(' ', spaceIndex + 1);
        if (newSpaceIndex == -1) {
            throw new IOException("put: wrong input");
        }
        int index = newSpaceIndex;
        while (str.indexOf(' ', newSpaceIndex + 1) == newSpaceIndex + 1) {
            ++newSpaceIndex;
        }
        String key = str.substring(spaceIndex + 1, index);
>>>>>>> 38f61f7ba6a6e3f4171e3e9c8ce606b57c1e3154
        String value = str.substring(newSpaceIndex + 1, str.length());
        HashMap<String, String> fileMap = DoCommand.getFileMap();
        if (fileMap.containsKey(key)) {
            System.out.println("overwrite");
            System.out.println(fileMap.get(key));
        } else {
            System.out.println("new");
        }
        fileMap.put(key, value);
<<<<<<< HEAD
=======
        DoCommand.updateFile("db.dat", fileMap);
>>>>>>> 38f61f7ba6a6e3f4171e3e9c8ce606b57c1e3154
    }

}
