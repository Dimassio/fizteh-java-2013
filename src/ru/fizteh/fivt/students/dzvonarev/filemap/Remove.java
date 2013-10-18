package ru.fizteh.fivt.students.dzvonarev.filemap;

import java.io.IOException;
import java.util.HashMap;

public class Remove {

    public static void removeItem(String str, int spaceIndex) throws IOException {
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
        DoCommand.updateFile("db.dat", fileMap);
    }

}
