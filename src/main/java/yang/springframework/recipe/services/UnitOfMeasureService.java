package yang.springframework.recipe.services;


import yang.springframework.recipe.commands.UnitOfMeasureCommand;

import java.util.Set;


public interface UnitOfMeasureService {
    public Set<UnitOfMeasureCommand> listAllUoms();

}
