package ru.fizteh.fivt.students.dzvonarev.filemap;

import java.io.IOException;
import java.util.HashMap;

public class Put {

    public static void putItem(String str, int spaceIndex) throws IOException {
        int newSpaceIndex = str.indexOf(' ', spaceIndex + 1);
        if (str.indexOf(' ', newSpaceIndex + 1) != -1) {
            throw new IOException("put: wrong input");
        }
        String key = str.substring(spaceIndex + 1, newSpaceIndex);
        String value = str.substring(newSpaceIndex + 1, str.length());
        HashMap<String, String> fileMap = DoCommand.getFileMap();
        if (fileMap.containsKey(key)) {
            System.out.println("overwrite");
            System.out.println(fileMap.get(key));
        } else {
            System.out.println("new");
        }
        fileMap.put(key, value);
        DoCommand.updateFile("db.dat", fileMap);
    }

}
