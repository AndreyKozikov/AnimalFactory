import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class AnimalRegistry {
    private static LocalDate parseDate(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Registry registry = new Registry();
        Scanner scanner = new Scanner(System.in);

        List<String> validAnimalTypes = List.of("dog", "cat", "hamster", "horse", "camel", "donkey");


        boolean exit = false;

        while (!exit) {
            System.out.println("\nМеню:");
            System.out.println("1. Завести новое животное");
            System.out.println("2. Показать всех животных");
            System.out.println("3. Показать команды животного");
            System.out.println("4. Обучить животное командам");
            System.out.println("5. Выход");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    try (Counter counter = Counter.getInstance()) {
                        counter.open();

                        String type = "";
                        while (!validAnimalTypes.contains(type.toLowerCase())) {
                            System.out.println("Введите тип животного " + validAnimalTypes + ": ");
                            type = scanner.nextLine();
                            if (!validAnimalTypes.contains(type.toLowerCase())) {
                                System.out.println("Неверный тип животного.");
                            }
                        }
                        System.out.print("Введите имя животного: ");
                        String name = scanner.nextLine();

                        LocalDate birthDate = null;
                        while (birthDate == null) {
                            System.out.print("Введите дату рождения (DD-MM-YYYY): ");
                            String dateString = scanner.nextLine();
                            birthDate = parseDate(dateString);
                            if (birthDate == null) {
                                System.out.println("Неверный формат даты. Пожалуйста, используйте формат DD-MM-YYYY.");
                            }
                        }

                        Animal animal = AnimalFactory.createAnimal(type, name, birthDate);

                        if (animal != null) {
                            registry.addAnimal(animal);
                            counter.add();
                            System.out.println(name + " добавлен в реестр.");
                        }

                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;

                case 2:
                    registry.displayAnimals();
                    break;

                case 3:
                    System.out.print("Введите имя животного для просмотра команд: ");
                    String animalNameForCommands = scanner.nextLine();
                    registry.listCommands(animalNameForCommands);
                    break;

                case 4:
                    System.out.print("Введите имя животного для обучения: ");
                    String animalNameForTraining = scanner.nextLine();
                    System.out.print("Введите команду для обучения: ");
                    String command = scanner.nextLine();
                    registry.trainAnimal(animalNameForTraining, command);
                    break;

                case 5:
                    exit = true;
                    break;

                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите правильный номер команды.");
            }
        }

        scanner.close();
    }
}
