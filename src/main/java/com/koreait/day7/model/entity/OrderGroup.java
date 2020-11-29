package com.koreait.day7.model.entity;

import com.koreait.day7.model.enumclass.OrderType;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@SequenceGenerator(
        name="GROUP_SEQ_GEN",
        sequenceName = "GROUP_SEQ",
        initialValue = 1,
        allocationSize = 1
)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"user"})
public class OrderGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GROUP_SEQ_GEN")
    private Long id;

    private String status;
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    private String revAddress;
    private String revName;
    private String paymentType;
    private BigDecimal totalPrice;
    private Integer totalQuantity;
    private LocalDateTime orderAt;
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;
//    private Long userId;

    // OrderGroup N : 1 Users
    @ManyToOne
    private Users user;

    // OrderGroup 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;
}
