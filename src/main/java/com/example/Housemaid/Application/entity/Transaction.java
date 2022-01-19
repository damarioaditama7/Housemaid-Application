package com.example.Housemaid.Application.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "trx_service")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(targetEntity = Maid.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "maid_id")
    private Maid maid;

    private Integer monthOfWork;

    private Boolean cancellation;

    private Boolean paymentStatus;

    private Boolean isMaidAccept;

    private Integer downPayment;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date reserveDate;

    @CreatedDate
    @Column(updatable = false)
    private Date createdAt;

    @PrePersist
    private void insertBefore() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }
    }
}
