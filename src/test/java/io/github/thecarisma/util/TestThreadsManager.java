package io.github.thecarisma.util;

import io.github.thecarisma.laner.InternetStatus;
import io.github.thecarisma.laner.LanerListener;
import io.github.thecarisma.laner.LanerNetworkInterface;
import io.github.thecarisma.laner.NetworkDevices;
import org.junit.Test;

import java.net.UnknownHostException;

public class TestThreadsManager {

    @Test
    public void Test1() throws UnknownHostException {
        final ThreadsManager threadsManager = new ThreadsManager();
        final int[] index = {0};
        NetworkDevices nd = new NetworkDevices(LanerNetworkInterface.getIPV4Address(), new LanerListener() {
            @Override
            public void report(Object o) {
                if (o instanceof NetworkDevices.NetworkDevice) {
                    if (index[0] >= 0) {
                        try {
                            System.out.println("Killing all the thread");
                            threadsManager.killAll();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(o);
                    index[0]++;
                }
            }
        });
        threadsManager.register("testnetworddevices", nd);
        nd.run();
    }

    @Test
    public void Test2() throws UnknownHostException {
        final ThreadsManager threadsManager = new ThreadsManager();
        final int[] index = {0};
        NetworkDevices nd = new NetworkDevices(LanerNetworkInterface.getIPV4Address(), new LanerListener() {
            @Override
            public void report(Object o) {
                if (o instanceof NetworkDevices.NetworkDevice) {
                    if (index[0] >= 0) {
                        try {
                            System.out.println("Killing all testnetworddevices1 runnables");
                            threadsManager.killAll("testnetworddevices1");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(o);
                    index[0]++;
                }
            }
        });
        final int[] index2 = {0};
        InternetStatus is = new InternetStatus("thecarisma.github.io", new LanerListener() {
            @Override
            public void report(Object o) {
                if (o instanceof InternetStatus.Status) {
                    if (index2[0] >= 0) {
                        try {
                            System.out.println("Killing all testnetworddevices2 Runables");
                            threadsManager.killAll("testnetworddevices2");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(o);
                    index2[0]++;
                }
            }
        });
        threadsManager.register("testnetworddevices1", nd);
        threadsManager.register("testnetworddevices2", is);
        is.run();
        nd.run();
    }

    //@Test
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        final ThreadsManager threadsManager = new ThreadsManager();
        final int[] index = {0};
        NetworkDevices nd = new NetworkDevices(LanerNetworkInterface.getIPV4Address(), new LanerListener() {
            @Override
            public void report(Object o) {
                if (o instanceof NetworkDevices.NetworkDevice) {
                    if (index[0] >= 0) {
                        try {
                            System.out.println("Killing all testnetworddevices runnables");
                            threadsManager.killAll("testnetworddevices");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(o);
                    index[0]++;
                }
            }
        });
        final int[] index2 = {0};
        InternetStatus is = new InternetStatus("thecarisma.github.io", new LanerListener() {
            @Override
            public void report(Object o) {
                if (o instanceof InternetStatus.Status) {
                    if (index2[0] >= 0) {
                        try {
                            System.out.println("Don't care killing in NetworkDevice status");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(o);
                    index2[0]++;
                }
            }
        });
        threadsManager.register("testnetworddevices", nd);
        //threadsManager.register("testnetworddevices", is);
        threadsManager.startAll("testnetworddevices");
    }

}
