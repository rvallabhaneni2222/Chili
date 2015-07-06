/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ayalamanchili
 */
@Entity
public class User {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @NotNull
    @Size(max = 64)
    private String id;

    @Column(name = "password", nullable = false)
    @NotNull
    @Size(max = 64)
    private String password;

    // getters

}