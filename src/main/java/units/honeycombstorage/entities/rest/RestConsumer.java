/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package units.honeycombstorage.entities.rest;

import units.honeycombstorage.entities.storage.Consumer;

/**
 *
 * @author massi
 */
public class RestConsumer {

    private String username;
    private String nameSurname;
    private String email;
    private String password;
    private long totUploaders;
    private long totFiles;
    private long unseenFiles;
    private long seenFiles;

    public RestConsumer() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    public long getTotUploaders() {
        return totUploaders;
    }

    public void setTotUploaders(long totUploaders) {
        this.totUploaders = totUploaders;
    }

    public long getTotFiles() {
        return totFiles;
    }

    public void setTotFiles(long totFiles) {
        this.totFiles = totFiles;
    }

    public long getUnseenFiles() {
        return unseenFiles;
    }

    public void setUnseenFiles(long unseenFiles) {
        this.unseenFiles = unseenFiles;
    }

    public long getSeenFiles() {
        return seenFiles;
    }

    public void setSeenFiles(long seenFiles) {
        this.seenFiles = seenFiles;
    }

}
