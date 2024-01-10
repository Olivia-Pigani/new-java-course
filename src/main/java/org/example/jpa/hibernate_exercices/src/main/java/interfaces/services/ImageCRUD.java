package interfaces.services;

import java.util.List;

public interface ImageCRUD<Image> {


    public void addImage(Image element);
    public void deleteImage(int id);
    public List<Image> getAllImage();
    public Image getByIdImage(int id);



}
