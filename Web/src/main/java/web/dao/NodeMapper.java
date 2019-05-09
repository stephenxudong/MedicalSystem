package web.dao;

import web.pojo.Node;

import java.util.List;

public interface NodeMapper {
   Node findByNodeId(int id)throws Exception;
   List<Node> selectNodesByModelId(int model_id);
}
