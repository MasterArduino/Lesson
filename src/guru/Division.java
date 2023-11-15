package guru;

public class Division {
    public Double divide(String... arguments) {
        try {
            int result = Integer.parseInt(arguments[0]);
            for (int i = 1; i < arguments.length; i++) {
                int divisor = Integer.parseInt(arguments[i]);
                if (divisor == 0) {
                    System.out.println("Деление на ноль запрещено.");
                    return null;
                }
                result = result / divisor;
            }
            return (double) result;
        } catch (NumberFormatException e) {
            System.out.println("Неверный аргумент. Пожалуйста, введите целые числа.");
        } catch (ArithmeticException e) {
            System.out.println("Произошла арифметическая ошибка: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Предоставлены недостаточные аргументы.");
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
        return null;
    }
}
