package app.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

@Embeddable
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Column(name = "Name", nullable = false)
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }
        
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        final Role other = (Role) obj;
        
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
