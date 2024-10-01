import java.util.ArrayList;

public class MoneyBag implements IMoney {
    ArrayList<Money> monies;

    private void checkInvariant() {
        assert monies != null : "Invariant: monies should not be null";
        assert monies.size() >= 0 : "Invariant: monies size should not be negative";
    }

    public MoneyBag() {
        this.monies = new ArrayList<>();
        checkInvariant();
    }

    public MoneyBag(Money[] bag) {
        assert bag != null : "Precondition: bag should not be null";
        this.monies = new ArrayList<>();

        for (Money money : bag) {
            this.appendMoney(money);
        }

        checkInvariant();
    }

    public void appendMoney(Money theM) {
        assert theM != null : "Precondition: theM should not be null";

        int index = currencyIsPresent(theM.getCurrency());
        if (index == -1) {
            this.monies.add(theM);
        } else {
            IMoney result = this.monies.get(index).add(theM);
            if (result instanceof Money) {
                this.monies.set(index, (Money) result);
            } else if (result instanceof MoneyBag) {
                this.monies.remove(index);
                MoneyBag newBag = (MoneyBag) result;
                for (Money m : newBag.monies) {
                    this.appendMoney(m);
                }
            }
        }

        checkInvariant();
    }

    private int currencyIsPresent(String currency) {
        assert currency != null && !currency.isEmpty() : "Precondition: currency should not be null or empty";

        for (int i = 0; i < this.monies.size(); i++) {
            if (this.monies.get(i).getCurrency().equals(currency)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public IMoney add(Money theM) {
        throw new UnsupportedOperationException("Operation not supported for MoneyBag");
    }

    public void theSame(MoneyBag otherBag) throws NotTheSameException {
        assert otherBag != null : "Precondition: otherBag should not be null";

        if (!(this.monies.equals(otherBag.monies))) {
            throw new NotTheSameException("Les deux MoneyBag sont différents");
        }

        assert this.monies.equals(otherBag.monies) : "Postcondition: MoneyBags should be the same after comparison";
    }

    @Override
    public String toString() {
        StringBuilder contenuBag = new StringBuilder();

        for (Money money : this.monies) {
            contenuBag.append("\n").append(money);
        }

        return contenuBag.toString().trim();
    }
}
