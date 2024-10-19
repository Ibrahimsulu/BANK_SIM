package com.example;

import akka.actor.AbstractActor;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import java.io.IOException;

public class BankAccount extends AbstractActor {
    private double balance = 100;

    static Props props() {
        return Props.create(BankAccount.class, BankAccount::new);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(GetBalance.class, this::handleGetBalance)
                .match(Deposit.class, this::handleDeposit)
                .match(Withdrawal.class, this::handleWithdrawal)
                .build();
    }

    private void handleGetBalance(GetBalance getBalance) {
        if (getBalance.isInitial()) {
            System.out.println("Initial Balance: \u00A3" + balance);
        }else {
            System.out.println("Terminated. Closing Balance is: \u00A3" + balance);
        }
    }

    private void handleDeposit(Deposit deposit) {
        double amount = deposit.getAmount();
        balance += amount;
        System.out.println("Deposit of \u00A3" + amount + " processed. New Balance: \u00A3" + balance);
    }

    private void handleWithdrawal(Withdrawal withdrawal) {
        double amount = withdrawal.getAmount();
        if(balance >= amount){
            balance -= amount;
            System.out.println("Withdrawal of \u00A3" + amount + " processed. New Balance: \u00A3" + balance);
        } else {
            double remainder = amount - balance;
            System.out.println("Insufficient funds for withdrawal of \u00A3" + amount + "! Deposit at least \u00A3" + remainder + " and try again.");
        }
    }
}
