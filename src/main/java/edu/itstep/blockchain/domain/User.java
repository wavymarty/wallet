package edu.itstep.blockchain.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="user_accounts")
public class User {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO, generator="user_seq")
 @Column(name="ID_USER")
 private Long id;
 
// @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
// private Set<WalletAddress> wa;
 
 private String name;
 
 private String password;
}
