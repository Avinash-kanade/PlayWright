package setupbrowserbinary;

import com.microsoft.playwright.CLI;

import java.io.IOException;
import java.net.URISyntaxException;

public class SetupBroswer {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        CLI.main(new String[]{"install"});

    }
}
