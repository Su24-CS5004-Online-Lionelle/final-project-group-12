package zoosys.model;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EnclosureManagement {
    private List<Enclosure> enclosures = new ArrayList<>();
    private String csvFilePath;
    
    public EnclosureManagement() {
        this.enclosures = new ArrayList<>();
    }

    public void setCSVFilePath(String filePath) {
        this.csvFilePath = filePath;
    }

    public void readCSV(String filePath) {
        this.csvFilePath = filePath;
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if(data.length == 8) {
                    int id = Integer.parseInt(data[0]);
                    double size = Double.parseDouble(data[1]);
                    double humidity = Double.parseDouble(data[2]);
                    double temperature = Double.parseDouble(data[3]);
                    double vegetationCoverage = Double.parseDouble(data[4]);
                    int zoneCleanliness = Integer.parseInt(data[5]);
                    int foodInTrough = Integer.parseInt(data[6]);
                    EnclosureType type = EnclosureType.valueOf(data[7]);

                    Enclosure enclosure = new Enclosure(id, size, humidity, temperature, vegetationCoverage, zoneCleanliness, foodInTrough, type);
                    enclosures.add(enclosure);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void writeCSV(String filePath) {
        this.csvFilePath = filePath;
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for(Enclosure enclosure : enclosures) {
                bw.write(String.format("%d,%.2f,%.2f,%.2f,%.2f,%d,%d,%s%n",
                enclosure.getId(),
                enclosure.getSize(),
                enclosure.getHumidity(),
                enclosure.getTemperature(),
                enclosure.getVegetationCoverage(),
                enclosure.getZoneCleanliness(),
                enclosure.getFoodInTrough(),
                enclosure.getType()));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addEnclosure(Enclosure enclosure) {
        if(enclosure != null) {
            enclosures.add(enclosure);
        }
    }

    public void removeEnclosure(int id) {
        enclosures.removeIf(enclosure -> enclosure.getId() == id);
    }

    public void updateEnclosure(Enclosure updatedEnclosure) {
        if(updatedEnclosure != null) {
            for(int i = 0; i < enclosures.size(); i++) {
                if(enclosures.get(i).getId() == updatedEnclosure.getId()) {
                    enclosures.set(i, updatedEnclosure);
                    break;
                }
            }
        }
    }

    public Enclosure getEnclosure(int id) {
        for(Enclosure enclosure : enclosures) {
            if(enclosure.getId() == id) {
                return enclosure;
            }
        }
        return null;
    }

   

    public List<Enclosure> getAllEnclosures() {
        return new ArrayList<>(enclosures);
    }

    public void addAnimal(Animal animal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAnimal'");
    }

    public void removeAnimal(Animal animal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeAnimal'");
    }

    public Animal[] getAnimals() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAnimals'");
    }

    public void setEnclosureSize(int id, double size) {
       Enclosure enclosure = getEnclosure(id);
       if(enclosure != null) {
        enclosure.setSize(size);
       }
    }

    public double getEnclosureSize(int id) {
        Enclosure enclosure = getEnclosure(id);
        return enclosure != null ? enclosure.getSize(id) : 0;
    }

    public void setHumidity(int id, double humidity) {
        Enclosure enclosure = getEnclosure(id);
        if(enclosure != null) {
            enclosure.setHumidity(humidity);
        }
    }

    public double getHumidity(int id) {
        Enclosure enclosure = getEnclosure(id);
        return enclosure != null ? enclosure.getHumidity() : 0;
    }

    public void setTemperature(int id, double temperature) {
        Enclosure enclosure = getEnclosure(id);
        if(enclosure != null) {
            enclosure.setTemperature(temperature);
        }
    }

    public double getTemperature(int id) {
        Enclosure enclosure = getEnclosure(id);
        return enclosure != null ? enclosure.getTemperature() : 0;
    }

    public void setVegetationCoverage(int id, double vegetationCoverage) {
        Enclosure enclosure = getEnclosure(id);
        if(enclosure != null) {
            enclosure.setVegetationCoverage(vegetationCoverage);
        }
    }

    public double getVegetationCoverage(int id) {
        Enclosure enclosure = getEnclosure(id);
        return enclosure != null ? enclosure.getVegetationCoverage() : 0;
    }

    public void setZoneCleanliness(int id, int zoneCleanliness) {
        Enclosure enclosure = getEnclosure(id);
        if(enclosure != null) {
            enclosure.setZoneCleanliness(zoneCleanliness);
        }
    }

    public int getZoneCleanliness(int id) {
       Enclosure enclosure = getEnclosure(id);
       return enclosure != null ? enclosure.getZoneCleanliness() : 0;
    }

    public void setFoodInTrough(int id, int FoodInTrough) {
        Enclosure enclosure = getEnclosure(id);
        if(enclosure != null) {
            enclosure.setFoodInTrough(FoodInTrough);
        }
    }

    public int getFoodInTrough(int id) {
        Enclosure enclosure = getEnclosure(id);
        return enclosure != null ? enclosure.getFoodInTrough() : 0;
    }

    public void setEnclosureType(int id, EnclosureType type) {
       Enclosure enclosure = getEnclosure(id);
       if(enclosure != null) {
        enclosure.setType(type);
       }
    }

    public EnclosureType getEnclosureType(int id) {
       Enclosure enclosure = getEnclosure(id);
       return enclosure != null ? enclosure.getType() : null;
    }
}