package com.minong.placefinder.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.google.gson.annotations.Expose;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class User {
  @Id
  @Column(length = 50)
  private String id;

  @Column(length = 50, nullable = false)
  private String name;

  @Column(length = 80, nullable = false)
  private String password;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name = "user_id")
  private List<History> historyList;

  @Builder
  public User(String id, String name, String password) {
    this.id = id;
    this.name = name;
    this.password = password;
  }
}