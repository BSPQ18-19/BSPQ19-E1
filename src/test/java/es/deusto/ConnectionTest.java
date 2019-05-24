package es.deusto;

import es.deusto.client.Client;
import es.deusto.server.IServer;
import es.deusto.server.Server;
import es.deusto.server.jdo.Movie;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
//import static org.graalvm.compiler.replacements.Log.println;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class ConnectionTest {
    static private Thread rmiRegistryThread;
    private static Thread rmiServerThread;
    private static IServer server;


    @BeforeClass
    static public void setupRMI() {
        class RMIRegistry implements Runnable {

            public void run() {
                try {
                    java.rmi.registry.LocateRegistry.createRegistry(1099);
                    System.out.println("RMI registry ready.");
                } catch (Exception e) {
                    System.out.println("Exception starting RMI registry:");
                    e.printStackTrace();
                }
            }
        }

        rmiRegistryThread = new Thread(new RMIRegistry());
        rmiRegistryThread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        class RMIServerRunnable implements Runnable {

            public void run() {
                System.setProperty("java.security.policy", "target/classes/security/java.policy");

                if (System.getSecurityManager() == null) {
                    System.setSecurityManager(new SecurityManager());
                }

                String name = "//127.0.0.1:1099/ConnectionTest";

                try {
                    server = new Server();
                    Naming.rebind(name, server);
                } catch (RemoteException re) {
                    System.err.println("Server RemoteException: " + re.getMessage());
                    re.printStackTrace();
                    System.exit(-1);
                } catch (MalformedURLException murle) {
                    System.err.println("Server MalformedURLException: " + murle.getMessage());
                    murle.printStackTrace();
                    System.exit(-1);
                }
            }
        }

        rmiServerThread = new Thread(new RMIServerRunnable());
        rmiServerThread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    @Test
    public void testConnectionToServer() throws NotBoundException, MalformedURLException, RemoteException {
        final String[] arg = {"127.0.0.1", "1099", "ConnectionTest"};

        Client client = new Client();
      // client.main(arg);
        String title = "Test Title";
        String director = "Test Director Name";
        List<String> actorCast = new ArrayList<String>();
        actorCast.add("Angelina Jolie");
        actorCast.add("Jhon Ramob");
        client.accessRMIServer(arg[0], arg[1], arg[2]);
        server.deleteMovie(title);
        client.addMovie(title, director, actorCast);

        assertNotNull(server.getMovie(title));
    }

    @AfterClass static public void tearDown() {
        try {
            rmiServerThread.join();
            rmiRegistryThread.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
