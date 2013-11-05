package ru.fizteh.fivt.students.dzvonarev.filemap;


import org.junit.Assert;
import org.junit.Test;
import ru.fizteh.fivt.storage.strings.Table;

public class MyTableTests {

    private Table table;

    @Test
    public void testCommitRollback() {
        MyTableProviderFactory factory = new MyTableProviderFactory();
        MyTableProvider provider;
        provider = factory.create(System.getProperty("fizteh.db.dir"));
        table = provider.createTable("my");
        Assert.assertNull(table.put("commit", "rollback"));
        Assert.assertEquals(table.get("commit"), "rollback");
        Assert.assertEquals(table.rollback(), 1);
        Assert.assertNull(table.get("commit"));
        Assert.assertNull(table.put("commit", "rollback"));
        Assert.assertEquals(table.get("commit"), "rollback");
        Assert.assertEquals(table.commit(), 1);
        Assert.assertEquals(table.remove("commit"), "rollback");
        Assert.assertNull(table.put("commit", "rollback1"));
        Assert.assertEquals(table.commit(), 1);
        Assert.assertEquals(table.get("commit"), "rollback1");
        table.remove("my");
    }

    @Test
    public void testCommitWithNoChanges() {
        MyTableProviderFactory factory = new MyTableProviderFactory();
        MyTableProvider provider;
        provider = factory.create(System.getProperty("fizteh.db.dir"));
        table = provider.createTable("my1");
        Assert.assertNull(table.put("test", "tester"));
        Assert.assertEquals(table.remove("test"), "tester");
        Assert.assertEquals(table.commit(), 0);
        Assert.assertNull(table.put("key", "value"));
        Assert.assertEquals(table.commit(), 1);
        Assert.assertEquals(table.put("key", "blablabla"), "value");
        Assert.assertEquals(table.put("key", "value"), "blablabla");
        Assert.assertEquals(table.commit(), 0);
    }

    @Test
    public void checkSize() {
        MyTableProviderFactory factory = new MyTableProviderFactory();
        MyTableProvider provider;
        provider = factory.create(System.getProperty("fizteh.db.dir"));
        table = provider.createTable("my2");
        table.put("key1", "value");
        table.remove("key1");
        table.put("key3", "value");
        Assert.assertEquals("Incorrect size", 1, table.size());
    }

    @Test
    public void testPutWithOverwriting() {
        MyTableProviderFactory factory = new MyTableProviderFactory();
        MyTableProvider provider;
        provider = factory.create(System.getProperty("fizteh.db.dir"));
        table = provider.createTable("my3");
        table.put("Dmitry", "Zvonarev");
        table.put("Dmitry", "Anatolievuch");
        Assert.assertEquals("expected second put value", "Anatolievuch", table.get("Dmitry"));
    }

    @Test
    public void testGetAfterRemove() {
        MyTableProviderFactory factory = new MyTableProviderFactory();
        MyTableProvider provider;
        provider = factory.create(System.getProperty("fizteh.db.dir"));
        table = provider.createTable("my4");
        table.put("key", "value");
        table.remove("key");
        Assert.assertNull("expected null when get removed value", table.get("key"));
    }

}
