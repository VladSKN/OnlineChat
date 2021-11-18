package settings;

import org.junit.jupiter.api.*;

import java.io.IOException;

public class SettingFileReaderTest {
    SettingsFileReader settingsFileReader = new SettingsFileReader();

    public SettingFileReaderTest() throws IOException {
    }


    @BeforeEach
    public void init() {
        System.out.println("Test started");
    }

    @Test
    public void settingFileReadTestGetHost() {

        String host = "127.0.0.1";

        String result = settingsFileReader.getHost();

        Assertions.assertEquals(result, host);
    }

    @Test
    public void settingFileReadTestGetPort() {

        int port = 34343;

        int result = settingsFileReader.getPort();

        Assertions.assertEquals(result, port);
    }
}
