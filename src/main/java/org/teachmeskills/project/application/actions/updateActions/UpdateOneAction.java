package org.teachmeskills.project.application.actions.updateActions;

import org.teachmeskills.project.controllers.UpdateController;
import org.teachmeskills.project.entitiy.EditableEntity;
import java.util.function.Supplier;

public class UpdateOneAction<T extends EditableEntity> implements UpdateAction {
    private UpdateController<T> controller;
    private Supplier<T> factory;

    public UpdateOneAction(UpdateController<T> controller, Supplier<T> factory) {
        this.controller = controller;
        this.factory = factory;
    }

    @Override
    public String getName() {
        return "Редактировать одну запись";
    }

    @Override
    public void action() { controller.update(factory.get()); }
}
