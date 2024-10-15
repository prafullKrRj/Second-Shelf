package com.prafull.secondshelf.model;

import com.prafull.secondshelf.dto.TransactionDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buyer_id", nullable = false)
    private UserEntity buyer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id", nullable = false)
    private UserEntity seller;

    @Column(nullable = false)
    private Double amount;

    @Column(name = "transaction_date", nullable = false)
    private Long transactionDate = System.currentTimeMillis();

    public Transaction(TransactionDto dto) {
        this.amount = dto.getAmount();
        this.transactionDate = dto.getTransactionDate();
    }

    public TransactionDto toDto() {
        return new TransactionDto(this.id, this.book.getId(), this.amount, this.transactionDate, this.seller.getUsername(), this.buyer.getUsername());
    }
}

