package edu.itstep.blockchain.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.security.PrivateKey;
import java.security.PublicKey;

@Entity
@Data
@Table(name="wallet_address")
public class WalletAddress {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="WALLET_ID")
	 private Long id;
	 @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
	 @JoinColumn(name = "ID_USER")
	 @OnDelete(action = OnDeleteAction.CASCADE)
	 private User user;
	 @Column(name="public_key")
	 private PublicKey publicKey;
	 @Column (name="private_key")
	 private PrivateKey privateKey;
}
