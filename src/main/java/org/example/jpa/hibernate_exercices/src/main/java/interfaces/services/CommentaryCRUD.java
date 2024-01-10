package interfaces.services;

import java.util.List;

public interface CommentaryCRUD <Commentary>{
    public void addCom(Commentary element);
    public void deleteCom(int id);
    public List<Commentary> getAllCom();
    public Commentary getByIdCom(int id);


}
