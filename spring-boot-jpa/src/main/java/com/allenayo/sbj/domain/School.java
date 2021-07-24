package com.allenayo.sbj.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 与User是多对多关系
 */
@Entity
@Table(name = "t_school")
@Data
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonBackReference // 查询时不会关联中间表，序列化时如果有值会被忽略，即结果不含该字段，防止双向引用导致序列化时无限递归
    @ManyToMany
    @JoinTable(name = "t_user_school", joinColumns = {@JoinColumn(name = "school_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> users;
}