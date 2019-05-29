package com.greenhair.template.domain.users;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greenhair.template.domain.BaseTimeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
public class Users extends BaseTimeEntity implements Serializable {

    private static final long serialVersionUID = 2187360787223678319L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonProperty
    private String name;

    @Column(nullable = false)
    @JsonProperty
    private String password;

    @Column(unique = true, nullable = false)
    @JsonProperty
    private String email;

    // @OneToMany(fetch=FetchType.LAZY,
    //             cascade = CascadeType.ALL,
    //             mappedBy = "users",
    //             orphanRemoval = true) // FetchType.LAZY도 있음
	// @JoinColumn(name="user_id")
    // private List<Diary> diaries = new ArrayList<>();
    
    @Column(nullable = false)
    @JsonProperty
    private int point = 0;

    @Column(nullable = false)
    @JsonProperty
    private int position = 0;

    @Builder
    public Users(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.point = 0;
        this.position = 1;
    }
    
}