package example.dao;

import example.pojo.Node;

public interface NodeMapper {
   Node findByNodeId(int id)throws Exception;

}
