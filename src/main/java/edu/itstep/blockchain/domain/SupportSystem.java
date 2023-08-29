package edu.itstep.blockchain.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "support_system")
public class SupportSystem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "support_system_seq")
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private boolean status;
}
