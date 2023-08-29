package edu.itstep.blockchain.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.context.annotation.Lazy;

@Entity
@Data
@Table(name = "issue")
@Lazy
public class Issue {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "issue_seq")
	private Long id;

	@Lob
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "support_system")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private SupportSystem ss;
}
