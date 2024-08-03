import java.util.ArrayList;
import java.util.List;


class Registry {
    private List<Animal> animals;

    public Registry() {
        this.animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void listCommands(String animalName) {
        Animal animal = findAnimalByName(animalName);
        if (animal != null) {
            System.out.println("Команды, которые выполняет " + animal.getName() + ": " + String.join(", ", animal.getCommands()));
        } else {
            System.out.println("Животное с именем " + animalName + " не найдено.");
        }
    }

    public void trainAnimal(String animalName, String command) {
        Animal animal = findAnimalByName(animalName);
        if (animal != null) {
            animal.addCommand(command);
            System.out.println(animalName + " обучен команде: " + command);
        } else {
            System.out.println("Животное с именем " + animalName + " не найдено.");
        }
    }

    private Animal findAnimalByName(String animalName) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(animalName)) {
                return animal;
            }
        }
        return null;
    }

    public void displayAnimals() {
        if (animals.isEmpty()) {
            System.out.println("В реестре нет животных.");
        } else {
            for (Animal animal : animals) {
                System.out.println(animal);
            }
        }
    }
}