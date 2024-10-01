public class Money implements IMoney {
    private int amount;
    private String currency;

    private boolean invariance() {
        boolean integrite = true;

        if (this.amount < 0) {
            integrite = false;
            throw new IllegalArgumentException("La valeur de la monnaie doit être positive ou égale à zéro.");
        }
        if (this.currency == null || this.currency.equals("")) {
            integrite = false;
            throw new IllegalArgumentException("La devise doit être une devise valide.");
        }

        return integrite;
    }

    public Money(int amount, String currency) {
        if (amount > 0) {
            this.amount = amount;
        } else {
            throw new RuntimeException("La monnaie doit être positive.");
        }

        if (currency != null && !currency.equals("")) {
            this.currency = currency;
        } else {
            throw new RuntimeException("La devise doit être une devise valide.");
        }

        invariance();
    }

    // ------- LES ACCESSEURS -------

    public int getAmount() {
        return this.amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    // ------- LES METHODES DE LA CLASSE -------

    @Override
    public IMoney add(Money anotherMoney) {
        if (this.currency.equals(anotherMoney.currency)) {
            return new Money(this.amount + anotherMoney.amount, this.currency);
        } else {
            MoneyBag newBag = new MoneyBag(new Money[]{this, anotherMoney});
            return newBag;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Money that = (Money) obj;

        if (this.amount != that.amount) return false;
        return this.currency.equals(that.currency);
    }

    @Override
    public String toString() {
        return this.amount + " " + this.currency;
    }
}