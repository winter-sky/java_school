package mainpackage.model;


import javax.persistence.*;

@Entity
@Table(name="params")
public class Params {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "param_id")
    private int paramId;

    @Column(name = "param_name")
    private String paramName;
}
