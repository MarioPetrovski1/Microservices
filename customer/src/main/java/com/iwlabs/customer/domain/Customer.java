package com.iwlabs.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	@SequenceGenerator(name = "customer_id_sequence", sequenceName = "customer_id_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customer_id_sequence")
	private Integer id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", updatable = false)
	private Date createdOn;
	@Column(name = "uuid", updatable = false)
	private String uuid;

	@PrePersist
	public void init() {
		uuid = UUID.randomUUID().toString().replace("-", "");
		createdOn = Date.from(java.time.ZonedDateTime.now(ZoneOffset.UTC).toInstant());
	}

}
