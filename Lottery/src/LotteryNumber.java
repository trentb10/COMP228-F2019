public class LotteryNumber {
    private int firstDigit;
    private int secondDigit;

    public LotteryNumber() {}

    public LotteryNumber(int FirstDigit,
                         int SecondDigit) {
        firstDigit = FirstDigit;
        secondDigit = SecondDigit;
    }

    public int getFirstDigit() { return firstDigit; }
    public void setFirstDigit(int FirstDigit) { firstDigit = FirstDigit; }

    public int getSecondDigit() { return secondDigit; }
    public void setSecondDigit(int SecondDigit) { secondDigit = SecondDigit; }
}

/*
    .
   _|________________
    |  .--.--.
    |  |  |  |
  `-`--'  '  `-

 */