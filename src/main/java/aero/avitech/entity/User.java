package aero.avitech.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUSERS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @Column(name = "USER_ID")
    private int id;

    @Column(name = "USER_GUID")
    private String guid;

    @Column(name = "USER_NAME")
    private String username;
}
