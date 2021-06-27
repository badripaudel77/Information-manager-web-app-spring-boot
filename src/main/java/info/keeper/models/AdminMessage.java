package info.keeper.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin_message")
public class AdminMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_message_id")
    private int id;

    @Column(name = "admin_message")
    private String message;

    @Column(name = "posted_by")
    private String postedBy;

    @Column(name = "posted_data")
    private Date date;
}

