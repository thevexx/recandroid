package com.example.admin.tutoserevices;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admin on 01/04/2018.
 */

public class reclamation extends HashMap<String, String> {

    public static int lenght;
    private String libelle_r,theme_r,date_r,lieu_r,etat_r,image_r,image_r_s,commentaire_r ,thumbnailUrl;


    public reclamation() {
    }
    public reclamation(String libelle_r,String thumbnailUrl, String date_r,String lieu_r,String etat_r,String image_r,String commentaire_r ) {
        this.libelle_r = libelle_r;
        this.date_r = date_r;
        this.lieu_r = lieu_r;
        this.etat_r = etat_r;
        this.image_r = image_r;
        this.commentaire_r = commentaire_r;
        this.thumbnailUrl =thumbnailUrl ;


    }
    public String getLibelle_r() {
        return libelle_r;
    }

    public void setLibelle_r(String libelle_r) {
        this.libelle_r = libelle_r;
    }

    public String getTheme_r() {
        return theme_r;
    }

    public void setTheme_r(String theme_r) {
        this.theme_r = theme_r;
    }

    public String getdate_r() {
        return date_r;
    }

    public void setDate_r (String  date_r) {
        this.date_r = date_r;
    }

    public String getLieu_r() {
        return lieu_r;
    }

    public void setlieu_r(String  lieu_r) {
       this.lieu_r= lieu_r;
    }
    public String getEtat_r() {
        return etat_r;
    }

    public void setEtat_r(String  etat_r) {
        this.etat_r= etat_r;
    }

    public String getImage_r() {
        return image_r;
    }

    public void setImage_r(String  image_r) {
        this.image_r= image_r;
    }
    public String getImage_r_s() {
        return image_r_s;
   }

    public void setImage_r_s(String  image_r_s) {
        this.image_r_s= image_r_s;
   }
   public String getThumbnailUrl() {
       return thumbnailUrl;
   }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
   // public String getDate_r_s() {
        //return date_r_s;
   // }

   // public void setDate_r_s(String  date_r_s) {
       // this.date_r_s= date_r_s;
   // }
   public String getCommentaire_r() {
        return commentaire_r;
    }

    public void setCommentaire_r(String  commentaire_r) {
        this.commentaire_r= commentaire_r;
   }


    // public String getId_c() {
       // return id_c;
   // }

   // public void setId_c(String  id_c ){
        //this.id_c= id_c;
   // }
}