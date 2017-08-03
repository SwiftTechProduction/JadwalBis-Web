/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelMaker;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import connection.koneksi;
import java.util.ArrayList;
import java.util.List;
import model.modelJalur;

/**
 *
 * @author hero
 */
public class conJalur {
    private DBConnection con;
    private String prefixJalur;
    
    private String prefixKodeJalur;
    private String prefixKotaAsal;
    private String prefixKotaTujuan;
    private String prefixTanggal;
    private String prefixKursi;
    
    public conJalur() {
        con = new koneksi().getConnection();
        prefixJalur = "http://www.tiket.com/jalur/";
        prefixKodeJalur = prefixJalur + "kodeJalur";
        prefixKotaAsal = prefixJalur + "kotaAsal";
        prefixKotaTujuan = prefixJalur + "kotaTujuan";
        prefixTanggal = prefixJalur + "tanggal";
        prefixKursi = prefixJalur + "kursi";
    }
    
    public List<modelJalur> getAllJalur() {
        List<modelJalur> listJalur = new ArrayList<>();
        ModelMaker modelMaker = ModelFactory.createModelRDBMaker(con);
        Model model = modelMaker.createDefaultModel();
        String query = "PREFIX jlr:<" + prefixJalur + "> "
                + "SELECT "
                + "?kodeJalur ?kotaAsal ?kotaTujuan ?tanggal ?kursi "
                + "WHERE { "
                + "?x jlr:kodeJalur ?kodeJalur; "
                + "jlr:kotaAsal ?kotaAsal; "
                + "jlr:kotaTujuan ?kotaTujuan; "
                + "jlr:tanggal ?tanggal; "
                + "jlr:kursi ?kursi . "
                + "}";
        
        QueryExecution queryExecution = QueryExecutionFactory.create(query, model);
        ResultSet resultSet = queryExecution.execSelect();
        while (resultSet.hasNext()) {
            QuerySolution querySolution = resultSet.nextSolution();
            modelJalur jlr = new modelJalur();
            jlr.setKodeJalur(querySolution.getLiteral("kodeJalur").getString());
            jlr.setKotaAsal(querySolution.getLiteral("kotaAsal").getString());
            jlr.setKotaTujuan(querySolution.getLiteral("kotaTujuan").getString());
            jlr.setTanggal(querySolution.getLiteral("tanggal").getString());
            jlr.setKursi(querySolution.getLiteral("kursi").getString());
            
            listJalur.add(jlr);
        }
        return listJalur;
    }
 public boolean insertJalur(modelJalur jlr) {
        ModelMaker modelMaker = ModelFactory.createModelRDBMaker(con);
        Model model = modelMaker.createDefaultModel();
        model.begin();

        model.setNsPrefix("jlr", prefixJalur);
        Property propKodeJalur = model.createProperty(prefixKodeJalur);
        Property propKotaAsal = model.createProperty(prefixKotaAsal);
        Property propKotaTujuan = model.createProperty(prefixKotaTujuan);
        Property propTanggal = model.createProperty(prefixTanggal);
        Property propKursi = model.createProperty(prefixKursi);

        Resource resource = model.createResource(prefixJalur + jlr.getKodeJalur());
        resource.addProperty(propKodeJalur, jlr.getKodeJalur());
        resource.addProperty(propKotaAsal, jlr.getKotaAsal());
        resource.addProperty(propKotaTujuan, jlr.getKotaTujuan());
        resource.addProperty(propTanggal, jlr.getTanggal());
        resource.addProperty(propKursi, jlr.getKursi());

        model.commit();
        return true;
    }
 public boolean deleteJalur(String kodeJalur) {
        ModelMaker modelMaker = ModelFactory.createModelRDBMaker(con);
        Model model = modelMaker.createDefaultModel();
        model.begin();
        
        model.getResource(prefixJalur + kodeJalur).removeAll(null);
        
        model.commit();
        return true;
    }   
}
