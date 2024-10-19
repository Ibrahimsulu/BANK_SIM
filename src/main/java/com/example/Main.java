package com.example;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import java.util.Random;
import akka.actor.Props;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ActorSystem system = ActorSystem.create("BankFramework");
        ActorRef bank_account = system.actorOf(BankAccount.props(), "bank_account");

        bank_account.tell(new GetBalance(true), ActorRef.noSender());

        Random random = new Random();
        int transactions = 10;
        int transactions_processed = 0;


        for (int i = 0; i < transactions; i++) {
            double amount = random.nextInt(2001) - 1000;
            if (amount > 0) {
                bank_account.tell(new Deposit(amount), ActorRef.noSender());
            } else {
                bank_account.tell(new Withdrawal(Math.abs(amount)), ActorRef.noSender());
            }
            transactions_processed++;
        }

        if (transactions_processed == transactions) {
            bank_account.tell(new GetBalance(false), ActorRef.noSender());
            system.terminate();

        }

    }
}
