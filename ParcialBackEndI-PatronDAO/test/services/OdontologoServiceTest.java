package services;

import daos.OdontologoDaoH2;
import entidades.Odontologo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {

    @Test
    public void guardarOdontologoH2(){

        OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologo1 = new Odontologo("c001","Maria","Hernandez");
        Odontologo odontologo2 = new Odontologo("c002","Catalina","Casas");
        Odontologo odontologo3 = new Odontologo("c003","Chad","Beltran");

        odontologoService.guardar(odontologo1);
        odontologoService.guardar(odontologo2);
        odontologoService.guardar(odontologo3);

        Assert.assertTrue(odontologoService.buscarTodos().size()==3);
    }

}