public class Winnings {
    private Boolean isAWinner;
    private int amount;

    public Winnings() {}

    public Winnings(Boolean IsAWinner,
                    int Amount) {
        isAWinner = IsAWinner;
        amount = Amount;
    }

    public Boolean getIsAWinner() { return isAWinner; }
    public void setIsAWinner(Boolean IsAWinner) { isAWinner = IsAWinner; }

    public int getAmount() { return amount; }
    public void setAmount(int Amount) { amount = Amount; }
}

/*
    .
   _|________________
    |  .--.--.
    |  |  |  |
  `-`--'  '  `-

 */