package org.teachmeskills.project.application.actions;

import org.teachmeskills.project.application.actions.updateActions.UpdateAction;
import org.teachmeskills.project.application.exceptions.StopApplicationException;
import org.teachmeskills.project.application.utils.Input;
import java.util.Map;

public interface CommonAction extends Action {
    void action() throws StopApplicationException;

    static void showNestedMenu(Map<Integer, UpdateAction> actions) {
        for (Map.Entry<Integer, UpdateAction> item : actions.entrySet()) {
            System.out.println(item.getKey() + " - " + item.getValue().getName());
        }
    }

    static UpdateAction getUpdateAction(Map<Integer, UpdateAction> actions) {
        showNestedMenu(actions);
        int number = Input.getInt("Выберите действие:");
        UpdateAction action = actions.get(number);
        if (action != null) {
            return action;
        }
        System.out.println("Нет такого действия. Повторите ввод.");
        return getUpdateAction(actions);
    }
}
