package org.teachmeskills.project.application.actions;

import org.springframework.stereotype.Component;
import org.teachmeskills.project.application.actions.updateActions.UpdateAction;
import org.teachmeskills.project.application.exceptions.ComeBackApplicationException;
import java.util.Map;

@Component
public class UpdateTransportAction implements CommonAction {
    private Map<Integer, UpdateAction> transportUpdateActions;

    public UpdateTransportAction(Map<Integer, UpdateAction> transportUpdateActions) {
        this.transportUpdateActions = transportUpdateActions;
    }

    @Override
    public String getName() { return "Редактировать транспорт"; }

    @Override
    public void action() {
        try {
            CommonAction.getUpdateAction(transportUpdateActions).action();
        } catch (ComeBackApplicationException e) {
            return;
        }
    }
}
