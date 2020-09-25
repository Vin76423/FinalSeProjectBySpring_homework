package org.teachmeskills.project.application.actions;

import org.springframework.stereotype.Component;
import org.teachmeskills.project.application.actions.updateActions.UpdateAction;
import org.teachmeskills.project.application.exceptions.ComeBackApplicationException;
import java.util.Map;

@Component
public class UpdateCitiesAction implements CommonAction {
    private Map<Integer, UpdateAction> cityUpdateActions;

    public UpdateCitiesAction(Map<Integer, UpdateAction> cityUpdateActions) {
        this.cityUpdateActions = cityUpdateActions;
    }

    @Override
    public String getName() {
        return "Редактировать города";
    }

    @Override
    public void action() {
        try {
            CommonAction.getUpdateAction(cityUpdateActions).action();
        } catch (ComeBackApplicationException e) {
            return;
        }
    }
}
