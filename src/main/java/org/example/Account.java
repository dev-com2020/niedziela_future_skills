package org.example;

interface CommisionPolicy {
    double calculateCommission(double amount);
}

class Account {
    private CommisionPolicy commisionPolicy;

    public Account(CommisionPolicy commisionPolicy) {
        this.commisionPolicy = commisionPolicy;
    }

    public double calculateCommission(double amount) {
        return commisionPolicy.calculateCommission(amount);
    }
}

class PremiumAccount implements CommisionPolicy {
    public double calculateCommission(double amount) {
        return amount * 0.01;
    }
}

class StdAccount implements CommisionPolicy {
    public double calculateCommission(double amount) {
        return amount * 0.03;
    }
}
