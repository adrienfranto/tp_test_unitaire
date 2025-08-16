package fr.emse.test;

import java.util.Vector;

class MoneyBag implements IMoney {

    private Vector<Money> fMonies = new Vector<Money>();

    MoneyBag(Money m1, Money m2) {
        appendMoney(m1);
        appendMoney(m2);
    }

    MoneyBag(Money bag[]) {
        for (int i = 0; i < bag.length; i++)
            appendMoney(bag[i]);
    }

    private void appendMoney(Money m) {
        if (fMonies.isEmpty()) {
            fMonies.add(m);
        } else {
            int i = 0;
            while ((i < fMonies.size())
                    && (!(fMonies.get(i).currency().equals(m.currency()))))
                i++;
            if (i >= fMonies.size()) {
                fMonies.add(m);
            } else {
                fMonies.set(i, new Money(fMonies.get(i).amount() + m.amount(), m.currency()));
            }
        }
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
    public IMoney add(IMoney aMoney) {
        if (aMoney instanceof Money) {
            return new MoneyBag(fMonies.toArray(new Money[0])).add((Money) aMoney);
        } else if (aMoney instanceof MoneyBag) {
            MoneyBag other = (MoneyBag) aMoney;
            MoneyBag result = new MoneyBag(fMonies.toArray(new Money[0]));
            for (Money m : other.fMonies) {
                result.appendMoney(m);
            }
            return result;
        }
        return null;
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
