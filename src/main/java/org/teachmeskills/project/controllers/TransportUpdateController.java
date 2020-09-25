package org.teachmeskills.project.controllers;

import org.springframework.stereotype.Component;
import org.teachmeskills.project.dao.TransportDao;
import org.teachmeskills.project.entitiy.Transport;
import java.util.List;

@Component
public class TransportUpdateController implements UpdateController<Transport> {
    TransportDao transportDao;

    public TransportUpdateController(TransportDao transportDBdao) {
        this.transportDao = transportDBdao;
    }

    @Override
    public void add(Transport transport) { transportDao.addTransport(transport); }

    @Override
    public List<Transport> getAll() { return transportDao.getAllTransport(); }

    @Override
    public void remove(Transport transport) { transportDao.removeTransport(transport); }

    @Override
    public void update(Transport transport) { transportDao.updateTransport(transport); }
}
