package settings;

import org.junit.jupiter.api.*;

import java.io.IOException;

public class SettingFileReaderTest {

    @BeforeEach
    public void init() {
        System.out.println("Test started");
    }

    @Test
    public void settingFileReadTestGetHost() throws IOException {
        SettingsFileReader settingsFileReader = new SettingsFileReader();

        String host = "127.0.0.1";

        String result = settingsFileReader.getHost();

        Assertions.assertEquals(result, host);
    }

    @Test
    public void settingFileReadTestGetPort() throws IOException {
        SettingsFileReader settingsFileReader = new SettingsFileReader();

        int port = 34343;

        int result = settingsFileReader.getPort();

        Assertions.assertEquals(result, port);
    }
}
