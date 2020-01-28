package io.github.thecarisma.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThreadsManager {

    private Map<String, ArrayList<Thread>> threads = new HashMap<>();
    private Map<String, ArrayList<TRunnable>> tRunnables = new HashMap<>();

    public void registerThread(String owner, Thread thread) {
        if (!threads.containsKey(owner)) {
            ArrayList<Thread> tThreads = new ArrayList<>();
            threads.put(owner, tThreads);
        }
        threads.get(owner).add(thread);
    }

    public ArrayList<Thread> getThreads(String owner) {
        return threads.get(owner);
    }

    public boolean unRegisterThread(String owner, Thread thread) {
        if (threads.containsKey(owner)) {
            return threads.get(owner).remove(thread);
        }
        return false;
    }

    public void killThread(String owner, Thread thread) {
        if (threads.containsKey(owner)) {
            for (int i = 0; i < threads.get(owner).size(); i++) {
                if (threads.get(owner).get(i).equals(thread)) {
                    threads.get(owner).get(i).stop();
                    unRegisterThread(owner, threads.get(owner).get(i));
                }
            }
        }
    }

    public void killAllThread(String owner) {
        if (threads.containsKey(owner)) {
            for (int i = 0; i < threads.get(owner).size(); i++) {
                threads.get(owner).get(i).stop();
                unRegisterThread(owner, threads.get(owner).get(i));
            }
        }
    }

    public void killAllThreads() {
        for (String owner : threads.keySet()) {
            for (int i = 0; i < threads.get(owner).size(); i++) {
                threads.get(owner).get(i).stop();
                unRegisterThread(owner, threads.get(owner).get(i));
            }
        }
    }

    public void registerTRunnable(String owner, TRunnable tRunnable) {
        if (!tRunnables.containsKey(owner)) {
            ArrayList<TRunnable> tRunable = new ArrayList<>();
            tRunnables.put(owner, tRunable);
        }
        tRunnables.get(owner).add(tRunnable);
    }

    public ArrayList<TRunnable> getTRunnables(String owner) {
        return tRunnables.get(owner);
    }

    public boolean unRegisterTRunnable(String owner, TRunnable tRunnable) {
        if (tRunnables.containsKey(owner)) {
            return tRunnables.get(owner).remove(tRunnable);
        }
        return false;
    }

    public void killTRunnable(String owner, TRunnable tRunnable) throws Exception {
        if (tRunnables.containsKey(owner)) {
            for (int i = 0; i < tRunnables.get(owner).size(); i++) {
                if (tRunnables.get(owner).get(i).equals(tRunnable)) {
                    tRunnables.get(owner).get(i).stop();
                    unRegisterTRunnable(owner, tRunnables.get(owner).get(i));
                }
            }
        }
    }

    public void killAllTRunnable(String owner) throws Exception {
        if (tRunnables.containsKey(owner)) {
            for (int i = 0; i < tRunnables.get(owner).size(); i++) {
                tRunnables.get(owner).get(i).stop();
                unRegisterTRunnable(owner, tRunnables.get(owner).get(i));
            }
        }
    }

    public void killAllTRunnables() throws Exception {
        for (String owner : tRunnables.keySet()) {
            for (int i = 0; i < tRunnables.get(owner).size(); i++) {
                tRunnables.get(owner).get(i).stop();
                unRegisterTRunnable(owner, tRunnables.get(owner).get(i));
            }
        }
    }

    public void killAll() throws Exception {
        killAllTRunnables();
        killAllThreads();
    }

}
