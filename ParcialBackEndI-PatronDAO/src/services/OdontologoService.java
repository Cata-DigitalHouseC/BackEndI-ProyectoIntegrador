package services;

import daos.IDao;
import entidades.Odontologo;
import org.apache.log4j.Logger;

import java.util.List;

public class OdontologoService implements IDao<Odontologo>{


    //pARA Log4j
    private static Logger LOGGER = Logger.getLogger(OdontologoService.class);

    //Para el manejo DAO
    private IDao<Odontologo> odontologoIDAO;


    public OdontologoService(IDao<Odontologo> odontologoIDAO) {
        this.odontologoIDAO = odontologoIDAO;
    }

    public void setOdontologoIDAO(IDao<Odontologo> odontologoIDAO) {
        this.odontologoIDAO = odontologoIDAO;
    }

    public Odontologo guardar(Odontologo o){
        //Comentario Log4j
        LOGGER.debug("Guardando al odontologo con nroMatricula: "+o.getNroMatricula());
        //delegar la responsabilidad de guardar al DAO -pasamanos
        return odontologoIDAO.guardar(o);
    }

    public List<Odontologo> buscarTodos(){
        //delegar la responsabilidad de buscarTodos al DAO -pasamanos
        LOGGER.debug("Obteniendo la lista de todos los odontologos");
        return odontologoIDAO.buscarTodos();

    }
}
