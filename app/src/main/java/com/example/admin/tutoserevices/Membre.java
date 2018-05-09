package com.example.admin.tutoserevices;

/**
 * Created by admin on 15/03/2018.
 */

public class Membre {

        private String login;
        private String pass;
        private String nom;
        private String prenom;
        private String email;
        private String tel;

        public Membre() {
            this.login = "";
            this.pass = "";
            this.nom = "";
            this.prenom = "";
            this.email = "";
            this.tel = "";
        }

        public Membre(String login, String pass, String nom, String prenom, String email, String tel) {
            this.login = login;
            this.pass = pass;
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.tel = tel;
        }

        @Override
        public String toString() {
            return "Membre{" +
                    "login='" + login + '\'' +
                    ", pass='" + pass + '\'' +
                    ", nom='" + nom + '\'' +
                    ", prenom='" + prenom + '\'' +
                    ", email='" + email + '\'' +
                    ", tel='" + tel + '\'' +
                    '}';
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }


