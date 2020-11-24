import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        synchronizedMethod();
    }

    static void synchronizedMethod() {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                do {
                    print(this.getName());
                } while (true);
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                do {
                    print(this.getName());
                } while (true);
            }
        };

        thread1.start();
        thread2.start();

    }

    static synchronized void print(String threadName) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Это поток " + threadName);
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

        thread1.start();
        thread2.start();

        sleep(TimeUnit.SECONDS.toMillis(3));
        thread1.interrupt();
        thread2.interrupt();

    }

    static void simpleJoin() {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println("Простой поток запустился");
            }
        };
        Thread thread = new Thread(new Runnable() {
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

    static void simpleThread() {
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
            public void run() {
                System.out.println("Поток созданный через интерфейс запустился");
            }
        });
        thread.start();
    }

    static void simpleInterruptedThread() throws InterruptedException {
        Thread thread1 = new Thread() {
            public void run() {
                do {
                    System.out.println("Тик");
                } while (!isInterrupted());
            }
        };

        thread1.start();
        sleep(TimeUnit.SECONDS.toMillis(3));
        thread1.interrupt();
    }
}

