package ru.fizteh.fivt.students.dzvonarev.filemap;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DoCommand {

    private static HashMap fileMap;

    public static HashMap getFileMap() {
        return fileMap;
    }

    public static void updateFile(String fileName, HashMap<String, String> newFileMap) throws IOException {
        fileMap = newFileMap;
        FileOutputStream fileWriter = DoCommand.openFileForWrite(fileName);
        Set fileSet = fileMap.entrySet();
        Iterator<Map.Entry<String, String>> i = fileSet.iterator();
        while (i.hasNext()) {
            Map.Entry<String, String> currItem = i.next();
            String key = currItem.getKey();
            String value = currItem.getValue();
            byte[] keyByte;
            byte[] valueByte;
            try {
                keyByte = key.getBytes("UTF-8");
                valueByte = value.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new IOException("updating file: can't get UTF-8 bytes from key and value");
            }
            try {
                fileWriter.write(key.length());
                fileWriter.write(value.length());
                fileWriter.write(keyByte);
                fileWriter.write(valueByte);
            } catch (IOException e) {
                throw new IOException("updating file: error in writing");
            }
        }
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new IOException("updating file: error in closing file");
        }
    }

    public static HashMap readFileMap(String fileName) throws IOException {
        HashMap<String, String> newFileMap = new HashMap<String, String>();
        FileInputStream fileReader = openFileForRead(fileName);
        try {
            while (fileReader.available() > 0) {
                int keyLen = fileReader.read();
                int valueLen = fileReader.read();
                byte[] keyByte = new byte[keyLen];
                byte[] valueByte = new byte[valueLen];
                int readKey = fileReader.read(keyByte, 0, keyLen);
                int readValue = fileReader.read(valueByte, 0, valueLen);
                if (readKey == -1 || readValue == -1) {
                    throw new IOException("reading file error: no more data");
                }
                String key = new String(keyByte, "UTF-8");
                String value = new String(valueByte, "UTF-8");
                newFileMap.put(key, value);
            }
            fileReader.close();
        } catch (IOException e) {
            throw new IOException("reading file error");
        }
        return newFileMap;
    }

    public static FileInputStream openFileForRead(String fileName) throws IOException {
        FileInputStream newFile;
        String dirName = System.getProperty("fizteh.db.dir");
        try {
            newFile = new FileInputStream(dirName + File.separator + fileName);
        } catch (FileNotFoundException e) {
            throw new IOException("reading from file: file not found");
        }
        return newFile;
    }

    public static FileOutputStream openFileForWrite(String fileName) throws IOException {
        FileOutputStream newFile;
        String dirName = System.getProperty("fizteh.db.dir");
        try {
            newFile = new FileOutputStream(dirName + File.separator + fileName);
        } catch (FileNotFoundException e) {
            throw new IOException("writing to file: file not found");
        }
        return newFile;
    }

    public static void run(String expression) throws IOException {
        fileMap = readFileMap("db.dat");
        String newExpression = expression.trim();
        int spaceIndex = newExpression.indexOf(' ', 0);
        if (spaceIndex != -1) {
            String command = newExpression.substring(0, spaceIndex);
            if (command.equals("put")) {
                Put.putItem(newExpression, spaceIndex);
            }
            if (command.equals("get")) {
                Get.getItem(newExpression, spaceIndex);
            }
            if (command.equals("remove")) {
                Remove.removeItem(newExpression, spaceIndex);
            }
            if (!command.equals("put") && !command.equals("get")
                    && !command.equals("remove")) {
                throw new IOException("wrong command " + command);
            }
        } else {
            if (newExpression.equals("exit")) {
                Exit.exitFileMap(0);
            }
            if (!newExpression.equals("exit")) {
                throw new IOException("Wrong command " + newExpression);
            }
        }
    }

}
