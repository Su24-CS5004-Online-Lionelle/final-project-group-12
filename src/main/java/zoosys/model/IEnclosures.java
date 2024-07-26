package zoosys.model;

public interface IEnclosures {
    void addAnimal(Animal animal);
    double getEnclosureSize();
    double getHumidity();
    double getTemperature();
    double getVegetationCoverage();
    int getZoneCleanliness();
    int getFoodInTrough();
}
