package com.allenayo.sbj.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 与Book是一对多的关系
 * 与Employee是一对一的关系
 * 与School是多对多的关系
 */
@Entity
@Table(name = "t_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonManagedReference // 反序列化时，注入@JsonBackReference标注的属性
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE) // user对应Book字段user，实现级联删除
    private List<Book> books;

    @JsonManagedReference
    @OneToOne(mappedBy = "user")
    private Employee employee;

    @ManyToMany(mappedBy = "users")
    private List<School> schools;
}