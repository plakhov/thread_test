package main.java;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        manyActionsInThreads();
    }

    static void manyActionsInThreads() throws InterruptedException {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                do {
                    System.out.println("Thread one");
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        return;
                    }
                } while (!isInterrupted());
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                do {
                    System.out.println("Thread two");
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        return;
                    }
                } while (!isInterrupted());
            }
        };

        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.MIN_PRIORITY);
        thread1.start();
        thread2.start();

        Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        thread1.interrupt();
        thread2.interrupt();

    }

    static void simpleJoin(){
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println("Простой поток запустился");
            }
        };
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Поток созданный через интерфейс запустился");
            }
        });
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.start();
    }

    static void simpleThread(){
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println("Простой поток запустился");
            }
        };

        thread1.start();
    }

    static void simpleRunnable() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Поток созданный через интерфейс запустился");
            }
        });
        thread.start();
    }

    static void simpleInterruptedThread() throws InterruptedException {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                do {
                    System.out.println("Тик");
                } while (!isInterrupted());
            }
        };

        thread1.start();
        Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        thread1.interrupt();
    }
}

