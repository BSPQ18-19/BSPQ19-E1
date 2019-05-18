package es.deusto.client.remote;

import es.deusto.server.IServer;
import org.apache.log4j.Logger;

import es.deusto.client.logger.ClientLogger;

public class RMIServiceLocator {

    private static RMIServiceLocator rsl = new RMIServiceLocator();
    private IServer clientManager = null;
    private Logger log;

    private RMIServiceLocator() {
        log = ClientLogger.getLogger();
        log.info("RMIServiceLocator initialized");

    }

    public static RMIServiceLocator getServiceLocator() {
        return rsl;
    }

    public void setService(String ip, String port, String serviceName) {
        String url = "//" + ip + ":" + port + "/" + serviceName;
        try {
            clientManager = (IServer) java.rmi.Naming.lookup(url);
            log.info("Connected to: " + url);
        } catch (Exception e) {
            log.fatal("Exception stablishing connection to: " + url);
        }
    }

    public IServer getClientManager() {
        return clientManager;
    }
}