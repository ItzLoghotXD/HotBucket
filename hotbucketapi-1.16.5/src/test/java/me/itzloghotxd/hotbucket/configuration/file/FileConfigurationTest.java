package me.itzloghotxd.hotbucket.configuration.file;

import static org.junit.Assert.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import me.itzloghotxd.hotbucket.configuration.MemoryConfigurationTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public abstract class FileConfigurationTest extends MemoryConfigurationTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Override
    public abstract FileConfiguration getConfig();

    public abstract String getTestValuesString();

    public abstract String getTestHeaderInput();

    public abstract String getTestHeaderResult();

    @Test
    public void testSave_File() throws Exception {
        FileConfiguration config = getConfig();
        File file = testFolder.newFile("test.config");

        for (Map.Entry<String, Object> entry : getTestValues().entrySet()) {
            config.set(entry.getKey(), entry.getValue());
        }

        config.save(file);

        assertTrue(file.isFile());
    }

    @Test
    public void testSave_String() throws Exception {
        FileConfiguration config = getConfig();
        File file = testFolder.newFile("test.config");

        for (Map.Entry<String, Object> entry : getTestValues().entrySet()) {
            config.set(entry.getKey(), entry.getValue());
        }

        config.save(file.getAbsolutePath());

        assertTrue(file.isFile());
    }

    @Test
    public void testSaveToString() {
        FileConfiguration config = getConfig();

        for (Map.Entry<String, Object> entry : getTestValues().entrySet()) {
            config.set(entry.getKey(), entry.getValue());
        }

        String result = config.saveToString();
        String expected = getTestValuesString();

        assertEquals(expected, result);
    }

    @Test
    public void testLoad_File() throws Exception {
        FileConfiguration config = getConfig();
        File file = testFolder.newFile("test.config");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        String saved = getTestValuesString();
        Map<String, Object> values = getTestValues();

        try {
            writer.write(saved);
        } finally {
            writer.close();
        }

        config.load(file);

        for (Map.Entry<String, Object> entry : values.entrySet()) {
            assertEquals(entry.getValue(), config.get(entry.getKey()));
        }

        assertEquals(values.keySet(), config.getKeys(true));
    }

    @Test
    public void testLoad_String() throws Exception {
        FileConfiguration config = getConfig();
        File file = testFolder.newFile("test.config");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        String saved = getTestValuesString();
        Map<String, Object> values = getTestValues();

        try {
            writer.write(saved);
        } finally {
            writer.close();
        }

        config.load(file.getAbsolutePath());

        for (Map.Entry<String, Object> entry : values.entrySet()) {
            assertEquals(entry.getValue(), config.get(entry.getKey()));
        }

        assertEquals(values.keySet(), config.getKeys(true));
    }

    @Test
    public void testLoadFromString() throws Exception {
        FileConfiguration config = getConfig();
        Map<String, Object> values = getTestValues();
        String saved = getTestValuesString();

        config.loadFromString(saved);

        for (Map.Entry<String, Object> entry : values.entrySet()) {
            assertEquals(entry.getValue(), config.get(entry.getKey()));
        }

        assertEquals(values.keySet(), config.getKeys(true));
        assertEquals(saved, config.saveToString());
    }

    @Test
    public void testSaveToStringWithHeader() {
        FileConfiguration config = getConfig();
        config.options().header(getTestHeaderInput());

        for (Map.Entry<String, Object> entry : getTestValues().entrySet()) {
            config.set(entry.getKey(), entry.getValue());
        }

        String result = config.saveToString();
        String expected = getTestHeaderResult() + "\n" + getTestValuesString();

        assertEquals(expected, result);
    }

    @Test
    public void testParseHeader() throws Exception {
        FileConfiguration config = getConfig();
        Map<String, Object> values = getTestValues();
        String saved = getTestValuesString();
        String header = getTestHeaderResult();
        String expected = getTestHeaderInput();

        config.loadFromString(header + "\n" + saved);

        assertEquals(expected, config.options().header());

        for (Map.Entry<String, Object> entry : values.entrySet()) {
            assertEquals(entry.getValue(), config.get(entry.getKey()));
        }

        assertEquals(values.keySet(), config.getKeys(true));
        assertEquals(header + "\n" + saved, config.saveToString());
    }

    @Test
    public void testCopyHeader() throws Exception {
        FileConfiguration config = getConfig();
        FileConfiguration defaults = getConfig();
        Map<String, Object> values = getTestValues();
        String saved = getTestValuesString();
        String header = getTestHeaderResult();
        String expected = getTestHeaderInput();

        defaults.loadFromString(header);
        config.loadFromString(saved);
        config.setDefaults(defaults);

        assertNull(config.options().header());
        assertEquals(expected, defaults.options().header());

        for (Map.Entry<String, Object> entry : values.entrySet()) {
            assertEquals(entry.getValue(), config.get(entry.getKey()));
        }

        assertEquals(values.keySet(), config.getKeys(true));
        assertEquals(header + "\n" + saved, config.saveToString());

        config = getConfig();
        config.loadFromString(getTestHeaderResult() + saved);
        assertEquals(getTestHeaderResult() + saved, config.saveToString());
    }

    @Test
    public void testReloadEmptyConfig() throws Exception {
        FileConfiguration config = getConfig();

        assertEquals("", config.saveToString());

        config = getConfig();
        config.loadFromString("");

        assertEquals("", config.saveToString());

        config = getConfig();
        config.loadFromString("\n\n"); // Should trim the first newlines of a header

        assertEquals("", config.saveToString());
    }

    @Test
    public void testReloadClear() throws Exception {
        // Test for SPIGOT-6274 - load does not clear values
        FileConfiguration config = getConfig();

        assertFalse(config.contains("test"));
        assertFalse(config.getBoolean("test"));

        config.set("test", true);
        assertTrue(config.contains("test"));
        assertTrue(config.getBoolean("test"));

        config.loadFromString("");
        assertFalse(config.contains("test"));
        assertFalse(config.getBoolean("test"));

        assertFalse(config.contains("test"));
        assertFalse(config.getBoolean("test"));
    }

    @Test
    public void testReloadClear2() throws Exception {
        // Test for SPIGOT-6274 - load does not clear values
        FileConfiguration config = getConfig();

        assertFalse(config.contains("test"));
        assertFalse(config.getBoolean("test"));

        config.set("test", true);
        assertTrue(config.contains("test"));
        assertTrue(config.getBoolean("test"));

        config.loadFromString("other: false"); // Test both null and non-null code paths
        assertFalse(config.contains("test"));
        assertFalse(config.getBoolean("test"));

        assertFalse(config.contains("test"));
        assertFalse(config.getBoolean("test"));
    }

    @Test
    public void testReloadClear3() throws Exception {
        // Test for SPIGOT-6274 - load does not clear values
        FileConfiguration config = getConfig();

        assertFalse(config.contains("test"));
        assertFalse(config.getBoolean("test"));

        config.set("test", true);
        assertTrue(config.contains("test"));
        assertTrue(config.getBoolean("test"));

        config.loadFromString("other: false\nsection:\n  value: true"); // SPIGOT-6313: Test with section
        assertFalse(config.contains("test"));
        assertFalse(config.getBoolean("test"));

        assertTrue(config.contains("other"));
        assertTrue(config.contains("section"));
        assertTrue(config.contains("section.value"));
        assertTrue(config.getBoolean("section.value"));

        assertFalse(config.contains("test"));
        assertFalse(config.getBoolean("test"));
    }
}
