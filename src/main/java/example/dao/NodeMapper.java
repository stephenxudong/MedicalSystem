package example.dao;

import example.pojo.Node;

import java.util.List;

public interface NodeMapper {
   Node findByNodeId(int id)throws Exception;
   List<Node> selectNodesByModelId(int model_id);
}
