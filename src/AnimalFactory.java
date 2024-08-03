import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


class AnimalFactory {
    private static final Map<String, AnimalCreator> animalMap = new HashMap<>();

    static {
        animalMap.put("dog", Dog::new);
        animalMap.put("cat", Cat::new);
        animalMap.put("hamster", Hamster::new);
        animalMap.put("horse", Horse::new);
        animalMap.put("camel", Camel::new);
        animalMap.put("donkey", Donkey::new);
    }

    public static Animal createAnimal(String type, String name, LocalDate birthDate) {
        AnimalCreator creator = animalMap.get(type.toLowerCase());
        if (creator != null) {
            return creator.create(name, birthDate);
        } else {
            return null;
        }
    }

    private interface AnimalCreator {
        Animal create(String name, LocalDate birthDate);
    }
}