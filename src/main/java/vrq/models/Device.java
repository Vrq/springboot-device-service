package vrq.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private int locationnumber;
    private Timestamp inserteddatetime;
    private int col1;
    private int col22;
    private int col34;
    private int col41;
    private String col58;
    private String col66;
    private String col74;

    public Device() {
    }

    public Device(long id) {
        this.id = id;
    }

    public Device(String name) {
        this.name = name;
    }

    public int getCol1() {
        return col1;
    }

    public int getCol22() {
        return col22;
    }

    public int getCol34() {
        return col34;
    }

    public int getCol41() {
        return col41;
    }

    public String getCol58() {
        return col58;
    }

    public String getCol66() {
        return col66;
    }

    public String getCol74() {
        return col74;
    }

    public int getLocationnumber() {
        return locationnumber;
    }

    public Timestamp getInserteddatetime() {
        return inserteddatetime;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
