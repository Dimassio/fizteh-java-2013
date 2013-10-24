package ru.fizteh.fivt.students.dzvonarev.filemap;

<<<<<<< HEAD
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
=======
import java.io.*;
>>>>>>> 38f61f7ba6a6e3f4171e3e9c8ce606b57c1e3154
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DoCommand {

<<<<<<< HEAD
    private static HashMap<String, String> fileMap;

    public static HashMap<String, String> getFileMap() {
        return fileMap;
    }

    public static void closeFile(RandomAccessFile file) throws IOException {
        try {
            file.close();
        } catch (IOException e) {
            throw new IOException("error in closing file");
        }
    }

    public static void updateFile(String fileName) throws IOException {
        RandomAccessFile fileWriter = openFileForWrite(fileName);
        Set fileSet = fileMap.entrySet();
        Iterator<Map.Entry<String, String>> i = fileSet.iterator();
        try {
            while (i.hasNext()) {
                Map.Entry<String, String> currItem = i.next();
                String key = currItem.getKey();
                String value = currItem.getValue();
                byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
                byte[] valueBytes = value.getBytes(StandardCharsets.UTF_8);
                fileWriter.writeInt(keyBytes.length);
                fileWriter.writeInt(valueBytes.length);
                fileWriter.write(keyBytes);
                fileWriter.write(valueBytes);
            }
        } catch (IOException e) {
            throw new IOException("updating file: error in writing");
        } finally {
            closeFile(fileWriter);
        }
    }

    public static void readFileMap(String fileName) throws IOException {
        fileMap = new HashMap<String, String>();
        RandomAccessFile fileReader = openFileForRead(fileName);
        long endOfFile = fileReader.length();
        long currFilePosition = fileReader.getFilePointer();
        while (currFilePosition != endOfFile) {
            int keyLen = fileReader.readInt();
            int valueLen = fileReader.readInt();
            if (keyLen <= 0 || valueLen <= 0) {
                closeFile(fileReader);
                System.out.println("db.dat: file is broken");
                System.exit(1);
            }
            byte[] keyByte = null;
            byte[] valueByte = null;
            try {
                keyByte = new byte[keyLen];
                valueByte = new byte[valueLen];
            } catch (OutOfMemoryError e) {
                closeFile(fileReader);
                System.out.println("db.dat: file is broken");
                System.exit(1);
            }
            fileReader.readFully(keyByte, 0, keyLen);
            fileReader.readFully(valueByte, 0, valueLen);
            String key = new String(keyByte);
            String value = new String(valueByte);
            fileMap.put(key, value);
            currFilePosition = fileReader.getFilePointer();
            endOfFile = fileReader.length();
        }
        closeFile(fileReader);
    }

    public static RandomAccessFile openFileForRead(String fileName) throws IOException {
        RandomAccessFile newFile;
        String dirName = System.getProperty("fizteh.db.dir");
        File destFile = new File(dirName + File.separator + fileName);
        String realPath;
        if (!destFile.exists()) {
            if (new File(System.getProperty("fizteh.db.dir")).getName().equals("db.dat")) {
                realPath = System.getProperty("fizteh.db.dir");
            } else {
                if (!destFile.createNewFile()) {
                    throw new IOException("can't create db.dat file");
                }
                realPath = dirName + File.separator + fileName;
            }
        } else {
            realPath = dirName + File.separator + fileName;
        }
        try {
            newFile = new RandomAccessFile(realPath, "rw");
=======
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
>>>>>>> 38f61f7ba6a6e3f4171e3e9c8ce606b57c1e3154
        } catch (FileNotFoundException e) {
            throw new IOException("reading from file: file not found");
        }
        return newFile;
    }

<<<<<<< HEAD
    public static RandomAccessFile openFileForWrite(String fileName) throws IOException {
        RandomAccessFile newFile;
        String dirName = System.getProperty("fizteh.db.dir");
        File destFile = new File(dirName + File.separator + fileName);
        String realPath;
        if (!destFile.exists()) {
            if (new File(System.getProperty("fizteh.db.dir")).getName().equals("db.dat")) {
                realPath = System.getProperty("fizteh.db.dir");
            } else {
                if (!destFile.createNewFile()) {
                    throw new IOException("can't create db.dat file");
                }
                realPath = dirName + File.separator + fileName;
            }
        } else {
            realPath = dirName + File.separator + fileName;
        }
        try {
            newFile = new RandomAccessFile(realPath, "rw");
            newFile.getChannel().truncate(0);
=======
    public static FileOutputStream openFileForWrite(String fileName) throws IOException {
        FileOutputStream newFile;
        String dirName = System.getProperty("fizteh.db.dir");
        try {
            newFile = new FileOutputStream(dirName + File.separator + fileName);
>>>>>>> 38f61f7ba6a6e3f4171e3e9c8ce606b57c1e3154
        } catch (FileNotFoundException e) {
            throw new IOException("writing to file: file not found");
        }
        return newFile;
    }

<<<<<<< HEAD
=======
    public static void run(String expression) throws IOException {
        fileMap = readFileMap("db.dat");
        String newExpression = expression.trim();
        int spaceIndex = newExpression.indexOf(' ', 0);
        if (spaceIndex != -1) {
            int index = spaceIndex;
            while (newExpression.indexOf(' ', spaceIndex + 1) == spaceIndex + 1) {
                ++spaceIndex;
            }
            String command = newExpression.substring(0, index);
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

>>>>>>> 38f61f7ba6a6e3f4171e3e9c8ce606b57c1e3154
}
