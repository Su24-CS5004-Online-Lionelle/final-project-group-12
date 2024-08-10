package zoosys.model;

import java.util.List;

public interface IEnclosure {
    void addAnimal(Animal animal);
    void removeAnimal(Animal animal);
    double getSize();
    void setSize(double size);
    double getEnclosureSize();
    void setEnclosureSize(double enclosureSize);
    double getHumidity();
    void setHumidity(double humidity);
    double getTemperature();
    void setTemperature(double temperature);
    double getVegetationCoverage();
    void setVegetationCoverage(double vegetationCoverage);
    int getZoneCleanliness();
    void setZoneCleanliness(int zoneCleanliness);
    int getFoodInTrough();
    void setFoodInTrough(int foodInTrough);
    List<Animal> getAnimals();
    EnclosureType getEnclosureType();
    void setType(EnclosureType type);
    int getId();
}
