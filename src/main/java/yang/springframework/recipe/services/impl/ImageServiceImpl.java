package yang.springframework.recipe.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import yang.springframework.recipe.models.Recipe;
import yang.springframework.recipe.repositories.RecipeRepository;
import yang.springframework.recipe.services.ImageService;

import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long id, MultipartFile file) {
        log.debug("Received a file");

        try{
            Recipe recipe = recipeRepository.findById(id).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i=0;

            for(byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            recipe.setImage(byteObjects);

            recipeRepository.save(recipe);

        }catch (IOException e){
            log.error("Error occurred",e);

            e.printStackTrace();
        }
    }
}
