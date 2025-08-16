package fr.emse.test;

import java.util.Vector;

public class MoneyBag implements IMoney {

    private Vector<Money> fMonies = new Vector<Money>();

    public MoneyBag(Money m1, Money m2) {
        appendMoney(m1);
        appendMoney(m2);
    }

    public MoneyBag(Money[] bag) {
        for (Money m : bag)
            appendMoney(m);
    }

    private void appendMoney(Money m) {
        if (fMonies.isEmpty()) {
            fMonies.add(m);
        } else {
            int i = 0;
            while (i < fMonies.size() && !fMonies.get(i).currency().equals(m.currency()))
                i++;
            if (i >= fMonies.size()) {
                fMonies.add(m);
            } else {
                fMonies.set(i, new Money(fMonies.get(i).amount() + m.amount(), m.currency()));
            }
        }
    }

    @Override
    public IMoney simplify() {
        if (fMonies.size() == 1) {
            return fMonies.get(0);
        }
        return this;
    }

    @Override
    public IMoney add(IMoney m) {
        return m.addMoneyBag(this).simplify(); 
    }

    @Override
    public IMoney addMoney(Money m) {
        MoneyBag newBag = new MoneyBag(fMonies.toArray(new Money[0]));
        newBag.appendMoney(m);
        return newBag.simplify();
    }

    @Override
    public IMoney addMoneyBag(MoneyBag mb) {
        MoneyBag newBag = new MoneyBag(fMonies.toArray(new Money[0]));
        for (Money m : mb.fMonies)
            newBag.appendMoney(m);
        return newBag.simplify();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof MoneyBag)) return false;
        MoneyBag other = (MoneyBag) obj;
        if (this.fMonies.size() != other.fMonies.size()) return false;
        for (IMoney m : this.fMonies) {
            boolean found = false;
            for (IMoney o : other.fMonies) {
                if (m.equals(o)) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        for (IMoney m : fMonies) {
            hash = 31 * hash + m.hashCode();
        }
        return hash;
    }
}
