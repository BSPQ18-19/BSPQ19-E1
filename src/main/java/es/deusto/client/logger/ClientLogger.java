package es.deusto.client.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientLogger {

    private static final Logger log;

    static {
        log = LoggerFactory.getLogger("ClientLog");
    }

    public static Logger getLogger() {
        return log;
    }

}
