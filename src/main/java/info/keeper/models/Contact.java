package info.keeper.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    private int id;

    @NotBlank(message = "Name can't be blank")
    @Size(min = 5, message = "Name should be at least 5 characters long")
    private String name;

    @NotBlank(message = "Email is required")
    @Size(min = 7, max = 100, message = "Email can't be less than 7 and can't exceed 100 characters")
    private String email;

    @Size(min = 10, message = "Phone Number should be at least 10 characters long")
    private String phoneNumber;

    private String imageURL;

    @Column(length = 2000)
    @Size(min = 5, message = "At least 5 characters long description is required")
    private String description;

    //contact has many to one relationship with user
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User user;
}
