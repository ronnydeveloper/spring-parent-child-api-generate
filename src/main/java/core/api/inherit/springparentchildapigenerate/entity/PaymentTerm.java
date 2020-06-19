package core.api.inherit.springparentchildapigenerate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Payment_Term")
public class PaymentTerm { 

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "Payment_Term_Code", nullable = true, length = 20)
    @Getter
    @Setter
    private String paymentTermCode;

    @Column(name = "Name", nullable = true, length = 60)
    @Getter
    @Setter
    private String name;

    @Column(name = "Jumlah")
    @Getter
    @Setter
    private Integer jumlah;

    @Column(name = "Term_Type", nullable = true, length = 20)
    @Getter
    @Setter
    private String termType;

    @Column(name = "Calendar_Flag", nullable = true, length = 1)
    @Getter
    @Setter
    private String calendarFlag;


    @Override
    public String toString() {
        return "PaymentTerm{" + 
                  "id=" + id + 
                  ", paymentTermCode='" + paymentTermCode+ "\'" + 
                  ", name='" + name+ "\'" + 
                  ", jumlah=" + jumlah + 
                  ", termType='" + termType+ "\'" + 
                  ", calendarFlag='" + calendarFlag+ "\'" + 
                 '}';
    }
} 