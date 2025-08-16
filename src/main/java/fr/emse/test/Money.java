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
    public IMoney add(IMoney aMoney) {
        if (aMoney instanceof Money) {
            Money m = (Money) aMoney;
            if (m.currency().equals(currency())) {
                return new Money(amount() + m.amount(), currency());
            } else {
                return new MoneyBag(this, m);
            }
        } else if (aMoney instanceof MoneyBag) {
            return ((MoneyBag) aMoney).add(this);
        }
        return null;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Money)) return false;
        Money other = (Money) obj;
        return this.fAmount == other.fAmount &&
               this.fCurrency.equals(other.fCurrency);
    }
}
