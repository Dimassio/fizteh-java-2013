package ru.fizteh.fivt.students.dzvonarev.filemap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.fizteh.fivt.storage.strings.TableProvider;

public class MyTableProviderTests {

    private TableProvider provider;

    @Before
    public void test() {
        MyTableProviderFactory factory = new MyTableProviderFactory();
        provider = factory.create(System.getProperty("fizteh.db.dir"));
    }

    @Test
    public void testSameInstanceGetCreate() {
        Assert.assertEquals(provider.createTable("example"), provider.getTable("example"));
        Assert.assertEquals(provider.getTable("example"), provider.getTable("example"));
        provider.removeTable("example");
    }

}
