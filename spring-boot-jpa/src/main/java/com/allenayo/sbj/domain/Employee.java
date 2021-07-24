package com.allenayo.sbj.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

/**
 * 与User是一对一关系
 */
@Entity
@Table(name = "t_employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;

    @JsonBackReference // 序列化时如果有值会被忽略，即结果不含该字段，防止双向引用导致序列化时无限递归
    @OneToOne
    @JoinColumn(name = "user_id") // 可以省略，默认为：字段名_id
    private User user;
}