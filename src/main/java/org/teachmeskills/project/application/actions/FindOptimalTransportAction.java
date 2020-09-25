package org.teachmeskills.project.application.actions;

import org.springframework.stereotype.Component;
import org.teachmeskills.project.application.utils.Input;
import org.teachmeskills.project.controllers.CitiesUpdateController;
import org.teachmeskills.project.controllers.FindOptimalTransportController;
import org.teachmeskills.project.controllers.TransportUpdateController;
import org.teachmeskills.project.entitiy.QueryOptimalTransport;
import org.teachmeskills.project.exceptions.NotCityException;
import org.teachmeskills.project.exceptions.NotOptimalTransportException;

@Component
public class FindOptimalTransportAction implements CommonAction {
    private FindOptimalTransportController findOptimalTransportController;

    public FindOptimalTransportAction(FindOptimalTransportController findOptimalTransportController) {
        this.findOptimalTransportController = findOptimalTransportController;
    }

    @Override
    public String getName() {
        return "Подобрать наиболее быстрый и наиболее дешевый транспорт";
    }

    @Override
    public void action() {
        try {
            System.out.println( findOptimalTransportController.getOptimalTransport(getUsersQuery()));
        }catch (NotCityException e){
            System.out.println(e.getMessage());
        }catch (NotOptimalTransportException e){
            System.out.println(e.getMessage());
        }
    }



    private QueryOptimalTransport<String> getUsersQuery() {
        String fromCityLine = Input.getString("Введите города, из которого отправляемся:");
        String toCityLine = Input.getString("Введите города, в который нужно попасть:");
        int quantityPeople = Input.getInt("Введите необходимаое колличество пасадочных мест:");
        int quantityCargo = Input.getInt("Введите необхлдимый объём перевозимого груза:");
        return new QueryOptimalTransport<>(fromCityLine, toCityLine, quantityPeople, quantityCargo);
    }
}
