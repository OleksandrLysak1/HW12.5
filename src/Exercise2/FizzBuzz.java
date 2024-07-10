package Exercise2;

class FizzBuzz {
    private final int n;
    private int current = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    public synchronized void fizz() throws InterruptedException {
        while (current <= n) {
            while (current % 3 != 0 || current % 5 == 0) {
                wait();
            }
            if (current > n) break;
            System.out.println("fizz");
            current++;
            notifyAll();
        }
    }

    public synchronized void buzz() throws InterruptedException {
        while (current <= n) {
            while (current % 5 != 0 || current % 3 == 0) {
                wait();
            }
            if (current > n) break;
            System.out.println("buzz");
            current++;
            notifyAll();
        }
    }

    public synchronized void fizzbuzz() throws InterruptedException {
        while (current <= n) {
            while (current % 15 != 0) {
                wait();
            }
            if (current > n) break;
            System.out.println("fizzbuzz");
            current++;
            notifyAll();
        }
    }

    public synchronized void number() throws InterruptedException {
        while (current <= n) {
            while (current % 3 == 0 || current % 5 == 0) {
                wait();
            }
            if (current > n) break;
            System.out.println(current);
            current++;
            notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int n = 15;
        FizzBuzz fizzBuzz = new FizzBuzz(n);

        Thread threadA = new Thread(() -> {
            try {
                fizzBuzz.fizz();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                fizzBuzz.buzz();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadD = new Thread(() -> {
            try {
                fizzBuzz.number();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        threadA.join();
        threadB.join();
        threadC.join();
        threadD.join();
    }
}
