package cl.dgac.incidentes.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name= "incidentes")
public class ModeloIncidentes {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column(nullable= false, length= 500)
    private  String descripcion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "id_tipo",           // Nombre de la columna FK en la tabla 'drones'
        referencedColumnName = "id",  // Columna a la que apunta en la tabla 'usuarios'
        foreignKey = @ForeignKey(name = "id_tipo") // Nombre del constraint
    )
    private ModeloTipoIncidente tipo ;
    @Column(nullable = false, length =  60)
    private  String quien;



}
