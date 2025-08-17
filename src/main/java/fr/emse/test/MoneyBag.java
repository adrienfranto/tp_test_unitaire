package fr.emse.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MoneyBag implements IMoney {

    private List<Money> fMonies = new ArrayList<>();

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
        if (!(obj instanceof MoneyBag other)) return false;
        if (fMonies.size() != other.fMonies.size()) return false;

        for (Money m : fMonies) {
            boolean found = other.fMonies.stream().anyMatch(m::equals); // method reference
            if (!found) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fMonies);
    }
}
