import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


// Абстрактный класс Animal
abstract class Animal {
    private String name;
    private LocalDate birthDate;
    private List<String> commands;

    public Animal(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.commands = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return String.format("%s, возраст: %d лет, команды: %s", name, getAge(), String.join(", ", commands));
    }
}