package es.deusto.client.logger;

import org.apache.log4j.Logger;

public class ClientLogger {

    private static final Logger log;

    static {
        log = Logger.getLogger("ClientLog");
    }

    public static Logger getLogger() {
        return log;
    }

}
