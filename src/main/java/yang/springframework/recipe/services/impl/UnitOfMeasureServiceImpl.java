package yang.springframework.recipe.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yang.springframework.recipe.commands.UnitOfMeasureCommand;
import yang.springframework.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import yang.springframework.recipe.repositories.UnitOfMeasureRepository;
import yang.springframework.recipe.services.UnitOfMeasureService;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureServiceImpl(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {

        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(), false)
                .map(unitOfMeasureToUnitOfMeasureCommand::convert)
                .collect(Collectors.toSet());
    }
}
