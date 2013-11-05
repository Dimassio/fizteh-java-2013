package ru.fizteh.fivt.students.dzvonarev.filemap;

import ru.fizteh.fivt.students.dzvonarev.shell.CommandInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class Get implements CommandInterface {

    public void execute(Vector<String> args) throws IOException {
        if (MultiFileMap.getWorkingTable().equals("noTable")) {
            throw new IOException("no table");
        }
        String str = args.elementAt(0);
        int spaceIndex = str.indexOf(' ', 0);
        while (str.indexOf(' ', spaceIndex + 1) == spaceIndex + 1) {
            ++spaceIndex;
        }
        if (str.indexOf(' ', spaceIndex + 1) != -1) {
            throw new IOException("get: wrong input");
        }
        String key = str.substring(spaceIndex + 1, str.length());
        String currTable = MultiFileMap.getWorkingTable();
        HashMap<String, String> fileMap = MultiFileMap.getMultiFileMap().get(currTable);
        if (fileMap == null) {
            fileMap = new HashMap<>();
        }
        String value = fileMap.get(key);
        if (value == null) {
            System.out.println("not found");
        } else {
            System.out.println("found");
            System.out.println(value);
        }
    }

}
