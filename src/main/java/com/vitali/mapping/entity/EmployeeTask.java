package com.vitali.mapping.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name="task")
public class EmployeeTask {
    @Id
    @Column(name="id")
    public Integer id;

    @Column(name="name")
    public String description;

    // С помощью аннотации @ManyToOne мы указали, что много объектов EmployeeTask могут ссылаться на один объект типа Employee.
    // Также с помощью аннотации @JoinColumn мы указали, в какой колонке нашей таблицы хранится id объекта Employee.
    @ManyToOne
    @JoinColumn(name = "employee_id")
    public Employee employee;

    @Column(name="deadline")
    public Date deadline;
}