package com.academy.bestprojectduoapp.model;

import java.time.LocalDate;
import javax.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "workHistory")
public class WorkHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID")
    private Long id;

    @Column(name = "EmpID",nullable = false)
    @NotNull
    @Positive
    private Long employeeID;

    @Column(name = "ProjectID",nullable = false)
    @NotNull
    @Positive
    private Long projectID;

    @Column(name = "DateFrom",nullable = false)
    private LocalDate dateFrom;

    @Column(name = "DateTo")
    private LocalDate dateTo;

    public Long getId() {
        return id;
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public Long getProjectID() {
        return projectID;
    }

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public WorkHistory(long id, long projectID, LocalDate dateFrom, LocalDate dateTo) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.projectID = projectID;
    }
}
