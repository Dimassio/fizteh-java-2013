package ru.fizteh.fivt.students.dzvonarev.filemap;


import ru.fizteh.fivt.students.dzvonarev.shell.CommandInterface;

import java.io.IOException;
import java.util.Vector;

public class DataBaseCreate implements CommandInterface {

    public DataBaseCreate(MyTableProvider newTableProvider) {
        tableProvider = newTableProvider;
    }

    private MyTableProvider tableProvider;

    public void execute(Vector<String> args) throws IOException, IllegalArgumentException {
        String str = args.elementAt(0);
        int spaceIndex = str.indexOf(' ', 0);
        if (spaceIndex == -1) {
            throw new IOException("create: wrong parameters");
        }
        while (str.indexOf(' ', spaceIndex + 1) == spaceIndex + 1) {
            ++spaceIndex;
        }
        if (str.indexOf(' ', spaceIndex + 1) != -1) {
            throw new IOException("create: wrong parameters");
        }
        String newName = str.substring(spaceIndex + 1, str.length());
        MyTable newTable = tableProvider.createTable(newName);
        if (newTable == null) {
            System.out.println(newName + " exists");
        } else {
            tableProvider.addTable(newTable, newName);
            System.out.println("created");
        }
    }
}