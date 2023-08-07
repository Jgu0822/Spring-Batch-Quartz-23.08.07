package com.example.quartsdemo.batch.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Coffee {

	@Id
	private Long coffee_id;

	@Column
	private String brand;

	@Column
	private String origin;

	@Column
	private String characteristics;

	@Column
	private String processed;

	public Coffee() {
		// TODO Auto-generated constructor stub
	}

	public Coffee(Long coffee_id, String brand, String origin, String characteristics, String processed) {
		this.coffee_id = coffee_id;
		this.brand = brand;
		this.origin = origin;
		this.characteristics = characteristics;
		this.processed = processed;
	}
}
