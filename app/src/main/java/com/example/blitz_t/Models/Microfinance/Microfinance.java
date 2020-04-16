package com.example.blitz_t.Models.Microfinance;

import com.example.blitz_t.Models.Currency.Currency;
import com.example.blitz_t.Models.Member.Member;

public class Microfinance{

    private String _id;
    private String nom;
    private String image;
    private String slogan;
    private Currency currency;
    private int age_minimal_requis;
    private double pourcentage_retrait;
    private double pourcentage_retrait_virement;
    private double pourcentage_retrait_epargne_sans_avis;
    private double pourcentage_decouvert_maximal;
    private double pourcentage_benefice_fin_epargne;
    private int nombre_minimum_beneficier;
    private int nombre_maximum_beneficier;
    private double montant_part_sociale;
    private double montant_ouverture_compte;
    private double montant_maximum_solde_compte_courant;
    private double montant_minimum_transaction;
    private int nombre_jour_avis_retrait_epargne;
    private int nombre_jour_attente_reponse_credit;
    private int nombre_mois_minimum_pour_decouvert;
    private int nombre_mois_minimum_pour_epargne;
    private double frais_debloquer_compte;
    private boolean accepter_manuellement_demande_client;
    private double remaining_subscription_time;
    private Member manager;

    public Microfinance () {
    }

    public Microfinance ( String _id , String nom , String image , String slogan , Currency currency , int age_minimal_requis ,
                          double pourcentage_retrait , double pourcentage_retrait_virement , double pourcentage_retrait_epargne_sans_avis ,
                          double pourcentage_decouvert_maximal , double pourcentage_benefice_fin_epargne , int nombre_minimum_beneficier ,
                          int nombre_maximum_beneficier , double montant_part_sociale , double montant_ouverture_compte ,
                          double montant_maximum_solde_compte_courant , double montant_minimum_transaction ,
                          int nombre_jour_avis_retrait_epargne , int nombre_jour_attente_reponse_credit ,
                          int nombre_mois_minimum_pour_decouvert , int nombre_mois_minimum_pour_epargne , double frais_debloquer_compte ,
                          boolean accepter_manuellement_demande_client , double remaining_subscription_time , Member manager ) {
        this._id = _id;
        this.nom = nom;
        this.image = image;
        this.slogan = slogan;
        this.currency = currency;
        this.age_minimal_requis = age_minimal_requis;
        this.pourcentage_retrait = pourcentage_retrait;
        this.pourcentage_retrait_virement = pourcentage_retrait_virement;
        this.pourcentage_retrait_epargne_sans_avis = pourcentage_retrait_epargne_sans_avis;
        this.pourcentage_decouvert_maximal = pourcentage_decouvert_maximal;
        this.pourcentage_benefice_fin_epargne = pourcentage_benefice_fin_epargne;
        this.nombre_minimum_beneficier = nombre_minimum_beneficier;
        this.nombre_maximum_beneficier = nombre_maximum_beneficier;
        this.montant_part_sociale = montant_part_sociale;
        this.montant_ouverture_compte = montant_ouverture_compte;
        this.montant_maximum_solde_compte_courant = montant_maximum_solde_compte_courant;
        this.montant_minimum_transaction = montant_minimum_transaction;
        this.nombre_jour_avis_retrait_epargne = nombre_jour_avis_retrait_epargne;
        this.nombre_jour_attente_reponse_credit = nombre_jour_attente_reponse_credit;
        this.nombre_mois_minimum_pour_decouvert = nombre_mois_minimum_pour_decouvert;
        this.nombre_mois_minimum_pour_epargne = nombre_mois_minimum_pour_epargne;
        this.frais_debloquer_compte = frais_debloquer_compte;
        this.accepter_manuellement_demande_client = accepter_manuellement_demande_client;
        this.remaining_subscription_time = remaining_subscription_time;
        this.manager = manager;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public String getNom () {
        return nom;
    }

    public void setNom ( String nom ) {
        this.nom = nom;
    }

    public String getImage () {
        return image;
    }

    public void setImage ( String image ) {
        this.image = image;
    }

    public String getSlogan () {
        return slogan;
    }

    public void setSlogan ( String slogan ) {
        this.slogan = slogan;
    }

    public Currency getCurrency () {
        return currency;
    }

    public void setCurrency ( Currency currency ) {
        this.currency = currency;
    }

    public int getAge_minimal_requis () {
        return age_minimal_requis;
    }

    public void setAge_minimal_requis ( int age_minimal_requis ) {
        this.age_minimal_requis = age_minimal_requis;
    }

    public double getPourcentage_retrait () {
        return pourcentage_retrait;
    }

    public void setPourcentage_retrait ( double pourcentage_retrait ) {
        this.pourcentage_retrait = pourcentage_retrait;
    }

    public double getPourcentage_retrait_virement () {
        return pourcentage_retrait_virement;
    }

    public void setPourcentage_retrait_virement ( double pourcentage_retrait_virement ) {
        this.pourcentage_retrait_virement = pourcentage_retrait_virement;
    }

    public double getPourcentage_retrait_epargne_sans_avis () {
        return pourcentage_retrait_epargne_sans_avis;
    }

    public void setPourcentage_retrait_epargne_sans_avis ( double pourcentage_retrait_epargne_sans_avis ) {
        this.pourcentage_retrait_epargne_sans_avis = pourcentage_retrait_epargne_sans_avis;
    }

    public double getPourcentage_decouvert_maximal () {
        return pourcentage_decouvert_maximal;
    }

    public void setPourcentage_decouvert_maximal ( double pourcentage_decouvert_maximal ) {
        this.pourcentage_decouvert_maximal = pourcentage_decouvert_maximal;
    }

    public double getPourcentage_benefice_fin_epargne () {
        return pourcentage_benefice_fin_epargne;
    }

    public void setPourcentage_benefice_fin_epargne ( double pourcentage_benefice_fin_epargne ) {
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

    public double getMontant_part_sociale () {
        return montant_part_sociale;
    }

    public void setMontant_part_sociale ( double montant_part_sociale ) {
        this.montant_part_sociale = montant_part_sociale;
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

    public double getRemaining_subscription_time () {
        return remaining_subscription_time;
    }

    public void setRemaining_subscription_time ( double remaining_subscription_time ) {
        this.remaining_subscription_time = remaining_subscription_time;
    }

    public Member getManager () {
        return manager;
    }

    public void setManager ( Member manager ) {
        this.manager = manager;
    }
}
