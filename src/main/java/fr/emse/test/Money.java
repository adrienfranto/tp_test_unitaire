package fr.emse.test;

import java.util.Objects;

public class Money implements IMoney {

    private final int fAmount;
    private final String fCurrency;

    public Money(int amount, String currency) {
        fAmount = amount;
        fCurrency = currency;
    }

    public int amount() {
        return fAmount;
    }

    public String currency() {
        return fCurrency;
    }

    @Override
    public IMoney addMoneyBag(MoneyBag mb) {
        return mb.addMoney(this).simplify();
    }

    @Override
    public IMoney add(IMoney m) {
        return m.addMoney(this).simplify();
    }

    @Override
    public IMoney addMoney(Money m) {
        if (Objects.equals(m.currency(), this.currency())) {
            return new Money(this.amount() + m.amount(), currency());
        }
        return new MoneyBag(this, m).simplify();
    }

    @Override
    public IMoney simplify() {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Money other)) return false; 
        return fAmount == other.fAmount &&
               Objects.equals(fCurrency, other.fCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fAmount,fCurrency);
    }
    
}
