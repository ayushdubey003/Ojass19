package ojass19.nitjsr.in.ojass19;

public class FAQ {

        private String question, answer;

        public FAQ(String question, String answer){
                this.question = question;
                this.answer = answer;
        }

        public FAQ(){

        }

        public String getAnswer() {
                return answer;
        }

        public String getQuestion() {
                return question;
        }

        public void setAnswer(String answer) {
                this.answer = answer;
        }

        public void setQuestion(String question) {
                this.question = question;
        }
}
