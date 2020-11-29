package beans.experts;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

public class Expert implements Serializable {
    private String name;
    private String org;

    public Expert(String name, String org) {
        this.name = name;
        this.org = org;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }
}
