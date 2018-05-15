package example.dao;

import example.pojo.Node;

public interface nodeMapper {
   Node findByNodeId(int id)throws Exception;

}
