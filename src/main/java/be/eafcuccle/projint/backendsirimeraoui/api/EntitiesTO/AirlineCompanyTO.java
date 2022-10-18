package be.eafcuccle.projint.backendsirimeraoui.api.EntitiesTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AirlineCompanyTO {
   

    @Id
    private long id;

    @Column
    private String name;

    @Column
    private String motto;

    @Column
    private long version;

 

    public AirlineCompanyTO(long id, String name, String motto,long version) {
        this.id = id;
        this.name = name;
        this.motto = motto;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getVersion() {
        return version;
    }

}
