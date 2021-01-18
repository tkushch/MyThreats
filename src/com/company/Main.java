package com.company;


import java.util.Scanner;

class Client implements Runnable {
    Bank bank;

    Client(Bank bank){
        this.bank = bank;
    }

    @Override
    public void run() {
        while (true){
            synchronized (bank) {
                bank.money -= 100;
            }
            synchronized (bank) {
                bank.money += 100;
            }
        }
    }
}

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        Bank bank = new Bank();

        Client client = new Client(bank);
        Thread thread = new Thread(client);
        thread.start();

        Client client1 = new Client(bank);
        Thread thread1 = new Thread(client1);
        thread1.start();

        Client client2 = new Client(bank);
        Thread thread2 = new Thread(client2);
        thread2.start();

        while (true){
            System.out.println("" + bank.money);
            Thread.sleep(500);
        }


    }
}

class Bank {
    int money = 5000;
}



