import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class RussianWordsParser implements Parser{
    private final String fileName;
    private final Map<String, Integer> wordsFrequency;

    public RussianWordsParser(String fileName) {
        //����� �������� �������� ������������� ����� fileName
        this.fileName = fileName;
        this.wordsFrequency = new TreeMap<>();

    }

    @Override
    public void parse() {
        if(!wordsFrequency.isEmpty()) {
            //���� �� �� ����� ��� ������� ������� ����, �� ������ �� ������
            return;
        }
        try(FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String s = null;
            while((s = bufferedReader.readLine()) != null) {
                //        String[] russianWords = s.split("[^�-��-�]+");
                String[] russianWords = s.split("[^�-��-�]+");

                //             String[] russianWords = s.split("[^A-za-z]+");
                for(String word : russianWords) {
                    if(!word.equals("")) {   //���� ����� �� �������� ������ �������
                        //����� ����� ���� ���� � ����� wordsFrequency, ���� �� ����.
                        //toLowerCase ����� ��� �������������� � ������� ��������
                        //���������� ��� ��� ����, ����� �������� ����� "������" � "������" ��������� �����������
                        String lowerCaseWord = word.toLowerCase();

                        if(wordsFrequency.get(lowerCaseWord) == null) {
                            //����� ����������� � ������ ���. �� ������ ������ ��� ������� ����� �������
                            wordsFrequency.put(lowerCaseWord, 1);
                        }
                        else {
                            //������ ������ ������� �����. ����������� �� �� �������.
                            int oldFrequency = wordsFrequency.get(lowerCaseWord);
                            wordsFrequency.replace(lowerCaseWord, oldFrequency + 1);
                        }
                    }
                }
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Map<String, Integer> frequency() {
        return wordsFrequency;
    }
//................................................................................
    //public String mostFrequentWord() � ���������� ���������� � ���� ������ � ����� ����� ����������� �����. �������� ������� � ����������� 13064 ����.

    public String mostFrequentWord(){
        String maxKey ="";
        int maxValue  = 0;

        for(Map.Entry<String,Integer> entry : wordsFrequency.entrySet() ){
            if(entry.getValue()>maxValue){
                maxKey = entry.getKey();
                maxValue = entry.getValue();
            }
        }
        String s= String.format("����� \"%s\" ���� ��� ����������� � ������ %d ��� ",maxKey,maxValue);
        return s;
    }
    //.........................................................................

    //public double averageFrequency() � ���������� ������� ������� ������� �����.

    public double averageFrequency(){
        double averagsSize = 0;
        double averagsNum=0;

        averagsSize= wordsFrequency.size();
        for(Map.Entry<String,Integer> entry: wordsFrequency.entrySet()){
            averagsNum+=entry.getValue();
        }

        double averagsSum= averagsNum/averagsSize;
        return averagsSum;
//...............................................................................
    }
}
