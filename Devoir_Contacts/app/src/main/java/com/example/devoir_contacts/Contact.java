package com.example.devoir_contacts;

public class Contact {
    private int id;
    private String nom;
    private String numero;
    private String adresse;

    public Contact() {
    }

    public Contact(int id, String nom, String numero, String adresse) {
        this.id = id;
        this.nom = nom;
        this.numero = numero;
        this.adresse = adresse;
    }

    public Contact(int id, String nom, String numero) {
        this.id = id;
        this.nom = nom;
        this.numero = numero;
    }

    public Contact(String nom, String numero, String adresse) {
        this.nom = nom;
        this.numero = numero;
        this.adresse = adresse;
    }

    public Contact(String nom, String numero) {
        this.nom = nom;
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", numero='" + numero + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }

    public String _toString() {
        return "  Nom: " + nom + '\n' +
                " Numero: " + numero + '\n' +
                " Adresse:" + adresse ;
    }

    public String __toString() {
        return " Nom: " + nom +
                " Numero: " + numero ;
    }
}
