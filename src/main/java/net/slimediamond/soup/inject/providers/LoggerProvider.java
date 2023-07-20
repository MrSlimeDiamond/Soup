package net.slimediamond.soup.inject.providers;

import com.google.inject.Provider;
import org.apache.logging.log4j.Logger;

public class LoggerProvider implements Provider<Logger> {
    private Logger logger;
    public LoggerProvider(Logger logger) {
        this.logger = logger;
    }
    @Override
    public Logger get() {
        return logger;
    }
}
