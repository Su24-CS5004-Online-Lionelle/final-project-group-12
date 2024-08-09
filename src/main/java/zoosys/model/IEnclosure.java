package zoosys.model;

import java.util.List;

public interface IEnclosure {
    void addAnimal(Animal animal);
    double getEnclosureSize();
    double getHumidity();
    double getTemperature();
    double getVegetationCoverage();
    int getZoneCleanliness();
    int getFoodInTrough();
    List<Animal> getAnimals();
    EnclosureType getEnclosureType();
}
