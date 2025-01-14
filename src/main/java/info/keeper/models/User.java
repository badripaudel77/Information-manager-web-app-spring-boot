package info.keeper.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Column(unique = true)
    @NotBlank(message = "Email can't be empty")
    private String  email;

    @Size(min = 3, max = 100, message = "Password should be between 3 and 100")
    private String  password;

    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    private boolean isActive;
    private String role;
    private String imageURL;

    @Size(min = 10, max = 400, message = "Intro should be between 10 and 400")
    private String about;

    //user has one-to-many relationship with contact
    //mapped by user field in Contact class, don't create additional table
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Contact> contacts;
}
