package com.example.firebaseapp;

public class Personne {
    private int id;
    private String prenom;
    private String nom;
    private String sexe;
    private String age;


    public Personne() {
    }

    public Personne(String prenom, String nom, String sexe, String age) {
        this.prenom = prenom;
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
    }

    public Personne(int id, String prenom, String nom, String sexe, String age) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", sexe='" + sexe + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
    public String toString2() {
        return
                "\n prenom: " + prenom +
                "\n nom: " + nom +
                "\n sexe: " + sexe +
                "\n age: " + age ;
    }
}
