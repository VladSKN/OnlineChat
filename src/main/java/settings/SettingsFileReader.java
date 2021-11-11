package settings;

import java.io.*;

public class SettingsFileReader {

    private final String[] hostString;
    private final File SETTINGS_FILE = new File("settings.txt");

    public SettingsFileReader() throws IOException {
        this.hostString = settingFileRead();
    }


    private String[] settingFileRead() throws IOException {
        String result = "";
        try (BufferedReader br = new BufferedReader(new FileReader(SETTINGS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                result = line;
            }
        }
        return result.split(":");
    }

    public String getHost() {
        return hostString[0];
    }

    public int getPort() {
        return Integer.parseInt(hostString[1]);
    }
}
