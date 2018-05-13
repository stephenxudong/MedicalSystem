package example.pojo;

public class question_answer {
    private int node_id;
    private String answer;
    private model_node model_node;
    public  question_answer(){
        super();
    }
    public  question_answer(String answer){
        this.answer = answer;
    }
    public  question_answer(int node_id,String answer){
        this.node_id=node_id;
        this.answer=answer;
    }

    public void setNode_id(int node_id) {
        this.node_id = node_id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setModel_node(example.pojo.model_node model_node) {
        this.model_node = model_node;
    }

    public int getNode_id() {
        return node_id;
    }

    public String getAnswer() {
        return answer;
    }

    public example.pojo.model_node getModel_node() {
        return model_node;
    }
}
