package cl.dgac.incidentes.model;


import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
        name = "id_tipo",          
        referencedColumnName = "id",  
        foreignKey = @ForeignKey(name = "id_tipo") 
    )
    private ModeloTipoIncidente tipo ;
    @Column(nullable = false, length =  60)
    private  String quien;
    @Column(
    nullable = false, 
    columnDefinition = "timestamp(0) default now()")
    private LocalDateTime fecha_reporte;
    @Column(nullable = false )
    private boolean resuelto;
    @Column(nullable = false,length= 35)
    private String region;

    @PrePersist
    protected void onCreate() {
    // Captura la fecha/hora del servidor de Spring y trunca los milisegundos para que calce con el (0)
    this.fecha_reporte = LocalDateTime.now().withNano(0);
}

}
