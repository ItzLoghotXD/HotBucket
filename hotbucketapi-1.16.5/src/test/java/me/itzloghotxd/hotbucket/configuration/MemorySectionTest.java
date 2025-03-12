package me.itzloghotxd.hotbucket.configuration;

public class MemorySectionTest extends ConfigurationSectionTest {
    @Override
    public ConfigurationSection getConfigurationSection() {
        return new MemoryConfiguration().createSection("section");
    }
}
