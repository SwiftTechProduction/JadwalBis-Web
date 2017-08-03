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
import model.modelTiket;


/**
 *
 * @author hero
 */
public class conTiket {
 private DBConnection con;
    private String prefixTiket;
    private String prefixKode;
    private String prefixJalur;
    private String prefixNoKursi;
    private String prefixJam;
    private String prefixHarga;

    public conTiket() {
        con = new koneksi().getConnection();
        prefixTiket = "http://www.tiket.com/tiket/";
        prefixKode = prefixTiket + "kode";
        prefixJalur = prefixTiket + "jalur";
        prefixNoKursi = prefixTiket + "noKursi";
        prefixJam = prefixTiket + "jam";
        prefixHarga = prefixTiket + "harga";
    }

    public List<modelTiket> getAllTiket() {
        List<modelTiket> listTiket = new ArrayList<>();
        ModelMaker modelMaker = ModelFactory.createModelRDBMaker(con);
        Model model = modelMaker.createDefaultModel();
        String query = "PREFIX tkt:<" + prefixTiket + "> "
                + "SELECT "
                + "?kode ?jalur ?noKursi ?jam ?harga "
                + "WHERE { "
                + "?x tkt:kode ?kode; "
                + "tkt:jalur ?jalur; "
                + "tkt:noKursi ?noKursi; "
                + "tkt:jam ?jam; "
                + "tkt:harga ?harga . "
                + "}";
        
        QueryExecution queryExecution = QueryExecutionFactory.create(query, model);
        ResultSet resultSet = queryExecution.execSelect();
        while (resultSet.hasNext()) {
            QuerySolution querySolution = resultSet.nextSolution();
            modelTiket tkt = new modelTiket();
            tkt.setKode(querySolution.getLiteral("kode").getString());
            tkt.setJalur(querySolution.getLiteral("jalur").getString());
            tkt.setNoKursi(querySolution.getLiteral("noKursi").getString());
            tkt.setJamBerangkat(querySolution.getLiteral("jam").getString());
            tkt.setHarga(querySolution.getLiteral("harga").getString());
            
            listTiket.add(tkt);
        }
        return listTiket;
    }

    public boolean insertTiket(modelTiket tkt) {
        ModelMaker modelMaker = ModelFactory.createModelRDBMaker(con);
        Model model = modelMaker.createDefaultModel();
        model.begin();

        model.setNsPrefix("tkt", prefixTiket);
        Property propKode = model.createProperty(prefixKode);
        Property propJalur = model.createProperty(prefixJalur);
        Property propNoKursi = model.createProperty(prefixNoKursi);
        Property propJam = model.createProperty(prefixJam);
        Property propHarga = model.createProperty(prefixHarga);

        Resource resource = model.createResource(prefixTiket + tkt.getKode());
        resource.addProperty(propKode, tkt.getKode());
        resource.addProperty(propJalur, tkt.getJalur());
        resource.addProperty(propNoKursi, tkt.getNoKursi());
        resource.addProperty(propJam, tkt.getJamBerangkat());
        resource.addProperty(propHarga, tkt.getHarga());

        model.commit();
        return true;
    }

    public boolean deleteTiket(String kode) {
        ModelMaker modelMaker = ModelFactory.createModelRDBMaker(con);
        Model model = modelMaker.createDefaultModel();
        model.begin();
        
        model.getResource(prefixTiket + kode).removeAll(null);
        
        model.commit();
        return true;
    }   
}
