package modal;

public class Sightings {
    private int id;
    private String ranger_name;
    private String animal_name;
    private String location;

    public static final String ZONE_A = "Zone A";
    public static final String ZONE_B = "Zone B";
    public static final String ZONE_C = "Zone C";

    public Sightings(String location, String ranger_name, String animal_name) {
//        this.id = id;
        this.ranger_name = ranger_name;
        this.animal_name = animal_name;
        this.location = location;
    }

    public String getRangerName() {
        return ranger_name;
    }

    public void setRangerName(String ranger_name) {
        this.ranger_name = ranger_name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAnimal_name() {
        return animal_name;
    }

    public void setAnimal_name(String animal_name) {
        this.animal_name = animal_name;
    }


}
