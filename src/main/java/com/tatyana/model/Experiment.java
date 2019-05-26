package com.tatyana.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class Experiment {

    private MultipartFile file;
    private User user;
    private Parameters parameters;
    private String status;
    private File result;

    public Experiment(MultipartFile file, User user, Parameters parameters) {
        this.file = file;
        this.user = user;
        this.parameters = parameters;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public File getResult() {
        return result;
    }

    public void setResult(File result) {
        this.result = result;
    }
}
