package example.pojo;

import java.util.List;

public class model_node {
    private String model_id;
    private int node_id;
    private String question_content;
    private String question_type;
    private String answer_type;
    private List<question_answer> question_answer;
    public model_node(){
        super();
    }

   public model_node(String model_id,int node_id,String question_content,String question_type,String answer_type){
        this.model_id=model_id;
        this.node_id=node_id;
        this.question_content=question_content;
        this.question_type=question_type;
        this.answer_type=answer_type;
   }

    public void setAnswer_type(String answer_type) {
        this.answer_type = answer_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public void setModel_id(String model_id) {
        this.model_id = model_id;
    }

    public void setNode_id(int node_id) {
        this.node_id = node_id;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public void setQuestion_answer(List<example.pojo.question_answer> question_answer) {
        this.question_answer = question_answer;
    }

    public int getNode_id() {
        return node_id;
    }

    public String getModel_id() {
        return model_id;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public String getAnswer_type() {
        return answer_type;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public List<example.pojo.question_answer> getQuestion_answer() {
        return question_answer;
    }
}
