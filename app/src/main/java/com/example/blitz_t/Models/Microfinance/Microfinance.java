package com.example.blitz_t.Models.Microfinance;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Microfinance implements Parcelable {
    private int _id;
    private String name;
    private String logo;
    private String slogan;
    private String devise;
    private int age_minimal_requis;
    private float pourcentage_retrait;
    private float pourcentage_retrait_transfert;
    private float pourcentage_retrait_epargne_sans_avis;
    private float pourcentage_decouvert_maximal;
    private float pourcentage_benefice_fin_epargne;
    private int nombre_minimum_beneficier;
    private int nombre_maximum_beneficier;
    private double montant_part_social;
    private double montant_ouverture_compte;
    private double montant_maximum_solde_compte_courant;
    private double montant_minimum_transaction;
    private int nombre_jour_avis_retrait_epargne;
    private int nombre_jour_attente_reponse_credit;
    private int nombre_mois_minimum_pour_decouvert;
    private int nombre_mois_minimum_pour_epargne;
    private double frais_debloquer_compte;
    private boolean accepter_manuellement_demande_client;

    public Microfinance () {
    }

    public Microfinance ( int _id , String name , String logo , String slogan , String devise ) {
        this._id = _id;
        this.name = name;
        this.logo = logo;
        this.slogan = slogan;
        this.devise = devise;
    }

    public Microfinance ( int _id , String name , String logo , String slogan , String devise , int age_minimal_requis ,
                          float pourcentage_retrait , float pourcentage_retrait_transfert , float pourcentage_retrait_epargne_sans_avis ,
                          float pourcentage_decouvert_maximal , float pourcentage_benefice_fin_epargne , int nombre_minimum_beneficier ,
                          int nombre_maximum_beneficier , double montant_part_social , double montant_ouverture_compte ,
                          double montant_maximum_solde_compte_courant , double montant_minimum_transaction ,
                          int nombre_jour_avis_retrait_epargne , int nombre_jour_attente_reponse_credit ,
                          int nombre_mois_minimum_pour_decouvert , int nombre_mois_minimum_pour_epargne ,
                          double frais_debloquer_compte , boolean accepter_manuellement_demande_client ) {

        this(_id, name, logo, slogan, devise);
        this.age_minimal_requis = age_minimal_requis;
        this.pourcentage_retrait = pourcentage_retrait;
        this.pourcentage_retrait_transfert = pourcentage_retrait_transfert;
        this.pourcentage_retrait_epargne_sans_avis = pourcentage_retrait_epargne_sans_avis;
        this.pourcentage_decouvert_maximal = pourcentage_decouvert_maximal;
        this.pourcentage_benefice_fin_epargne = pourcentage_benefice_fin_epargne;
        this.nombre_minimum_beneficier = nombre_minimum_beneficier;
        this.nombre_maximum_beneficier = nombre_maximum_beneficier;
        this.montant_part_social = montant_part_social;
        this.montant_ouverture_compte = montant_ouverture_compte;
        this.montant_maximum_solde_compte_courant = montant_maximum_solde_compte_courant;
        this.montant_minimum_transaction = montant_minimum_transaction;
        this.nombre_jour_avis_retrait_epargne = nombre_jour_avis_retrait_epargne;
        this.nombre_jour_attente_reponse_credit = nombre_jour_attente_reponse_credit;
        this.nombre_mois_minimum_pour_decouvert = nombre_mois_minimum_pour_decouvert;
        this.nombre_mois_minimum_pour_epargne = nombre_mois_minimum_pour_epargne;
        this.frais_debloquer_compte = frais_debloquer_compte;
        this.accepter_manuellement_demande_client = accepter_manuellement_demande_client;
    }

    protected Microfinance ( Parcel in ) {
        _id = in.readInt();
        name = in.readString();
        logo = in.readString();
        slogan = in.readString();
        devise = in.readString();
        age_minimal_requis = in.readInt();
        pourcentage_retrait = in.readFloat();
        pourcentage_retrait_transfert = in.readFloat();
        pourcentage_retrait_epargne_sans_avis = in.readFloat();
        pourcentage_decouvert_maximal = in.readFloat();
        pourcentage_benefice_fin_epargne = in.readFloat();
        nombre_minimum_beneficier = in.readInt();
        nombre_maximum_beneficier = in.readInt();
        montant_part_social = in.readDouble();
        montant_ouverture_compte = in.readDouble();
        montant_maximum_solde_compte_courant = in.readDouble();
        montant_minimum_transaction = in.readDouble();
        nombre_jour_avis_retrait_epargne = in.readInt();
        nombre_jour_attente_reponse_credit = in.readInt();
        nombre_mois_minimum_pour_decouvert = in.readInt();
        nombre_mois_minimum_pour_epargne = in.readInt();
        frais_debloquer_compte = in.readDouble();
        accepter_manuellement_demande_client = in.readByte() != 0;
    }

    public static final Creator<Microfinance> CREATOR = new Creator<Microfinance>() {
        @Override
        public Microfinance createFromParcel ( Parcel in ) {
            return new Microfinance(in);
        }

        @Override
        public Microfinance[] newArray ( int size ) {
            return new Microfinance[size];
        }
    };

    public int get_id () {
        return _id;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getLogo () {
        return logo;
    }

    public void setLogo ( String logo ) {
        this.logo = logo;
    }

    public String getSlogan () {
        return slogan;
    }

    public void setSlogan ( String slogan ) {
        this.slogan = slogan;
    }

    public String getDevise () {
        return devise;
    }

    public void setDevise ( String devise ) {
        this.devise = devise;
    }

    public int getAge_minimal_requis () {
        return age_minimal_requis;
    }

    public void setAge_minimal_requis ( int age_minimal_requis ) {
        this.age_minimal_requis = age_minimal_requis;
    }

    public float getPourcentage_retrait () {
        return pourcentage_retrait;
    }

    public void setPourcentage_retrait ( float pourcentage_retrait ) {
        this.pourcentage_retrait = pourcentage_retrait;
    }

    public float getPourcentage_retrait_transfert () {
        return pourcentage_retrait_transfert;
    }

    public void setPourcentage_retrait_transfert ( float pourcentage_retrait_transfert ) {
        this.pourcentage_retrait_transfert = pourcentage_retrait_transfert;
    }

    public float getPourcentage_retrait_epargne_sans_avis () {
        return pourcentage_retrait_epargne_sans_avis;
    }

    public void setPourcentage_retrait_epargne_sans_avis ( float pourcentage_retrait_epargne_sans_avis ) {
        this.pourcentage_retrait_epargne_sans_avis = pourcentage_retrait_epargne_sans_avis;
    }

    public float getPourcentage_decouvert_maximal () {
        return pourcentage_decouvert_maximal;
    }

    public void setPourcentage_decouvert_maximal ( float pourcentage_decouvert_maximal ) {
        this.pourcentage_decouvert_maximal = pourcentage_decouvert_maximal;
    }

    public float getPourcentage_benefice_fin_epargne () {
        return pourcentage_benefice_fin_epargne;
    }

    public void setPourcentage_benefice_fin_epargne ( float pourcentage_benefice_fin_epargne ) {
        this.pourcentage_benefice_fin_epargne = pourcentage_benefice_fin_epargne;
    }

    public int getNombre_minimum_beneficier () {
        return nombre_minimum_beneficier;
    }

    public void setNombre_minimum_beneficier ( int nombre_minimum_beneficier ) {
        this.nombre_minimum_beneficier = nombre_minimum_beneficier;
    }

    public int getNombre_maximum_beneficier () {
        return nombre_maximum_beneficier;
    }

    public void setNombre_maximum_beneficier ( int nombre_maximum_beneficier ) {
        this.nombre_maximum_beneficier = nombre_maximum_beneficier;
    }

    public double getMontant_part_social () {
        return montant_part_social;
    }

    public void setMontant_part_social ( double montant_part_social ) {
        this.montant_part_social = montant_part_social;
    }

    public double getMontant_ouverture_compte () {
        return montant_ouverture_compte;
    }

    public void setMontant_ouverture_compte ( double montant_ouverture_compte ) {
        this.montant_ouverture_compte = montant_ouverture_compte;
    }

    public double getMontant_maximum_solde_compte_courant () {
        return montant_maximum_solde_compte_courant;
    }

    public void setMontant_maximum_solde_compte_courant ( double montant_maximum_solde_compte_courant ) {
        this.montant_maximum_solde_compte_courant = montant_maximum_solde_compte_courant;
    }

    public double getMontant_minimum_transaction () {
        return montant_minimum_transaction;
    }

    public void setMontant_minimum_transaction ( double montant_minimum_transaction ) {
        this.montant_minimum_transaction = montant_minimum_transaction;
    }

    public int getNombre_jour_avis_retrait_epargne () {
        return nombre_jour_avis_retrait_epargne;
    }

    public void setNombre_jour_avis_retrait_epargne ( int nombre_jour_avis_retrait_epargne ) {
        this.nombre_jour_avis_retrait_epargne = nombre_jour_avis_retrait_epargne;
    }

    public int getNombre_jour_attente_reponse_credit () {
        return nombre_jour_attente_reponse_credit;
    }

    public void setNombre_jour_attente_reponse_credit ( int nombre_jour_attente_reponse_credit ) {
        this.nombre_jour_attente_reponse_credit = nombre_jour_attente_reponse_credit;
    }

    public int getNombre_mois_minimum_pour_decouvert () {
        return nombre_mois_minimum_pour_decouvert;
    }

    public void setNombre_mois_minimum_pour_decouvert ( int nombre_mois_minimum_pour_decouvert ) {
        this.nombre_mois_minimum_pour_decouvert = nombre_mois_minimum_pour_decouvert;
    }

    public int getNombre_mois_minimum_pour_epargne () {
        return nombre_mois_minimum_pour_epargne;
    }

    public void setNombre_mois_minimum_pour_epargne ( int nombre_mois_minimum_pour_epargne ) {
        this.nombre_mois_minimum_pour_epargne = nombre_mois_minimum_pour_epargne;
    }

    public double getFrais_debloquer_compte () {
        return frais_debloquer_compte;
    }

    public void setFrais_debloquer_compte ( double frais_debloquer_compte ) {
        this.frais_debloquer_compte = frais_debloquer_compte;
    }

    public boolean isAccepter_manuellement_demande_client () {
        return accepter_manuellement_demande_client;
    }

    public void setAccepter_manuellement_demande_client ( boolean accepter_manuellement_demande_client ) {
        this.accepter_manuellement_demande_client = accepter_manuellement_demande_client;
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( !(o instanceof Microfinance) ) return false;
        Microfinance that = (Microfinance) o;
        return get_id() == that.get_id();
    }

    @Override
    public int hashCode () {
        return Objects.hash(get_id());
    }

    @Override
    public String toString () {
        return name;
    }

    @Override
    public int describeContents () {
        return 0;
    }

    @Override
    public void writeToParcel ( Parcel dest , int flags ) {
        dest.writeInt(_id);
        dest.writeString(name);
        dest.writeString(logo);
        dest.writeString(slogan);
        dest.writeString(devise);
        dest.writeInt(age_minimal_requis);
        dest.writeFloat(pourcentage_retrait);
        dest.writeFloat(pourcentage_retrait_transfert);
        dest.writeFloat(pourcentage_retrait_epargne_sans_avis);
        dest.writeFloat(pourcentage_decouvert_maximal);
        dest.writeFloat(pourcentage_benefice_fin_epargne);
        dest.writeInt(nombre_minimum_beneficier);
        dest.writeInt(nombre_maximum_beneficier);
        dest.writeDouble(montant_part_social);
        dest.writeDouble(montant_ouverture_compte);
        dest.writeDouble(montant_maximum_solde_compte_courant);
        dest.writeDouble(montant_minimum_transaction);
        dest.writeInt(nombre_jour_avis_retrait_epargne);
        dest.writeInt(nombre_jour_attente_reponse_credit);
        dest.writeInt(nombre_mois_minimum_pour_decouvert);
        dest.writeInt(nombre_mois_minimum_pour_epargne);
        dest.writeDouble(frais_debloquer_compte);
        dest.writeByte((byte) (accepter_manuellement_demande_client ? 1 : 0));
    }
}
