package ru.fizteh.fivt.students.dzvonarev.filemap;

import ru.fizteh.fivt.storage.structured.TableProviderFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyTableProviderFactory implements TableProviderFactory, AutoCloseable {

    private List<MyTableProvider> tableProvidersList;
    private volatile boolean isTableProviderFactoryClosed;

    public MyTableProviderFactory() {
        tableProvidersList = new ArrayList<>();
        isTableProviderFactoryClosed = false;
    }

    @Override
    public MyTableProvider create(String dir) throws IOException, RuntimeException {
        checkIfFactoryClosed();
        if (dir == null || dir.trim().isEmpty()) {
            throw new IllegalArgumentException("wrong type (invalid name of table provider)");
        }
        File providerFile = new File(dir);
        if (!providerFile.exists()) {
            if (!providerFile.mkdirs()) {
                throw new IOException("can't create provider in " + dir);
            }
        } else {
            if (!providerFile.isDirectory()) {
                throw new IllegalArgumentException("wrong type (table provider is not a directory)");
            }
        }
        MyTableProvider newTableProvider = new MyTableProvider(dir, this);
        tableProvidersList.add(newTableProvider);
        return newTableProvider;  // will read data in here
    }

    @Override
    public void close() {
        if (isTableProviderFactoryClosed) {
            return;
        }
        for (MyTableProvider tableProvider : tableProvidersList) {
            tableProvider.close();
        }
        tableProvidersList.clear();
        isTableProviderFactoryClosed = true;
    }

    public void removeProvider(MyTableProvider provider) {
        tableProvidersList.remove(provider);
    }

    private void checkIfFactoryClosed() {
        if (isTableProviderFactoryClosed) {
            throw new IllegalStateException("table provider factory " + this.getClass().getSimpleName() + " is closed");
        }
    }
}
