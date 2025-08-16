package fr.emse.test;

public class Money implements IMoney {

    private int fAmount;
    private String fCurrency;

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
    public IMoney add(IMoney m) {
        return m.addMoney(this);
    }

    @Override
    public IMoney addMoney(Money m) {
        if (m.currency().equals(this.currency()))
            return new Money(this.amount() + m.amount(), currency());
        return new MoneyBag(this, m);
    }

    @Override
    public IMoney addMoneyBag(MoneyBag mb) {
        return mb.addMoney(this); 
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Money)) return false;
        Money other = (Money) obj;
        return this.fAmount == other.fAmount &&
               this.fCurrency.equals(other.fCurrency);
    }
    
    @Override
    public int hashCode() {
        return 31 * fAmount + fCurrency.hashCode();
    }
}
