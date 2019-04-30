package es.deusto.client.remote;

import org.apache.log4j.Logger;

import es.deusto.client.logger.ClientLogger;
import es.deusto.server.remote.IClientManager;

public class RMIServiceLocator {

    private static RMIServiceLocator rsl = new RMIServiceLocator();
    private IClientManager clientManager = null;
    private Logger log;

    private RMIServiceLocator() {
        log = ClientLogger.getLogger();
        log.info("RMIServiceLocator initialized");
    }

    public static RMIServiceLocator getServiceLocator() {
        return rsl;
    }

    public void setService(String ip, int port, String serviceName) {
        String url = "//" + ip + ":" + port + "/" + serviceName;
        try {
            clientManager = (IClientManager) java.rmi.Naming.lookup(url);
            log.info("Connected to: " + url);
        } catch (Exception e) {
            log.fatal("Exception stablishing connection to: " + url);
        }
    }

    public IClientManager getClientManager() {
        return clientManager;
    }
}