package guru;

public class DivisionTest {
    public static void main(String[] args) {
        Division division = new Division();


        String[] testArgs1 = {"10", "0"};
        division.divide(testArgs1);

        String[] testArgs2 = {"10", "5", "0"};
        division.divide(testArgs2);

        String[] testArgs3 = {"10", "test"};
        division.divide(testArgs3);

        String[] testArgs4 = {"10"};
        division.divide(testArgs4);

        String[] testArgs5 = {};
        division.divide(testArgs5);
    }
}
