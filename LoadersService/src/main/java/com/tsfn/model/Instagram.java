package com.tsfn.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "instagram")
public class Instagram {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;

	private LocalDateTime timestamp;
	private String postType;
	private int impressions;
	private int reach;
	private int saves;
	private int likes;
	private int comments;
	private int shares;

	@Column(name = "ctr")
	private double ctr;

	@Column(name = "engagement_rate")
	private double engagementRate;

	@PrePersist
	public void calculateMetrics() {
		if (impressions > 0) {
			ctr = (double) saves / impressions;
		} else {
			ctr = 0.0;
		}

		if (reach > 0) {
			engagementRate = (double) (likes + comments + shares) / reach;
		} else {
			engagementRate = 0.0;
		}
	}
}