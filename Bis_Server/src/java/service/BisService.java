/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import controller.conJalur;
import controller.conTiket;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.modelJalur;
import model.modelTiket;

/**
 *
 * @author hero
 */
@WebService(serviceName = "BisService")
public class BisService {

    /**
     * Web service operation
     */
   @WebMethod(operationName = "getAllTiket")
    public List<model.modelTiket> getAllTiket() {
        return new conTiket().getAllTiket();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertTiket")
    public boolean operation(@WebParam(name = "tkt") modelTiket tkt) {
       return new conTiket().insertTiket(tkt);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteTiket")
    public boolean deleteTiket(@WebParam(name = "kode") String kode) {
        //TODO write your implementation code here:
        return new conTiket().deleteTiket(kode);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllJalur")
    public List<model.modelJalur> getAllJalur() {
        //TODO write your implementation code here:
        return new conJalur().getAllJalur();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertJalur")
    public boolean insertJalur(@WebParam(name = "jlr") modelJalur jlr) {
        //TODO write your implementation code here:
        return new conJalur().insertJalur(jlr);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteJalur")
    public boolean deleteJalur(@WebParam(name = "kodeJalur") String kodeJalur) {
        //TODO write your implementation code here:
        return new conJalur().deleteJalur(kodeJalur);
    }

    /**
     * Web service operation
     */
   
}
