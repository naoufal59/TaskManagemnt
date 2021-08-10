package com.task.management.entites;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "task_id")
	private Long id;
	@NotEmpty(message = "{task.name.not.empty}")
	private String name;
	@NotEmpty(message = "{task.description.not.empty}")
	@Column(length = 1200)
	@Size(max = 1200, message = "{task.description.size}")
	private String description;
	@NotNull(message = "{task.date.not.null}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate start_date;
	private boolean isCompleted;
	private int taskEstimation;
    private int taskIncurred;
    
	public long daysLeftUntilDeadline(LocalDate date) {
		return ChronoUnit.DAYS.between(LocalDate.now(), date);
	}
	public int getHourdLeftUntilInEstimation(int taskIncurred) {
		if (taskIncurred == 0) {
			return this.taskEstimation;
		}

		else if (this.taskEstimation == taskIncurred) {
			return 0;
		} else
			return this.taskEstimation - taskIncurred;
	}
	public Task() {
	}

	public Task(@NotEmpty String name, @NotEmpty @Size(max = 1200) String description, @NotNull LocalDate date,
			boolean isCompleted, int taskEstimation, int taskIncurred) {
		this.name 			= name;
		this.description 	= description;
		this.start_date 	= date;
		this.isCompleted 	= isCompleted;
		this.taskEstimation = taskEstimation;
		this.taskIncurred  	= taskIncurred;
	}

	public int getTaskIncurred() {
		return taskIncurred;
	}

	public void setTaskIncurred(int taskIncurred) {
		this.taskIncurred = taskIncurred;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public int getTaskEstimation() {
		return taskEstimation;
	}

	public void setTaskEstimation(int taskEstimation) {
		this.taskEstimation = taskEstimation;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

}
